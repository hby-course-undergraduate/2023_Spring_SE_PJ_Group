package com.group4.eshopping.controller;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.impl.AccountServiceImpl;
import com.group4.eshopping.service.impl.TransferAccountServiceImpl;
import com.group4.eshopping.utils.GetInfoFromReq;
import com.group4.eshopping.utils.WriteStrToRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    TransferAccountServiceImpl transferAccountService;
    @Autowired
    AccountServiceImpl accountService;

    @CrossOrigin
    @PostMapping(value = "/api/account-record/user")
    public void getUserAccountRecord(@RequestBody JSONObject requestObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                int days = requestObject.getInteger("limit");
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = accountService.getAccountRecord("user", days, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/account-record/shop")
    public void getShopAccountRecord(@RequestBody JSONObject requestObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                int days = requestObject.getInteger("limit");
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = accountService.getAccountRecord("shop", days, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/account-record/middle")
    public void getMiddleAccountRecord(@RequestBody JSONObject requestObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                int days = requestObject.getInteger("limit");
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = accountService.getAccountRecord("middle", days, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/account-record/profit")
    public void getProfitAccountRecord(@RequestBody JSONObject requestObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                int days = requestObject.getInteger("limit");
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = accountService.getAccountRecord("profit", days, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
