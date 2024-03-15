package com.group4.eshopping.controller;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.Goods;
import com.group4.eshopping.entity.GoodsChange;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.impl.*;
import com.group4.eshopping.utils.GetInfoFromReq;
import com.group4.eshopping.utils.WriteStrToRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class GoodsController {
    @Autowired
    GoodsServiceImpl goodsService;
    @Autowired
    GoodsChangeServiceImpl goodsChangeService;
    @Autowired
    GoodsCancelServiceImpl goodsCancelService;
    @Autowired
    ApplyServiceImpl applyService;
    @Autowired
    ActivityServiceImpl activityService;

    @CrossOrigin
    @PostMapping(value = "api/goods/apply")//申请商品
    public void applyForGoods(Goods requestGoods, @RequestParam("picFile") MultipartFile[] file, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = goodsService.applyForGoods(requestGoods, file, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

//    @CrossOrigin
//    @PostMapping(value = "api/goods/apply/pic")//申请商品
//    public void applyForGoods(@RequestParam("picFile") MultipartFile file, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//        try {
//            try {
//                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
//                String s = MyUtils.getPicture(file);
//                System.out.println(s);
////                Result result = goodsService.applyForGoods(requestGoods, userInfo);
////                WriteStrToRes.writeResultAll(httpServletResponse, result);
//            } catch (CheckException checkException) {
//                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
//            }
//        } catch (Exception e) {
//            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        }
//    }


    @CrossOrigin
    @PostMapping(value = "api/goods/approve")//批准商品
    public void approveForGoods(@RequestBody JSONObject request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = applyService.approveForGoods(request, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "api/goods/change/approve")
    public void approveForGoodsChange(@RequestBody JSONObject request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = applyService.approveForGoodsChange(request, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @GetMapping(value = "api/goods/{goodsId}")
    public void getOneGoods(@PathVariable int goodsId, HttpServletResponse httpServletResponse) {
        try {
            try {
                Result result = activityService.displayOneGoods(goodsId);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping(value = "api/goods/display")//展示界面
    public void getApprovedGoods(HttpServletResponse httpServletResponse) {
        try {
            Result result = goodsService.displayApprovedGoods();
            WriteStrToRes.writeResultAll(httpServletResponse, result);
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping(value = "api/goods/recommend")
    public void geRecommendedGoods(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
            Result result = goodsService.displayRecommendedGoods(userInfo);
            WriteStrToRes.writeResultAll(httpServletResponse, result);
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping(value = "api/goods/approve")//批准界面
    public void getAllGoods(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = goodsService.displayAllGoods(userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping(value = "api/goods/apply/record")
    public void goodsApplyRecord(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = goodsService.goodsApplyRecord(userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @PutMapping(value = "api/goods/change")
    public void changeGoods(GoodsChange goodsChange, @RequestParam("picFile") MultipartFile[] file,
                            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = goodsChangeService.goodsChange(goodsChange, file, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping(value = "api/goods/change/approve")
    public void getGoodsChanges(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = goodsChangeService.displayGoodsChanges(userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping(value = "api/goods/change/record")
    public void goodsChangeRecord(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = goodsChangeService.goodsChangeRecord(userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/goods/cancel")
    public void cancelGoods(@RequestBody JSONObject requestObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                int goodsId = requestObject.getInteger("goodsId");
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = goodsCancelService.cancelGoods(goodsId, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
