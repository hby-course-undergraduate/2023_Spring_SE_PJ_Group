package com.group4.eshopping.token;

import com.group4.eshopping.result.Result;
import com.group4.eshopping.utils.WriteStrToRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    @ResponseBody
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        //获取token
        String token = request.getHeader(TokenUtil.tokenHeader);
//        System.out.println("token====================" + token);

        if (token == null || token.isEmpty()) {
            System.out.println("token lost");
            Result result = new Result(httpServletResponse.SC_UNAUTHORIZED, "未携带token");
            WriteStrToRes.writeResultAll(httpServletResponse, result);
            return false;
        }
        try {
//            获取token信息
            TokenUtil.getTokenBody(token);
            String role = TokenUtil.getRole(token);
            request.setAttribute("role", role);
            Integer id = TokenUtil.getUserId(token);
            request.setAttribute("userId", id);
            System.out.println("token exist  " + role + ":" + id);
            return true;
//        request.getServletContext().setAttribute("getServletContext", "getServletContext");
//        TokenUtil.getUUIDKey(token,"uuidKey");
        } catch (Exception e) {
//            e.printStackTrace();
            Result result = new Result(httpServletResponse.SC_UNAUTHORIZED, "token无效或已过期");
            WriteStrToRes.writeResultAll(httpServletResponse, result);
//            httpServletResponse.sendRedirect("https://www.fudan.edu.cn/");
//            response.sendRedirect("https://ys.mihoyo.com/");
//            response.sendRedirect("http://127.0.0.1:8080/login");
//            httpServletResponse.setStatus(httpServletResponse.SC_FORBIDDEN);
//            httpServletResponse.setContentType("application/json");
//            PrintWriter out = httpServletResponse.getWriter();
//            result = new Result("token is wrong or token is out of date");
//            out.println(result.jsonObjectToString());
//            out.close();
            return false;
        }
    }
}
