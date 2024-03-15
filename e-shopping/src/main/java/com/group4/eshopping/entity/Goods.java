package com.group4.eshopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group4.eshopping.exception.CheckException;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "goods")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int goodsId;
    @Column(name = "goods_name")
    private String goodsName;
    @Column(name = "price")
    private double price;
    @Column(name = "description")
    private String description;
    @Column(name = "number")
    private int number;
    @Column(name = "state")
    private int state;
    @Column(name = "tag")
    private String tag;
    @Column(name = "remain_number")
    private int remainNumber;
    @Column(name = "shop_id")
    private int shopId;
    @Column(name = "activityId")
    private int activityId;
    @Transient
    private double recommendLevel;

    public Goods() {
        state = 0;
        recommendLevel = 0;
    }

    public Goods(String goodsName, double price, String description, int number, int shopId) {
        setGoodsName(goodsName);
        setPrice(price);
        setDescription(description);
        setNumber(number);
        setRemainNumber(number);
        setShopId(shopId);
        recommendLevel = 0;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getRemainNumber() {
        return remainNumber;
    }

    public void setRemainNumber(int remainNumber) {
        this.remainNumber = remainNumber;
    }

    public void setRemainNumberAuto() {
        this.remainNumber = this.number;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public void setApproved() {
        this.state = 1;
    }

    public void setNotApproved() {
        this.state = 2;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getActivityId() {
        return activityId;
    }

    public double getRecommendLevel() {
        return recommendLevel;
    }

    public void setRecommendLevel(double recommendLevel) {
        this.recommendLevel = recommendLevel;
    }
}
