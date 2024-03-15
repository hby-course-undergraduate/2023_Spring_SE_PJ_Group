package com.group4.eshopping.service;

import com.group4.eshopping.entity.Order;
import com.group4.eshopping.exception.CheckException;

public interface OrderService {
    void addOrUpdate(Order order);

    void addNewOrder(int userId, int goodsId, int number, double price, int group, int shopId, int addressId, double minusAmount);

    boolean isUnfinishedOrder(int goodsId) throws CheckException;


//    Result cancelOrder(int orderId, JSONObject userInfo);
//
//    Result confirmOrder(int orderId, JSONObject userInfo);
}
