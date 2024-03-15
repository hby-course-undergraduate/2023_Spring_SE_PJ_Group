package com.group4.eshopping.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.dao.AccountDAO;
import com.group4.eshopping.entity.*;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.AccountService;
import com.group4.eshopping.utils.CheckCondition;
import com.group4.eshopping.utils.MyUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDAO accountDAO;
    @Autowired
    TransferAccountServiceImpl transferAccountService;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    UserServiceImpl userService;

    @Override
    public void addOrUpdate(Account userAccount) {
        accountDAO.save(userAccount);
    }

    @Override
    public boolean isUserAccountExist(int userId) {
        return getUserByUserId(userId) != null;
    }

    @Override
    public boolean isShopAccountExist(int userId) {
        return getShopByUserId(userId) != null;
    }


    @Override
    public Account getShopByShopId(int shopId) throws CheckException {
        int userId = shopService.getByShopId(shopId).getUserId();
        Account shopAccount = getShopByUserId(userId);
        if (shopAccount == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "该商家未开店");
        return shopAccount;
    }

    @Override
    public Account getShopAccountByShopId(int shopId) {
        int userId = shopService.getByShopId(shopId).getUserId();
        return getShopByUserId(userId);
    }

    @Override
    public Account getUserByAccountId(int accountId) {
        return accountDAO.findUserByAccountId(accountId);
    }

    @Override
    public Account getShopByAccountId(int accountId) {
        return accountDAO.findShopByAccountId(accountId);
    }

    @Override
    public Account getUserByUserId(int userId) {
        return accountDAO.findUserByUserId(userId);
    }

    @Override
    public Account getShopByUserId(int userId) {
        return accountDAO.findShopByUserId(userId);
    }

    @Override
    public Account getAllUserByUserId(int userId) {
        return accountDAO.findAllUserByUserId(userId);
    }

    @Override
    public Account getAllShopByUserId(int userId) {
        return accountDAO.findAllShopByUserId(userId);
    }

    @Override
    public Account getProfit() {
        return accountDAO.findProfitAccount();
    }

    @Override
    public Account getMiddle() {
        return accountDAO.findMiddleAccount();
    }


    @Override
    public void increaseAccount(Account account, double increaseAmount) {
        double newAmount = account.getAmount() + increaseAmount;
        account.setAmount(newAmount);
        addOrUpdate(account);
    }

    @Override
    public void decreaseAccount(Account account, double decreaseAmount) throws CheckException {
        double newAmount = account.getAmount() - decreaseAmount;
        if (newAmount < 0)
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "余额不足");
        account.setAmount(newAmount);
        addOrUpdate(account);
    }

    @Override
    public void transToMiddle(Account account, double transferAmount, String type) throws CheckException {
        decreaseAccount(account, transferAmount);
        increaseAccount(getMiddle(), transferAmount);
        transferAccountService.addNewRecord(account.getAccountId(), account.getType(),
                getMiddle().getAccountId(), "middle", transferAmount, type);
    }

    @Override
    public void transToProfit(Account account, double transferAmount, String type) throws CheckException {
        decreaseAccount(account, transferAmount);
        increaseAccount(getProfit(), transferAmount);
        transferAccountService.addNewRecord(account.getAccountId(), account.getType(),
                getProfit().getAccountId(), "profit", transferAmount, type);
    }

    @Override
    public void middleToProfit(double amount, String type) throws CheckException {
        if (amount <= 0)
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "转账金额不得少于0元");
        Account middleAccount = getMiddle();
        Account profitAccount = getProfit();
        decreaseAccount(middleAccount, amount);
        increaseAccount(profitAccount, amount);
        transferAccountService.addNewRecord(middleAccount.getAccountId(), "middle",
                profitAccount.getAccountId(), "profit", amount, type);
    }

    @Override
    public void middleToUser(int userId, double amount, String type) throws CheckException {
        Account userAccount = getUserByUserId(userId);
        decreaseAccount(getMiddle(), amount);
        increaseAccount(userAccount, amount);
        transferAccountService.addNewRecord(getMiddle().getAccountId(), "middle", userId, "user", amount, type);
    }

    @Override
    public void middleToShop(int shopId, double amount, String type) {
        int userId = shopService.getByShopId(shopId).getUserId();
        Account shopAccount = getShopByShopId(shopId);
        decreaseAccount(getMiddle(), amount);
        increaseAccount(shopAccount, amount);
        transferAccountService.addNewRecord(getMiddle().getAccountId(), "middle", userId, "shop", amount, type);
    }

    @Override
    public void transfer(Account fromAccount, String fromType, Account toAccount,
                         String toType, double amount, String type) throws CheckException {
        if (amount <= 0)
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "转账金额不得少于0元");
        decreaseAccount(fromAccount, amount);
        increaseAccount(toAccount, amount);
        transferAccountService.addNewRecord(fromAccount.getAccountId(), fromType, toAccount.getAccountId(), toType, amount, type);
    }


    @Override
    public Result checkUserAccount(JSONObject userInfo) throws CheckException {
        int userId = userInfo.getInteger("userId");
        Account userAccount = getUserByUserId(userId);
        if (userAccount == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "用户未创建账户");

        String[] Strings = {"accountId", "userId", "amount"};
        JSONObject resObject = new JSONObject();
        resObject.put("amount", MyUtils.getMoneyDouble(userAccount.getAmount()));

        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithAll(userAccount, resObject, Strings));
        return result;
    }

    @Override
    public Result checkShopAccount(JSONObject userInfo) throws CheckException {
        int userId = CheckCondition.getSellerId(userInfo);
        Account shopAccount = getShopByUserId(userId);
        if (shopAccount == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无商店账户");
        double fund = shopService.getByUserId(userId).getFund();
        JSONObject resObject = new JSONObject();
        resObject.put("registerFund", fund);
        resObject.put("amount", MyUtils.getMoneyDouble(shopAccount.getAmount()));
        resObject.put("state", shopAccount.isState());

        Result result = new Result(HttpServletResponse.SC_OK);
        result.setResObject(resObject);
        return result;
    }

    @Override
    public Result checkAdminAccount(JSONObject userInfo) {
        CheckCondition.checkAdmin(userInfo);

        Account middleAccount = getMiddle();
        Account profitAccount = getProfit();
        if (profitAccount == null || middleAccount == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无管理员账户");

        JSONObject resObject = new JSONObject();
        double middleAccountAmount = MyUtils.getMoneyDouble(middleAccount.getAmount());
        double profitAccountAmount = MyUtils.getMoneyDouble(profitAccount.getAmount());
        resObject.put("middleAccountAmount", middleAccountAmount);
        resObject.put("profitAccountAmount", profitAccountAmount);

        Result result = new Result(HttpServletResponse.SC_OK);
        result.setResObject(resObject);
        return result;
    }

    @Override
    public Result rechargeUserAccount(double amount, JSONObject userInfo) throws CheckException {
        int userId = userInfo.getInteger("userId");
        String role = userInfo.getString("role");
        if (!role.equals("buyer") && !role.equals("seller"))
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "用户身份错误，管理员请调用其他充值接口？");
        if (amount <= 0)
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "充值金额不得少于0元");
        if (!isUserAccountExist(userId))
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无账户");
        increaseAccount(getUserByUserId(userId), amount);
        transferAccountService.addNewRechargeRecord(userId, "user", amount);
        return new Result(HttpServletResponse.SC_OK);
    }

    @Override
    public Result rechargeShopAccount(double amount, JSONObject userInfo) throws CheckException {
        if (amount <= 0)
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "充值金额不得少于0元");
        int userId = userInfo.getInteger("userId");
        String role = userInfo.getString("role");
        if (!Objects.equals(role, "seller"))
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "不是商家，无法向商店账户转账");
        if (!isShopAccountExist(userId))
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无账户");
        decreaseAccount(getUserByUserId(userId), amount);
        increaseAccount(getShopByUserId(userId), amount);
        transferAccountService.addNewRecord(userId, "user", userId, "shop", amount, "transferShop");
        return new Result(HttpServletResponse.SC_OK);
    }

    @Override
    public Result rechargeAdminAccount(JSONObject amountObject, JSONObject userInfo) {
        CheckCondition.checkAdmin(userInfo);
        int specialUserId = userInfo.getInteger("userId");
        String amountStr = amountObject.getString("type");

        double amount = amountObject.getDouble("amount");
        try {
            if (amount <= 0)
                throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "充值金额不得少于0元");
        } catch (Exception e) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "充值金额错误");
        }

        if (amountStr.equals("profit")) {
            increaseAccount(getProfit(), amount);
            transferAccountService.addNewRechargeRecord(specialUserId, "profit", amount);
        } else if (amountStr.equals("middle")) {
            increaseAccount(getMiddle(), amount);
            transferAccountService.addNewRechargeRecord(specialUserId, "middle", amount);
        } else throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "你这是什么type");

        return new Result(HttpServletResponse.SC_OK);
    }

    @Override
    public void addNewAccount(int userId, String type) {
//        先找当前有没有失效的账户
//        如果有,转移全部资金,激活该账户
//        如果没有，新建
        Account account;
        if (type.equals("user")) {
            account = getAllUserByUserId(userId);
        } else if (type.equals("shop")) {
            account = getAllShopByUserId(userId);
        } else throw new CheckException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "注册类型有误");
        if (account != null) {
            if (account.getAmount() != 0)
                transToProfit(account, account.getAmount(), "clearNotValidAccount");
            account.setState(true);
        } else account = new Account(userId, type);
        addOrUpdate(account);
    }

    @Override
    public void deleteAccount(Account account) {
        account.setState(false);
        addOrUpdate(account);
    }


    @Override
    public void registerFund(int userId, double amount) {
//        此时还没有shop账户 通过user传
        Account userAccount = getUserByUserId(userId);
//        注册资金记录
        transferAccountService.addNewRechargeRecord(userAccount.getAccountId(), "user", amount);
        increaseAccount(getMiddle(), amount);
//        转移记录
        transferAccountService.addNewRecord(userAccount.getAccountId(), "user",
                getMiddle().getAccountId(), "middle", amount, "register");
    }

    @Override
    public void registerDnuf(int userId, double fund) {
//        返还注册资金
        Account userAccount = getUserByUserId(userId);
        try {
            transfer(getMiddle(), "middle", userAccount, "user", fund, "registerDnuf");
        } catch (Exception ignored) {
//            无用户账户 不做处理
        }
    }


    @Override
    public double getSaleroomByShop(Shop shop, int day) {
        int userId = shop.getUserId();
        List<TransferAccount> record = transferAccountService.getSaleroom(userId, day);
        double saleroom = 0;
        for (TransferAccount transferAccount : record) {
            saleroom += transferAccount.getAmount();
        }
        return saleroom;
    }


    @Override
    public Result getAccountRecord(String type, int days, JSONObject userInfo) {
        List<TransferAccount> record = transferAccountService.getAccountRecord(type, days, userInfo);
        List<JSONObject> recordList = new ArrayList<>();
        for (TransferAccount transferAccount : record) {
            recordList.add(getAccountObject(transferAccount));
        }
        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(recordList));
        return result;
    }

    public JSONObject getAccountObject(TransferAccount transferAccount) {
        String[] strings = {"fromId", "toId"};
        JSONObject addObj = new JSONObject();
        addObj.put("fromName", getTransferName(transferAccount.getFromType(), transferAccount.getFromId()));
        addObj.put("toName", getTransferName(transferAccount.getToType(), transferAccount.getToId()));
        String type = null;
        switch (transferAccount.getType()) {
            case "recharge" -> type = "充值";
            case "refund" -> type = "退款";
            case "brokerage" -> type = "佣金";
            case "register" -> type = "注册商店";
            case "registerFund" -> type = "商店注册到账";
            case "registerDnuf" -> type = "商店注册退还";
            case "transferShop" -> type = "转移资金至商店";
            case "transferUser" -> type = "转移资金至用户";
            case "transactionUser" -> type = "购买商品";
            case "transactionShop" -> type = "售出商品";
            case "clearNotValidAccount" -> type = "清理无效账户";
            case "holdActivity" -> type = "开办活动";
            case "endActivity" -> type = "活动结束";
            default -> type = "未知类型";
        }
        transferAccount.setType(type);
        return MyUtils.changeObj(transferAccount, addObj, strings);
    }

    public String getTransferName(String type, int userId) {
        try {
            return switch (type) {
                case "user" -> userService.getById(userId).getUsername();
                case "shop" -> shopService.getByUserId(userId).getShopName();
                case "middle" -> "商城中间账户";
                case "profit" -> "商城利润账户";
                default -> throw new CheckException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "getTransferName wrong");
            };
        } catch (Exception e) {
            return "null";
        }
    }

    public void profitToMiddle(double amount, String type) {
        if (amount <= 0)
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "转账金额不得少于0元");
        Account middleAccount = getMiddle();
        Account profitAccount = getProfit();
        decreaseAccount(profitAccount, amount);
        increaseAccount(middleAccount, amount);
        transferAccountService.addNewRecord(profitAccount.getAccountId(), "profit",
                middleAccount.getAccountId(), "middle", amount, type);
    }
}
