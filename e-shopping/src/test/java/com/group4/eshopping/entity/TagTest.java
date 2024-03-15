package com.group4.eshopping.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TagTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Tag#Tag()}
     *   <li>{@link Tag#setTagId(int)}
     *   <li>{@link Tag#setTagName(String)}
     *   <li>{@link Tag#getTagId()}
     *   <li>{@link Tag#getTagName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Tag actualTag = new Tag();
        actualTag.setTagId(1);
        actualTag.setTagName("Tag Name");
        assertEquals(1, actualTag.getTagId());
        assertEquals("Tag Name", actualTag.getTagName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Tag#Tag(String)}
     *   <li>{@link Tag#setTagId(int)}
     *   <li>{@link Tag#setTagName(String)}
     *   <li>{@link Tag#getTagId()}
     *   <li>{@link Tag#getTagName()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Tag actualTag = new Tag("Tag Name");
        actualTag.setTagId(1);
        actualTag.setTagName("Tag Name");
        assertEquals(1, actualTag.getTagId());
        assertEquals("Tag Name", actualTag.getTagName());
    }
}

