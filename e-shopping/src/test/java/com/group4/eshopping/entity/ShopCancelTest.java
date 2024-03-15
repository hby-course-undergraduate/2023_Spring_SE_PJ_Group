package com.group4.eshopping.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;


import org.junit.jupiter.api.Test;

class ShopCancelTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ShopCancel#ShopCancel()}
     *   <li>{@link ShopCancel#setCreateAt(LocalDateTime)}
     *   <li>{@link ShopCancel#setOrderFinish(boolean)}
     *   <li>{@link ShopCancel#setReason(String)}
     *   <li>{@link ShopCancel#setShopCancelId(int)}
     *   <li>{@link ShopCancel#setShopId(int)}
     *   <li>{@link ShopCancel#setState(int)}
     *   <li>{@link ShopCancel#setApproved()}
     *   <li>{@link ShopCancel#setNotApproved()}
     *   <li>{@link ShopCancel#getCreateAt()}
     *   <li>{@link ShopCancel#getReason()}
     *   <li>{@link ShopCancel#getShopCancelId()}
     *   <li>{@link ShopCancel#getShopId()}
     *   <li>{@link ShopCancel#getState()}
     *   <li>{@link ShopCancel#isOrderFinish()}
     * </ul>
     */
    @Test
    void testConstructor() {
        ShopCancel actualShopCancel = new ShopCancel();
        LocalDateTime createAt = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualShopCancel.setCreateAt(createAt);
        actualShopCancel.setOrderFinish(true);
        actualShopCancel.setReason("Just cause");
        actualShopCancel.setShopCancelId(1);
        actualShopCancel.setShopId(1);
        actualShopCancel.setState(1);
        actualShopCancel.setApproved();
        actualShopCancel.setNotApproved();
        assertSame(createAt, actualShopCancel.getCreateAt());
        assertEquals("Just cause", actualShopCancel.getReason());
        assertEquals(1, actualShopCancel.getShopCancelId());
        assertEquals(1, actualShopCancel.getShopId());
        assertEquals(2, actualShopCancel.getState());
        assertTrue(actualShopCancel.isOrderFinish());
    }

    /**
     * Method under test: {@link ShopCancel#ShopCancel(int, boolean, String)}
     */
    @Test
    void testConstructor2() {
        ShopCancel actualShopCancel = new ShopCancel(1, true, "Just cause");

        assertTrue(actualShopCancel.isOrderFinish());
        assertEquals(0, actualShopCancel.getState());
        assertEquals(1, actualShopCancel.getShopId());
        assertEquals("Just cause", actualShopCancel.getReason());
    }
}

