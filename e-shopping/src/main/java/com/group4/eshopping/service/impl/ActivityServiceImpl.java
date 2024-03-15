package com.group4.eshopping.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.dao.ActivityDAO;
import com.group4.eshopping.dao.GoodsSaleDAO;
import com.group4.eshopping.dao.ShopActivityDAO;
import com.group4.eshopping.entity.*;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.ActivityService;
import com.group4.eshopping.utils.CheckCondition;
import com.group4.eshopping.utils.MyUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityDAO activityDAO;
    @Autowired
    ShopActivityDAO shopActivityDAO;
    @Autowired
    TagServiceImpl tagService;
    @Autowired
    GoodsServiceImpl goodsService;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    GoodsSaleDAO goodsSaleDAO;

    public void addOrUpdate(Activity activity) {
        activityDAO.save(activity);
    }

    public Activity getValidById(int id) {
//        返回特定activity一切均为valid
        return activityDAO.findById(id);
    }

    @Override
    public Result getActivity() {
        checkActivityState();
        List<Activity> activities = activityDAO.findAll();
        List<JSONObject> jsonObjectList = new ArrayList<>();
        for (Activity activity : activities
        ) {
            jsonObjectList.add(getActivityObject(activity));
        }
        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(jsonObjectList));
        return result;
    }

    @Override
    public Result getActiveActivity() {
        checkActivityState();
        List<Activity> activities = activityDAO.findValidActivity();
        List<JSONObject> jsonObjectList = new ArrayList<>();
        for (Activity activity : activities
        ) {
            jsonObjectList.add(getActivityObject(activity));
        }
        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(jsonObjectList));
        return result;
    }


    @Override
    public Result organizeActivity(Activity activity, JSONObject userInfo) {
        activity.setName(CheckCondition.transferMeaning(activity.getName()));
        checkOrganize(activity);

        String tag = CheckCondition.transferMeaning(activity.getTags());
        List<Integer> tagIds = tagService.transferTagsToTagId(tag);
        activity.setTags(MyUtils.joinByComma(tagIds));
        activity.setRemainFund(activity.getFund());

        LocalDateTime endTime = activity.getEndTime();
        if (endTime.isBefore(activity.getStartTime()))
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "活动时间异常");
        if (endTime.isBefore(LocalDateTime.now()))
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "活动已结束");
        activityDAO.save(activity);
        checkActivityState();
        return new Result(HttpServletResponse.SC_OK);
    }

    public void checkOrganize(Activity activity) {
        double fund = activity.getFund();
        double amountMinus = activity.getAmountMinus();
        double amountArrive = activity.getAmountArrive();
        if (fund <= 0 || amountMinus <= 0 || amountArrive <= 0 || activity.getRegisterFund() < 0)
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "资金需大于零");
        if (fund < amountMinus)
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "开办活动资金过少");
        if (accountService.getProfit().getAmount() < fund)
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "利润账户余额不足以开办此活动");
        if (amountArrive <= amountMinus)
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "返利过高");

        if (activity.getSellNumber() < 0 || activity.getSellAmount() < 0)
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "开办活动门槛错误");
    }


    @Override
    public Result applyActivity(int activityId, JSONObject userInfo) {
        checkActivityState();
        int sellerId = CheckCondition.getSellerId(userInfo);
        Shop shop = shopService.getValidByUserId(sellerId);
        Activity activity = getValidById(activityId);
        if (shop == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无商店信息");
        if (activity == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无活动信息");

        if (!checkActivity(activity, shop))
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "申请资格不满足");
        if (activity.getEndTime().isBefore(LocalDateTime.now()))
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "活动已结束");

        addNewShopActivity(activityId, shop.getShopId());
        return new Result(HttpServletResponse.SC_OK);
    }

    public boolean checkActivity(Activity activity, Shop shop) {
//        要求已经验证activity和shop有效
        List<Integer> activityTags = MyUtils.getIntListFromTag(activity.getTags());
        List<Integer> shopTags = MyUtils.getIntListFromTag(shop.getTag());
        activityTags.retainAll(shopTags);
        if (activityTags.isEmpty())
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "无任何可以参加活动的商品");
        if (shop.getFund() < activity.getRegisterFund())
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "您的商店注册资金过低");
        if (accountService.getSaleroomByShop(shop, 30) < activity.getSellAmount())
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "您的商店销售额过低");

        int saleNum = 0;
        for (GoodsSale goodsSale : goodsSaleDAO.findByShopId(shop.getShopId())) {
            saleNum += goodsSale.getNumber();
        }
        if (saleNum < activity.getSellNumber())
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "您的商店销量过低");

        return true;
    }

    public ShopActivity getShopActivity(int activityId, int shopId) {
        return shopActivityDAO.findByShopIdAndActivityId(activityId, shopId);
    }

    @Override
    public void addNewShopActivity(int activityId, int shopId) {
        List<ShopActivity> shopActivityList = shopActivityDAO.findByShopIdAndState(shopId, 1);
        if (!shopActivityList.isEmpty())
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "您有正在进行的活动");
        shopActivityList = shopActivityDAO.findByShopIdAndState(shopId, 0);
        for (ShopActivity thisShopActivity : shopActivityList) {
            if (thisShopActivity.getIsApproved() != 2)
                throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "您有正在申请的活动");
        }

        try {
//            前端传的不是unique id需要额外判断保证唯一
            ShopActivity shopActivity = getShopActivity(activityId, shopId);
            if (shopActivity != null) {
                shopActivity.setIsApproved(0);
                shopActivityDAO.save(shopActivity);
                return;
            }
        } catch (Exception e) {
            throw new CheckException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "查询ShopActivity有误");
        }

        ShopActivity shopActivity = new ShopActivity(activityId, shopId);
        shopActivityDAO.save(shopActivity);
    }

    @Override
    public Result checkApplyingShopByActivity(JSONObject userInfo) {
        checkActivityState();
        CheckCondition.checkAdmin(userInfo);
        List<ShopActivity> shopActivities = shopActivityDAO.findAll();
        List<JSONObject> shopAc = new ArrayList<>();
        for (ShopActivity shopActivity : shopActivities) {
            Activity activity = getValidById(shopActivity.getActivityId());

            String[] strings = {"id"};
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("activityId", shopActivity.getActivityId());
            jsonObject.put("shopId", shopActivity.getShopId());
            jsonObject.put("state", shopActivity.getState());
            jsonObject.put("isApproved", shopActivity.getIsApproved());

            shopAc.add(MyUtils.changeObj(getActivityObject(activity), jsonObject, strings));
        }
        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(shopAc));
        return result;
    }

    @Override
    public Result approveActivity(int activityId, int shopId, boolean isApproved, JSONObject userInfo) {
        checkActivityState();
        Shop shop = shopService.getValidByShopId(shopId);
        Activity activity = getValidById(activityId);
        if (shop == null || activity == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无对应信息");
        ShopActivity shopActivity = shopActivityDAO.findByShopIdAndActivityIdAndIsApproved(shopId, activityId, 0);

        shopActivity.setIsApproved(isApproved ? 1 : 2);
        if (isApproved && activity.getEndTime().isBefore(LocalDateTime.now()))
            shopActivity.setState(2);
        else if (isApproved && activity.getStartTime().isBefore(LocalDateTime.now())) {
            shopActivity.setState(1);
            startActivityEachShop(activity, shop);
        } else if (!isApproved)
            shopActivity.setState(2);

        shopActivityDAO.save(shopActivity);
        return new Result(HttpServletResponse.SC_OK);
    }


    public void startActivityEachGoods(Goods goods) {
        Shop shop = shopService.getValidByShopId(goods.getShopId());
        if (shop == null)
            return;
        int activityId = shop.getActivityId();
        if (activityId != 0) {
            Activity activity = getValidById(activityId);
            if (activity == null)
                return;
//            if (activity.getStartTime().isBefore(LocalDateTime.now()) && activity.getEndTime().isAfter(LocalDateTime.now()))
            checkActivityState();
            if (isTagIntersection(goods.getTag(), activity.getTags())) {
                goods.setActivityId(activityId);
            } else {
                goods.setActivityId(0);
            }
            goodsService.addOrUpdate(goods);
        }
    }

    @Override
    public void checkActivityState() {
        List<Activity> activities = activityDAO.findAll();
        for (Activity activity : activities) {
            if (activity.getState() == 2)
                continue;
            LocalDateTime startTime = activity.getStartTime();
            LocalDateTime endTime = activity.getEndTime();
            LocalDateTime now = LocalDateTime.now();
            if (now.isAfter(startTime))
                if (activity.getState() == 0) {
                    startActivity(activity);
                }
            if (now.isAfter(endTime) || activity.getRemainFund() < activity.getAmountMinus())
                if (activity.getState() != 2) {
                    endActivity(activity);
                }
        }
    }

    public void startActivity(Activity activity) {
//        List<Integer> tags = MyUtils.getIntListFromTag(activity.getTags());
//        for (int tagId : tags) {
//            List<Shop> shops = shopService.getShopsByTag(tagId);
////            List<Goods> goodsList = goodsService.getValidGoodsByTagId(tagId);
//            for (Shop shop : shops) {
////                activity
//            }
//        }
        double fund = activity.getFund();
        if (fund > accountService.getProfit().getAmount() || fund <= 0) {
//            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "活动开启失败，资金不足");
            activity.setState(2);
            activityDAO.save(activity);
            return;
        }
        accountService.profitToMiddle(fund, "holdActivity");
        activity.setState(1);
        activityDAO.save(activity);

        List<ShopActivity> shopActivities = shopActivityDAO.findByActivityId(activity.getId());
        for (ShopActivity shopActivity : shopActivities) {
            if (shopActivity.getState() == 0) {
                shopActivity.setState(1);
                shopActivityDAO.save(shopActivity);
                Shop shop = shopService.getValidByShopId(shopActivity.getShopId());
                if (shop != null)
                    startActivityEachShop(activity, shop);
            }
        }
    }

    public void startActivityEachShop(Activity activity, Shop shop) {
//        提前保证不为null
        int activityId = activity.getId();
        shop.setActivityId(activityId);
        shopService.addOrUpdate(shop);
        List<Goods> goodsList = goodsService.getValidGoodsByShopId(shop.getShopId());
        for (Goods goods : goodsList) {
//            如果商品tag在活动tag内
            if (isTagIntersection(goods.getTag(), activity.getTags())) {
                goods.setActivityId(activityId);
                goodsService.addOrUpdate(goods);
            }
        }
    }

    public void endActivity(Activity activity) {
        double fund = activity.getRemainFund();
        if (fund > 0)
            accountService.middleToProfit(fund, "endActivity");
        List<ShopActivity> shopActivities = shopActivityDAO.findByActivityId(activity.getId());
        for (ShopActivity shopActivity : shopActivities) {
            if (shopActivity.getState() != 2) {
                shopActivity.setState(2);
                shopActivityDAO.save(shopActivity);
                int shopId = shopActivity.getShopId();
                Shop shop = shopService.getByShopId(shopId);
                if (shop != null)
                    endActivityEachShop(shop);
            }
        }
        activity.setState(2);
        activityDAO.save(activity);
    }

    public void endActivityEachShop(Shop shop) {
//        提前保证不为null
        shop.setActivityId(0);
        shopService.addOrUpdate(shop);
        List<Goods> goodsList = goodsService.getAllGoodsByShopId(shop.getShopId());
        for (Goods goods : goodsList) {
            goods.setActivityId(0);
            goodsService.addOrUpdate(goods);
        }
    }


    @Override
    public Result getGoodsByActivity(int activityId) {
        checkActivityState();
//        先开活动后申请商品怎么办
//        函数写在哪
        Activity activity = getValidById(activityId);
        String tag = activity.getTags();
        List<Integer> tags = MyUtils.getIntListFromTag(tag);
        List<Goods> goodsList = new ArrayList<>();
        for (int tagId : tags) {
            List<Goods> goodsInAc = goodsService.getValidGoodsByTagId(tagId);
            for (Goods goods : goodsInAc) {
                goods.setActivityId(activityId);
                goodsService.addOrUpdate(goods);
            }
            goodsList.addAll(goodsInAc);
        }

        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(goodsList));
        return result;
    }

    @Override
    public Result getActivityByGoods() {
        checkActivityState();
        List<JSONObject> goodsList = goodsService.getApprovedGoods();
        for (JSONObject goods : goodsList) {
            int activityId = goods.getInteger("activityId");
            List<Integer> tags = MyUtils.getIntListFromTag(goods.getString("tag"));
            Activity activity = getValidById(activityId);
            List<Integer> activityTags = MyUtils.getIntListFromTag(goods.getString(activity.getTags()));
            if (!activityTags.containsAll(tags)) {
                goods.remove("activityId");
                goods.put("activityId", 0);
            }
        }

        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(goodsList));
        return result;
    }

    @Override
    public Result displayOneGoods(int goodsId) {
        checkActivityState();
        Goods goods = goodsService.getValidByGoodsId(goodsId);
        if (goods == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无商品信息");
        JSONObject jsonObject = goodsService.transferPicAndTag(goods, true);
        Result result = new Result(HttpServletResponse.SC_OK);
        JSONObject addObj = new JSONObject();

        int activityId = goods.getActivityId();
        if (activityId == 0) {
            addObj.put("activityRule", "该商品未参与活动");
        } else {
            Activity activity = getValidById(activityId);
            if (activity == null) {
                addObj.put("activityRule", "商品未参与活动");
            } else {
                addObj.put("activityRule", "满" + activity.getAmountArrive() + "减" + activity.getAmountMinus());
            }
        }
        result.setReturnString(MyUtils.getJsonStringWithAll(jsonObject, addObj));
        return result;
    }


    public JSONObject getActivityObject(Activity activity) {
        List<Integer> tagList = MyUtils.getIntListFromTag(activity.getTags());
        List<String> tags = new ArrayList<>();
        for (int tagId : tagList) {
            tags.add(tagService.getTagNameByTagId(tagId));
        }
        activity.setTags(MyUtils.joinByComma(tags));

        String[] strings = {};
        JSONObject addObj = new JSONObject();

        return MyUtils.changeObj(activity, addObj, strings);
    }

    public boolean isTagIntersection(String a, String b) {
        List<Integer> aList = MyUtils.getIntListFromTag(a);
        List<Integer> bList = MyUtils.getIntListFromTag(b);
        return !MyUtils.isIntersectionEmpty(aList, bList);
    }

}
