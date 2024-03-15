package com.group4.eshopping.service;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.GoodsChange;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import org.springframework.web.multipart.MultipartFile;

public interface GoodsChangeService {
    void  addOrUpdate(GoodsChange goodsChange);

    GoodsChange getByGoodsChangeId(int goodsChangeId);

    Result goodsChange(GoodsChange goodsChange, MultipartFile[] file, JSONObject userInfo) throws CheckException;

    Result displayGoodsChanges(JSONObject userInfo);

    Result goodsChangeRecord(JSONObject userInfo);

    Result approveForGoodsChange(JSONObject request, JSONObject userInfo);
}
