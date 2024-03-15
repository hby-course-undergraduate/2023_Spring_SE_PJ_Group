package com.group4.eshopping.dao;

import com.group4.eshopping.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(int userId);

    Order findByOrderId(int orderId);

    Order findByOrderIdAndStatusIsTrue(int orderId);

    List<Order> findByShopId(int shopId);

    @Query("SELECT MAX(order.group) FROM Order order ")
    int findMaxGroup();

    List<Order> findByGroupAndStatusIsTrue(int group);

    List<Order> findByUserIdAndStatusIsTrue(int group);

    List<Order> findByShopIdAndRefundingIsTrue(int shopId);

    @Query("select goodsOrder from Order goodsOrder where goodsOrder.group = ?1 " +
            "and goodsOrder.state = ?2 and goodsOrder.status = true")
    List<Order> findClassByGroup(int group, String str);

    @Query("select goodsOrder from Order goodsOrder where goodsOrder.userId = ?1 " +
            "and goodsOrder.state = ?2 and goodsOrder.status = true order by goodsOrder.group")
    List<Order> findClassByUserId(int userId, String str);

    @Query("select goodsOrder from Order goodsOrder where goodsOrder.shopId = ?1 " +
            "and goodsOrder.state = ?2  order by goodsOrder.group")
    List<Order> findClassByShopId(int shopId, String str);

    //    不能用自动生成的不带is
//    @Query("select goodsOrder from Order goodsOrder where goodsOrder.orderId = ?1 " +
//            "and goodsOrder.state = true and goodsOrder.isConfirm = false ")
//    Order findByGoodsIdAndStateIsTrueAndIsConfirmIsFalse(int orderId);
//
//    Order findByOrderIdAndStateIsTrueAndIsConfirmIsFalse(int orderId);
}

