package com.group4.eshopping.dao;

import com.group4.eshopping.entity.OrderGroupDiscounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderGroupDiscountsDAO extends JpaRepository<OrderGroupDiscounts, Integer> {
    OrderGroupDiscounts findByGroup(int group);
}
