package com.group4.eshopping.service;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.Goods;
import com.group4.eshopping.entity.GoodsPic;
import com.group4.eshopping.entity.Shop;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GoodsService {
    Result applyForGoods(Goods requestGoods, MultipartFile[] picFile, JSONObject userInfo);

    List<GoodsPic> getNotValidPicByBelongTo(int belongTo);

    List<GoodsPic> getValidPicByBelongTo(int belongTo);

    void addOrUpdate(Goods goods);

    Result approveForGoods(JSONObject request, JSONObject userInfo);

    Goods getByGoodsId(int goodsId);

    Goods getValidByGoodsId(int goodsId);

    List<Goods> getValidGoodsByShopId(int shopId);

    List<Goods> getAllGoodsByShopId(int shopId);

    Result displayAllGoods(JSONObject userInfo);

    Result displayApprovedGoods();

    Result goodsApplyRecord(JSONObject userInfo);

    Result displayOneGoods(int goodsId);

    void updateRemainNumber(Goods goods, int remainNumber);

    byte[] picToByte(MultipartFile picFile) throws CheckException;

    void setPic(int goodsId, List<byte[]> bytes);

    void addOrUpdatePic(GoodsPic goodsPic);

    List<Goods> getValidGoodsByTagId(int tagId);

    JSONObject getByGoodsIdWithPic(int goodsId);

    List<Goods> getGoodsByTag(String tagName);

    boolean isGoodsTagBelongToShop(Goods goods, int shopId);

    Result displayRecommendedGoods(JSONObject userInfo);
}
