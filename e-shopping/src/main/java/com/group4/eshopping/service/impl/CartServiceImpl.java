package com.group4.eshopping.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.dao.CartDAO;
import com.group4.eshopping.entity.Cart;
import com.group4.eshopping.entity.Goods;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import com.group4.eshopping.service.CartService;
import com.group4.eshopping.utils.MyUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartDAO cartDAO;
    @Autowired
    GoodsServiceImpl goodsService;

    @Override
    public Result addCart(Cart cart, JSONObject userInfo) {
        Goods goods = goodsService.getValidByGoodsId(cart.getGoodsId());
        if (goods == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无该商品");

        cart.setUserId(userInfo.getInteger("userId"));
        Cart pastCart = cartDAO.findByUserIdAndGoodsId(cart.getUserId(), cart.getGoodsId());
        if (pastCart != null) {
            pastCart.setNumber(pastCart.getNumber() + cart.getNumber());
            cart = pastCart;
        }
        cart.setUpdateAtAuto();
        addOrUpdate(cart);
        return new Result(HttpServletResponse.SC_OK);
    }

    @Override
    public void addOrUpdate(Cart cart) {
        cartDAO.save(cart);
    }

    @Override
    public List<Cart> getByGoodsId(int goodsId) {
        return cartDAO.findByGoodsId(goodsId);
    }

    @Override
    public Result getCart(JSONObject userInfo) {
        int userId = userInfo.getInteger("userId");
        List<Cart> carts = cartDAO.findByUserId(userId);
        JSONArray fullCarts = new JSONArray();
        for (Cart cart : carts) {
            JSONObject goods = goodsService.getByGoodsIdWithPic(cart.getGoodsId());
//            cart要放后面 避免number被覆盖
            JSONObject jsonObject = MyUtils.changeObj(goods, MyUtils.getJsonObj(cart));
            fullCarts.add(jsonObject);
        }
        Result result = new Result(HttpServletResponse.SC_OK);
//        result.setResArray(carts);
//        result.setResArray(JSONObject.parseArray(JSONObject.toJSONStringWithDateFormat(carts, "yyyy-MM-dd HH:mm:ss")));
        result.setReturnString(MyUtils.getJsonStringWithDate(fullCarts));
        return result;
    }

    @Override
    public Result deleteCart(int cartId, JSONObject userInfo) {
        int userId = userInfo.getInteger("userId");
        Cart cart = cartDAO.findByIdAndIsValidIsTrue(cartId);
        if (cart == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无该购物车或购物车无效");
        else if (cart.getUserId() != userId)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无该购物车或购物车无效");
        else cartDAO.delete(cart);
//        删除而不能修改state
//        以后还会买
        return new Result(HttpServletResponse.SC_OK);
    }

}

