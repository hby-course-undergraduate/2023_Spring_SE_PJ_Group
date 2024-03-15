package com.group4.eshopping.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class OrderGroupDiscountsTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link OrderGroupDiscounts#OrderGroupDiscounts()}
     *   <li>{@link OrderGroupDiscounts#setDiscounts(String)}
     *   <li>{@link OrderGroupDiscounts#setGroup(int)}
     *   <li>{@link OrderGroupDiscounts#setId(int)}
     *   <li>{@link OrderGroupDiscounts#getDiscounts()}
     *   <li>{@link OrderGroupDiscounts#getGroup()}
     *   <li>{@link OrderGroupDiscounts#getId()}
     * </ul>
     */
    @Test
    void testConstructor() {
        OrderGroupDiscounts actualOrderGroupDiscounts = new OrderGroupDiscounts();
        actualOrderGroupDiscounts.setDiscounts("3");
        actualOrderGroupDiscounts.setGroup(1);
        actualOrderGroupDiscounts.setId(1);
        assertEquals("3", actualOrderGroupDiscounts.getDiscounts());
        assertEquals(1, actualOrderGroupDiscounts.getGroup());
        assertEquals(1, actualOrderGroupDiscounts.getId());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link OrderGroupDiscounts#OrderGroupDiscounts(int, String)}
     *   <li>{@link OrderGroupDiscounts#setDiscounts(String)}
     *   <li>{@link OrderGroupDiscounts#setGroup(int)}
     *   <li>{@link OrderGroupDiscounts#setId(int)}
     *   <li>{@link OrderGroupDiscounts#getDiscounts()}
     *   <li>{@link OrderGroupDiscounts#getGroup()}
     *   <li>{@link OrderGroupDiscounts#getId()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        OrderGroupDiscounts actualOrderGroupDiscounts = new OrderGroupDiscounts(1, "Str");
        actualOrderGroupDiscounts.setDiscounts("3");
        actualOrderGroupDiscounts.setGroup(1);
        actualOrderGroupDiscounts.setId(1);
        assertEquals("3", actualOrderGroupDiscounts.getDiscounts());
        assertEquals(1, actualOrderGroupDiscounts.getGroup());
        assertEquals(1, actualOrderGroupDiscounts.getId());
    }
}

