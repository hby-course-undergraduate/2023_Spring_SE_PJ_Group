package com.group4.eshopping.dao;

import com.group4.eshopping.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

//@EnableJdbcRepositories
//@EnableJpaRepositories
public interface GoodsDAO extends JpaRepository<Goods, Integer> {
    Goods findByGoodsId(int goodsId);

    @Query("select goods from Goods goods where goods.goodsId = ?1 and goods.state = 1")
    Goods findByGoodsIdAndStateIsApproved(int goodsId);

    @Query("select goods from Goods goods where goods.shopId = ?1 and goods.state = 1")
    List<Goods> findByShopIdAndStateIsApproved(int shopId);

    @Query("select goods from Goods goods where goods.state = 1")
    List<Goods> findAllByStateIsApproved();

    List<Goods> findAll();

    List<Goods> findByShopId(int shopId);

    List<Goods> findByTagLike(int tagId);

    @Query(value = "select goods from Goods  goods where goods.goodsName like %?1% and goods.state = 1")
    List<Goods> findByTagLikeAndStateIsTrue(int tagId);
}
