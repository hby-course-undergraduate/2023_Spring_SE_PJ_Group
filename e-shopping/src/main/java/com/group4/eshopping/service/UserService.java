package com.group4.eshopping.service;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.User;
import com.group4.eshopping.result.Result;


public interface UserService {

    User getUsernameAndPassword(String username, String password);

    void addOrUpdate(User user);

    boolean isUsernameExist(String username);

    boolean isIdCardExist(String idCard);

    boolean isEmailExist(String email);

    boolean isPhoneExist(String phone);

    User getByName(String username);

    User getByIdCard(String idCard);

    User getByEmail(String email);

    User getByPhone(String phone);

    User getById(int id);

    Result login(User requestUser);

    Result changePassword(JSONObject request, JSONObject userInfo);

    Result changeUsername(JSONObject request, JSONObject userInfo);

    Result changeEmail(JSONObject request, JSONObject userInfo);

    Result changePhone(JSONObject request, JSONObject userInfo);

    Result checkUserInfo(JSONObject userInfo);
}
