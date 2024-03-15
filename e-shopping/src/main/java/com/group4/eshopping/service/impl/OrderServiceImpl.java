package com.group4.eshopping.service.impl;

import com.group4.eshopping.dao.OrderDAO;
import com.group4.eshopping.entity.Order;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDAO orderDAO;

    @Override
    public void addOrUpdate(Order order) {
        orderDAO.save(order);
    }

    @Override
    public void addNewOrder(int userId, int goodsId, int number, double price, int group, int shopId, int addressId, double minusAmount) {
        Order order = new Order(userId, goodsId, number, price, group, shopId, addressId,minusAmount);
        addOrUpdate(order);
    }

    public Order getByOrderId(int orderId) {
        return orderDAO.findByOrderIdAndStatusIsTrue(orderId);
    }

    public Order getAllByOrderId(int orderId) {
        return orderDAO.findByOrderId(orderId);
    }

    @Override
    public boolean isUnfinishedOrder(int shopId) throws CheckException {
//        是否有未完成的订单（只检测未发货）
        List<Order> orders = orderDAO.findByShopId(shopId);
        for (Order order : orders) {
            if (Objects.equals(order.getState(), "undelivered"))
                return true;
        }
        return false;
//        Order order = orderDAO.findByGoodsIdAndStateIsTrueAndIsConfirmIsFalse(goodsId);
//        return order != null;
    }

    public int getGroup() {
        try {
            return orderDAO.findMaxGroup() + 1;
        } catch (Exception e) {
            return 1;
        }
    }

    public List<Order> getUnpaidByGroup(int group) {
        return orderDAO.findClassByGroup(group, "unpaid");
    }

    public List<Order> getAllByUserId(int userId) {
        return orderDAO.findByUserIdAndStatusIsTrue(userId);
    }

    public List<Order> getUnpaidByUserId(int userId) {
        return orderDAO.findClassByUserId(userId, "unpaid");
    }

    public List<Order> getUndeliveredByUserId(int userId) {
        return orderDAO.findClassByUserId(userId, "undelivered");
    }

    public List<Order> getUnreceivedByUserId(int userId) {
        return orderDAO.findClassByUserId(userId, "unreceived");
    }

    public List<Order> getReceivedByUserId(int userId) {
        return orderDAO.findClassByUserId(userId, "received");
    }

    public List<Order> getCancelledByUserId(int userId) {
        return orderDAO.findClassByUserId(userId, "cancelled");
    }

    public List<Order> getRefundedByUserId(int userId) {
        return orderDAO.findClassByUserId(userId, "refunded");
    }

    public List<Order> getRefundingOrder(int shopId) {
        return orderDAO.findByShopIdAndRefundingIsTrue(shopId);
    }

    public List<Order> getAllByShopId(int shopId) {
        return orderDAO.findByShopId(shopId);
    }

    public List<Order> getUnpaidByShopId(int shopId) {
        return orderDAO.findClassByShopId(shopId, "unpaid");
    }

    public List<Order> getUndeliveredByShopId(int shopId) {
        return orderDAO.findClassByShopId(shopId, "undelivered");
    }

    public List<Order> getUnreceivedByShopId(int shopId) {
        return orderDAO.findClassByShopId(shopId, "unreceived");
    }

    public List<Order> getReceivedByShopId(int shopId) {
        return orderDAO.findClassByShopId(shopId, "received");
    }

    public List<Order> getCancelledByShopId(int shopId) {
        return orderDAO.findClassByShopId(shopId, "cancelled");
    }

    public List<Order> getRefundedByShopId(int shopId) {
        return orderDAO.findClassByShopId(shopId, "refunded");
    }
}
