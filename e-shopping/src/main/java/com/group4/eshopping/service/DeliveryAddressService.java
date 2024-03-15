package com.group4.eshopping.service;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.DeliveryAddress;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;

import java.util.List;

public interface DeliveryAddressService {
    void addOrUpdate(DeliveryAddress deliveryAddress);

    void addOrUpdateWithCheck(DeliveryAddress deliveryAddress) throws CheckException;

    List<DeliveryAddress> getValidDeliveryAddressByUserId(int userId);

    boolean isDeliveryAddressExist(DeliveryAddress deliveryAddress);

    Result applyDeliveryAddress(DeliveryAddress deliveryAddress, JSONObject userInfo);

    Result changeDeliveryAddress(DeliveryAddress deliveryAddress, JSONObject userInfo);

    Result cancelDeliveryAddress(int id, JSONObject userInfo);
     Result getDeliveryAddress(JSONObject userInfo) ;

    DeliveryAddress getDeliveryAddressById(int id);
}
