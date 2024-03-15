package com.group4.eshopping.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.dao.DeliveryAddressDAO;
import com.group4.eshopping.entity.DeliveryAddress;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.DeliveryAddressService;
import com.group4.eshopping.utils.CheckCondition;
import com.group4.eshopping.utils.MyUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {
    @Autowired
    DeliveryAddressDAO deliveryAddressDAO;

    @Override
    public void addOrUpdate(DeliveryAddress deliveryAddress) {
        deliveryAddressDAO.save(deliveryAddress);
    }

    @Override
    public void addOrUpdateWithCheck(DeliveryAddress deliveryAddress) throws CheckException {
//        函数内部进行检验
        String phone = CheckCondition.transferMeaning(deliveryAddress.getConsigneePhone());
        CheckCondition.checkPhone(phone);
        deliveryAddress.setConsigneePhone(phone);

        String consignee = CheckCondition.transferMeaning(deliveryAddress.getConsignee());
        deliveryAddress.setConsignee(consignee);

        String address = CheckCondition.transferMeaning(deliveryAddress.getAddress());
        deliveryAddress.setAddress(address);

        deliveryAddressDAO.save(deliveryAddress);
    }

    @Override
    public List<DeliveryAddress> getValidDeliveryAddressByUserId(int userId) {
//        return deliveryAddressDAO.findByUserId(userId);
        return deliveryAddressDAO.findByUserIdAndStateIsTrue(userId);
    }


    @Override
    public boolean isDeliveryAddressExist(DeliveryAddress deliveryAddress) {
        return deliveryAddressDAO.findById(deliveryAddress.getId()) != null;
    }

    //    增
    @Override
    public Result applyDeliveryAddress(DeliveryAddress deliveryAddress, JSONObject userInfo) {
        deliveryAddress.setUserId(CheckCondition.getUserId(userInfo));
        addOrUpdateWithCheck(deliveryAddress);
        return new Result(HttpServletResponse.SC_OK);
    }

    //    改
    @Override
    public Result changeDeliveryAddress(DeliveryAddress deliveryAddress, JSONObject userInfo) {
        int userId = CheckCondition.getUserId(userInfo);
        DeliveryAddress deliveryAddress1 = deliveryAddressDAO.findById(deliveryAddress.getId());
        if (deliveryAddress1 == null || deliveryAddress1.getUserId() != userId)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无对应收货地址");

        deliveryAddress1.setAddress(deliveryAddress.getAddress());
        deliveryAddress1.setConsignee(deliveryAddress.getConsignee());
        deliveryAddress1.setConsigneePhone(deliveryAddress.getConsigneePhone());

        addOrUpdateWithCheck(deliveryAddress1);
        return new Result(HttpServletResponse.SC_OK);
    }

    //    删
    @Override
    public Result cancelDeliveryAddress(int id, JSONObject userInfo) {
        int userId = CheckCondition.getUserId(userInfo);
        DeliveryAddress deliveryAddress1 = deliveryAddressDAO.findById(id);
        if (deliveryAddress1 == null || deliveryAddress1.getUserId() != userId)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无对应收货地址");

        deliveryAddress1.setState(false);
        addOrUpdate(deliveryAddress1);
        return new Result(HttpServletResponse.SC_OK);
    }

    //    查
    @Override
    public Result getDeliveryAddress(JSONObject userInfo) {
        int userId = CheckCondition.getUserId(userInfo);
        List<DeliveryAddress> deliveryAddressList = getValidDeliveryAddressByUserId(userId);
//        return new Result(HttpServletResponse.SC_OK, MyUtils.getJsonStringWithDate(deliveryAddressList));
        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(deliveryAddressList));
        return result;
    }

    @Override
    public DeliveryAddress getDeliveryAddressById(int id) {
        return deliveryAddressDAO.findById(id);
    }
}
