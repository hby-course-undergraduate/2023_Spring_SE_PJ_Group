package com.group4.eshopping.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.result.Result;

public interface DealService {

    Result purchase(JSONArray goodsList, int addressId, JSONObject userInfo);

    //    支付
    Result pay(int orderId, JSONObject userInfo);

    //    确认收货
    Result receiptOrder(int id, JSONObject userInfo);



    //    发货
    Result deliverOrder(int id, JSONObject userInfo);

    Result refundOrder(int orderId, JSONObject userInfo);

    Result cancelOrder(int orderId, JSONObject userInfo);

    Result approveRefunded(int orderId, boolean isApproved, JSONObject userInfo);

    Result getRefundingOrder(JSONObject userInfo);

    Result deleteOrder(int orderId, JSONObject userInfo);

    Result getOneOrder(int orderId, JSONObject userInfo);

    Result getAllOrder(String state, JSONObject userInfo);

    Result getUnpaidOrder(JSONObject userInfo);

    Result getAllOrderBySeller(String state, JSONObject userInfo);

}
