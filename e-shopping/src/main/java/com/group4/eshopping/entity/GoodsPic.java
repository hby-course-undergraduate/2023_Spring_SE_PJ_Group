package com.group4.eshopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "goods_pic")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class GoodsPic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "goods_id")
    private int goodsId;
    @Lob
    @Column(name = "pic")
    private byte[] pic;
    @Column(name = "state")
    private int state;
    @Column(name = "belong_to")
    private int belongTo;

    public GoodsPic() {

    }

    public GoodsPic(int goodsId, byte[] bytes) {
        this.goodsId = goodsId;
        pic = bytes;
        this.belongTo = 0;
    }

    public GoodsPic(int goodsId, int belongTo , byte[] bytes) {
        this.belongTo = belongTo;
        this.goodsId = goodsId;
        pic = bytes;
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


    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setApproved() {
        this.state = 1;
    }

    public void setAbandon() {
        this.state = 2;
    }

    public int getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(int belongTo) {
        this.belongTo = belongTo;
    }
}
