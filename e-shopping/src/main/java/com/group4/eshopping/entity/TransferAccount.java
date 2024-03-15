package com.group4.eshopping.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group4.eshopping.utils.MyUtils;
import jakarta.persistence.*;
import org.joda.time.DateTime;

import java.time.LocalDateTime;


@Entity
@Table(name = "transfer_account")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class TransferAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "from_id")
    private int fromId;
    @Column(name = "from_type")
    private String fromType;
    @Column(name = "to_type")
    private String toType;
    @Column(name = "to_id")
    private int toId;
    @Column(name = "amount")
    private double amount;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "type")
    private String type;

    public TransferAccount(int fromId, int toId, double amount) {
        setFromId(fromId);
        setToId(toId);
        setAmount(amount);
        setCreateAt();
    }

    public TransferAccount(int fromId, String fromType, int toId, String toType, double amount,String transferType) {
        setFromType(fromType);
        setToType(toType);
        setFromId(fromId);
        setToId(toId);
        setAmount(amount);
        this.type = transferType;
        setCreateAt();
    }

    public TransferAccount() {

    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt() {
        this.createAt = MyUtils.getNowTime();
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    public String getToType() {
        return toType;
    }

    public void setToType(String toType) {
        this.toType = toType;
    }


    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
