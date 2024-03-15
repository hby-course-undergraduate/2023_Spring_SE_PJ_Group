package com.group4.eshopping.service;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.ShopCancel;
import com.group4.eshopping.result.Result;

public interface GoodsCancelService {

    boolean CheckDeleteCondition(int goodsId);

    void addOrUpdate(ShopCancel shopCancel);

    Result cancelGoods(int goodsId, JSONObject userInfo);

    void setNotValidCart(int goodsId);

    Result cancelShop(String reason, JSONObject userInfo);

    Result displayShopCancel(JSONObject userInfo);

    Result approveForShopCancel(JSONObject request, JSONObject userInfo);

    ShopCancel getByShopCancelId(int shopCancelId);

}
