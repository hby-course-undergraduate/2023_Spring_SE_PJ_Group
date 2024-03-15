package com.group4.eshopping.controller;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.Shop;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.impl.AccountServiceImpl;
import com.group4.eshopping.service.impl.GoodsCancelServiceImpl;
import com.group4.eshopping.service.impl.RegisterServiceImpl;
import com.group4.eshopping.service.impl.ShopServiceImpl;
import com.group4.eshopping.utils.GetInfoFromReq;
import com.group4.eshopping.utils.WriteStrToRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ShopController {
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    GoodsCancelServiceImpl goodsCancelService;
    @Autowired
    RegisterServiceImpl registerService;

    @CrossOrigin
    @PostMapping(value = "api/shop/apply")//申请开店
    public void applyForShop(@RequestBody Shop shop, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = registerService.applyForShop(shop, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
//        try {
//            return applyForShopService.applyForShop(shop);
//        } catch (Exception e) {
//            return new Result(returnCode.INTERNAL_SERVER_ERROR.code, "后端错误");
//        }
    }


    @CrossOrigin
    @GetMapping(value = "api/shop/approve")//批准开店界面
    public void displayAllShop(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = shopService.displayAllShops(userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @PostMapping(value = "api/shop/approve")//批准开店
    public void approveForShop(@RequestBody JSONObject request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = registerService.approveForShop(request, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
//        try {
//            return shopService.approveForShop(object);
//        } catch (Exception e) {
//            return new Result(returnCode.INTERNAL_SERVER_ERROR.code, "后端错误");
//        }
    }


    @CrossOrigin
    @GetMapping(value = "api/shop/display")//展示界面
    public void displayApprovedShop(HttpServletResponse httpServletResponse) {
        try {
            Result result = shopService.displayApprovedShop();
            WriteStrToRes.writeResultAll(httpServletResponse, result);
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "api/shop/account/recharge")//转账给商店
    public void rechargeShopAccount(@RequestBody JSONObject amountObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                double amount = amountObject.getDouble("amount");
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = accountService.rechargeShopAccount(amount, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @PutMapping(value = "api/shop/change")
    public void changeShop(@RequestBody JSONObject changeObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = registerService.changeShop(changeObject, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/api/shop/account")
    public void getShopAccount(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = accountService.checkShopAccount(userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/api/shop/profile")
    public void getUserInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = shopService.checkShopInfo(userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @PostMapping(value = "api/shop/cancel")//注销商店
    public void cancelShop(@RequestBody JSONObject request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                String reason = request.getString("reason");
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = goodsCancelService.cancelShop(reason, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @PostMapping(value = "api/shop/cancel/approve")//注销商店批准
    public void approveForShopCancel(@RequestBody JSONObject request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = goodsCancelService.approveForShopCancel(request, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @GetMapping(value = "api/shop/cancel/approve")//注销商店批准界面
    public void displayAllShopCancel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = goodsCancelService.displayShopCancel(userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
