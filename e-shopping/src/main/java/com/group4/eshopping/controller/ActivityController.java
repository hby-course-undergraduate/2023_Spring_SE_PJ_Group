package com.group4.eshopping.controller;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.Activity;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.impl.ActivityServiceImpl;
import com.group4.eshopping.utils.GetInfoFromReq;
import com.group4.eshopping.utils.WriteStrToRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ActivityController {
    @Autowired
    ActivityServiceImpl activityService;

    @CrossOrigin
    @PostMapping(value = "/api/activity/organize")
    public void organizeActivity(@RequestBody Activity activity, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = activityService.organizeActivity(activity, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/activity/apply")
    public void applyActivity(@RequestBody JSONObject jsonObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                int activityId = jsonObject.getInteger("activityId");
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = activityService.applyActivity(activityId, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/api/activity/approve")
    public void checkApplyingActivity(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = activityService.checkApplyingShopByActivity(userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/activity/approve")
    public void approveActivity(@RequestBody JSONObject jsonObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                int activityId = jsonObject.getInteger("activityId");
                int shopId = jsonObject.getInteger("shopId");
                boolean isApproved = jsonObject.getBoolean("isApproved");
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = activityService.approveActivity(activityId, shopId, isApproved, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/api/activity")
    public void getActivity(HttpServletResponse httpServletResponse) {
        try {
            try {
                Result result = activityService.getActivity();
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/api/activity/active")
    public void getActiveActivity(HttpServletResponse httpServletResponse) {
        try {
            try {
                Result result = activityService.getActiveActivity();
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/api/activity/goods")
    public void getGoodsByActivity(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse) {
        try {
            try {
                int activityId = jsonObject.getInteger("activityId");
                Result result = activityService.getGoodsByActivity(activityId);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
