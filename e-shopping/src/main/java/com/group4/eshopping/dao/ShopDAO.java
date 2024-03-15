package com.group4.eshopping.dao;

import com.group4.eshopping.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface ShopDAO extends JpaRepository<Shop, Integer> {
    Shop findByShopId(int shopId);

    //    Shop getByUserId(int userId);
//    Shop deleteByShopId(int shopId);
    //加上state
    Shop findByUserId(int userId);

    @Query("select shop from Shop shop where shop.shopId = ?1 and shop.state = 1")
    Shop findValidByShopId(int shopId);

    @Query("select shop from Shop shop where shop.userId = ?1 and shop.state = 1")
    Shop findValidByUserId(int userId);

    List<Shop> findAll();

    @Query("select shop from Shop shop where shop.state = 1")
    List<Shop> findAllByStateIsApproved();

    List<Shop> findByTagLike(int tagId);

    List<Shop> findByTagLikeAndStateIsTrue(int tagId);

//    //    SELECT * FROM `shop` WHERE state = 0 LIMIT 10;
//    List<Shop> findAllByStateIsTrue();
//    List<Shop> findAllByStateIsFalse();
}

