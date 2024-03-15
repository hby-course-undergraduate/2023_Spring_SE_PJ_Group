package com.group4.eshopping.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UserTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link User}
     *   <li>{@link User#setAdmin(Boolean)}
     *   <li>{@link User#setEmail(String)}
     *   <li>{@link User#setIdCard(String)}
     *   <li>{@link User#setPassword(String)}
     *   <li>{@link User#setPhone(String)}
     *   <li>{@link User#setUsername(String)}
     *   <li>{@link User#getAdmin()}
     *   <li>{@link User#getEmail()}
     *   <li>{@link User#getId()}
     *   <li>{@link User#getIdCard()}
     *   <li>{@link User#getPassword()}
     *   <li>{@link User#getPhone()}
     *   <li>{@link User#getUsername()}
     * </ul>
     */
    @Test
    void testConstructor() {
        User actualUser = new User();
        actualUser.setAdmin(true);
        actualUser.setEmail("jane.doe@example.org");
        actualUser.setIdCard("Id Card");
        actualUser.setPassword("iloveyou");
        actualUser.setPhone("6625550144");
        actualUser.setUsername("janedoe");
        assertTrue(actualUser.getAdmin());
        assertEquals("jane.doe@example.org", actualUser.getEmail());
        assertEquals(0, actualUser.getId());
        assertEquals("Id Card", actualUser.getIdCard());
        assertEquals("iloveyou", actualUser.getPassword());
        assertEquals("6625550144", actualUser.getPhone());
        assertEquals("janedoe", actualUser.getUsername());
    }

    /**
     * Method under test: {@link User#getIsSeller()}
     */
    @Test
    void testGetIsSeller() {
        assertFalse((new User()).getIsSeller());
    }

    /**
     * Method under test: {@link User#getIsSeller()}
     */
    @Test
    void testGetIsSeller2() {
        User user = new User();
        user.setAdmin(true);
        user.setEmail("jane.doe@example.org");
        user.setIdCard("Id Card");
        user.setIsSeller(true);
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setUsername("janedoe");
        assertTrue(user.getIsSeller());
    }

    /**
     * Method under test: {@link User#setIsSeller(Boolean)}
     */
    @Test
    void testSetIsSeller() {
        User user = new User();
        user.setIsSeller(true);
        assertTrue(user.getIsSeller());
    }

    /**
     * Method under test: {@link User#getRole()}
     */
    @Test
    void testGetRole() {
        assertEquals("buyer", (new User()).getRole());
    }

    /**
     * Method under test: {@link User#getRole()}
     */
    @Test
    void testGetRole2() {
        User user = new User();
        user.setAdmin(true);
        user.setEmail("jane.doe@example.org");
        user.setIdCard("Id Card");
        user.setIsSeller(true);
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setUsername("janedoe");
        assertEquals("admin", user.getRole());
    }

    /**
     * Method under test: {@link User#getRole()}
     */
    @Test
    void testGetRole3() {
        User user = new User();
        user.setIsSeller(true);
        assertEquals("seller", user.getRole());
    }
}

