package com.group4.eshopping.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.Goods;
import com.group4.eshopping.entity.GoodsChange;
import com.group4.eshopping.entity.GoodsPic;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.ApplyService;
import com.group4.eshopping.utils.CheckCondition;
import com.group4.eshopping.utils.MyUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    ActivityServiceImpl activityService;
    @Autowired
    GoodsServiceImpl goodsService;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    GoodsChangeServiceImpl goodsChangeService;

    /**
     * @param request
     * @param userInfo
     * @return
     * @throws CheckException
     */
    @Override
    public Result approveForGoods(JSONObject request, JSONObject userInfo) throws CheckException {
        CheckCondition.checkAdmin(userInfo);
        //api:批准状态 isApproved  商品id shopId
        boolean isApproved = request.getString("isApproved").equalsIgnoreCase("true");
        int goodsId = request.getIntValue("goodsId");
        Goods goods = goodsService.getByGoodsId(goodsId);

        if (goods == null) {
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "商品信息不存在");
        }
        if (goods.getState() != 0) {
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "商品请求不存在");
        }
        if (isApproved) {
            goods.setApproved();
            List<GoodsPic> goodsPicList = goodsService.getNotValidPicByGoodsId(goodsId);
            for (GoodsPic goodsPic : goodsPicList) {
                goodsPic.setApproved();
                goodsService.addOrUpdatePic(goodsPic);
            }
            try {
                int activityId = shopService.getByShopId(goods.getShopId()).getActivityId();
                if (activityId != 0)
                    activityService.startActivityEachGoods(goods);
//                    if (checkGoodsHoldActivity(activityId, goods.getTag()))
//                        goods.setActivityId(activityId);
            } catch (Exception e) {
                goods.setActivityId(0);
            }
        } else {
            goods.setNotApproved();
        }
        goodsService.addOrUpdate(goods);
        return new Result(HttpServletResponse.SC_OK);
    }


    @Override
    public Result approveForGoodsChange(JSONObject request, JSONObject userInfo) throws CheckException {
//        任何状态的商品都可以申请修改，修改通过后商品state改为1
        CheckCondition.checkAdmin(userInfo);
        //api:批准状态 isApproved  商品id shopId
        boolean isApproved = request.getString("isApproved").equalsIgnoreCase("true");
        int goodsChangeId = request.getIntValue("goodsChangeId");
        GoodsChange goodsChange = goodsChangeService.getByGoodsChangeId(goodsChangeId);


        if (goodsChange == null) {
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "商品修改信息不存在");
        }
        if (goodsChange.getState() != 0) {
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "商品修改请求不存在");
        }
        if (isApproved) {
            goodsChange.setApproved();
            goodsChangeService.changeAll(goodsChange);
//           判断修改后activity是否仍能参加
            int goodsId = goodsChange.getGoodsId();
            Goods goods = goodsService.getByGoodsId(goodsId);
            int activityId = goods.getActivityId();
//            if (activityId != 0) {
            if (goodsChange.getTag() != null && !Objects.equals(goodsChange.getTag(), goodsChange.getPastGoodsTag()))
                activityService.startActivityEachGoods(goods);
//                if (checkGoodsHoldActivity(activityId, goodsChange.getTag())) {
//                    goods.setActivityId(activityId);
//                    goodsService.addOrUpdate(goods);
//                }
        } else {
            goodsChange.setNotApproved();
        }
        goodsChangeService.addOrUpdate(goodsChange);

        return new Result(HttpServletResponse.SC_OK);
    }


    public boolean checkGoodsHoldActivity(int activityId, String tag) {
        List<Integer> goodsTag = MyUtils.getIntListFromTag(tag);
        List<Integer> activityTag = MyUtils.getIntListFromTag(activityService.getValidById(activityId).getTags());
        return activityTag.containsAll(goodsTag);
//        一件商品对应一个标签
    }
}
