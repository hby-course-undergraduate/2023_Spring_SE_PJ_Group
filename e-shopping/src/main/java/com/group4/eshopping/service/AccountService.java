package com.group4.eshopping.service;


import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.Account;
import com.group4.eshopping.entity.Shop;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;

public interface AccountService {
    void addOrUpdate(Account userAccount);

    boolean isUserAccountExist(int userId);

    boolean isShopAccountExist(int userId);

    Account getUserByAccountId(int accountId);

    Account getShopByAccountId(int accountId);

    Account getShopByShopId(int shopId);

    Account getShopAccountByShopId(int shopId);

    Account getUserByUserId(int userId);

    Account getShopByUserId(int userId);

    Account getAllUserByUserId(int userId);

    Account getAllShopByUserId(int userId);

    Account getProfit();

    Account getMiddle();


    void increaseAccount(Account account, double increaseAmount) throws CheckException;

    void decreaseAccount(Account account, double decreaseAmount) throws CheckException;

    void transToMiddle(Account account, double transferAmount, String transactionUser) throws CheckException;

    void transToProfit(Account account, double transferAmount, String type) throws CheckException;

    void middleToProfit(double amount, String type) throws CheckException;

    void middleToUser(int userId, double amount, String refund);

    void middleToShop(int shopId, double amount, String transactionShop);

    void transfer(Account fromAccount, String fromType, Account toAccount,
                  String toType, double amount, String type) throws CheckException;

    void addNewAccount(int userId, String type);

    void deleteAccount(Account account);

    void registerFund(int userId, double amount);

    void registerDnuf(int userId, double fund);

    double getSaleroomByShop(Shop shop, int day);


    Result checkUserAccount(JSONObject userInfo);

    Result checkShopAccount(JSONObject userInfo);

    Result checkAdminAccount(JSONObject userInfo);

    Result rechargeUserAccount(double amount, JSONObject userInfo) throws CheckException;

    Result rechargeShopAccount(double amount, JSONObject userInfo) throws CheckException;

    Result rechargeAdminAccount(JSONObject amountObject, JSONObject userInfo);

    Result getAccountRecord(String shop, int days, JSONObject userInfo);

//    void transfer(Account fromAccount, String fromType, Account toAccount,
//                  String toType, double amount) throws CheckException;
//
//    Result middleToProfit(double amount) throws CheckException;
//Result transToProfit(Account account, double transferAmount) throws CheckException;

}
