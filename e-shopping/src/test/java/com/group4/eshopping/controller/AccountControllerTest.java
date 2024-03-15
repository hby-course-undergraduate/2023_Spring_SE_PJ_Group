package com.group4.eshopping.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AccountControllerTest {
    /**
     * Method under test: {@link AccountController#getUserAccountRecord(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testGetUserAccountRecord4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        AccountController accountController = new AccountController();
        JSONObject requestObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = new HttpServletRequestWrapper(new Request(new Connector()));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        accountController.getUserAccountRecord(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletResponse).setStatus(anyInt());
    }

    /**
     * Method under test: {@link AccountController#getShopAccountRecord(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testGetShopAccountRecord4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        AccountController accountController = new AccountController();
        JSONObject requestObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = new HttpServletRequestWrapper(new Request(new Connector()));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        accountController.getShopAccountRecord(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletResponse).setStatus(anyInt());
    }

    /**
     * Method under test: {@link AccountController#getMiddleAccountRecord(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testGetMiddleAccountRecord4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        AccountController accountController = new AccountController();
        JSONObject requestObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = new HttpServletRequestWrapper(new Request(new Connector()));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        accountController.getMiddleAccountRecord(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletResponse).setStatus(anyInt());
    }

    /**
     * Method under test: {@link AccountController#getProfitAccountRecord(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testGetProfitAccountRecord4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        AccountController accountController = new AccountController();
        JSONObject requestObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = new HttpServletRequestWrapper(new Request(new Connector()));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        accountController.getProfitAccountRecord(requestObject, httpServletRequest, httpServletResponse);
        verify(httpServletResponse).setStatus(anyInt());
    }
}

