package com.group4.eshopping.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.group4.eshopping.dao.UserDAO;
import com.group4.eshopping.entity.User;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.UserService;
import com.group4.eshopping.token.TokenUtil;
import com.group4.eshopping.utils.CheckCondition;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public User getUsernameAndPassword(String username, String password) {
        return userDAO.getByUsernameAndPassword(username, password);
    }

    @Override
    public void addOrUpdate(User user) {
        userDAO.save(user);
    }

    @Override
    public boolean isUsernameExist(String username) {
        User user = getByName(username);
        return null != user;
    }

    @Override
    public boolean isIdCardExist(String idCard) {
        User user = getByIdCard(idCard);
        return null != user;
    }

    @Override
    public boolean isEmailExist(String email) {
        User user = getByEmail(email);
        return null != user;
    }

    @Override
    public boolean isPhoneExist(String phone) {
        User user = getByPhone(phone);
        return null != user;
    }

    @Override
    public User getByName(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public User getById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public User getByIdCard(String idCard) {
        return userDAO.findByIdCard(idCard);
    }

    @Override
    public User getByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public User getByPhone(String phone) {
        return userDAO.findByPhone(phone);
    }

    @Override
    public Result login(User requestUser) {
        //转义+加密
        String username = CheckCondition.transferMeaning(requestUser.getUsername());
        String password = CheckCondition.encryptPassword(requestUser.getPassword());

        User user = getUsernameAndPassword(username, password);
        if (null == user) {
            throw new CheckException(HttpServletResponse.SC_UNAUTHORIZED, "用户名或密码错误");
        }

        boolean isAdmin = user.getAdmin();
        boolean isSeller = user.getIsSeller();
        String token = TokenUtil.getToken(user.getId(), user.getRole());

//        System.out.println(token);
        Result result = new Result(HttpServletResponse.SC_OK);
        result.setLoginInfo(isAdmin, isSeller, token);
        return result;
    }


    @Override
    public Result changePassword(JSONObject request, JSONObject userInfo) throws CheckException {
        //验证旧密码
        int userId = userInfo.getInteger("userId");
        String pastPwd = CheckCondition.encryptPassword(request.getString("pastPassword"));
        User user = userDAO.findById(userId);
        if (!user.getPassword().equals(pastPwd))
            throw new CheckException(HttpServletResponse.SC_UNAUTHORIZED, "密码错误");

        //设置新密码
        String presentPwd = request.getString("presentPassword");
        CheckCondition.checkPassword(presentPwd);
        presentPwd = CheckCondition.encryptPassword(presentPwd);
        user.setPassword(presentPwd);
        addOrUpdate(user);
        return new Result(HttpServletResponse.SC_OK);
    }

    @Override
    public Result changeUsername(JSONObject request, JSONObject userInfo) throws CheckException {
//        查询用户名
        String username = CheckCondition.transferMeaning(request.getString("username"));
        CheckCondition.checkUsername(username);
        if (isUsernameExist(username)) {
            throw new CheckException(HttpServletResponse.SC_CONFLICT, "用户名已被注册");
        }

//        设置用户名
        int userId = userInfo.getInteger("userId");
        User user = userDAO.findById(userId);
        user.setUsername(username);
        addOrUpdate(user);
        return new Result(HttpServletResponse.SC_OK);
    }

    @Override
    public Result changeEmail(JSONObject request, JSONObject userInfo) {
//        验证旧邮箱
        int userId = userInfo.getInteger("userId");
        String pastEmail = CheckCondition.transferMeaning(request.getString("pastEmail"));
        User user = userDAO.findById(userId);
        if (!user.getEmail().equals(pastEmail))
            throw new CheckException(HttpServletResponse.SC_UNAUTHORIZED, "电子邮箱错误");

//        查询邮箱
        String presentEmail = CheckCondition.transferMeaning(request.getString("presentEmail"));
        CheckCondition.checkEmail(presentEmail);
        if (isEmailExist(presentEmail)) {
            throw new CheckException(HttpServletResponse.SC_CONFLICT, "电子邮箱已被注册");
        }

//        设置邮箱
        user.setEmail(presentEmail);
        addOrUpdate(user);
        return new Result(HttpServletResponse.SC_OK);
    }

    @Override
    public Result changePhone(JSONObject request, JSONObject userInfo) {
//        验证旧手机
        int userId = userInfo.getInteger("userId");
        String pastPhone = CheckCondition.transferMeaning(request.getString("pastPhone"));
        User user = userDAO.findById(userId);
        if (!user.getPhone().equals(pastPhone))
            throw new CheckException(HttpServletResponse.SC_UNAUTHORIZED, "手机号错误");

//        查询手机号
        String presentPhone = CheckCondition.transferMeaning(request.getString("presentPhone"));
        CheckCondition.checkPhone(presentPhone);
        if (isPhoneExist(presentPhone)) {
            throw new CheckException(HttpServletResponse.SC_CONFLICT, "手机号已被注册");
        }

//        设置邮箱
        user.setPhone(presentPhone);
        addOrUpdate(user);
        return new Result(HttpServletResponse.SC_OK);
    }

    @Override
    public Result checkUserInfo(JSONObject userInfo) {
        int userId = userInfo.getInteger("userId");
        User user = getById(userId);
        if (user == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "用户未创建账号");

        Result result = new Result(HttpServletResponse.SC_OK);

        String[] excludeProperties = {"password", "id", "admin","isSeller"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter = filters.addFilter();
        excludeFilter.addExcludes(excludeProperties);
        result.setReturnString(JSONObject.toJSONString(user, excludeFilter));
        return result;
    }
}




