package com.group4.eshopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "shop_activity")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ShopActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "shop_id")
    private int shopId;
    @Column(name = "activity_id")
    private int activityId;
    @Column(name = "is_approved")
    private int isApproved;
    @Column(name = "state")
    private int state;

    public ShopActivity(int activityId, int shopId) {
        this.activityId = activityId;
        this.shopId = shopId;
        state = 0;
        isApproved = 0;
    }

    public ShopActivity() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
    }

    public int getIsApproved() {
        return isApproved;
    }
}
