package com.group4.eshopping.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TokenConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("^/api/goods/\\d+$", "/api/goods/{id:\\d+}")
                //https://blog.csdn.net/weixin_43008154/article/details/127879620
                //不拦截login和静态资源
                .excludePathPatterns("/api/login", "/api/shop/display", "/api/goods/display", "/api/activity",
                        "/api/register", "/api/static/**");
        //,"/login"
//        正则处理下面两个
//                .addPathPatterns("/api/goods/purchase");
//        "/api/goods/{goodsId}",
    }

    //    addResourceHandlers 方法用于添加静态资源处理器，
//    其中 /static/** 表示将以 /static 开头的请求映射到 classpath:/static/ 目录下的资源文件。
// 例如，访问 /static/js/app.js 将会返回 classpath:/static/js/app.js 文件中的内容。
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
    }

}
