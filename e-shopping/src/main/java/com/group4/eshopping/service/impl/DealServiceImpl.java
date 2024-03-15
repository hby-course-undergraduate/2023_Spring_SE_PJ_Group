package com.group4.eshopping.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.dao.GoodsSaleDAO;
import com.group4.eshopping.dao.OrderGroupDiscountsDAO;
import com.group4.eshopping.entity.*;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.DealService;
import com.group4.eshopping.utils.CheckCondition;
import com.group4.eshopping.utils.MyUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@Service
public class DealServiceImpl implements DealService {
    @Autowired
    GoodsServiceImpl goodsService;
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    DeliveryAddressServiceImpl deliveryAddressService;
    @Autowired
    GoodsSaleDAO goodsSaleDAO;
    @Autowired
    ActivityServiceImpl activityService;
    @Autowired
    OrderGroupDiscountsDAO orderGroupDiscountsDAO;

    @Override
    public Result purchase(JSONArray goodsList, int addressId, JSONObject userInfo) {
//                判断商品存在，购买数量为正数，商品数量足够
        DeliveryAddress deliveryAddress = deliveryAddressService.getDeliveryAddressById(addressId);
        int userId = CheckCondition.getUserId(userInfo);
        if (userId != deliveryAddress.getUserId())
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无该收货地址");


        HashMap<Integer, Double> activityMap = new HashMap<>();
        for (Object goodsObj : goodsList) {
            JSONObject jsonObject = JSONObject.parseObject(goodsObj.toString());
            int goodsId = jsonObject.getInteger("goodsId");
            int number = jsonObject.getInteger("number");
            Goods goods = goodsService.getValidByGoodsId(goodsId);
            if (goods == null)
                throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无该商品");
            if (number <= 0)
                throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "购买数量需为正数");
            if (number > goods.getRemainNumber())
                throw new CheckException(HttpServletResponse.SC_CONFLICT, "商品数量不足");
            int activityId = goods.getActivityId();
            if (activityId != 0) {
                if (activityMap.containsKey(activityId)) {
                    activityMap.replace(activityId, activityMap.get(activityId) + number * goods.getPrice());
                } else {
                    activityMap.put(activityId, number * goods.getPrice());
                }
            }
        }
//        判断之后再写入数据库
//        数据库单元
        int group = orderService.getGroup();
        for (Object goodsObj : goodsList) {
            JSONObject jsonObject = JSONObject.parseObject(goodsObj.toString());
            int goodsId = jsonObject.getInteger("goodsId");
            int number = jsonObject.getInteger("number");
            Goods goods = goodsService.getValidByGoodsId(goodsId);
            double minusAmount = 0;
            int activityId = goods.getActivityId();
            if (activityMap.containsKey(activityId)) {
                Activity activity = activityService.getValidById(activityId);
                double thisAmountInAc = activityMap.get(activityId);
                int temp = (int) (thisAmountInAc / activity.getAmountArrive());
                if (temp > 0) {
                    minusAmount = temp * activity.getAmountMinus() * goods.getPrice() * number / thisAmountInAc;
                }
            }
            orderService.addNewOrder(userId, goodsId, number, goods.getPrice(), group, goods.getShopId(), addressId, minusAmount);
        }
//        记录参加了哪些活动
        List<JSONObject> discount = new ArrayList<>();
        for (Integer i : activityMap.keySet()) {
            Activity activity = activityService.getValidById(i);
            double thisAmountInAc = activityMap.get(i);
            int times = (int) (thisAmountInAc / activity.getAmountArrive());
            if (times > 0) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("amountMinus", activity.getAmountMinus());
                jsonObject.put("amountArrive", activity.getAmountArrive());
                jsonObject.put("times", times);
                discount.add(jsonObject);
            }
        }
        String str;
        if (discount.isEmpty())
            str = "null";
        else str = MyUtils.getJsonStringWithDate(discount);
        OrderGroupDiscounts orderGroupDiscounts = new OrderGroupDiscounts(group, str);
        orderGroupDiscountsDAO.save(orderGroupDiscounts);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("group", group);
        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(jsonObject));
        return result;
    }

    @Override
    public Result pay(int group, JSONObject userInfo) {
//        !!!!!!!要检测商店是否关闭
//               用户有钱   用户账户减，商店账户加
        int userId = CheckCondition.getUserId(userInfo);

        List<Order> orders = orderService.getUnpaidByGroup(group);
        if (orders.isEmpty())
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无对应订单");
        double totalAmount = 0;
        for (Order order : orders) {
            int goodsId = order.getGoodsId();
            Goods goods = goodsService.getValidByGoodsId(goodsId);
            if (goods == null || goods.getState() != 1)
                throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "商品状态异常");
            Shop shop = shopService.getByShopId(goods.getShopId());
            if (shop == null || shop.getState() != 1)
                throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "商店状态异常");

            totalAmount += order.getNumber() * order.getUnitPrice() - order.getMinusAmount();
        }

        Account userAccount = accountService.getUserByUserId(userId);
        if (totalAmount > userAccount.getAmount())
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "账户余额不足");
        accountService.transToMiddle(userAccount, totalAmount, "transactionUser");

        for (Order order : orders) {
            order.setPaid();
            orderService.addOrUpdate(order);
            double minusAmount = order.getMinusAmount();
            if (minusAmount == 0)
                continue;
            int activityId = goodsService.getByGoodsId(order.getGoodsId()).getActivityId();
            if (activityId == 0)
                continue;
//        维护剩余资金
//        这里我们假设活动剩余资金为折扣的钱
//        不考虑佣金可以抵消的那部分
//        发生在付款阶段 因为物流+不能退货
            Activity activity = activityService.getValidById(activityId);
            activity.setRemainFund(activity.getRemainFund() - minusAmount);
            activityService.addOrUpdate(activity);
        }
        return new Result(HttpServletResponse.SC_OK);
    }

    @Override
    public Result deliverOrder(int orderId, JSONObject userInfo) {
//        如果是自己店里的商品就可以发货
//        商品数量足够，更新商品数量
//        List<Order> orders = orderService.getUndeliveredByGroup(group);
        int sellerId = CheckCondition.getSellerId(userInfo);
        Shop shop = shopService.getByUserId(sellerId);
        if (shop == null || shop.getState() != 1)
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "商店状态异常");
        int shopId = shop.getShopId();
        Order order = orderService.getByOrderId(orderId);
        if (order == null || order.getShopId() != shopId)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无对应订单");
        if (!Objects.equals(order.getState(), "undelivered"))
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "订单状态不满足");
        int goodsId = order.getGoodsId();
        Goods goods = goodsService.getByGoodsId(goodsId);
        if (goods == null || goods.getState() != 1)
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "商品状态异常");

        int buyNumber = order.getNumber();
        int num = goods.getRemainNumber() - buyNumber;
        if (num < 0)
            throw new CheckException(HttpServletResponse.SC_CONFLICT, "商品数量不足");
        goodsService.updateRemainNumber(goods, num);

        order.setDelivered();
        orderService.addOrUpdate(order);
        return new Result(HttpServletResponse.SC_OK);
    }

    @Override
    public Result receiptOrder(int orderId, JSONObject userInfo) throws CheckException {
//        订单归属确定
//        不考虑中间账户没钱
        int userId = CheckCondition.getUserId(userInfo);
        Order order = orderService.getByOrderId(orderId);
        if (order == null || order.getUserId() != userId)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无对应订单");
        if (!Objects.equals(order.getState(), "unreceived"))
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "订单状态不满足");
        int goodsId = order.getGoodsId();
        Goods goods = goodsService.getValidByGoodsId(goodsId);
        if (goods == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无对应商品");
        int activityId = goods.getActivityId();
        if (activityId != 0) {
            Activity activity = activityService.getValidById(activityId);
            if (activity == null)
                throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无对应活动");
        }
        int shopId = goods.getShopId();
        int number = order.getNumber();
        double unitPrice = order.getUnitPrice();
        double minusAmount = order.getMinusAmount();
        double amount1 = (unitPrice * number - minusAmount) * 0.05;
        double amount2 = unitPrice * number - amount1;


        accountService.middleToProfit(amount1, "brokerage");
        accountService.middleToShop(shopId, amount2, "transactionShop");

        order.setReceived();
        orderService.addOrUpdate(order);

        GoodsSale goodsSale = new GoodsSale(shopId, goodsId, number, unitPrice, userId);
        goodsSaleDAO.save(goodsSale);

//        List<Order> orders = orderService.getUnreceivedByGroup(orderId);
//        for (Order order : orders) {
//            if (order.getUserId() != userId)
//                throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无该订单或该订单已完成");
//        }
//
//        for (Order order : orders) {
//            int shopId = goodsService.getByGoodsId(order.getGoodsId()).getShopId();
//            double amount = order.getUnitPrice() * order.getNumber();
//            accountService.middleToShop(shopId, amount);
//
//            order.setReceived();
//            orderService.addOrUpdate(order);
//        }
        return new Result(HttpServletResponse.SC_OK);
    }

    @Override
    public Result cancelOrder(int group, JSONObject userInfo) {
        int userId = CheckCondition.getUserId(userInfo);

        List<Order> orders = orderService.getUnpaidByGroup(group);
        if (orders.isEmpty())
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无对应订单");
        for (Order order : orders) {
            if (order == null || order.getUserId() != userId)
                throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无对应订单");
            if (!Objects.equals(order.getState(), "unpaid"))
                throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "订单状态不满足");
        }
        for (Order order : orders) {
            order.setCancelled();
            orderService.addOrUpdate(order);
        }
        return new Result(HttpServletResponse.SC_OK);
    }

    @Override
    public Result refundOrder(int orderId, JSONObject userInfo) {
//        可以重复操作？
        int userId = CheckCondition.getUserId(userInfo);
        Order order = orderService.getByOrderId(orderId);
        if (order == null || order.getUserId() != userId)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无对应订单");

        if (!(Objects.equals(order.getState(), "unreceived")
                || Objects.equals(order.getState(), "undelivered")))
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "订单状态不满足");

        OrderGroupDiscounts orderGroupDiscounts = orderGroupDiscountsDAO.findByGroup(order.getGroup());
        if (orderGroupDiscounts != null) {
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "满减商品无法退款");
        }

        order.setRefunding(true);
        orderService.addOrUpdate(order);
//        List<Order> orders = orderService.getUndeliveredByGroup(orderId);
//        orders.addAll(orderService.getUnreceivedByGroup(orderId));
//        for (Order order : orders) {
//            if (order.getUserId() != userId)
//                throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无对应订单");
//        }
//        for (Order order : orders) {
//            order.setRefunding(true);
//            orderService.addOrUpdate(order);
//        }
        return new Result(HttpServletResponse.SC_OK);
    }

    @Override
    public Result approveRefunded(int orderId, boolean isApproved, JSONObject userInfo) {
//        依然不考虑middle没钱
        int sellerId = CheckCondition.getSellerId(userInfo);
//        List<Order> orders = orderService.getUndeliveredByGroup(orderId);
//        orders.addAll(orderService.getUnreceivedByGroup(orderId));
        Order order = orderService.getByOrderId(orderId);
        if (order == null || order.getShopId() != shopService.getByUserId(sellerId).getShopId() || !order.isRefunding())
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无对应订单");
        if (!(Objects.equals(order.getState(), "unreceived") || Objects.equals(order.getState(), "undelivered")))
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "订单状态不满足");

        if (!isApproved) {
            order.setRefunding(false);
            orderService.addOrUpdate(order);
        } else {
//            退款
//        都要设置false refunding 避免再次查到
            accountService.middleToUser(order.getUserId(), order.getUnitPrice() * order.getNumber(), "refund");
            order.setRefunding(false);
            order.setRefunded();
            orderService.addOrUpdate(order);
        }
        return new Result(HttpServletResponse.SC_OK);
    }

    @Override
    public Result deleteOrder(int orderId, JSONObject userInfo) {
//        检验状态
        int userId = CheckCondition.getUserId(userInfo);
        Order order = orderService.getByOrderId(orderId);
        if (order == null || order.getUserId() != userId)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无对应订单");
        if (!(Objects.equals(order.getState(), "received") || Objects.equals(order.getState(), "cancelled")
                || Objects.equals(order.getState(), "refunded")))
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "订单目前不可删除");
        order.setStatus(false);
        orderService.addOrUpdate(order);
        return new Result(HttpServletResponse.SC_OK);
    }


    @Override
    public Result getOneOrder(int orderId, JSONObject userInfo) {
        int userId = CheckCondition.getUserId(userInfo);
        Order order = orderService.getByOrderId(orderId);
        if (order == null || !(order.getUserId() == userId || shopService.getByShopId(order.getShopId()).getUserId() == userId))
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无对应订单");
        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(getOrderObject(order)));
        return result;
//        感觉可以写成构造函数
//        很怪 懒了
    }

    @Override
    public Result getAllOrder(String state, JSONObject userInfo) {
        int userId = CheckCondition.getUserId(userInfo);
        List<Order> orders = switch (state) {
            case "all" -> orderService.getAllByUserId(userId);
            case "unpaid" -> orderService.getUnpaidByUserId(userId);
            case "undelivered" -> orderService.getUndeliveredByUserId(userId);
            case "unreceived" -> orderService.getUnreceivedByUserId(userId);
            case "refunded" -> orderService.getRefundedByUserId(userId);
            case "received" -> orderService.getReceivedByUserId(userId);
            case "cancelled" -> orderService.getCancelledByUserId(userId);
            default -> throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "请求消息有误");
        };
        JSONArray jsonArray = new JSONArray();
        for (Order order : orders) {
            jsonArray.add(getOrderObject(order));
        }
        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(jsonArray));
        return result;

//        JSONArray jsonArray = new JSONArray();
//        JSONObject jsonObject = new JSONObject();
//        List<Order> orders1 = new ArrayList<>();
//        if (orders.isEmpty())
//            return new Result(HttpServletResponse.SC_OK, "{}");
//
//        int lastOne = orders.get(0).getGroup();
//        for (Order order : orders) {
//            if (order.getGroup() != lastOne) {
//                jsonObject.put("order", orders1);
//                jsonObject.put("groupId", lastOne);
//                jsonArray.add(jsonObject);
//                jsonObject = new JSONObject();
//                orders1 = new ArrayList<>();
//                lastOne = order.getGroup();
//            }
//            orders1.add(order);
//        }
//        return new Result(HttpServletResponse.SC_OK, MyUtils.getJsonStringWithDate(jsonArray));
    }

    @Override
    public Result getUnpaidOrder(JSONObject userInfo) {
        int userId = CheckCondition.getUserId(userInfo);
        List<Order> orders = orderService.getUnpaidByUserId(userId);
        if (orders.isEmpty())
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无对应订单");

//        根据group升序排列
        JSONArray orderArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> orders1 = new ArrayList<>();

        int lastOne = orders.get(0).getGroup();
        for (Order order : orders) {
            if (order.getGroup() != lastOne) {
                jsonObject.put("order", orders1);
                jsonObject.put("groupId", lastOne);

                OrderGroupDiscounts orderGroupDiscounts = orderGroupDiscountsDAO.findByGroup(lastOne);
                String str;
                if (orderGroupDiscounts == null)
                    str = "";
                else str = orderGroupDiscounts.getDiscounts();
                jsonObject.put("discounts", JSONArray.parse(str));

                orderArray.add(jsonObject);
                jsonObject = new JSONObject();
                orders1 = new ArrayList<>();
                lastOne = order.getGroup();
            }
            orders1.add(getOrderObject(order));
        }
        jsonObject.put("order", orders1);
        jsonObject.put("groupId", lastOne);


        OrderGroupDiscounts orderGroupDiscounts = orderGroupDiscountsDAO.findByGroup(lastOne);
        String str;
        if (orderGroupDiscounts == null)
            str = "null";
        else str = orderGroupDiscounts.getDiscounts();
        jsonObject.put("discounts", JSONArray.parse(str));

        orderArray.add(jsonObject);

        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(orderArray));
        return result;
    }

    @Override
    public Result getAllOrderBySeller(String state, JSONObject userInfo) {
        int sellerId = CheckCondition.getSellerId(userInfo);
        Shop shop = shopService.getValidByUserId(sellerId);
        if (shop == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "您未开店");
        int shopId = shop.getShopId();
        List<Order> orders = switch (state) {
            case "all" -> orderService.getAllByShopId(shopId);
            case "unpaid" -> orderService.getUnpaidByShopId(shopId);
            case "undelivered" -> orderService.getUndeliveredByShopId(shopId);
            case "unreceived" -> orderService.getUnreceivedByShopId(shopId);
            case "refunded" -> orderService.getRefundedByShopId(shopId);
            case "received" -> orderService.getReceivedByShopId(shopId);
            case "cancelled" -> orderService.getCancelledByShopId(shopId);
            default -> throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "请求消息有误");
        };
        JSONArray jsonArray = new JSONArray();
        for (Order order : orders) {
            jsonArray.add(getOrderObject(order));
        }
        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(jsonArray));
        return result;
    }

    @Override
    public Result getRefundingOrder(JSONObject userInfo) {
        int sellerId = CheckCondition.getSellerId(userInfo);
        Shop shop = shopService.getValidByUserId(sellerId);
        if (shop == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "您未开店");
        int shopId = shop.getShopId();
        List<Order> orders = orderService.getRefundingOrder(shopId);

        JSONArray jsonArray = new JSONArray();
        for (Order order : orders) {
            jsonArray.add(getOrderObject(order));
        }
        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(jsonArray));
        return result;
    }

    public JSONObject getOrderObject(Order order) {
        DeliveryAddress deliveryAddress = deliveryAddressService.getDeliveryAddressById(order.getAddressId());
        if (deliveryAddress == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无收货地址信息");
        String[] strings = {"level"};
        JSONObject addObj = new JSONObject();
        double totalPrice = order.getUnitPrice() * order.getNumber() - order.getMinusAmount();
        addObj.put("totalPrice", MyUtils.getMoneyDouble(totalPrice));
        addObj.put("address", deliveryAddress.getAddress());
        addObj.put("consignee", deliveryAddress.getConsignee());
        addObj.put("consigneePhone", deliveryAddress.getConsigneePhone());
        Goods goods = goodsService.getByGoodsId(order.getGoodsId());
        if (goods == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无有效商品");
        String goodsName = goods.getGoodsName();
        addObj.put("goodsName", goodsName);
        try {
            addObj.put("shopName", shopService.getByShopId(order.getShopId()).getShopName());
        } catch (Exception e) {
            addObj.put("shopName", "Unknown Shop!!!!");
        }
        switch (order.getState()) {
            case "unpaid" -> order.setState("待支付");
            case "undelivered" -> order.setState("待发货");
            case "unreceived" -> order.setState("待收货");
            case "received" -> order.setState("已完成");
            case "cancelled" -> order.setState("已撤销");
            case "refunded" -> order.setState("已退款");
            default -> {
            }
        }
        return MyUtils.changeObj(order, addObj, strings);
    }
}
