package com.group4.eshopping.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.dao.TransferAccountDAO;
import com.group4.eshopping.entity.TransferAccount;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.TransferAccountService;
import com.group4.eshopping.utils.CheckCondition;
import com.group4.eshopping.utils.MyUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TransferAccountServiceImpl implements TransferAccountService {
    @Autowired
    TransferAccountDAO transferAccountDAO;

    @Override
    public void addOrUpdate(TransferAccount transferAccount) {
        transferAccountDAO.save(transferAccount);
    }

    @Override
    public void addNewRecord(int fromId, String fromType, int toId, String toType, double Amount, String transferType) {
        TransferAccount transferAccount = new TransferAccount(fromId, fromType, toId, toType, Amount, transferType);
        addOrUpdate(transferAccount);
    }

    @Override
    public void addNewRechargeRecord(int id, String type, double amount) {
        TransferAccount transferAccount = new TransferAccount(id, "exterior", id, type, amount, "recharge");
        addOrUpdate(transferAccount);
    }

    public List<TransferAccount> getSaleroom(int userId, int days) {
        return transferAccountDAO.findByToIdAndToTypeAndTypeAndCreateAtAfter(userId,
                "shop", "transactionShop", LocalDateTime.now().minusDays(days));
    }

    public List<TransferAccount> getUserToRecord(int userId, int days) {
        return transferAccountDAO.findByToIdAndToTypeAndCreateAtAfter(userId,
                "user", LocalDateTime.now().minusDays(days));
    }

    public List<TransferAccount> getUserFromRecord(int userId, int days) {
        return transferAccountDAO.findByFromIdAndFromTypeAndCreateAtAfter(userId,
                "user", LocalDateTime.now().minusDays(days));
    }

    public List<TransferAccount> getShopToRecord(int userId, int days) {
        return transferAccountDAO.findByToIdAndToTypeAndCreateAtAfter(userId,
                "shop", LocalDateTime.now().minusDays(days));
    }

    public List<TransferAccount> getShopFromRecord(int userId, int days) {
        return transferAccountDAO.findByFromIdAndFromTypeAndCreateAtAfter(userId,
                "shop", LocalDateTime.now().minusDays(days));
    }

    public List<TransferAccount> getMiddleToRecord(int days) {
        return transferAccountDAO.findByToTypeAndCreateAtAfter(
                "middle", LocalDateTime.now().minusDays(days));
    }

    public List<TransferAccount> getMiddleFromRecord(int days) {
        return transferAccountDAO.findByFromTypeAndCreateAtAfter(
                "middle", LocalDateTime.now().minusDays(days));
    }

    public List<TransferAccount> getProfitToRecord(int days) {
        return transferAccountDAO.findByToTypeAndCreateAtAfter(
                "profit", LocalDateTime.now().minusDays(days));
    }

    public List<TransferAccount> getProfitFromRecord(int days) {
        return transferAccountDAO.findByFromTypeAndCreateAtAfter(
                "profit", LocalDateTime.now().minusDays(days));
    }

    public double getPureProfit(String type, int userId, int days) {
        if (days == 0)
            days = 3650;
        List<TransferAccount> plusRecord = new ArrayList<>();
        List<TransferAccount> minusRecord = new ArrayList<>();
        switch (type) {
            case "user" -> {
                plusRecord = getUserToRecord(userId, days);
                minusRecord = getUserFromRecord(userId, days);
            }
            case "shop" -> {
                plusRecord = getShopToRecord(userId, days);
                minusRecord = getShopFromRecord(userId, days);
            }
            case "middle" -> {
                plusRecord = getMiddleToRecord(days);
                minusRecord = getMiddleFromRecord(days);
            }
            case "profit" -> {
                plusRecord = getProfitToRecord(days);
                minusRecord = getProfitFromRecord(days);
            }
            default -> throw new CheckException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "getPureProfit wrong");
        }
        double pureProfit = 0;
        for (TransferAccount record : plusRecord) {
            pureProfit += record.getAmount();
        }
        for (TransferAccount record : minusRecord) {
            pureProfit -= record.getAmount();
        }
        return pureProfit;
    }

    @Override
    public List<TransferAccount> getAccountRecord(String type, int days, JSONObject userInfo) {
        if (days == 0)
            days = 3650;
        List<TransferAccount> record = new ArrayList<>();
        switch (type) {
            case "user" -> {
                int userId = CheckCondition.getUserId(userInfo);
                record = getUserToRecord(userId, days);
                record.addAll(getUserFromRecord(userId, days));
            }
            case "shop" -> {
                int userId = CheckCondition.getSellerId(userInfo);
                record = getShopToRecord(userId, days);
                record.addAll(getShopFromRecord(userId, days));
            }
            case "middle" -> {
                CheckCondition.checkAdmin(userInfo);
                record = getMiddleToRecord(days);
                record.addAll(getMiddleFromRecord(days));
            }
            case "profit" -> {
                CheckCondition.checkAdmin(userInfo);
                record = getProfitToRecord(days);
                record.addAll(getProfitFromRecord(days));
            }
            default -> throw new CheckException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "getAccountRecord wrong");
        }
        record.sort(Comparator.comparing(TransferAccount::getCreateAt).reversed());
        return record;
    }


}
