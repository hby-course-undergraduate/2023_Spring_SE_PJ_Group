package com.group4.eshopping.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group4.eshopping.utils.MyUtils;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "goods_order")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "goods_id")
    private int goodsId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "number")
    private int number;
    @Column(name = "unit_price")
    private double unitPrice;
    @Column(name = "state")
    private String state;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "buy_time")
    private LocalDateTime buyTime;
    @Column(name = "level")
    private Integer level;
    @Column(name = "order_group")
    private int group;
    @Column(name = "shop_id")
    private int shopId;
    @Column(name = "address_id")
    private Integer addressId;
    @Column(name = "status")
    private boolean status;
    @Column(name = "refunding")
    private boolean refunding;
    @Column(name = "minus_amount")
    private double minusAmount;
//    public Order(int userId, int goodsId, int buyNumber, double price) {
//
//    }

    public Order() {
    }

    public Order(int userId, int goodsId, int number, double price, int group, int shopId, int addressId, double minusAmount) {
        this.minusAmount = minusAmount;
        this.userId = userId;
        this.goodsId = goodsId;
        this.number = number;
        this.unitPrice = price;
        this.group = group;
        this.shopId = shopId;
        this.state = "unpaid";
        this.status = true;
        this.refunding = false;
        this.addressId = addressId;
        setBuyTimeAuto();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDateTime getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(LocalDateTime buyTime) {
        this.buyTime = buyTime;
    }

    public void setBuyTimeAuto() {
        this.buyTime = MyUtils.getNowTime();
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPaid() {
        this.state = "undelivered";
    }

    public void setDelivered() {
        this.state = "unreceived";
    }

    public void setReceived() {
        this.state = "received";
    }

    public void setCancelled() {
        this.state = "cancelled";
    }

    public void setRefunded() {
        this.state = "refunded";
    }

    public boolean isRefunding() {
        return refunding;
    }

    public void setRefunding(boolean refunding) {
        this.refunding = refunding;
    }

    public void setMinusAmount(double minusAmount) {
        this.minusAmount = minusAmount;
    }

    public double getMinusAmount() {
        return minusAmount;
    }
}
