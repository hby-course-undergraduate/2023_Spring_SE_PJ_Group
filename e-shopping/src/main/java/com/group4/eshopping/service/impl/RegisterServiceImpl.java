package com.group4.eshopping.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.entity.Shop;
import com.group4.eshopping.entity.User;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.RegisterService;
import com.group4.eshopping.utils.CheckCondition;
import com.group4.eshopping.utils.MyUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.HtmlUtils;

import java.util.Objects;

//    因为要调用UserService和ShopService，不应该写在这两个类中，解耦合

//        shopName: *shopName,
//                tag: *tag,
//                introduction: *introduction,
//                fund: *fund,
//                idcard: *idcard,
//                address: *address
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    TagServiceImpl tagService;

    @Override
    public Result register(@RequestBody User requestUser) throws CheckException {
        String username = CheckCondition.transferMeaning(requestUser.getUsername());
        CheckCondition.checkUsername(username);

        String email = CheckCondition.transferMeaning(requestUser.getEmail());
        CheckCondition.checkEmail(email);

        String phone = CheckCondition.transferMeaning(requestUser.getPhone());
        CheckCondition.checkPhone(phone);

        String idCard = CheckCondition.transferMeaning(requestUser.getIdCard());
        CheckCondition.checkIdCard(idCard);

        CheckCondition.checkPassword(requestUser.getPassword());
        requestUser.setPassword(CheckCondition.encryptPassword(requestUser.getPassword()));

        //四个分开 是否需[实时显示]每一模块有无被使用
        if (userService.isUsernameExist(username)) {
            throw new CheckException(HttpServletResponse.SC_CONFLICT, "用户名已被注册");
        }
        if (userService.isEmailExist(email)) {
            throw new CheckException(HttpServletResponse.SC_CONFLICT, "电子邮箱已被注册");
        }
        if (userService.isPhoneExist(phone)) {
            throw new CheckException(HttpServletResponse.SC_CONFLICT, "手机号码已被注册");
        }
        if (userService.isIdCardExist(idCard)) {
            throw new CheckException(HttpServletResponse.SC_CONFLICT, "身份证号已被注册");
        }

        userService.addOrUpdate(requestUser);
//        上一步获得了id 务必不能修改此处
//        add new account
        accountService.addNewAccount(requestUser.getId(), "user");
        return new Result(HttpServletResponse.SC_OK);
    }


    @Override
    public Result applyForShop(Shop shop, JSONObject userInfo) throws CheckException {
        int userId = CheckCondition.getSellerId(userInfo);
        User user = userService.getById(userId);
//        User user = userService.getByIdCard(shop.getIdCard());
        if (null == user) {
            //理论上不会抛出这个错误
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "用户未注册");
        }
//        CheckCondition.checkIdCard(user.getIdCard());
        CheckCondition.checkIdCard(shop.getIdCard());
        if (!Objects.equals(shop.getIdCard(), user.getIdCard())) {
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "身份证号错误");
        }
        if (!user.getIsSeller()) {
            //理论上不会抛出这个错误 除非修改个人身份后token立即失效
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "用户非商家身份注册");
        }
//        validShop not shop
        if (shopService.isValidShopExistByUserId(user.getId())) {
            throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "每位用户仅可申请一次开店");
        }
        if (shopService.getByUserId(userId) != null) {
            if (shopService.getByUserId(userId).getState() != 2) {
                throw new CheckException(HttpServletResponse.SC_FORBIDDEN, "已有待审批的开店申请");
            }
        }

        String shopName = CheckCondition.transferMeaning(shop.getShopName());
        CheckCondition.checkShopName(shopName);
        shop.setShopName(shopName);

        String tag = HtmlUtils.htmlEscape(shop.getTag());
        CheckCondition.checkTag(tag);
        setTag(shop, tag);


        String introduction = HtmlUtils.htmlEscape(shop.getIntroduction());
        CheckCondition.checkIntroduction(introduction);
        shop.setIntroduction(introduction);

        String address = HtmlUtils.htmlEscape(shop.getAddress());
        CheckCondition.checkAddress(address);
        shop.setAddress(address);

        CheckCondition.checkFund(shop.getFund());

        shop.setUserId(user.getId());
        shop.setRegisterDate();
//        account
//        申请阶段
        accountService.registerFund(userId, shop.getFund());

        shopService.addOrUpdate(shop);
        return new Result(HttpServletResponse.SC_OK);
    }

    private void setTag(Shop shop, String tag) {
        String[] tags = tag.split(",");
        StringBuilder tagStr = new StringBuilder();
        for (String str : tags) {
            tagService.addTag(str);
            tagStr.append(tagService.getIdByTag(str)).append(",");
        }
        shop.setTag(tagStr.substring(0, tagStr.length() - 1));
//        tag = "";
//        List<Integer> list = new ArrayList<>();
//        for (String str : tags) {
//            list.add(tagService.getIdByTag(str));
//        }
//        tag = String.join(",", list.toArray());
//        shop.setTag(tag);
    }


    @Override
    public Result approveForShop(JSONObject request, JSONObject userInfo) throws CheckException {
        CheckCondition.checkAdmin(userInfo);
        //api:批准状态 isApproved  商户id shopId
        boolean isApproved = request.getString("isApproved").equalsIgnoreCase("true");
        int shopId = request.getIntValue("shopId");
        Shop shop = shopService.getByShopId(shopId);

        if (shop == null) {
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "商店信息不存在");
//            return new Result(returnCode.FORBIDDEN.code, "商店信息不存在");
        }
        if (shop.getState() != 0) {
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "商店请求不存在");
        }

        int userId = shopService.getByShopId(shopId).getUserId();
        double fund = shop.getFund();
        if (isApproved) {
            shop.setApproved();
            accountService.addNewAccount(userId, "shop");
            accountService.middleToProfit(fund, "registerFund");
        } else {
            shop.setNotApproved();
            accountService.registerDnuf(userId, fund);
        }
        shopService.addOrUpdate(shop);
        return new Result(HttpServletResponse.SC_OK);
    }

    @Override
    public Result changeShop(JSONObject changeObject, JSONObject userInfo) {
//        还要做审核界面，新增数据库，暂时摆烂了，而且没有记录修改时间
        int userId = CheckCondition.getSellerId(userInfo);

        Shop shop = shopService.getByUserId(userId);
        if (shop == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "未注册商店");

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
            setTag(shop, tag);
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

        shopService.addOrUpdate(shop);
        return new Result(HttpServletResponse.SC_OK);
    }

}
