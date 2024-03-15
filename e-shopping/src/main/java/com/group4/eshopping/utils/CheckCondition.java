package com.group4.eshopping.utils;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.exception.CheckException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import java.util.Objects;
import java.util.regex.Pattern;

//成功不管 失败抛出异常+错误信息
public class CheckCondition {

    public static void checkUsername(String username) throws CheckException {
        if ((username == null) || (username.isEmpty())) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "用户名不能为为空");
        }
        if (!Pattern.matches("^[a-zA-Z0-9_]{3,10}$", username)) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "用户名格式错误");
        }
    }


    public static void checkPassword(String password) throws CheckException {
        if ((password == null) || (password.isEmpty())) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "密码不能为为空");
        }
        if (!Pattern.matches("((?=.*[a-zA-Z])(?=.*\\d)|(?=[a-zA-Z])(?=.*[-\\_])|(?=.*\\d)(?=.*[-\\_]))[a-zA-Z\\d-\\_]{6,32}", password)) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "密码格式错误");
        }
    }


    public static void checkPhone(String phone) throws CheckException {
//        第一位数字肯定是1	1
//        第二位数字只能是3或4或5或6或7或8或9	 [3-9]
//        剩下的九位数字可以是0-9之间任意一位数字 \d {9}
        if ((phone == null) || (phone.isEmpty())) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "手机号码不能为为空");
        }
        if (!Pattern.matches("^1[3-9]\\d{9}$", phone)) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "手机号码格式错误");
        }
    }


    public static void checkIdCard(String idCard) throws CheckException {
        if ((idCard == null) || (idCard.isEmpty())) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "身份证号不能为空");
        }
        try {
            if (!IdCardVerification.IDCardValidate(idCard)) {
                throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "身份证号格式错误");
            }
        } catch (Exception e) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "身份证号格式错误");
        }
    }


    public static void checkShopName(String shopName) throws CheckException {
        if ((shopName == null) || (shopName.isEmpty())) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "商店名称不能为为空");
        }
        if (shopName.length() >= 13) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "商店名称过长");
        }
    }


    public static void checkTag(String tag) {
        if ((tag == null) || (tag.isEmpty())) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "商店类别不能为为空");
        }
        //数据库大小
        if (tag.length() >= 256) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "商店类别过长");
        }
    }


    public static void checkIntroduction(String introduction) {
        if ((introduction == null) || (introduction.isEmpty())) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "商店简介不能为为空");
        }
        if (introduction.length() >= 129) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "商店简介过长");
        }
    }

    public static void checkDescription(String description) {
        if ((description == null) || (description.isEmpty())) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "商品简介不能为为空");
        }
        if (description.length() >= 129) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "商品简介过长");
        }
    }


    public static void checkAddress(String address) {
        if ((address == null) || (address.isEmpty())) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "备案地址不能为为空");
        }
        if (address.length() >= 33) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "备案地址过长");
        }
    }


    public static void checkEmail(String email) {
        if ((email == null) || (email.isEmpty())) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "email不能为为空");

        }
        if (!Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email)) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "email格式错误");
        }
    }


    public static void checkFund(double fund) {
        if (!(fund >= 1000.0)) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "最小注册资金为1000元");
        }
    }


    public static String encryptPassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }


    public static String transferMeaning(String str) {
//        对 html 标签进行转义，防止 XSS 攻击
        try {
            return HtmlUtils.htmlEscape(str);
        } catch (Exception e) {
            return "";
        }

    }

    public static void checkPic(MultipartFile pic) throws CheckException{
        if (pic.isEmpty())
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST,"图片不能为空");
        long picSize = pic.getSize();
    }


    public static void checkAdmin(JSONObject userInfo) throws CheckException {
        String role = userInfo.getString("role");
        int userId = userInfo.getInteger("userId");
        if (!Objects.equals(role, "admin") || userId != 1) {
            throw new CheckException(HttpServletResponse.SC_UNAUTHORIZED, "该用户不是管理员");
        }
    }


    public static int getSellerId(JSONObject userInfo) throws CheckException {
        String role = userInfo.getString("role");
        if (!Objects.equals(role, "seller")) {
            throw new CheckException(HttpServletResponse.SC_UNAUTHORIZED, "该用户不是商家");
        }
        return userInfo.getInteger("userId");
    }

    public static int getUserId(JSONObject userInfo) {
//        不会伪造token,认为得到的userId是安全的
        return userInfo.getInteger("userId");
    }

    public static void checkPrice(double price) {
        if (price <= 0.0) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "商品价格应当为正数");
        }
    }

    public static void checkNumber(double number) {
        if (number <= 0) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "商品数量应当为正数");
        }
    }

    public static void checkGoodsName(String goodsName) {
        if ((goodsName == null) || (goodsName.isEmpty())) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "商品名称不能为为空");
        }
        if (goodsName.length() >= 33) {
            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "商品名称过长");
        }
    }
}


