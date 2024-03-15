package com.group4.eshopping.controller;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.User;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.AccountService;
import com.group4.eshopping.service.impl.RegisterServiceImpl;
import com.group4.eshopping.service.impl.UserServiceImpl;
import com.group4.eshopping.utils.GetInfoFromReq;
import com.group4.eshopping.utils.WriteStrToRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    AccountService accountService;
    @Autowired
    RegisterServiceImpl registerService;

    @CrossOrigin
    @PostMapping(value = "api/login")//登录
    public void login(@RequestBody User requestUser, HttpServletResponse httpServletResponse) {
        try {
            try {
                Result result = userService.login(requestUser);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
            //return new Result(returnCode.INTERNAL_SERVER_ERROR.code, "后端错误");
        }
    }


    @CrossOrigin
    @PostMapping(value = "api/register")//注册
    public void register(@RequestBody User requestUser, HttpServletResponse httpServletResponse) {
        try {
            try {
                Result result = registerService.register(requestUser);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @PutMapping(value = "api/user/profile/password")
    public void changePassword(@RequestBody JSONObject request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = userService.changePassword(request, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping(value = "api/user/profile/email")
    public void changeEmail(@RequestBody JSONObject request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = userService.changeEmail(request, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping(value = "api/user/profile/username")
    public void changeUsername(@RequestBody JSONObject request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = userService.changeUsername(request, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping(value = "api/user/profile/phone")
    public void changePhone(@RequestBody JSONObject request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = userService.changePhone(request, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "api/user/account/recharge")
    public void rechargeUserAccount(@RequestBody JSONObject amountObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                double amount = amountObject.getDouble("amount");
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = accountService.rechargeUserAccount(amount, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/api/user/account")
    public void checkUserAccount(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = accountService.checkUserAccount(userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/api/user/profile")
    public void checkUserInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = userService.checkUserInfo(userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @GetMapping(value = "/api/admin/account")
    public void checkAdminAccount(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = accountService.checkAdminAccount(userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @PostMapping(value = "api/admin/account/recharge")
    public void rechargeAdminAccount(@RequestBody JSONObject amountObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = accountService.rechargeAdminAccount(amountObject, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
