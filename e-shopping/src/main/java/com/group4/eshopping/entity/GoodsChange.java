package com.group4.eshopping.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group4.eshopping.utils.MyUtils;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "goods_change")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class GoodsChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "goods_id")
    private int goodsId;
    @Column(name = "goods_present_name")
    private String goodsName;
    @Column(name = "goods_present_price")
    private Double price;
    @Column(name = "goods_present_description")
    private String description;
    @Column(name = "goods_present_number")
    private Integer number;
    @Column(name = "state")
    private int state;
    @Column(name = "goods_present_tag")
    private String tag;

    @Column(name = "goods_past_name")
    private String pastGoodsName;
    @Column(name = "goods_past_tag")
    private String pastGoodsTag;
    @Column(name = "goods_past_price")
    private double pastPrice;
    @Column(name = "goods_past_description")
    private String pastDescription;
    @Column(name = "goods_past_number")
    private int pastNumber;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_at")
    private LocalDateTime createAt;

    public void getPastAuto(Goods pastGoods) {
        this.pastDescription = pastGoods.getDescription();
        this.pastGoodsName = pastGoods.getGoodsName();
        this.pastPrice = pastGoods.getPrice();
        this.pastNumber = pastGoods.getNumber();
        this.state = 0;
        this.setState(0);
        this.setCreateAtAuto();
        this.pastGoodsTag = pastGoods.getTag();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public double getPastPrice() {
        return pastPrice;
    }

    public void setPastPrice(double pastPrice) {
        this.pastPrice = pastPrice;
    }

    public String getPastDescription() {
        return pastDescription;
    }

    public void setPastDescription(String pastDescription) {
        this.pastDescription = pastDescription;
    }

    public int getPastNumber() {
        return pastNumber;
    }

    public void setPastNumber(int pastNumber) {
        this.pastNumber = pastNumber;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void setCreateAtAuto() {
        this.createAt = MyUtils.getNowTime();
    }

    public String getPastGoodsName() {
        return pastGoodsName;
    }

    public void setPastGoodsName(String pastGoodsName) {
        this.pastGoodsName = pastGoodsName;
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

    public String getPastGoodsTag() {
        return pastGoodsTag;
    }

    public void setPastGoodsTag(String pastGoodsTag) {
        this.pastGoodsTag = pastGoodsTag;
    }
}
