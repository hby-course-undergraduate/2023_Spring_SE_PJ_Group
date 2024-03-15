package com.group4.eshopping.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

class GoodsPicTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link GoodsPic#GoodsPic()}
     *   <li>{@link GoodsPic#setBelongTo(int)}
     *   <li>{@link GoodsPic#setGoodsId(int)}
     *   <li>{@link GoodsPic#setId(int)}
     *   <li>{@link GoodsPic#setPic(byte[])}
     *   <li>{@link GoodsPic#setState(int)}
     *   <li>{@link GoodsPic#setAbandon()}
     *   <li>{@link GoodsPic#setApproved()}
     *   <li>{@link GoodsPic#getBelongTo()}
     *   <li>{@link GoodsPic#getGoodsId()}
     *   <li>{@link GoodsPic#getId()}
     *   <li>{@link GoodsPic#getPic()}
     *   <li>{@link GoodsPic#getState()}
     * </ul>
     */
    @Test
    void testConstructor() throws UnsupportedEncodingException {
        GoodsPic actualGoodsPic = new GoodsPic();
        actualGoodsPic.setBelongTo(1);
        actualGoodsPic.setGoodsId(1);
        actualGoodsPic.setId(1);
        byte[] pic = "AXAXAXAX".getBytes(StandardCharsets.UTF_8);
        actualGoodsPic.setPic(pic);
        actualGoodsPic.setState(1);
        actualGoodsPic.setAbandon();
        actualGoodsPic.setApproved();
        assertEquals(1, actualGoodsPic.getBelongTo());
        assertEquals(1, actualGoodsPic.getGoodsId());
        assertEquals(1, actualGoodsPic.getId());
        assertSame(pic, actualGoodsPic.getPic());
        assertEquals(1, actualGoodsPic.getState());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link GoodsPic#GoodsPic(int, int, byte[])}
     *   <li>{@link GoodsPic#setBelongTo(int)}
     *   <li>{@link GoodsPic#setGoodsId(int)}
     *   <li>{@link GoodsPic#setId(int)}
     *   <li>{@link GoodsPic#setPic(byte[])}
     *   <li>{@link GoodsPic#setState(int)}
     *   <li>{@link GoodsPic#setAbandon()}
     *   <li>{@link GoodsPic#setApproved()}
     *   <li>{@link GoodsPic#getBelongTo()}
     *   <li>{@link GoodsPic#getGoodsId()}
     *   <li>{@link GoodsPic#getId()}
     *   <li>{@link GoodsPic#getPic()}
     *   <li>{@link GoodsPic#getState()}
     * </ul>
     */
    @Test
    void testConstructor2(){
        GoodsPic actualGoodsPic = new GoodsPic(1, 1, "AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        actualGoodsPic.setBelongTo(1);
        actualGoodsPic.setGoodsId(1);
        actualGoodsPic.setId(1);
        byte[] pic = "AXAXAXAX".getBytes(StandardCharsets.UTF_8);
        actualGoodsPic.setPic(pic);
        actualGoodsPic.setState(1);
        actualGoodsPic.setAbandon();
        actualGoodsPic.setApproved();
        assertEquals(1, actualGoodsPic.getBelongTo());
        assertEquals(1, actualGoodsPic.getGoodsId());
        assertEquals(1, actualGoodsPic.getId());
        assertSame(pic, actualGoodsPic.getPic());
        assertEquals(1, actualGoodsPic.getState());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link GoodsPic#GoodsPic(int, byte[])}
     *   <li>{@link GoodsPic#setBelongTo(int)}
     *   <li>{@link GoodsPic#setGoodsId(int)}
     *   <li>{@link GoodsPic#setId(int)}
     *   <li>{@link GoodsPic#setPic(byte[])}
     *   <li>{@link GoodsPic#setState(int)}
     *   <li>{@link GoodsPic#setAbandon()}
     *   <li>{@link GoodsPic#setApproved()}
     *   <li>{@link GoodsPic#getBelongTo()}
     *   <li>{@link GoodsPic#getGoodsId()}
     *   <li>{@link GoodsPic#getId()}
     *   <li>{@link GoodsPic#getPic()}
     *   <li>{@link GoodsPic#getState()}
     * </ul>
     */
    @Test
    void testConstructor3(){
        GoodsPic actualGoodsPic = new GoodsPic(1, "AXAXAXAX".getBytes(StandardCharsets.UTF_8));
        actualGoodsPic.setBelongTo(1);
        actualGoodsPic.setGoodsId(1);
        actualGoodsPic.setId(1);
        byte[] pic = "AXAXAXAX".getBytes(StandardCharsets.UTF_8);
        actualGoodsPic.setPic(pic);
        actualGoodsPic.setState(1);
        actualGoodsPic.setAbandon();
        actualGoodsPic.setApproved();
        assertEquals(1, actualGoodsPic.getBelongTo());
        assertEquals(1, actualGoodsPic.getGoodsId());
        assertEquals(1, actualGoodsPic.getId());
        assertSame(pic, actualGoodsPic.getPic());
        assertEquals(1, actualGoodsPic.getState());
    }
}

