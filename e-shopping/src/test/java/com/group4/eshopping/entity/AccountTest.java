package com.group4.eshopping.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AccountTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Account#Account()}
     *   <li>{@link Account#setAccountId(int)}
     *   <li>{@link Account#setAmount(double)}
     *   <li>{@link Account#setState(boolean)}
     *   <li>{@link Account#setType(String)}
     *   <li>{@link Account#setUserId(int)}
     *   <li>{@link Account#getAccountId()}
     *   <li>{@link Account#getAmount()}
     *   <li>{@link Account#getType()}
     *   <li>{@link Account#getUserId()}
     *   <li>{@link Account#isState()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Account actualAccount = new Account();
        actualAccount.setAccountId(1);
        actualAccount.setAmount(10.0d);
        actualAccount.setState(true);
        actualAccount.setType("Type");
        actualAccount.setUserId(1);
        assertEquals(1, actualAccount.getAccountId());
        assertEquals(10.0d, actualAccount.getAmount());
        assertEquals("Type", actualAccount.getType());
        assertEquals(1, actualAccount.getUserId());
        assertTrue(actualAccount.isState());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Account#Account(int, String)}
     *   <li>{@link Account#setAccountId(int)}
     *   <li>{@link Account#setAmount(double)}
     *   <li>{@link Account#setState(boolean)}
     *   <li>{@link Account#setType(String)}
     *   <li>{@link Account#setUserId(int)}
     *   <li>{@link Account#getAccountId()}
     *   <li>{@link Account#getAmount()}
     *   <li>{@link Account#getType()}
     *   <li>{@link Account#getUserId()}
     *   <li>{@link Account#isState()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Account actualAccount = new Account(1, "Type");
        actualAccount.setAccountId(1);
        actualAccount.setAmount(10.0d);
        actualAccount.setState(true);
        actualAccount.setType("Type");
        actualAccount.setUserId(1);
        assertEquals(1, actualAccount.getAccountId());
        assertEquals(10.0d, actualAccount.getAmount());
        assertEquals("Type", actualAccount.getType());
        assertEquals(1, actualAccount.getUserId());
        assertTrue(actualAccount.isState());
    }
}

