package com.group4.eshopping.dao;

import com.group4.eshopping.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.util.List;

public interface CartDAO extends JpaRepository<Cart, Integer> {
    List<Cart> findByUserId(int userId);

    Cart findByUserIdAndGoodsId(int userId, int goodsId);

    Cart findByIdAndIsValidIsTrue(int id);

    List<Cart> findByGoodsId(int goodsId);
}
