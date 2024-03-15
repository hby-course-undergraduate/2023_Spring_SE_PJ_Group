package com.group4.eshopping.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;


import org.junit.jupiter.api.Test;

class GoodsChangeTest {
    /**
     * Method under test: {@link GoodsChange#getPastAuto(Goods)}
     */
    @Test
    void testGetPastAuto() {
        GoodsChange goodsChange = new GoodsChange();
        goodsChange.getPastAuto(new Goods("Goods Name", 10.0d, "The characteristics of someone or something", 10, 1));
        assertEquals(0, goodsChange.getState());
        assertEquals(10.0d, goodsChange.getPastPrice());
        assertEquals(10, goodsChange.getPastNumber());
        assertNull(goodsChange.getPastGoodsTag());
        assertEquals("Goods Name", goodsChange.getPastGoodsName());
        assertEquals("The characteristics of someone or something", goodsChange.getPastDescription());
    }

    /**
     * Method under test: {@link GoodsChange#getPrice()}
     */
    @Test
    void testGetPrice2() {
        GoodsChange goodsChange = new GoodsChange();
        goodsChange.setCreateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        goodsChange.setDescription("The characteristics of someone or something");
        goodsChange.setGoodsId(1);
        goodsChange.setGoodsName("Goods Name");
        goodsChange.setId(1);
        goodsChange.setNumber(10);
        goodsChange.setPastDescription("Past Description");
        goodsChange.setPastGoodsName("Past Goods Name");
        goodsChange.setPastGoodsTag("Past Goods Tag");
        goodsChange.setPastNumber(10);
        goodsChange.setPastPrice(10.0d);
        goodsChange.setPrice(10.0d);
        goodsChange.setState(1);
        goodsChange.setTag("Tag");
        assertEquals(10.0d, goodsChange.getPrice());
    }

    /**
     * Method under test: {@link GoodsChange#setPrice(double)}
     */
    @Test
    void testSetPrice() {
        GoodsChange goodsChange = new GoodsChange();
        goodsChange.setPrice(10.0d);
        assertEquals(10.0d, goodsChange.getPrice());
    }

    /**
     * Method under test: {@link GoodsChange#getNumber()}
     */
    @Test
    void testGetNumber2() {
        GoodsChange goodsChange = new GoodsChange();
        goodsChange.setCreateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        goodsChange.setDescription("The characteristics of someone or something");
        goodsChange.setGoodsId(1);
        goodsChange.setGoodsName("Goods Name");
        goodsChange.setId(1);
        goodsChange.setNumber(10);
        goodsChange.setPastDescription("Past Description");
        goodsChange.setPastGoodsName("Past Goods Name");
        goodsChange.setPastGoodsTag("Past Goods Tag");
        goodsChange.setPastNumber(10);
        goodsChange.setPastPrice(10.0d);
        goodsChange.setPrice(10.0d);
        goodsChange.setState(1);
        goodsChange.setTag("Tag");
        assertEquals(10, goodsChange.getNumber());
    }

    /**
     * Method under test: {@link GoodsChange#setNumber(int)}
     */
    @Test
    void testSetNumber() {
        GoodsChange goodsChange = new GoodsChange();
        goodsChange.setNumber(10);
        assertEquals(10, goodsChange.getNumber());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link GoodsChange}
     *   <li>{@link GoodsChange#setCreateAt(LocalDateTime)}
     *   <li>{@link GoodsChange#setDescription(String)}
     *   <li>{@link GoodsChange#setGoodsId(int)}
     *   <li>{@link GoodsChange#setGoodsName(String)}
     *   <li>{@link GoodsChange#setId(int)}
     *   <li>{@link GoodsChange#setPastDescription(String)}
     *   <li>{@link GoodsChange#setPastGoodsName(String)}
     *   <li>{@link GoodsChange#setPastGoodsTag(String)}
     *   <li>{@link GoodsChange#setPastNumber(int)}
     *   <li>{@link GoodsChange#setPastPrice(double)}
     *   <li>{@link GoodsChange#setState(int)}
     *   <li>{@link GoodsChange#setTag(String)}
     *   <li>{@link GoodsChange#setApproved()}
     *   <li>{@link GoodsChange#setNotApproved()}
     *   <li>{@link GoodsChange#getCreateAt()}
     *   <li>{@link GoodsChange#getDescription()}
     *   <li>{@link GoodsChange#getGoodsId()}
     *   <li>{@link GoodsChange#getGoodsName()}
     *   <li>{@link GoodsChange#getId()}
     *   <li>{@link GoodsChange#getPastDescription()}
     *   <li>{@link GoodsChange#getPastGoodsName()}
     *   <li>{@link GoodsChange#getPastGoodsTag()}
     *   <li>{@link GoodsChange#getPastNumber()}
     *   <li>{@link GoodsChange#getPastPrice()}
     *   <li>{@link GoodsChange#getState()}
     *   <li>{@link GoodsChange#getTag()}
     * </ul>
     */
    @Test
    void testConstructor() {
        GoodsChange actualGoodsChange = new GoodsChange();
        LocalDateTime createAt = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualGoodsChange.setCreateAt(createAt);
        actualGoodsChange.setDescription("The characteristics of someone or something");
        actualGoodsChange.setGoodsId(1);
        actualGoodsChange.setGoodsName("Goods Name");
        actualGoodsChange.setId(1);
        actualGoodsChange.setPastDescription("Past Description");
        actualGoodsChange.setPastGoodsName("Past Goods Name");
        actualGoodsChange.setPastGoodsTag("Past Goods Tag");
        actualGoodsChange.setPastNumber(10);
        actualGoodsChange.setPastPrice(10.0d);
        actualGoodsChange.setState(1);
        actualGoodsChange.setTag("Tag");
        actualGoodsChange.setApproved();
        actualGoodsChange.setNotApproved();
        assertSame(createAt, actualGoodsChange.getCreateAt());
        assertEquals("The characteristics of someone or something", actualGoodsChange.getDescription());
        assertEquals(1, actualGoodsChange.getGoodsId());
        assertEquals("Goods Name", actualGoodsChange.getGoodsName());
        assertEquals(1, actualGoodsChange.getId());
        assertEquals("Past Description", actualGoodsChange.getPastDescription());
        assertEquals("Past Goods Name", actualGoodsChange.getPastGoodsName());
        assertEquals("Past Goods Tag", actualGoodsChange.getPastGoodsTag());
        assertEquals(10, actualGoodsChange.getPastNumber());
        assertEquals(10.0d, actualGoodsChange.getPastPrice());
        assertEquals(2, actualGoodsChange.getState());
        assertEquals("Tag", actualGoodsChange.getTag());
    }
}

