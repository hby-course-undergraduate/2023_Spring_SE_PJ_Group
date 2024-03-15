package com.group4.eshopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "goods_sale")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class GoodsSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "shop_id")
    private int shopId;
    @Column(name = "goods_id")
    private int goodsId;
    @Column(name = "number")
    private int number;
    @Column(name = "unit_price")
    private double unitPrice;
    @Column(name = "finish_at")
    private LocalDateTime finishAt;
    @Column(name = "buy_by")
    private int buyBy;

    public GoodsSale() {
    }

    public GoodsSale(int shopId, int goodsId, int number, double unitPrice, int buyBy) {
        this.shopId = shopId;
        this.goodsId = goodsId;
        this.number = number;
        this.unitPrice = unitPrice;
        this.buyBy = buyBy;
        this.finishAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void getId(int id) {
        this.id = id;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getBuyBy() {
        return buyBy;
    }

    public void setBuyBy(int buyBy) {
        this.buyBy = buyBy;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void getUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDateTime getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(LocalDateTime finishAt) {
        this.finishAt = finishAt;
    }
}
