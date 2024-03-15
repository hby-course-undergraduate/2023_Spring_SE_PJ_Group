package com.group4.eshopping.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;


import org.junit.jupiter.api.Test;

class CartTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link Cart}
     *   <li>{@link Cart#setGoodsId(int)}
     *   <li>{@link Cart#setId(int)}
     *   <li>{@link Cart#setNumber(int)}
     *   <li>{@link Cart#setUpdateAt(LocalDateTime)}
     *   <li>{@link Cart#setUserId(int)}
     *   <li>{@link Cart#setValid(boolean)}
     *   <li>{@link Cart#getGoodsId()}
     *   <li>{@link Cart#getId()}
     *   <li>{@link Cart#getNumber()}
     *   <li>{@link Cart#getUpdateAt()}
     *   <li>{@link Cart#getUserId()}
     *   <li>{@link Cart#isValid()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Cart actualCart = new Cart();
        actualCart.setGoodsId(1);
        actualCart.setId(1);
        actualCart.setNumber(10);
        LocalDateTime updateAt = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualCart.setUpdateAt(updateAt);
        actualCart.setUserId(1);
        actualCart.setValid(true);
        assertEquals(1, actualCart.getGoodsId());
        assertEquals(1, actualCart.getId());
        assertEquals(10, actualCart.getNumber());
        assertSame(updateAt, actualCart.getUpdateAt());
        assertEquals(1, actualCart.getUserId());
        assertTrue(actualCart.isValid());
    }
}

