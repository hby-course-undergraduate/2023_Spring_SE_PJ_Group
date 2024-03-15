package com.group4.eshopping.service;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.Shop;
import com.group4.eshopping.entity.User;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import org.springframework.web.bind.annotation.RequestBody;

public interface RegisterService {
    Result register(@RequestBody User requestUser) throws CheckException;

    Result applyForShop(Shop shop, JSONObject userInfo) throws CheckException;

    Result approveForShop(JSONObject request, JSONObject userInfo) throws CheckException;

    Result changeShop(JSONObject changeObject, JSONObject userInfo);
}

