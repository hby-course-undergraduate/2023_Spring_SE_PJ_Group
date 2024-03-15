package com.group4.eshopping.service;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.Activity;
import com.group4.eshopping.result.Result;

public interface ActivityService {
    void addNewShopActivity(int activityId, int shopId);

    Result organizeActivity(Activity activity, JSONObject userInfo);

    Result applyActivity(int shopId, JSONObject userInfo);

    Result getActivity();

    Result getActiveActivity();

    Result approveActivity(int activityId, int shopId, boolean isApproved, JSONObject userInfo);

    Result checkApplyingShopByActivity(JSONObject userInfo);

    Result getGoodsByActivity(int activityId);

    Result getActivityByGoods();

    void checkActivityState();

    Result displayOneGoods(int goodsId);
}
