package com.group4.eshopping.result;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Result {
    //响应码
    private int code;
    //报错信息
    private String returnString;

    private JSONArray resArray;

    private JSONObject resObject;

    private byte[] pic;

    public JSONObject getResObject() {
        return resObject;
    }

    public void setResObject(JSONObject resObject) {
        this.resObject = resObject;
    }

    public Result(int code) {
        this.code = code;
//        if (resObject.isEmpty())
        resObject = new JSONObject();
        resArray = new JSONArray();
    }

    public Result(String msg) {
        resObject = new JSONObject();
        resObject.put("msg", msg);
    }

    public Result(int code, String msg) {
        this.code = code;
        resObject = new JSONObject();
        resObject.put("msg", msg);
//        注意此处构造函数的string和setReturnString不同
//        一个写报错一个写返回数据
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setLoginInfo(boolean isAdmin, boolean isSeller, String token) {
        resObject = new JSONObject();
        resObject.put("token", token);
        resObject.put("isAdmin", isAdmin);
        resObject.put("isSeller", isSeller);
    }

    public void setAdmin() {
        resObject.put("isAdmin", true);
    }

    public void setNotAdmin() {
        resObject.put("isAdmin", false);
    }

    public void setSeller() {
        resObject.put("isSeller", true);
    }

    public void setNotSeller() {
        resObject.put("isSeller", false);
    }

    public void setToken(String token) {
        resObject.put("token", token);
    }

    public String getReturnString() {
        return returnString;
    }

    public void setReturnString(String returnString) {
        this.returnString = returnString;
    }

    public JSONArray getResArray() {
        return resArray;
    }

    public void setResArray(JSONArray resArray) {
        this.resArray = resArray;
    }

    public <T> void setResObject(T t) {
        //没测试过，慎用
        this.resObject = (JSONObject) JSONObject.toJSON(t);
    }

    public String jsonObjectToString() {
        String str = "";
        if (returnString != null && !returnString.isEmpty())
            str = returnString;
        else if (resArray != null && !resArray.isEmpty())
            str = JSONArray.toJSONString(resArray);
        else str = JSONObject.toJSONString(resObject);
        return str;
//        new Gson().toJson(result);
//        return new ObjectMapper().writeValueAsString(jsonObject);
//        if (!resObject.isEmpty())
//            str = ""
//        str = JSONObject.toJSONString(resArray);
    }

    public <T> void setResArray(List<T> list) {
        this.resArray = JSONObject.parseArray(JSONObject.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss"));
//        <T extends Comparable<T>>
//        public void setResArray (List < E > list)
//        List<T> list = new ArrayList<T>();
//        JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
//        setResArray(JSONObject.parseArray(JSONObject.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss")));
    }
}
