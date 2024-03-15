package com.group4.eshopping.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ShopActivityTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ShopActivity#ShopActivity()}
     *   <li>{@link ShopActivity#setActivityId(int)}
     *   <li>{@link ShopActivity#setId(int)}
     *   <li>{@link ShopActivity#setIsApproved(int)}
     *   <li>{@link ShopActivity#setShopId(int)}
     *   <li>{@link ShopActivity#setState(int)}
     *   <li>{@link ShopActivity#getActivityId()}
     *   <li>{@link ShopActivity#getId()}
     *   <li>{@link ShopActivity#getIsApproved()}
     *   <li>{@link ShopActivity#getShopId()}
     *   <li>{@link ShopActivity#getState()}
     * </ul>
     */
    @Test
    void testConstructor() {
        ShopActivity actualShopActivity = new ShopActivity();
        actualShopActivity.setActivityId(1);
        actualShopActivity.setId(1);
        actualShopActivity.setIsApproved(1);
        actualShopActivity.setShopId(1);
        actualShopActivity.setState(1);
        assertEquals(1, actualShopActivity.getActivityId());
        assertEquals(1, actualShopActivity.getId());
        assertEquals(1, actualShopActivity.getIsApproved());
        assertEquals(1, actualShopActivity.getShopId());
        assertEquals(1, actualShopActivity.getState());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ShopActivity#ShopActivity(int, int)}
     *   <li>{@link ShopActivity#setActivityId(int)}
     *   <li>{@link ShopActivity#setId(int)}
     *   <li>{@link ShopActivity#setIsApproved(int)}
     *   <li>{@link ShopActivity#setShopId(int)}
     *   <li>{@link ShopActivity#setState(int)}
     *   <li>{@link ShopActivity#getActivityId()}
     *   <li>{@link ShopActivity#getId()}
     *   <li>{@link ShopActivity#getIsApproved()}
     *   <li>{@link ShopActivity#getShopId()}
     *   <li>{@link ShopActivity#getState()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        ShopActivity actualShopActivity = new ShopActivity(1, 1);
        actualShopActivity.setActivityId(1);
        actualShopActivity.setId(1);
        actualShopActivity.setIsApproved(1);
        actualShopActivity.setShopId(1);
        actualShopActivity.setState(1);
        assertEquals(1, actualShopActivity.getActivityId());
        assertEquals(1, actualShopActivity.getId());
        assertEquals(1, actualShopActivity.getIsApproved());
        assertEquals(1, actualShopActivity.getShopId());
        assertEquals(1, actualShopActivity.getState());
    }
}

