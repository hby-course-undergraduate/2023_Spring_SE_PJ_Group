package com.group4.eshopping.dao;


import com.group4.eshopping.entity.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryAddressDAO extends JpaRepository<DeliveryAddress, Integer> {
    List<DeliveryAddress> findByUserId(int userId);

    List<DeliveryAddress> findByUserIdAndStateIsTrue(int userId);

    DeliveryAddress findById(int id);
}
