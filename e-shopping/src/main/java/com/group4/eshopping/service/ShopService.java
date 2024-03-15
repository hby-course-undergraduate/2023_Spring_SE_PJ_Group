package com.group4.eshopping.service;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.Shop;
import com.group4.eshopping.result.Result;

import java.util.List;

public interface ShopService {

    boolean isShopExistByUserId(int userId);

    boolean isValidShopExistByUserId(int userId);

    Shop getByUserId(int userId);

    void addOrUpdate(Shop shop);

    Shop getByShopId(int shopId);

    Result displayApprovedShop();

//    List<Shop> displayNotApprovedShop();

    Shop getValidByShopId(int shopId);

    Shop getValidByUserId(int userId);

    Result displayAllShops(JSONObject userInfo);


    void deleteShopByUserId(int userId);

    Result shopChange(JSONObject changeObject, JSONObject userInfo);

    Result checkShopInfo(JSONObject userInfo);

    List<Shop> getShopsByTag(String tagName);

    List<Shop> getShopsByTag(int tagId);

    boolean isTagBelongToShop(String tag, int shopId);
}
