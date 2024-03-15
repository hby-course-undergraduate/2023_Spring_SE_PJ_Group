package com.group4.eshopping.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.dao.ShopDAO;
import com.group4.eshopping.entity.Shop;
import com.group4.eshopping.entity.Tag;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.ShopService;
import com.group4.eshopping.utils.CheckCondition;
import com.group4.eshopping.utils.MyUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopDAO shopDAO;
    @Autowired
    TagServiceImpl tagService;

    @Override
    public void addOrUpdate(Shop shop) {
        shopDAO.save(shop);
    }

    @Override
    public boolean isShopExistByUserId(int userId) {
        return shopDAO.findByUserId(userId) != null;
    }

    @Override
    public boolean isValidShopExistByUserId(int userId) {
        return shopDAO.findValidByUserId(userId) != null;
    }


    @Override
    public Shop getByUserId(int userId) {
        return shopDAO.findByUserId(userId);
    }

    @Override
    public Shop getByShopId(int shopId) {
        return shopDAO.findByShopId(shopId);
    }

    @Override
    public Shop getValidByShopId(int shopId) {
        return shopDAO.findValidByShopId(shopId);
    }

    @Override
    public Shop getValidByUserId(int userId) {
        return shopDAO.findValidByUserId(userId);
    }


    @Override
    public boolean isTagBelongToShop(String tagName, int shopId) {
        Tag thisTag = tagService.getTagByTagName(tagName);
        if (thisTag == null)
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "您的商店不存在该类别");
        Shop shop = getByShopId(shopId);
        String[] tags = shop.getTag().split(",");
        List<String> tagArray = Arrays.asList(tags);
        return tagArray.contains(Integer.toString(thisTag.getTagId()));

//        List<Shop> shopList = getShopsByTag(tag);
//        for (Shop shop : shopList) {
//            if (shop.getShopId() == shopId)
//                return true;
//        }
//        return false;
    }

    @Override
    public List<Shop> getShopsByTag(String tagName) {
        if (tagService.isTagExist(tagName)) {
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "tag不存在");
        }
        int id = tagService.getIdByTag(tagName);
        List<Shop> shopList = shopDAO.findByTagLike(id);
        return shopList;
    }

    @Override
    public List<Shop> getShopsByTag(int tagId) {
        return shopDAO.findByTagLikeAndStateIsTrue(tagId);
    }


    @Override
    public void deleteShopByUserId(int userId) {
        Shop shop = getByUserId(userId);
        if (shop == null)
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "无商店");
        shop.setNotApproved();
        addOrUpdate(shop);
    }

    @Override
    public Result shopChange(JSONObject changeObject, JSONObject userInfo) throws CheckException {
//        还要做审核界面，新增数据库，暂时摆烂了，而且没有记录修改时间
        int userId = CheckCondition.getSellerId(userInfo);

        if (!isShopExistByUserId(userId))
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "未注册商店");
        Shop shop = getByUserId(userId);

        String shopName = changeObject.getString("shopName");
        if (shopName != null) {
            shopName = CheckCondition.transferMeaning(shopName);
            CheckCondition.checkShopName(shopName);
            shop.setShopName(shopName);
        }

        String tag = changeObject.getString("tag");
        if (tag != null) {
            tag = HtmlUtils.htmlEscape(tag);
            CheckCondition.checkTag(tag);
            shop.setTag(tag);
        }

        String introduction = changeObject.getString("introduction");
        if (introduction != null) {
            introduction = HtmlUtils.htmlEscape(introduction);
            CheckCondition.checkIntroduction(introduction);
            shop.setIntroduction(introduction);
        }

        String address = changeObject.getString("address");
        if (address != null) {
            address = HtmlUtils.htmlEscape(address);
            CheckCondition.checkAddress(address);
            shop.setAddress(address);
        }

        addOrUpdate(shop);
        return new Result(HttpServletResponse.SC_OK);
    }

    @Override
    public Result checkShopInfo(JSONObject userInfo) {
        int userId = CheckCondition.getSellerId(userInfo);
        Shop shop = getByUserId(userId);
        if (shop == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "用户未开店");

        String[] strings = {"shopId", "userId", "fund", "registerDate"};
        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(MyUtils.changeObj(shop, strings)));
        return result;
    }

    @Override
    public Result displayAllShops(JSONObject userInfo) throws CheckException {
        CheckCondition.checkAdmin(userInfo);
        List<Shop> shops = shopDAO.findAll();

        List<JSONObject> shopList = new ArrayList<>();
        for (Shop shop : shops) {
            shopList.add(getShopObject(shop));
        }
        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(shopList));
        return result;
    }

    @Override
    public Result displayApprovedShop() {
        List<Shop> shops = shopDAO.findAllByStateIsApproved();

        List<JSONObject> shopList = new ArrayList<>();
        for (Shop shop : shops) {
            shopList.add(getShopObject(shop));
        }
        Result result = new Result(HttpServletResponse.SC_OK);
        result.setReturnString(MyUtils.getJsonStringWithDate(shopList));
        return result;
    }


    public JSONObject getShopObject(Shop shop) {
        try {
            List<Integer> tagList = MyUtils.getIntListFromTag(shop.getTag());
            List<String> tags = new ArrayList<>();
            for (int tagId : tagList) {
                tags.add(tagService.getTagNameByTagId(tagId));
            }
            String tag = MyUtils.joinByComma(tags);
            if (!Objects.equals(tag, "null"))
                shop.setTag(tag);
        } catch (Exception ignored) {
        }
        String[] strings = {};
        JSONObject addObj = new JSONObject();
//        addObj.put("fromName", getTransferName(transferAccount.getFromType(), transferAccount.getFromId()));
//        addObj.put("toName", getTransferName(transferAccount.getToType(), transferAccount.getToId()));

        return MyUtils.changeObj(shop, addObj, strings);

    }
}
