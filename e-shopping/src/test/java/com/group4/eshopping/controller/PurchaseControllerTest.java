package com.group4.eshopping.controller;

import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.Cart;
import com.group4.eshopping.entity.DeliveryAddress;
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

class PurchaseControllerTest {
    /**
     * Method under test: {@link PurchaseController#applyDeliveryAddress(DeliveryAddress, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testApplyDeliveryAddress5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        DeliveryAddress deliveryAddress = mock(DeliveryAddress.class);
        doNothing().when(deliveryAddress).setAddress(Mockito.<String>any());
        doNothing().when(deliveryAddress).setConsignee(Mockito.<String>any());
        doNothing().when(deliveryAddress).setConsigneePhone(Mockito.<String>any());
        doNothing().when(deliveryAddress).setId(anyInt());
        doNothing().when(deliveryAddress).setState(anyBoolean());
        doNothing().when(deliveryAddress).setUserId(anyInt());
        deliveryAddress.setAddress("42 Main St");
        deliveryAddress.setConsignee("Consignee");
        deliveryAddress.setConsigneePhone("6625550144");
        deliveryAddress.setId(1);
        deliveryAddress.setState(true);
        deliveryAddress.setUserId(1);
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.applyDeliveryAddress(deliveryAddress, httpServletRequest, httpServletResponse);
        verify(deliveryAddress).setAddress(Mockito.<String>any());
        verify(deliveryAddress).setConsignee(Mockito.<String>any());
        verify(deliveryAddress).setConsigneePhone(Mockito.<String>any());
        verify(deliveryAddress).setId(anyInt());
        verify(deliveryAddress).setState(anyBoolean());
        verify(deliveryAddress).setUserId(anyInt());
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#applyDeliveryAddress(DeliveryAddress, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testApplyDeliveryAddress6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        DeliveryAddress deliveryAddress = mock(DeliveryAddress.class);
        doNothing().when(deliveryAddress).setAddress(Mockito.<String>any());
        doNothing().when(deliveryAddress).setConsignee(Mockito.<String>any());
        doNothing().when(deliveryAddress).setConsigneePhone(Mockito.<String>any());
        doNothing().when(deliveryAddress).setId(anyInt());
        doNothing().when(deliveryAddress).setState(anyBoolean());
        doNothing().when(deliveryAddress).setUserId(anyInt());
        deliveryAddress.setAddress("42 Main St");
        deliveryAddress.setConsignee("Consignee");
        deliveryAddress.setConsigneePhone("6625550144");
        deliveryAddress.setId(1);
        deliveryAddress.setState(true);
        deliveryAddress.setUserId(1);
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
        purchaseController.applyDeliveryAddress(deliveryAddress, httpServletRequest, httpServletResponse);
        verify(deliveryAddress).setAddress(Mockito.<String>any());
        verify(deliveryAddress).setConsignee(Mockito.<String>any());
        verify(deliveryAddress).setConsigneePhone(Mockito.<String>any());
        verify(deliveryAddress).setId(anyInt());
        verify(deliveryAddress).setState(anyBoolean());
        verify(deliveryAddress).setUserId(anyInt());
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PurchaseController#getDeliveryAddress(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testGetDeliveryAddress5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.getDeliveryAddress(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#getDeliveryAddress(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testGetDeliveryAddress6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
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
        purchaseController.getDeliveryAddress(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PurchaseController#cancelDeliveryAddress(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCancelDeliveryAddress4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject requestObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = new HttpServletRequestWrapper(new Request(new Connector()));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.cancelDeliveryAddress(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletResponse).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#changeDeliveryAddress(DeliveryAddress, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testChangeDeliveryAddress5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        DeliveryAddress deliveryAddress = mock(DeliveryAddress.class);
        doNothing().when(deliveryAddress).setAddress(Mockito.<String>any());
        doNothing().when(deliveryAddress).setConsignee(Mockito.<String>any());
        doNothing().when(deliveryAddress).setConsigneePhone(Mockito.<String>any());
        doNothing().when(deliveryAddress).setId(anyInt());
        doNothing().when(deliveryAddress).setState(anyBoolean());
        doNothing().when(deliveryAddress).setUserId(anyInt());
        deliveryAddress.setAddress("42 Main St");
        deliveryAddress.setConsignee("Consignee");
        deliveryAddress.setConsigneePhone("6625550144");
        deliveryAddress.setId(1);
        deliveryAddress.setState(true);
        deliveryAddress.setUserId(1);
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.changeDeliveryAddress(deliveryAddress, httpServletRequest, httpServletResponse);
        verify(deliveryAddress).setAddress(Mockito.<String>any());
        verify(deliveryAddress).setConsignee(Mockito.<String>any());
        verify(deliveryAddress).setConsigneePhone(Mockito.<String>any());
        verify(deliveryAddress).setId(anyInt());
        verify(deliveryAddress).setState(anyBoolean());
        verify(deliveryAddress).setUserId(anyInt());
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#changeDeliveryAddress(DeliveryAddress, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testChangeDeliveryAddress6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        DeliveryAddress deliveryAddress = mock(DeliveryAddress.class);
        doNothing().when(deliveryAddress).setAddress(Mockito.<String>any());
        doNothing().when(deliveryAddress).setConsignee(Mockito.<String>any());
        doNothing().when(deliveryAddress).setConsigneePhone(Mockito.<String>any());
        doNothing().when(deliveryAddress).setId(anyInt());
        doNothing().when(deliveryAddress).setState(anyBoolean());
        doNothing().when(deliveryAddress).setUserId(anyInt());
        deliveryAddress.setAddress("42 Main St");
        deliveryAddress.setConsignee("Consignee");
        deliveryAddress.setConsigneePhone("6625550144");
        deliveryAddress.setId(1);
        deliveryAddress.setState(true);
        deliveryAddress.setUserId(1);
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
        purchaseController.changeDeliveryAddress(deliveryAddress, httpServletRequest, httpServletResponse);
        verify(deliveryAddress).setAddress(Mockito.<String>any());
        verify(deliveryAddress).setConsignee(Mockito.<String>any());
        verify(deliveryAddress).setConsigneePhone(Mockito.<String>any());
        verify(deliveryAddress).setId(anyInt());
        verify(deliveryAddress).setState(anyBoolean());
        verify(deliveryAddress).setUserId(anyInt());
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PurchaseController#addCart(Cart, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testAddCart5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        Cart cart = mock(Cart.class);
        doNothing().when(cart).setGoodsId(anyInt());
        doNothing().when(cart).setId(anyInt());
        doNothing().when(cart).setNumber(anyInt());
        doNothing().when(cart).setUpdateAt(Mockito.<LocalDateTime>any());
        doNothing().when(cart).setUpdateAtAuto();
        doNothing().when(cart).setUserId(anyInt());
        doNothing().when(cart).setValid(anyBoolean());
        cart.setGoodsId(1);
        cart.setId(1);
        cart.setNumber(10);
        cart.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        cart.setUpdateAtAuto();
        cart.setUserId(1);
        cart.setValid(true);
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.addCart(cart, httpServletRequest, httpServletResponse);
        verify(cart).setGoodsId(anyInt());
        verify(cart).setId(anyInt());
        verify(cart).setNumber(anyInt());
        verify(cart).setUpdateAt(Mockito.<LocalDateTime>any());
        verify(cart).setUpdateAtAuto();
        verify(cart).setUserId(anyInt());
        verify(cart).setValid(anyBoolean());
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#addCart(Cart, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testAddCart6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        Cart cart = mock(Cart.class);
        doNothing().when(cart).setGoodsId(anyInt());
        doNothing().when(cart).setId(anyInt());
        doNothing().when(cart).setNumber(anyInt());
        doNothing().when(cart).setUpdateAt(Mockito.<LocalDateTime>any());
        doNothing().when(cart).setUpdateAtAuto();
        doNothing().when(cart).setUserId(anyInt());
        doNothing().when(cart).setValid(anyBoolean());
        cart.setGoodsId(1);
        cart.setId(1);
        cart.setNumber(10);
        cart.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        cart.setUpdateAtAuto();
        cart.setUserId(1);
        cart.setValid(true);
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
        purchaseController.addCart(cart, httpServletRequest, httpServletResponse);
        verify(cart).setGoodsId(anyInt());
        verify(cart).setId(anyInt());
        verify(cart).setNumber(anyInt());
        verify(cart).setUpdateAt(Mockito.<LocalDateTime>any());
        verify(cart).setUpdateAtAuto();
        verify(cart).setUserId(anyInt());
        verify(cart).setValid(anyBoolean());
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PurchaseController#getCart(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testGetCart5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.getCart(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#getCart(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testGetCart6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
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
        purchaseController.getCart(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PurchaseController#deleteCart(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testDeleteCart5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject requestObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.deleteCart(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#deleteCart(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testDeleteCart6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject requestObject = new JSONObject();
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
        purchaseController.deleteCart(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PurchaseController#purchase(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testPurchase5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject jsonObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = new HttpServletRequestWrapper(new Request(new Connector()));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.purchase(jsonObject, httpServletRequest, httpServletResponse);
        verify(httpServletResponse).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#pay(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testPay4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject jsonObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = new HttpServletRequestWrapper(new Request(new Connector()));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.pay(jsonObject, httpServletRequest, httpServletResponse);
        verify(httpServletResponse).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#deliverOrder(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testDeliverOrder4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject jsonObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = new HttpServletRequestWrapper(new Request(new Connector()));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.deliverOrder(jsonObject, httpServletRequest, httpServletResponse);
        verify(httpServletResponse).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#cancelOrder(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCancelOrder5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject requestObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.cancelOrder(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#cancelOrder(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCancelOrder6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject requestObject = new JSONObject();
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
        purchaseController.cancelOrder(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PurchaseController#refundOrder(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRefundOrder5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject requestObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.refundOrder(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#refundOrder(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRefundOrder6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject requestObject = new JSONObject();
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
        purchaseController.refundOrder(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PurchaseController#checkRefundOrder(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCheckRefundOrder5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.checkRefundOrder(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#checkRefundOrder(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCheckRefundOrder6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
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
        purchaseController.checkRefundOrder(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PurchaseController#approveRefundOrder(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testApproveRefundOrder5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject requestObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.approveRefundOrder(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#approveRefundOrder(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testApproveRefundOrder6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject requestObject = new JSONObject();
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
        purchaseController.approveRefundOrder(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PurchaseController#receiptOrder(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testReceiptOrder4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject jsonObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = new HttpServletRequestWrapper(new Request(new Connector()));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.receiptOrder(jsonObject, httpServletRequest, httpServletResponse);
        verify(httpServletResponse).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#deleteOrder(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testDeleteOrder5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject requestObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.deleteOrder(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#deleteOrder(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testDeleteOrder6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject requestObject = new JSONObject();
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
        purchaseController.deleteOrder(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PurchaseController#displayOneOrder(int, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testDisplayOneOrder5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.displayOneOrder(1, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#displayOneOrder(int, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testDisplayOneOrder6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
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
        purchaseController.displayOneOrder(1, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PurchaseController#displayAllOrder(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testDisplayAllOrder6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject requestObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.displayAllOrder(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#displayAllOrder(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testDisplayAllOrder7() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject requestObject = new JSONObject();
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
        purchaseController.displayAllOrder(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PurchaseController#displayAllOrder(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testDisplayAllOrder12() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.displayAllOrder(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#displayAllOrder(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testDisplayAllOrder13() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
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
        purchaseController.displayAllOrder(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PurchaseController#displayAllOrderBySeller(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testDisplayAllOrderBySeller6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject requestObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        purchaseController.displayAllOrderBySeller(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link PurchaseController#displayAllOrderBySeller(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testDisplayAllOrderBySeller7() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PurchaseController purchaseController = new PurchaseController();
        JSONObject requestObject = new JSONObject();
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
        purchaseController.displayAllOrderBySeller(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }
}

