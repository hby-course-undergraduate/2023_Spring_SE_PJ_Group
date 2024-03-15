package com.group4.eshopping.service;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;

public interface ApplyService {
    Result approveForGoods(JSONObject request, JSONObject userInfo) throws CheckException;

    Result approveForGoodsChange(JSONObject request, JSONObject userInfo) throws CheckException;
}
