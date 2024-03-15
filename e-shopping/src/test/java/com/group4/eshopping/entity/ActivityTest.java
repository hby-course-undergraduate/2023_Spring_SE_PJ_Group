package com.group4.eshopping.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class ActivityTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link Activity}
     *   <li>{@link Activity#setAmountArrive(double)}
     *   <li>{@link Activity#setAmountMinus(double)}
     *   <li>{@link Activity#setEndTime(LocalDateTime)}
     *   <li>{@link Activity#setId(int)}
     *   <li>{@link Activity#setName(String)}
     *   <li>{@link Activity#setRegisterFund(double)}
     *   <li>{@link Activity#setSellAmount(double)}
     *   <li>{@link Activity#setSellNumber(int)}
     *   <li>{@link Activity#setStartTime(LocalDateTime)}
     *   <li>{@link Activity#setState(int)}
     *   <li>{@link Activity#setTags(String)}
     *   <li>{@link Activity#getAmountArrive()}
     *   <li>{@link Activity#getAmountMinus()}
     *   <li>{@link Activity#getEndTime()}
     *   <li>{@link Activity#getId()}
     *   <li>{@link Activity#getName()}
     *   <li>{@link Activity#getRegisterFund()}
     *   <li>{@link Activity#getSellAmount()}
     *   <li>{@link Activity#getSellNumber()}
     *   <li>{@link Activity#getStartTime()}
     *   <li>{@link Activity#getState()}
     *   <li>{@link Activity#getTags()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Activity actualActivity = new Activity();
        actualActivity.setAmountArrive(10.0d);
        actualActivity.setAmountMinus(10.0d);
        LocalDateTime endTime = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualActivity.setEndTime(endTime);
        actualActivity.setId(1);
        actualActivity.setName("Name");
        actualActivity.setRegisterFund(10.0d);
        actualActivity.setSellAmount(10.0d);
        actualActivity.setSellNumber(10);
        LocalDateTime startTime = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualActivity.setStartTime(startTime);
        actualActivity.setState(1);
        actualActivity.setTags("Tags");
        assertEquals(10.0d, actualActivity.getAmountArrive());
        assertEquals(10.0d, actualActivity.getAmountMinus());
        assertSame(endTime, actualActivity.getEndTime());
        assertEquals(1, actualActivity.getId());
        assertEquals("Name", actualActivity.getName());
        assertEquals(10.0d, actualActivity.getRegisterFund());
        assertEquals(10.0d, actualActivity.getSellAmount());
        assertEquals(10, actualActivity.getSellNumber());
        assertSame(startTime, actualActivity.getStartTime());
        assertEquals(1, actualActivity.getState());
        assertEquals("Tags", actualActivity.getTags());
    }
}

