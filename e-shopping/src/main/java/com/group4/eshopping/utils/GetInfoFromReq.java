package com.group4.eshopping.utils;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;

public class GetInfoFromReq {
    public static JSONObject getInfoFromReq(HttpServletRequest httpServletRequest) {
//        System.out.println(httpServletRequest.getAttribute("role"));
//        System.out.println(httpServletRequest.getAttribute("userId"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("role", httpServletRequest.getAttribute("role"));
        jsonObject.put("userId", httpServletRequest.getAttribute("userId"));
        return jsonObject;
    }
}
