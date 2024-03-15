package com.group4.eshopping.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.User;
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

class UserControllerTest {
    /**
     * Method under test: {@link UserController#login(User, HttpServletResponse)}
     */
    @Test
    void testLogin3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
        User requestUser = mock(User.class);
        doNothing().when(requestUser).setAdmin(Mockito.<Boolean>any());
        doNothing().when(requestUser).setEmail(Mockito.<String>any());
        doNothing().when(requestUser).setIdCard(Mockito.<String>any());
        doNothing().when(requestUser).setIsSeller(Mockito.<Boolean>any());
        doNothing().when(requestUser).setPassword(Mockito.<String>any());
        doNothing().when(requestUser).setPhone(Mockito.<String>any());
        doNothing().when(requestUser).setUsername(Mockito.<String>any());
        requestUser.setAdmin(true);
        requestUser.setEmail("jane.doe@example.org");
        requestUser.setIdCard("Id Card");
        requestUser.setIsSeller(true);
        requestUser.setPassword("iloveyou");
        requestUser.setPhone("6625550144");
        requestUser.setUsername("janedoe");
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        userController.login(requestUser, httpServletResponse);
        verify(requestUser).setAdmin(Mockito.<Boolean>any());
        verify(requestUser).setEmail(Mockito.<String>any());
        verify(requestUser).setIdCard(Mockito.<String>any());
        verify(requestUser).setIsSeller(Mockito.<Boolean>any());
        verify(requestUser).setPassword(Mockito.<String>any());
        verify(requestUser).setPhone(Mockito.<String>any());
        verify(requestUser).setUsername(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
    }

    /**
     * Method under test: {@link UserController#register(User, HttpServletResponse)}
     */
    @Test
    void testRegister3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
        User requestUser = mock(User.class);
        doNothing().when(requestUser).setAdmin(Mockito.<Boolean>any());
        doNothing().when(requestUser).setEmail(Mockito.<String>any());
        doNothing().when(requestUser).setIdCard(Mockito.<String>any());
        doNothing().when(requestUser).setIsSeller(Mockito.<Boolean>any());
        doNothing().when(requestUser).setPassword(Mockito.<String>any());
        doNothing().when(requestUser).setPhone(Mockito.<String>any());
        doNothing().when(requestUser).setUsername(Mockito.<String>any());
        requestUser.setAdmin(true);
        requestUser.setEmail("jane.doe@example.org");
        requestUser.setIdCard("Id Card");
        requestUser.setIsSeller(true);
        requestUser.setPassword("iloveyou");
        requestUser.setPhone("6625550144");
        requestUser.setUsername("janedoe");
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        userController.register(requestUser, httpServletResponse);
        verify(requestUser).setAdmin(Mockito.<Boolean>any());
        verify(requestUser).setEmail(Mockito.<String>any());
        verify(requestUser).setIdCard(Mockito.<String>any());
        verify(requestUser).setIsSeller(Mockito.<Boolean>any());
        verify(requestUser).setPassword(Mockito.<String>any());
        verify(requestUser).setPhone(Mockito.<String>any());
        verify(requestUser).setUsername(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
    }

    /**
     * Method under test: {@link UserController#changePassword(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testChangePassword5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
        JSONObject request = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        userController.changePassword(request, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link UserController#changePassword(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testChangePassword6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
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
        userController.changePassword(request, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserController#changeEmail(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testChangeEmail5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
        JSONObject request = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        userController.changeEmail(request, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link UserController#changeEmail(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testChangeEmail6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
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
        userController.changeEmail(request, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserController#changeUsername(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testChangeUsername5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
        JSONObject request = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        userController.changeUsername(request, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link UserController#changeUsername(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testChangeUsername6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
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
        userController.changeUsername(request, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserController#changePhone(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testChangePhone5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
        JSONObject request = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        userController.changePhone(request, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link UserController#changePhone(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testChangePhone6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
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
        userController.changePhone(request, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserController#rechargeUserAccount(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRechargeUserAccount4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
        JSONObject amountObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = new HttpServletRequestWrapper(new Request(new Connector()));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        doNothing().when(httpServletResponse).setStatus(anyInt());
        userController.rechargeUserAccount(amountObject, httpServletRequest, httpServletResponse);
        verify(httpServletResponse).setStatus(anyInt());
    }

    /**
     * Method under test: {@link UserController#checkUserAccount(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCheckUserAccount5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        userController.checkUserAccount(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link UserController#checkUserAccount(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCheckUserAccount6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
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
        userController.checkUserAccount(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserController#checkUserInfo(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCheckUserInfo5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        userController.checkUserInfo(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link UserController#checkUserInfo(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCheckUserInfo6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
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
        userController.checkUserInfo(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserController#checkAdminAccount(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCheckAdminAccount5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        userController.checkAdminAccount(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link UserController#checkAdminAccount(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCheckAdminAccount6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
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
        userController.checkAdminAccount(httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserController#rechargeAdminAccount(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRechargeAdminAccount5() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
        JSONObject amountObject = new JSONObject();
        HttpServletRequestWrapper httpServletRequest = mock(HttpServletRequestWrapper.class);
        when(httpServletRequest.getAttribute(Mockito.<String>any())).thenThrow(new CheckException(1, "Wrong Msg"));
        HttpServletResponseWrapper httpServletResponse = mock(HttpServletResponseWrapper.class);
        when(httpServletResponse.getWriter()).thenReturn(null);
        doNothing().when(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        doNothing().when(httpServletResponse).setContentType(Mockito.<String>any());
        doNothing().when(httpServletResponse).setStatus(anyInt());
        userController.rechargeAdminAccount(amountObject, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse, atLeast(1)).setStatus(anyInt());
    }

    /**
     * Method under test: {@link UserController#rechargeAdminAccount(JSONObject, HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRechargeAdminAccount6() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserController userController = new UserController();
        JSONObject amountObject = new JSONObject();
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
        userController.rechargeAdminAccount(amountObject, httpServletRequest, httpServletResponse);
        verify(httpServletRequest).getAttribute(Mockito.<String>any());
        verify(httpServletResponse).getWriter();
        verify(httpServletResponse).setCharacterEncoding(Mockito.<String>any());
        verify(httpServletResponse).setContentType(Mockito.<String>any());
        verify(httpServletResponse).setStatus(anyInt());
        verify(coyoteWriter).close();
        verify(coyoteWriter).println(Mockito.<String>any());
    }
}

