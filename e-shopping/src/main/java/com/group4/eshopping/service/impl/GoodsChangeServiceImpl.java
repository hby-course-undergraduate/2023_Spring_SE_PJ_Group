package com.group4.eshopping.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.dao.GoodsChangeDAO;
import com.group4.eshopping.entity.Goods;
import com.group4.eshopping.entity.GoodsChange;
import com.group4.eshopping.entity.GoodsPic;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.GoodsChangeService;
import com.group4.eshopping.utils.CheckCondition;
import com.group4.eshopping.utils.MyUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsChangeServiceImpl implements GoodsChangeService {
    @Autowired
    GoodsChangeDAO goodsChangeDAO;
    @Autowired
    GoodsServiceImpl goodsService;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    TagServiceImpl tagService;

    @Override
    public void addOrUpdate(GoodsChange goodsChange) {
        goodsChangeDAO.save(goodsChange);
    }

    @Override
    public GoodsChange getByGoodsChangeId(int goodsChangeId) {
        return goodsChangeDAO.findById(goodsChangeId);
    }

    @Override
    public Result goodsChange(GoodsChange goodsChange, MultipartFile[] file, JSONObject userInfo) throws CheckException {
        int userId = CheckCondition.getSellerId(userInfo);
        Goods goods = goodsService.getByGoodsId(goodsChange.getGoodsId());
        if (goods == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "未找到商品");

        if (shopService.getByUserId(userId).getShopId() != goods.getShopId())
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "不是你的商品");

        goodsChange.getPastAuto(goods);

        String goodsName = CheckCondition.transferMeaning(goodsChange.getGoodsName());
        if (goodsName.isEmpty()) {
            goodsChange.setGoodsName(goodsChange.getPastGoodsName());
        } else {
            CheckCondition.checkGoodsName(goodsName);
            goodsChange.setGoodsName(goodsName);
        }

        String description = CheckCondition.transferMeaning(goodsChange.getDescription());
        if (description.isEmpty()) {
            goodsChange.setGoodsName(goodsChange.getPastGoodsName());
        } else {
            CheckCondition.checkDescription(description);
            goodsChange.setDescription(description);
        }
//图片
//        MultipartFile file
//        byte[] bytes = file.getBytes();
//        Path path = Paths.get("uploads/" + file.getOriginalFilename());
//        Files.write(path, bytes);
        try {
            CheckCondition.checkPrice(goodsChange.getPrice());
        } catch (Exception e) {
            goodsChange.setPrice(goodsChange.getPastPrice());
        }
        try {
            CheckCondition.checkNumber(goodsChange.getNumber());
        } catch (Exception e) {
            goodsChange.setNumber(goodsChange.getPastNumber());
        }

        String tag = CheckCondition.transferMeaning(goodsChange.getTag());
        if (tag.isEmpty()) {
            goodsChange.setTag(goodsChange.getPastGoodsTag());
        } else {
//            List<Integer> tagList = MyUtils.getIntListFromTag(tag);
            String preTag = goods.getTag();
            try {
                goods.setTag(String.valueOf(tagService.getIdByTag(tag)));
                goods.setTag(tag);
            } catch (Exception e) {
                throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "未知的标签（商品只能有一个标签，请不要再标签中加,）");
            }
            if (!goodsService.isGoodsTagBelongToShop(goods, goods.getShopId()))
                throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "您的商店不存在该类别");
            goodsChange.setTag(String.valueOf(tagService.transferTagToTagId(tag)));
            goods.setTag(preTag);
        }


        List<byte[]> bytes = new ArrayList<>();
        for (MultipartFile eachPicFile : file) {
            bytes.add(goodsService.picToByte(eachPicFile));
        }


        addOrUpdate(goodsChange);
//        goods.setTag("hhh");神奇的bug这里会修改goods表
        setChangePic(goodsChange.getGoodsId(), goodsChange.getId(), bytes);

        return new Result(HttpServletResponse.SC_OK);
    }


    @Override
    public Result displayGoodsChanges(JSONObject userInfo) {
        CheckCondition.checkAdmin(userInfo);
        Result result = new Result(HttpServletResponse.SC_OK);
        List<GoodsChange> goodsChanges = goodsChangeDAO.findAll();
        List<JSONObject> changeObj = new ArrayList<>();
        for (GoodsChange goodsChange : goodsChanges) {
            changeObj.add(transferPic(goodsChange));
        }

        result.setReturnString(MyUtils.getJsonStringWithDate(changeObj));
        return result;
    }

    @Override
    public Result goodsChangeRecord(JSONObject userInfo) {
        int userId = CheckCondition.getSellerId(userInfo);
        if (!shopService.isShopExistByUserId(userId))
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "未开店");

        List<Goods> goods = goodsService.getAllGoodsByShopId(shopService.getByUserId(userId).getShopId());
        List<JSONObject> changeObj = new ArrayList<>();
        for (Goods eachGoods : goods) {
            List<GoodsChange> goodsChangeList = goodsChangeDAO.findByGoodsId(eachGoods.getGoodsId());
            for (GoodsChange goodsChange : goodsChangeList) {
                changeObj.add(transferPic(goodsChange));
            }
        }

        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(changeObj));
        return result;
    }

    @Override
    public Result approveForGoodsChange(JSONObject request, JSONObject userInfo) throws CheckException {
//        任何状态的商品都可以申请修改，修改通过后商品state改为1
        CheckCondition.checkAdmin(userInfo);
        //api:批准状态 isApproved  商品id shopId
        boolean isApproved = request.getString("isApproved").equalsIgnoreCase("true");
        int goodsChangeId = request.getIntValue("goodsChangeId");
        GoodsChange goodsChange = getByGoodsChangeId(goodsChangeId);


        if (goodsChange == null) {
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "商品修改信息不存在");
        }
        if (goodsChange.getState() != 0) {
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "商品修改请求不存在");
        }
        if (isApproved) {
            goodsChange.setApproved();
            changeAll(goodsChange);
        } else {
            goodsChange.setNotApproved();
        }
        addOrUpdate(goodsChange);

        return new Result(HttpServletResponse.SC_OK);
    }

    //    批准true时修改商品信息
    public void changeAll(GoodsChange goodsChange) {
//        尝试修改goodsChange中的所有内容
//        图片state反转（删除）
        int goodsId = goodsChange.getGoodsId();
        Goods goods = goodsService.getByGoodsId(goodsId);
        try {
            goods.setPrice(goodsChange.getPrice());
        } catch (Exception ignored) {
        }
        try {
            goods.setNumber(goodsChange.getNumber());
        } catch (Exception ignored) {
        }
        try {
            goods.setGoodsName(goodsChange.getGoodsName());
        } catch (Exception ignored) {
        }
        try {
            goods.setDescription(goodsChange.getDescription());
        } catch (Exception ignored) {
        }
        try {
            goods.setTag(goodsChange.getTag());
        } catch (Exception ignored) {
        }
//修改商品图片是否有效
        reverseChangePic(goodsId, goodsChange.getId());
        goodsService.addOrUpdate(goods);
    }

    private void reverseChangePic(int goodsId, int goodsChangeId) {
        goodsService.reversePic(goodsId);
        List<GoodsPic> goodsPicList = goodsService.getNotValidPicByBelongTo(goodsChangeId);
        for (GoodsPic goodsPic : goodsPicList) {
            goodsPic.setApproved();
            goodsService.addOrUpdatePic(goodsPic);
        }
    }

    //    展示时 整合pic
//得到修改记录中的图片
//    之前的+要修改的
    public JSONObject transferPic(GoodsChange goodsChange) throws CheckException {
        String[] strings = {"pic"};
        JSONArray validPicArray = new JSONArray();
        JSONArray notValidPicArray = new JSONArray();

        List<GoodsPic> goodsPicList = goodsService.getValidPicByBelongTo(goodsChange.getId());
        for (GoodsPic goodsPic : goodsPicList) {
            byte[] picBytes = goodsPic.getPic();
            if (picBytes == null || picBytes.length == 0) {
                continue;
            }
            validPicArray.add(MyUtils.getBase64Pic(picBytes));
        }

        goodsPicList = goodsService.getNotValidPicByBelongTo(goodsChange.getId());
        for (GoodsPic goodsPic : goodsPicList) {
            byte[] picBytes = goodsPic.getPic();
            if (picBytes == null || picBytes.length == 0) {
                continue;
            }
            notValidPicArray.add(MyUtils.getBase64Pic(picBytes));
        }

        JSONObject picObj = new JSONObject();
        picObj.put("pic", validPicArray);
        picObj.put("presentPic", notValidPicArray);

        return MyUtils.changeObj(goodsChange, picObj, strings);
    }

    //    申请时 添加goodsPic数据库
    public void setChangePic(int goodsId, int belongTo, List<byte[]> bytes) {
        for (byte[] pic : bytes) {
            GoodsPic goodsPic = new GoodsPic(goodsId, belongTo, pic);
            goodsService.addOrUpdatePic(goodsPic);
        }
    }
}
