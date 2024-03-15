package com.group4.eshopping.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class OrderTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Order#Order()}
     *   <li>{@link Order#setBuyTime(LocalDateTime)}
     *   <li>{@link Order#setGoodsId(int)}
     *   <li>{@link Order#setGroup(int)}
     *   <li>{@link Order#setMinusAmount(double)}
     *   <li>{@link Order#setNumber(int)}
     *   <li>{@link Order#setOrderId(int)}
     *   <li>{@link Order#setRefunding(boolean)}
     *   <li>{@link Order#setShopId(int)}
     *   <li>{@link Order#setState(String)}
     *   <li>{@link Order#setStatus(boolean)}
     *   <li>{@link Order#setUnitPrice(double)}
     *   <li>{@link Order#setUserId(int)}
     *   <li>{@link Order#setCancelled()}
     *   <li>{@link Order#setDelivered()}
     *   <li>{@link Order#setPaid()}
     *   <li>{@link Order#setReceived()}
     *   <li>{@link Order#setRefunded()}
     *   <li>{@link Order#getBuyTime()}
     *   <li>{@link Order#getGoodsId()}
     *   <li>{@link Order#getGroup()}
     *   <li>{@link Order#getMinusAmount()}
     *   <li>{@link Order#getNumber()}
     *   <li>{@link Order#getOrderId()}
     *   <li>{@link Order#getShopId()}
     *   <li>{@link Order#getState()}
     *   <li>{@link Order#getUnitPrice()}
     *   <li>{@link Order#getUserId()}
     *   <li>{@link Order#isRefunding()}
     *   <li>{@link Order#isStatus()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Order actualOrder = new Order();
        LocalDateTime buyTime = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualOrder.setBuyTime(buyTime);
        actualOrder.setGoodsId(1);
        actualOrder.setGroup(1);
        actualOrder.setMinusAmount(10.0d);
        actualOrder.setNumber(10);
        actualOrder.setOrderId(1);
        actualOrder.setRefunding(true);
        actualOrder.setShopId(1);
        actualOrder.setState("MD");
        actualOrder.setStatus(true);
        actualOrder.setUnitPrice(10.0d);
        actualOrder.setUserId(1);
        actualOrder.setCancelled();
        actualOrder.setDelivered();
        actualOrder.setPaid();
        actualOrder.setReceived();
        actualOrder.setRefunded();
        assertSame(buyTime, actualOrder.getBuyTime());
        assertEquals(1, actualOrder.getGoodsId());
        assertEquals(1, actualOrder.getGroup());
        assertEquals(10.0d, actualOrder.getMinusAmount());
        assertEquals(10, actualOrder.getNumber());
        assertEquals(1, actualOrder.getOrderId());
        assertEquals(1, actualOrder.getShopId());
        assertEquals("refunded", actualOrder.getState());
        assertEquals(10.0d, actualOrder.getUnitPrice());
        assertEquals(1, actualOrder.getUserId());
        assertTrue(actualOrder.isRefunding());
        assertTrue(actualOrder.isStatus());
    }

    /**
     * Method under test: {@link Order#Order(int, int, int, double, int, int, int, double)}
     */
    @Test
    void testConstructor2() {
        Order actualOrder = new Order(1, 1, 10, 10.0d, 1, 1, 1, 10.0d);

        assertEquals(1, actualOrder.getAddressId());
        assertTrue(actualOrder.isStatus());
        assertFalse(actualOrder.isRefunding());
        assertEquals(1, actualOrder.getUserId());
        assertEquals(10.0d, actualOrder.getUnitPrice());
        assertEquals("unpaid", actualOrder.getState());
        assertEquals(1, actualOrder.getShopId());
        assertEquals(10, actualOrder.getNumber());
        assertEquals(10.0d, actualOrder.getMinusAmount());
        assertEquals(1, actualOrder.getGroup());
        assertEquals(1, actualOrder.getGoodsId());
    }

    /**
     * Method under test: {@link Order#getLevel()}
     */
    @Test
    void testGetLevel2() {
        Order order = new Order(1, 1, 10, 10.0d, 1, 1, 1, 10.0d);
        order.setLevel(1);
        assertEquals(1, order.getLevel());
    }

    /**
     * Method under test: {@link Order#getAddressId()}
     */
    @Test
    void testGetAddressId() {
        assertEquals(1, (new Order(1, 1, 10, 10.0d, 1, 1, 1, 10.0d)).getAddressId());
    }

    /**
     * Method under test: {@link Order#setAddressId(int)}
     */
    @Test
    void testSetAddressId() {
        Order order = new Order(1, 1, 10, 10.0d, 1, 1, 1, 10.0d);
        order.setAddressId(1);
        assertEquals(1, order.getAddressId());
    }
}

