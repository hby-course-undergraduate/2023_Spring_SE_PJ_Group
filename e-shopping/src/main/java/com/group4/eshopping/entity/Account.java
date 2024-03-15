package com.group4.eshopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "account")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;
    @Column(name = "amount")
    private double amount;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "type")
    private String type;
    //    user/shop/profit/middle
    @Column(name = "state")
    private boolean state;

    public Account() {
    }

    public Account(int userId, String type) {
        this.userId = userId;
        this.type = type;
        this.state = true;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
