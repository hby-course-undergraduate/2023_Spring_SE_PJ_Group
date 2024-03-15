package com.group4.eshopping.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ShopTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link Shop}
     *   <li>{@link Shop#setActivityId(int)}
     *   <li>{@link Shop#setAddress(String)}
     *   <li>{@link Shop#setFund(double)}
     *   <li>{@link Shop#setIdCard(String)}
     *   <li>{@link Shop#setIntroduction(String)}
     *   <li>{@link Shop#setShopName(String)}
     *   <li>{@link Shop#setTag(String)}
     *   <li>{@link Shop#setUserId(int)}
     *   <li>{@link Shop#setApproved()}
     *   <li>{@link Shop#setNotApproved()}
     *   <li>{@link Shop#getActivityId()}
     *   <li>{@link Shop#getAddress()}
     *   <li>{@link Shop#getFund()}
     *   <li>{@link Shop#getIdCard()}
     *   <li>{@link Shop#getIntroduction()}
     *   <li>{@link Shop#getRegisterDate()}
     *   <li>{@link Shop#getShopId()}
     *   <li>{@link Shop#getShopName()}
     *   <li>{@link Shop#getState()}
     *   <li>{@link Shop#getTag()}
     *   <li>{@link Shop#getUserId()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Shop actualShop = new Shop();
        actualShop.setActivityId(1);
        actualShop.setAddress("42 Main St");
        actualShop.setFund(10.0d);
        actualShop.setIdCard("Id Card");
        actualShop.setIntroduction("Introduction");
        actualShop.setShopName("Shop Name");
        actualShop.setTag("Tag");
        actualShop.setUserId(1);
        actualShop.setApproved();
        actualShop.setNotApproved();
        assertEquals(1, actualShop.getActivityId());
        assertEquals("42 Main St", actualShop.getAddress());
        assertEquals(10.0d, actualShop.getFund());
        assertEquals("Id Card", actualShop.getIdCard());
        assertEquals("Introduction", actualShop.getIntroduction());
        assertNull(actualShop.getRegisterDate());
        assertEquals(0, actualShop.getShopId());
        assertEquals("Shop Name", actualShop.getShopName());
        assertEquals(2, actualShop.getState());
        assertEquals("Tag", actualShop.getTag());
        assertEquals(1, actualShop.getUserId());
    }
}

