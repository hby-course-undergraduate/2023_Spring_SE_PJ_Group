package com.group4.eshopping.dao;

import com.group4.eshopping.entity.GoodsChange;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface GoodsChangeDAO extends JpaRepository<GoodsChange, Integer> {

    List<GoodsChange> findAll();

    List<GoodsChange> findByGoodsId(int goodsId);

    GoodsChange findById(int id);
//    List<GoodsChange> findNotApprovedByGoodsId(int goodsId);
}
