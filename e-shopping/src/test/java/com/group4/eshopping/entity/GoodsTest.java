package com.group4.eshopping.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GoodsTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Goods#Goods()}
     *   <li>{@link Goods#setDescription(String)}
     *   <li>{@link Goods#setGoodsId(int)}
     *   <li>{@link Goods#setGoodsName(String)}
     *   <li>{@link Goods#setNumber(int)}
     *   <li>{@link Goods#setPrice(double)}
     *   <li>{@link Goods#setRemainNumber(int)}
     *   <li>{@link Goods#setShopId(int)}
     *   <li>{@link Goods#setState(int)}
     *   <li>{@link Goods#setTag(String)}
     *   <li>{@link Goods#setApproved()}
     *   <li>{@link Goods#setNotApproved()}
     *   <li>{@link Goods#setRemainNumberAuto()}
     *   <li>{@link Goods#getDescription()}
     *   <li>{@link Goods#getGoodsId()}
     *   <li>{@link Goods#getGoodsName()}
     *   <li>{@link Goods#getNumber()}
     *   <li>{@link Goods#getPrice()}
     *   <li>{@link Goods#getRemainNumber()}
     *   <li>{@link Goods#getShopId()}
     *   <li>{@link Goods#getState()}
     *   <li>{@link Goods#getTag()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Goods actualGoods = new Goods();
        actualGoods.setDescription("The characteristics of someone or something");
        actualGoods.setGoodsId(1);
        actualGoods.setGoodsName("Goods Name");
        actualGoods.setNumber(10);
        actualGoods.setPrice(10.0d);
        actualGoods.setRemainNumber(10);
        actualGoods.setShopId(1);
        actualGoods.setState(1);
        actualGoods.setTag("Tag");
        actualGoods.setApproved();
        actualGoods.setNotApproved();
        actualGoods.setRemainNumberAuto();
        assertEquals("The characteristics of someone or something", actualGoods.getDescription());
        assertEquals(1, actualGoods.getGoodsId());
        assertEquals("Goods Name", actualGoods.getGoodsName());
        assertEquals(10, actualGoods.getNumber());
        assertEquals(10.0d, actualGoods.getPrice());
        assertEquals(10, actualGoods.getRemainNumber());
        assertEquals(1, actualGoods.getShopId());
        assertEquals(2, actualGoods.getState());
        assertEquals("Tag", actualGoods.getTag());
    }

    /**
     * Method under test: {@link Goods#Goods(String, double, String, int, int)}
     */
    @Test
    void testConstructor2() {
        Goods actualGoods = new Goods("Goods Name", 10.0d, "The characteristics of someone or something", 10, 1);

        assertEquals("The characteristics of someone or something", actualGoods.getDescription());
        assertEquals(1, actualGoods.getShopId());
        assertEquals(10, actualGoods.getRemainNumber());
        assertEquals(10.0d, actualGoods.getPrice());
        assertEquals(10, actualGoods.getNumber());
        assertEquals("Goods Name", actualGoods.getGoodsName());
    }
}

