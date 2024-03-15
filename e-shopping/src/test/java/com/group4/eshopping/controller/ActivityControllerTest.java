package com.group4.eshopping.controller;

import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.Activity;
import com.group4.eshopping.exception.CheckException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.CoyoteWriter;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ActivityControllerTest {
    /**
     * Method under test: {@link ActivityController#organizeActivity(Activity, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testOrganizeActivity5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ActivityController activityController = new ActivityController();
        Activity activity = mock(Activity.class);
        doNothing().when(activity).setAmountArrive(anyDouble());
        doNothing().when(activity).setAmountMinus(anyDouble());
        doNothing().when(activity).setEndTime(Mockito.<LocalDateTime>any());
        doNothing().when(activity).setFund(anyDouble());
        doNothing().when(activity).setId(anyInt());
        doNothing().when(activity).setName(Mockito.<String>any());
        doNothing().when(activity).setRegisterFund(anyDouble());
        doNothing().when(activity).setRemainFund(anyDouble());
        doNothing().when(activity).setSellAmount(anyDouble());
        doNothing().when(activity).setSellNumber(anyInt());
        doNothing().when(activity).setStartTime(Mockito.<LocalDateTime>any());
        doNothing().when(activity).setState(anyInt());
        doNothing().when(activity).setTags(Mockito.<String>any());
        activity.setAmountArrive(10.0d);
        activity.setAmountMinus(10.0d);
        activity.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        activity.setFund(10.0d);
        activity.setId(1);
        activity.setName("Name");
        activity.setRegisterFund(10.0d);
        activity.setRemainFund(10.0d);
        activity.setSellAmount(10.0d);
        activity.setSellNumber(10);
        activity.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        activity.setState(1);
        activity.setTags("Tags");
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        activityController.organizeActivity(activity, httpServletRequest, httpServletResponse);
        verify(activity).setAmountArrive(anyDouble());
        verify(activity).setAmountMinus(anyDouble());
        verify(activity).setEndTime(Mockito.<LocalDateTime>any());
        verify(activity).setFund(anyDouble());
        verify(activity).setId(anyInt());
        verify(activity).setName(Mockito.<String>any());
        verify(activity).setRegisterFund(anyDouble());
        verify(activity).setRemainFund(anyDouble());
        verify(activity).setSellAmount(anyDouble());
        verify(activity).setSellNumber(anyInt());
        verify(activity).setStartTime(Mockito.<LocalDateTime>any());
        verify(activity).setState(anyInt());
        verify(activity).setTags(Mockito.<String>any());
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link ActivityController#organizeActivity(Activity, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testOrganizeActivity6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ActivityController activityController = new ActivityController();
        Activity activity = mock(Activity.class);
        doNothing().when(activity).setAmountArrive(anyDouble());
        doNothing().when(activity).setAmountMinus(anyDouble());
        doNothing().when(activity).setEndTime(Mockito.<LocalDateTime>any());
        doNothing().when(activity).setFund(anyDouble());
        doNothing().when(activity).setId(anyInt());
        doNothing().when(activity).setName(Mockito.<String>any());
        doNothing().when(activity).setRegisterFund(anyDouble());
        doNothing().when(activity).setRemainFund(anyDouble());
        doNothing().when(activity).setSellAmount(anyDouble());
        doNothing().when(activity).setSellNumber(anyInt());
        doNothing().when(activity).setStartTime(Mockito.<LocalDateTime>any());
        doNothing().when(activity).setState(anyInt());
        doNothing().when(activity).setTags(Mockito.<String>any());
        activity.setAmountArrive(10.0d);
        activity.setAmountMinus(10.0d);
        activity.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        activity.setFund(10.0d);
        activity.setId(1);
        activity.setName("Name");
        activity.setRegisterFund(10.0d);
        activity.setRemainFund(10.0d);
        activity.setSellAmount(10.0d);
        activity.setSellNumber(10);
        activity.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        activity.setState(1);
        activity.setTags("Tags");
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        CoyoteWriter coyoteWriter = mock(CoyoteWriter.class);
        doNothing().when(coyoteWriter).close();
        doNothing().when(coyoteWriter).println(Mockito.<String>any());
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(coyoteWriter);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        activityController.organizeActivity(activity, httpServletRequest, httpServletResponse);
        verify(activity).setAmountArrive(anyDouble());
        verify(activity).setAmountMinus(anyDouble());
        verify(activity).setEndTime(Mockito.<LocalDateTime>any());
        verify(activity).setFund(anyDouble());
        verify(activity).setId(anyInt());
        verify(activity).setName(Mockito.<String>any());
        verify(activity).setRegisterFund(anyDouble());
        verify(activity).setRemainFund(anyDouble());
        verify(activity).setSellAmount(anyDouble());
        verify(activity).setSellNumber(anyInt());
        verify(activity).setStartTime(Mockito.<LocalDateTime>any());
        verify(activity).setState(anyInt());
        verify(activity).setTags(Mockito.<String>any());
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ActivityController#applyActivity(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testApplyActivity4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ActivityController activityController = new ActivityController();
        JSONObject jsonObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = new HttpServletRequestWrapper(new Request(new Connector()));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        activityController.applyActivity(jsonObject, httpServletRequest, httpServletResponse);
        verify(httpServletResponse).setStatus(anyInt());
    }

    /**
     * Method under test: {@link ActivityController#checkApplyingActivity(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCheckApplyingActivity5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ActivityController activityController = new ActivityController();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        activityController.checkApplyingActivity(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link ActivityController#checkApplyingActivity(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCheckApplyingActivity6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ActivityController activityController = new ActivityController();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        CoyoteWriter coyoteWriter = mock(CoyoteWriter.class);
        doNothing().when(coyoteWriter).close();
        doNothing().when(coyoteWriter).println(Mockito.<String>any());
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(coyoteWriter);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        activityController.checkApplyingActivity(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ActivityController#approveActivity(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testApproveActivity4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ActivityController activityController = new ActivityController();
        JSONObject jsonObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = new HttpServletRequestWrapper(new Request(new Connector()));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        activityController.approveActivity(jsonObject, httpServletRequest, httpServletResponse);
        verify(httpServletResponse).setStatus(anyInt());
    }

    /**
     * Method under test: {@link ActivityController#getActivity(HttpServletResponse)}
     */
    @Test
    void testGetActivity2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ActivityController activityController = new ActivityController();
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        activityController.getActivity(httpServletResponse);
        verify(httpServletResponse).setStatus(anyInt());
    }

    /**
     * Method under test: {@link ActivityController#getActiveActivity(HttpServletResponse)}
     */
    @Test
    void testGetActiveActivity2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ActivityController activityController = new ActivityController();
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        activityController.getActiveActivity(httpServletResponse);
        verify(httpServletResponse).setStatus(anyInt());
    }

    /**
     * Method under test: {@link ActivityController#getGoodsByActivity(JSONObject, HttpServletResponse)}
     */
    @Test
    void testGetGoodsByActivity3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ActivityController activityController = new ActivityController();
        JSONObject jsonObject = new JSONObject();
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        activityController.getGoodsByActivity(jsonObject, httpServletResponse);
        verify(httpServletResponse).setStatus(anyInt());
    }
}

