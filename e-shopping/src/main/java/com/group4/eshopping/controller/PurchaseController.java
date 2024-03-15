package com.group4.eshopping.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.Cart;
import com.group4.eshopping.entity.DeliveryAddress;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.impl.CartServiceImpl;
import com.group4.eshopping.service.impl.DealServiceImpl;
import com.group4.eshopping.service.impl.DeliveryAddressServiceImpl;
import com.group4.eshopping.service.impl.OrderServiceImpl;
import com.group4.eshopping.utils.GetInfoFromReq;
import com.group4.eshopping.utils.WriteStrToRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PurchaseController {
    @Autowired
    DeliveryAddressServiceImpl deliveryAddressService;
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    DealServiceImpl dealService;


    @CrossOrigin
    @PostMapping(value = "api/delivery-address/apply")
    public void applyDeliveryAddress(@RequestBody DeliveryAddress deliveryAddress, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = deliveryAddressService.applyDeliveryAddress(deliveryAddress, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @GetMapping(value = "api/delivery-address")
    public void getDeliveryAddress(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = deliveryAddressService.getDeliveryAddress(userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "api/delivery-address/cancel")
    public void cancelDeliveryAddress(@RequestBody JSONObject requestObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                int id = requestObject.getInteger("id");
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = deliveryAddressService.cancelDeliveryAddress(id, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @PostMapping(value = "api/delivery-address/change")
    public void changeDeliveryAddress(@RequestBody DeliveryAddress deliveryAddress, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = deliveryAddressService.changeDeliveryAddress(deliveryAddress, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    //    购物车增删改查
//    可以cart为负数?
    @CrossOrigin
    @PostMapping(value = "/api/cart/add")
    public void addCart(@RequestBody Cart cart, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = cartService.addCart(cart, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/api/cart")
    public void getCart(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = cartService.getCart(userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/cart/cancel")
    public void deleteCart(@RequestBody JSONObject requestObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                int cartId = requestObject.getInteger("cartId");
                Result result = cartService.deleteCart(cartId, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    //  购买
    @CrossOrigin
    @PostMapping(value = "/api/goods/purchase")
    public void purchase(@RequestBody JSONObject jsonObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("array");
                int addressId = jsonObject.getInteger("addressId");
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = dealService.purchase(jsonArray, addressId, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/goods/pay")
    public void pay(@RequestBody JSONObject jsonObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                int orderId = jsonObject.getInteger("group");
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = dealService.pay(orderId, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/order/deliver")
    public void deliverOrder(@RequestBody JSONObject jsonObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                int id = jsonObject.getInteger("orderId");
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = dealService.deliverOrder(id, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/order/cancel")
    public void cancelOrder(@RequestBody JSONObject requestObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                int group = requestObject.getInteger("group");
                Result result = dealService.cancelOrder(group, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/order/refund")
    public void refundOrder(@RequestBody JSONObject requestObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                int orderId = requestObject.getInteger("orderId");
                Result result = dealService.refundOrder(orderId, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @GetMapping(value = "/api/order/refund/approve")
    public void checkRefundOrder(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = dealService.getRefundingOrder(userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/order/refund/approve")
    public void approveRefundOrder(@RequestBody JSONObject requestObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                int orderId = requestObject.getInteger("orderId");
                boolean isApproved = requestObject.getBoolean("isApproved");
                Result result = dealService.approveRefunded(orderId, isApproved, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/order/receipt")
    public void receiptOrder(@RequestBody JSONObject jsonObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                int id = jsonObject.getInteger("orderId");
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = dealService.receiptOrder(id, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @PostMapping(value = "/api/order/delete")
    public void deleteOrder(@RequestBody JSONObject requestObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                int orderId = requestObject.getInteger("orderId");
                Result result = dealService.deleteOrder(orderId, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @GetMapping(value = "/api/order/{orderId}")
    public void displayOneOrder(@PathVariable int orderId, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = dealService.getOneOrder(orderId, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @PostMapping(value = "/api/order/all/customer")
    public void displayAllOrder(@RequestBody JSONObject requestObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                String state = requestObject.getString("state");
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = dealService.getAllOrder(state, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/order/all/seller")
    public void displayAllOrderBySeller(@RequestBody JSONObject requestObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                String state = requestObject.getString("state");
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = dealService.getAllOrderBySeller(state, userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @GetMapping(value = "/api/order/unpaid")
    public void displayAllOrder(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            try {
                JSONObject userInfo = GetInfoFromReq.getInfoFromReq(httpServletRequest);
                Result result = dealService.getUnpaidOrder(userInfo);
                WriteStrToRes.writeResultAll(httpServletResponse, result);
            } catch (CheckException checkException) {
                WriteStrToRes.writeStatusAndStr(httpServletResponse, checkException);
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(httpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
