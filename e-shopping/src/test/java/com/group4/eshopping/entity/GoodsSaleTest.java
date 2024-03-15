package com.group4.eshopping.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class GoodsSaleTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link GoodsSale#GoodsSale()}
     *   <li>{@link GoodsSale#setBuyBy(int)}
     *   <li>{@link GoodsSale#setFinishAt(LocalDateTime)}
     *   <li>{@link GoodsSale#setGoodsId(int)}
     *   <li>{@link GoodsSale#setNumber(int)}
     *   <li>{@link GoodsSale#setShopId(int)}
     *   <li>{@link GoodsSale#getId(int)}
     *   <li>{@link GoodsSale#getUnitPrice(double)}
     *   <li>{@link GoodsSale#getBuyBy()}
     *   <li>{@link GoodsSale#getFinishAt()}
     *   <li>{@link GoodsSale#getGoodsId()}
     *   <li>{@link GoodsSale#getId()}
     *   <li>{@link GoodsSale#getNumber()}
     *   <li>{@link GoodsSale#getShopId()}
     *   <li>{@link GoodsSale#getUnitPrice()}
     * </ul>
     */
    @Test
    void testConstructor() {
        GoodsSale actualGoodsSale = new GoodsSale();
        actualGoodsSale.setBuyBy(1);
        LocalDateTime finishAt = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualGoodsSale.setFinishAt(finishAt);
        actualGoodsSale.setGoodsId(1);
        actualGoodsSale.setNumber(10);
        actualGoodsSale.setShopId(1);
        actualGoodsSale.getId(1);
        actualGoodsSale.getUnitPrice(10.0d);
        assertEquals(1, actualGoodsSale.getBuyBy());
        assertSame(finishAt, actualGoodsSale.getFinishAt());
        assertEquals(1, actualGoodsSale.getGoodsId());
        assertEquals(1, actualGoodsSale.getId());
        assertEquals(10, actualGoodsSale.getNumber());
        assertEquals(1, actualGoodsSale.getShopId());
        assertEquals(10.0d, actualGoodsSale.getUnitPrice());
    }

    /**
     * Method under test: {@link GoodsSale#GoodsSale(int, int, int, double, int)}
     */
    @Test
    void testConstructor2() {
        GoodsSale actualGoodsSale = new GoodsSale(1, 1, 10, 10.0d, 1);

        assertEquals(1, actualGoodsSale.getBuyBy());
        assertEquals(10.0d, actualGoodsSale.getUnitPrice());
        assertEquals(1, actualGoodsSale.getShopId());
        assertEquals(10, actualGoodsSale.getNumber());
        assertEquals(1, actualGoodsSale.getGoodsId());
    }
}

