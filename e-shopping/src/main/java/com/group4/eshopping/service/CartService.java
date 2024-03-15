package com.group4.eshopping.service;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.Cart;
import com.group4.eshopping.result.Result;

import java.util.List;

public interface CartService {
    Result addCart(Cart goodsList, JSONObject userInfo);

    void addOrUpdate(Cart cart);

    List<Cart> getByGoodsId(int goodsId);

    Result getCart(JSONObject userInfo);

    Result deleteCart(int cartId, JSONObject userInfo);
}
