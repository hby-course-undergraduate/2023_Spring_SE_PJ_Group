package com.group4.eshopping.dao;

import com.group4.eshopping.entity.ShopCancel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

public interface ShopCancelDAO extends JpaRepository<ShopCancel, Integer> {
    ShopCancel findById(int id);
}
