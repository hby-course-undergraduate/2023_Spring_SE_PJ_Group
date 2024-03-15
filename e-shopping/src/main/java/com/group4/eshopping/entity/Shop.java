package com.group4.eshopping.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "shop")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private int shopId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "shop_name")
    private String shopName;
    @Column(name = "tag")
    private String tag;
    @Column(name = "introduction")
    private String introduction;
    @Column(name = "address")
    private String address;
    @Column(name = "fund")
    private double fund;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Column(name = "register_date")
    private LocalDateTime registerDate;
    @Column(name = "state")
    private int state;
    @Column(name = "activity")
    private int activityId;
    //注册时间的理解
    @Transient
    private String idCard;

    public Shop() {
        state = 0;
        //初始化为false
    }

    public int getShopId() {
        return shopId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate() {
        this.registerDate = LocalDateTime.now();//初始化了吗
    }

    public int getState() {
        return state;
    }

    public void setApproved() {
        this.state = 1;
    }

    public void setNotApproved() {
        this.state = 2;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public double getFund() {
        return this.fund;
    }

    public void setFund(double fund) {
        this.fund = fund;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activity) {
        this.activityId = activity;
    }
}

