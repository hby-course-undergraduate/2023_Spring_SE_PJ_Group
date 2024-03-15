package com.group4.eshopping.dao;

import com.group4.eshopping.entity.GoodsPic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface GoodsPicDAO extends JpaRepository<GoodsPic, Integer> {
    @Query("select goodsPic from GoodsPic goodsPic where goodsPic.state = 1 and goodsPic.goodsId = ?1 ")
    List<GoodsPic> findByGoodsIdAndStateIsTrue(int goodsId);

    @Query("select goodsPic from GoodsPic goodsPic where goodsPic.state = 0 and goodsPic.goodsId = ?1 ")
    List<GoodsPic> findByGoodsIdAndStateIsFalse(int goodsId);

    List<GoodsPic> findByGoodsId(int goodsId);

    List<GoodsPic> findById(int id);

    @Query("select goodsPic from GoodsPic goodsPic where goodsPic.state = 0 and goodsPic.belongTo = ?1 ")
    List<GoodsPic> findByBelongToAndStateIsFalse(int belongTo);

    @Query("select goodsPic from GoodsPic goodsPic where goodsPic.state = 1 and goodsPic.belongTo = ?1 ")
    List<GoodsPic> findByBelongToAndStateIsTrue(int belongTo);
}
