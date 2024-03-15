package com.group4.eshopping.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DeliveryAddressTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link DeliveryAddress}
     *   <li>{@link DeliveryAddress#setAddress(String)}
     *   <li>{@link DeliveryAddress#setConsignee(String)}
     *   <li>{@link DeliveryAddress#setConsigneePhone(String)}
     *   <li>{@link DeliveryAddress#setId(int)}
     *   <li>{@link DeliveryAddress#setState(boolean)}
     *   <li>{@link DeliveryAddress#setUserId(int)}
     *   <li>{@link DeliveryAddress#getAddress()}
     *   <li>{@link DeliveryAddress#getConsignee()}
     *   <li>{@link DeliveryAddress#getConsigneePhone()}
     *   <li>{@link DeliveryAddress#getId()}
     *   <li>{@link DeliveryAddress#getUserId()}
     *   <li>{@link DeliveryAddress#isState()}
     * </ul>
     */
    @Test
    void testConstructor() {
        DeliveryAddress actualDeliveryAddress = new DeliveryAddress();
        actualDeliveryAddress.setAddress("42 Main St");
        actualDeliveryAddress.setConsignee("Consignee");
        actualDeliveryAddress.setConsigneePhone("6625550144");
        actualDeliveryAddress.setId(1);
        actualDeliveryAddress.setState(true);
        actualDeliveryAddress.setUserId(1);
        assertEquals("42 Main St", actualDeliveryAddress.getAddress());
        assertEquals("Consignee", actualDeliveryAddress.getConsignee());
        assertEquals("6625550144", actualDeliveryAddress.getConsigneePhone());
        assertEquals(1, actualDeliveryAddress.getId());
        assertEquals(1, actualDeliveryAddress.getUserId());
        assertTrue(actualDeliveryAddress.isState());
    }
}

