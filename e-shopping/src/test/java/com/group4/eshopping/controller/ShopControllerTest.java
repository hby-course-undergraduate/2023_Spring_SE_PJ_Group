package com.group4.eshopping.controller;

import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.Shop;
import com.group4.eshopping.exception.CheckException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.CoyoteWriter;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ShopControllerTest {
    /**
     * Method under test: {@link ShopController#applyForShop(Shop, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testApplyForShop5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
        Shop shop = mock(Shop.class);
        doNothing().when(shop).setActivityId(anyInt());
        doNothing().when(shop).setAddress(Mockito.<String>any());
        doNothing().when(shop).setFund(anyDouble());
        doNothing().when(shop).setIdCard(Mockito.<String>any());
        doNothing().when(shop).setIntroduction(Mockito.<String>any());
        doNothing().when(shop).setShopName(Mockito.<String>any());
        doNothing().when(shop).setTag(Mockito.<String>any());
        doNothing().when(shop).setUserId(anyInt());
        shop.setActivityId(1);
        shop.setAddress("42 Main St");
        shop.setFund(10.0d);
        shop.setIdCard("Id Card");
        shop.setIntroduction("Introduction");
        shop.setShopName("Shop Name");
        shop.setTag("Tag");
        shop.setUserId(1);
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        shopController.applyForShop(shop, httpServletRequest, httpServletResponse);
        verify(shop).setActivityId(anyInt());
        verify(shop).setAddress(Mockito.<String>any());
        verify(shop).setFund(anyDouble());
        verify(shop).setIdCard(Mockito.<String>any());
        verify(shop).setIntroduction(Mockito.<String>any());
        verify(shop).setShopName(Mockito.<String>any());
        verify(shop).setTag(Mockito.<String>any());
        verify(shop).setUserId(anyInt());
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link ShopController#applyForShop(Shop, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testApplyForShop6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
        Shop shop = mock(Shop.class);
        doNothing().when(shop).setActivityId(anyInt());
        doNothing().when(shop).setAddress(Mockito.<String>any());
        doNothing().when(shop).setFund(anyDouble());
        doNothing().when(shop).setIdCard(Mockito.<String>any());
        doNothing().when(shop).setIntroduction(Mockito.<String>any());
        doNothing().when(shop).setShopName(Mockito.<String>any());
        doNothing().when(shop).setTag(Mockito.<String>any());
        doNothing().when(shop).setUserId(anyInt());
        shop.setActivityId(1);
        shop.setAddress("42 Main St");
        shop.setFund(10.0d);
        shop.setIdCard("Id Card");
        shop.setIntroduction("Introduction");
        shop.setShopName("Shop Name");
        shop.setTag("Tag");
        shop.setUserId(1);
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
        shopController.applyForShop(shop, httpServletRequest, httpServletResponse);
        verify(shop).setActivityId(anyInt());
        verify(shop).setAddress(Mockito.<String>any());
        verify(shop).setFund(anyDouble());
        verify(shop).setIdCard(Mockito.<String>any());
        verify(shop).setIntroduction(Mockito.<String>any());
        verify(shop).setShopName(Mockito.<String>any());
        verify(shop).setTag(Mockito.<String>any());
        verify(shop).setUserId(anyInt());
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }
    /**
     * Method under test: {@link ShopController#displayAllShop(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testDisplayAllShop5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        shopController.displayAllShop(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link ShopController#displayAllShop(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testDisplayAllShop6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
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
        shopController.displayAllShop(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ShopController#approveForShop(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testApproveForShop5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
        JSONObject request = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        shopController.approveForShop(request, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link ShopController#approveForShop(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testApproveForShop6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
        JSONObject request = new JSONObject();
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
        shopController.approveForShop(request, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ShopController#displayApprovedShop(HttpServletResponse)}
     */
    @Test
    void testDisplayApprovedShop2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        shopController.displayApprovedShop(httpServletResponse);
        verify(httpServletResponse).setStatus(anyInt());
    }

    /**
     * Method under test: {@link ShopController#rechargeShopAccount(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRechargeShopAccount4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
        JSONObject amountObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = new HttpServletRequestWrapper(new Request(new Connector()));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        shopController.rechargeShopAccount(amountObject, httpServletRequest, httpServletResponse);
        verify(httpServletResponse).setStatus(anyInt());
    }

    /**
     * Method under test: {@link ShopController#changeShop(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testChangeShop5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
        JSONObject changeObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        shopController.changeShop(changeObject, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link ShopController#changeShop(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testChangeShop6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
        JSONObject changeObject = new JSONObject();
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
        shopController.changeShop(changeObject, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ShopController#getShopAccount(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testGetShopAccount5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        shopController.getShopAccount(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link ShopController#getShopAccount(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testGetShopAccount6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
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
        shopController.getShopAccount(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ShopController#getUserInfo(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testGetUserInfo5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        shopController.getUserInfo(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link ShopController#getUserInfo(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testGetUserInfo6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
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
        shopController.getUserInfo(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ShopController#cancelShop(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCancelShop6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
        JSONObject request = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        shopController.cancelShop(request, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link ShopController#cancelShop(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCancelShop7() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
        JSONObject request = new JSONObject();
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
        shopController.cancelShop(request, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ShopController#approveForShopCancel(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testApproveForShopCancel5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
        JSONObject request = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        shopController.approveForShopCancel(request, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link ShopController#approveForShopCancel(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testApproveForShopCancel6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
        JSONObject request = new JSONObject();
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
        shopController.approveForShopCancel(request, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ShopController#displayAllShopCancel(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testDisplayAllShopCancel5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        shopController.displayAllShopCancel(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link ShopController#displayAllShopCancel(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testDisplayAllShopCancel6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ShopController shopController = new ShopController();
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
        shopController.displayAllShopCancel(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }
}

