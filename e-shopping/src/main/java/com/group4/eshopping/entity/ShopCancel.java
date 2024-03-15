package com.group4.eshopping.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group4.eshopping.utils.MyUtils;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "shop_cancel")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ShopCancel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int shopCancelId;
    @Column(name = "shop_id")
    private int shopId;
    @Column(name = "state")
    private int state;
    @Column(name = "order_finish")
    private boolean isOrderFinish;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column
    private String reason;

    public ShopCancel() {

    }

    public ShopCancel(int shopId, boolean isOrderFinish, String reason) {
        this.shopId = shopId;
        this.isOrderFinish = isOrderFinish;
        state = 0;
        this.reason = reason;
        setCreateAtAuto();
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isOrderFinish() {
        return isOrderFinish;
    }

    public void setOrderFinish(boolean orderFinish) {
        isOrderFinish = orderFinish;
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

    public void setApproved() {
        this.state = 1;
    }


    public void setNotApproved() {
        this.state = 2;
    }

    public int getShopCancelId() {
        return shopCancelId;
    }

    public void setShopCancelId(int shopCancelId) {
        this.shopCancelId = shopCancelId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
