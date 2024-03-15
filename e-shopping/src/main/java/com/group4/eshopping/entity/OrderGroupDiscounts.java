package com.group4.eshopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "order_group_discounts")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class OrderGroupDiscounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "group_id")
    private int group;
    @Column(name = "discounts")
    private String discounts;

    public OrderGroupDiscounts(int group, String str) {
        this.group = group;
        this.discounts = str;
    }

    public OrderGroupDiscounts() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getDiscounts() {
        return discounts;
    }

    public void setDiscounts(String discounts) {
        this.discounts = discounts;
    }
}
