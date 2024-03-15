package com.group4.eshopping.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "activity")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "tags")
    private String tags;
    @Column(name = "fund")
    private double fund;
    @Column(name = "register_fund")
    private double registerFund;
    @Column(name = "amount_arrive")
    private double amountArrive;
    @Column(name = "amount_minus")
    private double amountMinus;
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "start_time")
    private LocalDateTime startTime;
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Column(name = "sell_number")
    private int sellNumber;
    @Column(name = "sell_amount")
    private double sellAmount;
    @Column(name = "remain_fund")
    private double remainFund;

    @Column(name = "state")
    private int state;
//    0未开始1在进行2已结束

    public Activity() {
        state = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }


    public double getAmountArrive() {
        return amountArrive;
    }

    public void setAmountArrive(double amountArrive) {
        this.amountArrive = amountArrive;
    }

    public double getAmountMinus() {
        return amountMinus;
    }

    public void setAmountMinus(double amountMinus) {
        this.amountMinus = amountMinus;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getSellNumber() {
        return sellNumber;
    }

    public void setSellNumber(int sellNumber) {
        this.sellNumber = sellNumber;
    }

    public double getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(double sellAmount) {
        this.sellAmount = sellAmount;
    }

    public double getRegisterFund() {
        return registerFund;
    }

    public void setRegisterFund(double registerFund) {
        this.registerFund = registerFund;
    }

    public double getFund() {
        return fund;
    }

    public void setFund(double fund) {
        this.fund = fund;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getRemainFund() {
            return remainFund;
    }

    public void setRemainFund(double remainFund) {
        this.remainFund = remainFund;
    }
}
