package com.group4.eshopping.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.dao.ShopCancelDAO;
import com.group4.eshopping.entity.*;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.GoodsCancelService;
import com.group4.eshopping.utils.CheckCondition;
import com.group4.eshopping.utils.MyUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GoodsCancelServiceImpl implements GoodsCancelService {
    @Autowired
    ShopCancelDAO shopCancelDAO;
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    GoodsServiceImpl goodsService;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    AccountServiceImpl accountService;

    @Override
    public void addOrUpdate(ShopCancel shopCancel) {
        shopCancelDAO.save(shopCancel);
    }

    @Override
    public ShopCancel getByShopCancelId(int shopCancelId) {
        return shopCancelDAO.findById(shopCancelId);
    }

    @Override
    public boolean CheckDeleteCondition(int shopId) {
//        List<Goods> goodsList = goodsService.getValidGoodsByShopId(shopId);
//        for (Goods goods : goodsList) {
//            if (orderService.isUnfinishedOrder(goods.getGoodsId()))
//                return false;
//        }
        return !orderService.isUnfinishedOrder(shopId);
    }

    @Override
    public void setNotValidCart(int goodsId) {
//        找全部cart
        List<Cart> carts = cartService.getByGoodsId(goodsId);
        for (Cart cart : carts) {
            cart.setValid(false);
            cartService.addOrUpdate(cart);
        }
    }

    @Override
    public Result cancelGoods(int goodsId, JSONObject userInfo) {
//        找全部商品还是state=1的商品
//        是否考虑order --应该不考虑
        int userId = CheckCondition.getSellerId(userInfo);
        Goods goods = goodsService.getByGoodsId(goodsId);
        if (goods == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无该商品");
        if (goods.getShopId() != shopService.getByUserId(userId).getShopId())
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "您的商店无对应商品");

        goods.setNotApproved();
        setNotValidCart(goodsId);

        goodsService.addOrUpdate(goods);
        goodsService.reversePic(goodsId);
        return new Result(HttpServletResponse.SC_OK);
    }


    @Override
    public Result cancelShop(String reason, JSONObject userInfo) {
        int userId = CheckCondition.getSellerId(userInfo);
        Shop shop = shopService.getByUserId(userId);
        if (shop == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "用户无商店");
        int shopId = shop.getShopId();

        boolean isOrderFinish = CheckDeleteCondition(shopId);

        ShopCancel shopCancel = new ShopCancel(shopId, isOrderFinish, reason);
        addOrUpdate(shopCancel);
//        account
//        这里状态码写什么
        if (!isOrderFinish)
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "有订单未完成");

        return new Result(HttpServletResponse.SC_OK);
    }


    @Override
    public Result displayShopCancel(JSONObject userInfo) throws CheckException {
        CheckCondition.checkAdmin(userInfo);
        List<ShopCancel> shopCancelList = shopCancelDAO.findAll();
//        过滤掉shop state不为1的元素
        shopCancelList.removeIf(shopCancel -> shopService.getValidByShopId(shopCancel.getShopId()) == null);
        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(shopCancelList));
        return result;
    }

    @Override
    public Result approveForShopCancel(JSONObject request, JSONObject userInfo) throws CheckException {
        CheckCondition.checkAdmin(userInfo);
        //api:批准状态 isApproved  商品id shopId
        boolean isApproved = request.getString("isApproved").equalsIgnoreCase("true");
        int shopCancelId = request.getIntValue("shopCancelId");
        ShopCancel shopCancel = getByShopCancelId(shopCancelId);

        if (shopCancel == null) {
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "商店删除信息不存在");
        }
        if (shopCancel.getState() != 0) {
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "商店删除请求无法批准");
        }
        if (isApproved) {
            shopCancel.setApproved();
            int shopId = shopCancel.getShopId();
            int userId = shopService.getByShopId(shopId).getUserId();
            Account shopAccount = accountService.getShopByShopId(shopId);
            if (shopAccount.getAmount() != 0) {
                accountService.transfer(shopAccount, "shop",
                        accountService.getUserByUserId(userId), "user", shopAccount.getAmount(), "transferUser");
            }
            accountService.deleteAccount(shopAccount);
            shopService.deleteShopByUserId(userId);
            setNotValidShop(shopId);
        } else {
            shopCancel.setNotApproved();
        }

        addOrUpdate(shopCancel);
        return new Result(HttpServletResponse.SC_OK);
    }

    private void setNotValidShop(int shopId) {
//        查询旗下所有商品（包括未审核）
//        设为无效
//        查询所有商品对应购物车
//        设为无效
        List<Goods> goodsList = goodsService.getAllGoodsByShopId(shopId);
        for (Goods goods : goodsList) {
            goods.setNotApproved();
            goodsService.addOrUpdate(goods);
            setNotValidCart(goods.getGoodsId());
        }
    }
}
