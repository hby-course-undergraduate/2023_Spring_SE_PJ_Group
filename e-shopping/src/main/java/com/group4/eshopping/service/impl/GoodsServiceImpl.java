package com.group4.eshopping.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.dao.GoodsDAO;
import com.group4.eshopping.dao.GoodsPicDAO;
import com.group4.eshopping.dao.GoodsSaleDAO;
import com.group4.eshopping.entity.Goods;
import com.group4.eshopping.entity.GoodsPic;
import com.group4.eshopping.entity.GoodsSale;
import com.group4.eshopping.entity.Shop;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.GoodsService;
import com.group4.eshopping.utils.CheckCondition;
import com.group4.eshopping.utils.MyUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsDAO goodsDAO;
    @Autowired
    GoodsPicDAO goodsPicDAO;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    TagServiceImpl tagService;
    @Autowired
    GoodsSaleDAO goodsSaleDAO;

    @Override
    public void addOrUpdate(Goods goods) {
        goodsDAO.save(goods);
    }

    @Override
    public void addOrUpdatePic(GoodsPic goodsPic) {
        goodsPicDAO.save(goodsPic);
    }

    @Override
    public Goods getByGoodsId(int goodsId) {
        return goodsDAO.findByGoodsId(goodsId);
    }

    @Override
    public Goods getValidByGoodsId(int goodsId) {
        return goodsDAO.findByGoodsIdAndStateIsApproved(goodsId);
    }

    @Override
    public List<Goods> getAllGoodsByShopId(int shopId) {
        return goodsDAO.findByShopId(shopId);
    }

    @Override
    public List<Goods> getValidGoodsByShopId(int shopId) {
        return goodsDAO.findByShopIdAndStateIsApproved(shopId);
    }

    public List<GoodsPic> getValidPicByGoodsId(int goodId) {
        return goodsPicDAO.findByGoodsIdAndStateIsTrue(goodId);
    }

    public List<GoodsPic> getAllPicByGoodsId(int goodId) {
        return goodsPicDAO.findByGoodsId(goodId);
    }

    public List<GoodsPic> getNotValidPicByGoodsId(int goodId) {
        return goodsPicDAO.findByGoodsIdAndStateIsFalse(goodId);
    }

    @Override
    public List<GoodsPic> getNotValidPicByBelongTo(int belongTo) {
        return goodsPicDAO.findByBelongToAndStateIsFalse(belongTo);
    }

    @Override
    public List<GoodsPic> getValidPicByBelongTo(int belongTo) {
        return goodsPicDAO.findByBelongToAndStateIsTrue(belongTo);
    }

    @Override
    public List<Goods> getGoodsByTag(String tagName) {
        if (tagService.isTagExist(tagName)) {
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "tag不存在");
        }
        int id = tagService.getIdByTag(tagName);
        List<Goods> goodsList = goodsDAO.findByTagLike(id);
        return goodsList;
    }

    @Override
    public List<Goods> getValidGoodsByTagId(int tagId) {
//        List<Goods> goodsList = goodsDAO.findByTagLike(tagId);
        List<Goods> goodsList = goodsDAO.findByTagLikeAndStateIsTrue(tagId);
        return goodsList;
    }


    @Override
    public void setPic(int goodsId, List<byte[]> bytes) {
        for (byte[] pic : bytes) {
            GoodsPic goodsPic = new GoodsPic(goodsId, pic);
            addOrUpdatePic(goodsPic);
        }
    }

    private void setGoodsTag(Goods goods) {
        //            约定,分割
        StringBuilder tagStr = new StringBuilder();
        for (String str : goods.getTag().split(",")) {
            tagStr.append(tagService.getIdByTag(str)).append(",");
        }
        goods.setTag(tagStr.substring(0, tagStr.length() - 1));
    }

    @Override
    public void updateRemainNumber(Goods goods, int remainNumber) {
        goods.setRemainNumber(remainNumber);
        addOrUpdate(goods);
    }


    @Override
    public JSONObject getByGoodsIdWithPic(int goodsId) {
        return transferPicAndTag(goodsDAO.findByGoodsId(goodsId), true);
    }

    public List<JSONObject> getApprovedGoods() {
        List<Goods> approvedGoods = goodsDAO.findAllByStateIsApproved();
        List<JSONObject> jsonObjectList = new ArrayList<>();
        for (Goods goods : approvedGoods) {
            jsonObjectList.add(transferPicAndTag(goods, true));
        }
        return jsonObjectList;
    }

    public List<JSONObject> getApprovedGoods(int userId) {
        List<Goods> approvedGoods = goodsDAO.findAllByStateIsApproved();
        List<Goods> goodsList = sortGoodsList(approvedGoods, userId);
        List<JSONObject> jsonObjectList = new ArrayList<>();
        for (Goods goods : goodsList) {
            jsonObjectList.add(transferPicAndTag(goods, true));
        }
        return jsonObjectList;
    }


    @Override
    public boolean isGoodsTagBelongToShop(Goods goods, int shopId) {
        String[] tags = goods.getTag().split(",");

        for (String tag : tags) {
            if (!shopService.isTagBelongToShop(tag, shopId))
                return false;
        }
        return true;
    }


    public List<Goods> sortGoodsList(List<Goods> preGoodsList, int userId) {
        List<GoodsSale> goodsSaleList = goodsSaleDAO.findByBuyBy(userId);

        HashMap<Integer, Integer> tagMap = getPreferTag(goodsSaleList);
        for (Goods goods : preGoodsList) {
            double level = 0;
            double levelNum = 0;
            List<Integer> tagList = new ArrayList<>();
            try {
                tagList = MyUtils.getIntListFromTag(goods.getTag());
            } catch (Exception ignored) {
            }
            for (Integer tagId : tagList) {
                if (tagMap.containsKey(tagId))
                    level += tagMap.get(tagId);
            }

            levelNum += goodsSaleDAO.findByGoodsId(goods.getGoodsId()).size();

            goods.setRecommendLevel(level + levelNum / 2);
        }

        preGoodsList.sort(Comparator.comparing(Goods::getRecommendLevel).reversed());
        return preGoodsList;
    }

    public HashMap<Integer, Integer> getPreferTag(List<GoodsSale> goodsSaleList) {
        HashMap<Integer, Integer> tagMap = new HashMap<>();
        for (GoodsSale goodsSale : goodsSaleList) {
            Goods goods = getByGoodsId(goodsSale.getGoodsId());
            if (goods == null)
                continue;
            List<Integer> tags = new ArrayList<>();
            try {
                tags = MyUtils.getIntListFromTag(goods.getTag());
            } catch (Exception ignored) {
            }

            for (Integer tagId : tags) {
                if (tagMap.containsKey(tagId)) {
                    tagMap.replace(tagId, tagMap.get(tagId) + 1);
                } else {
                    tagMap.put(tagId, 1);
                }
            }
        }
        return tagMap;
    }


    @Override
    public Result displayAllGoods(JSONObject userInfo) {
        CheckCondition.checkAdmin(userInfo);

        List<Goods> allGoods = goodsDAO.findAll();
        List<JSONObject> jsonObjectList = new ArrayList<>();
        for (Goods goods : allGoods) {
            jsonObjectList.add(transferPicAndTag(goods, true));
        }

        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(jsonObjectList));
        return result;
    }

    @Override
    public Result displayApprovedGoods() {
        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(getApprovedGoods()));
        return result;
    }

    @Override
    public Result displayOneGoods(int goodsId) throws CheckException {
        Goods goods = goodsDAO.findByGoodsIdAndStateIsApproved(goodsId);
        if (goods == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无商品信息");
        Result result = new Result(HttpServletResponse.SC_OK);

        result.setReturnString(MyUtils.getJsonStringWithDate(transferPicAndTag(goods, true)));
        return result;
    }

    @Override
    public Result displayRecommendedGoods(JSONObject userInfo) {
        int userId = CheckCondition.getUserId(userInfo);

        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(getApprovedGoods(userId)));
        return result;
    }

    @Override
    public Result applyForGoods(Goods requestGoods, MultipartFile[] picFile, JSONObject userInfo) {
        int userId = CheckCondition.getSellerId(userInfo);
        if (!shopService.isShopExistByUserId(userId))
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "未开店");

        String goodsName = CheckCondition.transferMeaning(requestGoods.getGoodsName());
        CheckCondition.checkGoodsName(goodsName);
        requestGoods.setGoodsName(goodsName);

        String description = CheckCondition.transferMeaning(requestGoods.getDescription());
        CheckCondition.checkDescription(description);
        requestGoods.setDescription(description);

        CheckCondition.checkPrice(requestGoods.getPrice());
        CheckCondition.checkNumber(requestGoods.getNumber());

        Shop shop = shopService.getByUserId(userId);
        requestGoods.setShopId(shop.getShopId());

        if (!isGoodsTagBelongToShop(requestGoods, shop.getShopId()))
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "类别不在商店类别中");
        else {
            setGoodsTag(requestGoods);
        }

        requestGoods.setRemainNumberAuto();


        List<byte[]> bytes = new ArrayList<>();
        for (MultipartFile eachPicFile : picFile) {
            bytes.add(picToByte(eachPicFile));
        }

        addOrUpdate(requestGoods);
        setPic(requestGoods.getGoodsId(), bytes);
        return new Result(HttpServletResponse.SC_OK);
    }

    @Override
    public Result goodsApplyRecord(JSONObject userInfo) throws CheckException {
        int userId = CheckCondition.getSellerId(userInfo);
        if (!shopService.isShopExistByUserId(userId))
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "未开店");

        List<Goods> applyRecord = goodsDAO.findByShopId(shopService.getByUserId(userId).getShopId());
        List<JSONObject> applyObject = new ArrayList<>();
        for (Goods goods : applyRecord) {
            applyObject.add(transferPicAndTag(goods, true));
        }
        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(applyObject));
        return result;
    }

    @Override
    public Result approveForGoods(JSONObject request, JSONObject userInfo) throws CheckException {
        CheckCondition.checkAdmin(userInfo);
        //api:批准状态 isApproved  商品id shopId
        boolean isApproved = request.getString("isApproved").equalsIgnoreCase("true");
        int goodsId = request.getIntValue("goodsId");
        Goods goods = getByGoodsId(goodsId);

        if (goods == null) {
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "商品信息不存在");
        }
        if (goods.getState() != 0) {
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "商品请求不存在");
        }
        if (isApproved) {
            goods.setApproved();
            List<GoodsPic> goodsPicList = getNotValidPicByGoodsId(goodsId);
            for (GoodsPic goodsPic : goodsPicList) {
                goodsPic.setApproved();
                addOrUpdatePic(goodsPic);
            }
            goods.setActivityId(shopService.getByShopId(goods.getShopId()).getActivityId());
        } else {
            goods.setNotApproved();
        }
        addOrUpdate(goods);
        return new Result(HttpServletResponse.SC_OK);
    }

    public void reversePic(int goodsId) {
//        不能getAll,state=0还要通过!!!
        List<GoodsPic> goodsPicList = getValidPicByGoodsId(goodsId);
//        List<GoodsPic> goodsPicList = getAllPicByGoodsId(goodsId);

        for (GoodsPic goodsPic : goodsPicList) {
//            为了用修改而不是删除
//            state改为int
            goodsPic.setAbandon();
            addOrUpdatePic(goodsPic);
        }
//        goodsPicList = getNotValidPicByGoodsId(goodsId);
//        for (GoodsPic goodsPic : goodsPicList) {
//            goodsPic.setApproved();
//            addOrUpdatePic(goodsPic);
//        }
    }

    @Override
    public byte[] picToByte(MultipartFile picFile) throws CheckException {
        try {
            if (picFile.isEmpty())
                throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "图片不能为空");

            Image img = null;
            try {
                img = ImageIO.read(picFile.getInputStream());
                if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                    throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "不是图片");
                }
            } catch (Exception e) {
                throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "不是图片");
            } finally {
                img = null;
            }

//前端给Multifile  数据库存byte[]  返回前端String base64

//            requestGoods.setPic(picFile.getBytes());
//            setPic(requestGoods.getGoodsId(), picFile.getBytes());
            return picFile.getBytes();

        } catch (IOException e) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "图片错误");
        }
    }

    public JSONObject transferPicAndTag(Goods goods, boolean isValid) throws CheckException {
        try {
            String tag = tagService.getTagNameByTagId(Integer.parseInt(goods.getTag()));
            goods.setTag(tag);
        } catch (Exception e) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "商品类别有误");
        }


//        将Goods里面的byte[]改为string
//        如果没有，只删
        String[] strings = {"pic"};
        JSONArray picArray = new JSONArray();
        List<GoodsPic> goodsPicList;
        if (isValid)
            goodsPicList = getValidPicByGoodsId(goods.getGoodsId());
        else goodsPicList = getNotValidPicByGoodsId(goods.getGoodsId());
        for (GoodsPic goodsPic : goodsPicList) {
//            byte[] picBytes = goods.getPic();
//            if (picBytes == null || picBytes.length == 0) {
//                return MyUtils.changeObj(goods, strings);
//            }
            byte[] picBytes = goodsPic.getPic();
            if (picBytes == null || picBytes.length == 0) {
                continue;
            }
            picArray.add(MyUtils.getBase64Pic(picBytes));
        }

        JSONObject picObj = new JSONObject();
        picObj.put("pic", picArray);
//        picObj.put("pic", getBase64Pic(goods.getPic()));
//            throw new CheckException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "无对应图片");

        return MyUtils.changeObj(goods, picObj, strings);
    }
}
