package com.group4.eshopping.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class TransferAccountTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link TransferAccount#TransferAccount()}
     *   <li>{@link TransferAccount#setAmount(double)}
     *   <li>{@link TransferAccount#setFromId(int)}
     *   <li>{@link TransferAccount#setFromType(String)}
     *   <li>{@link TransferAccount#setToId(int)}
     *   <li>{@link TransferAccount#setToType(String)}
     *   <li>{@link TransferAccount#setType(String)}
     *   <li>{@link TransferAccount#getAmount()}
     *   <li>{@link TransferAccount#getCreateAt()}
     *   <li>{@link TransferAccount#getFromId()}
     *   <li>{@link TransferAccount#getFromType()}
     *   <li>{@link TransferAccount#getToId()}
     *   <li>{@link TransferAccount#getToType()}
     *   <li>{@link TransferAccount#getType()}
     * </ul>
     */
    @Test
    void testConstructor() {
        TransferAccount actualTransferAccount = new TransferAccount();
        actualTransferAccount.setAmount(10.0d);
        actualTransferAccount.setFromId(1);
        actualTransferAccount.setFromType("jane.doe@example.org");
        actualTransferAccount.setToId(1);
        actualTransferAccount.setToType("To Type");
        actualTransferAccount.setType("Type");
        assertEquals(10.0d, actualTransferAccount.getAmount());
        assertNull(actualTransferAccount.getCreateAt());
        assertEquals(1, actualTransferAccount.getFromId());
        assertEquals("jane.doe@example.org", actualTransferAccount.getFromType());
        assertEquals(1, actualTransferAccount.getToId());
        assertEquals("To Type", actualTransferAccount.getToType());
        assertEquals("Type", actualTransferAccount.getType());
    }

    /**
     * Method under test: {@link TransferAccount#TransferAccount(int, int, double)}
     */
    @Test
    void testConstructor2() {
        TransferAccount actualTransferAccount = new TransferAccount(1, 1, 10.0d);

        assertEquals(10.0d, actualTransferAccount.getAmount());
        assertEquals(1, actualTransferAccount.getToId());
        assertEquals(1, actualTransferAccount.getFromId());
    }

    /**
     * Method under test: {@link TransferAccount#TransferAccount(int, String, int, String, double, String)}
     */
    @Test
    void testConstructor3() {
        TransferAccount actualTransferAccount = new TransferAccount(1, "jane.doe@example.org", 1, "To Type", 10.0d,
                "Transfer Type");

        assertEquals(10.0d, actualTransferAccount.getAmount());
        assertEquals("Transfer Type", actualTransferAccount.getType());
        assertEquals("To Type", actualTransferAccount.getToType());
        assertEquals(1, actualTransferAccount.getToId());
        assertEquals("jane.doe@example.org", actualTransferAccount.getFromType());
        assertEquals(1, actualTransferAccount.getFromId());
    }
}

