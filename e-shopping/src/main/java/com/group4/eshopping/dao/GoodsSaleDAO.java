package com.group4.eshopping.dao;

import com.group4.eshopping.entity.GoodsSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsSaleDAO extends JpaRepository<GoodsSale, Integer> {
    List<GoodsSale> findByShopId(int shopId);

    List<GoodsSale> findByBuyBy(int buyBy);

    List<GoodsSale> findByGoodsId(int goodsId);
}
