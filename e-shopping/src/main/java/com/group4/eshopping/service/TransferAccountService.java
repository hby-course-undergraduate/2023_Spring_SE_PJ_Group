package com.group4.eshopping.service;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.TransferAccount;

import java.util.List;

public interface TransferAccountService {
    void addOrUpdate(TransferAccount transferAccount);

//    void addNewRecord(int fromId, String fromType, int toId, String toType, double Amount);

    void addNewRecord(int fromId, String fromType, int toId, String toType, double Amount, String transferType);

    void addNewRechargeRecord(int id, String type, double amount);

    List<TransferAccount> getAccountRecord(String type, int days, JSONObject userInfo);

}
