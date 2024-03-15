package com.group4.eshopping.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    //        加密的秘钥,相当于服务器私钥,一定保管好,不能泄露
    private static final String secret = "secret+cuAihCz53DZRjZwbsGcZJ2Ai6At";
    //        获取token的key,一般token存在请求头和响应头中
    public static final String tokenHeader = "tokenHeader";
    //        token的有效时间,不需要自己验证失效,当失效后,会自动抛出异常,改为10min
    private static final Long expTime = 60 * 60 * 1000L;

    public static String getToken(Integer id, String name) {
        //也可以添加这些之外的其他信息------------------------|
        //这里额外添加一个信息,尝试获取
        String uuid = id.toString();
        Map<String, Object> map = new HashMap<>();
        map.put("uuidKey", uuid);
        //也可以添加这些之外的其他信息------------------------|


//        byte[] keyBytes = Decoders.BASE64.decode(secret);
//        Key key = Keys.hmacShaKeyFor(keyBytes);
//        return Jwts.builder().setClaims(map).signWith(key).compact();

        JwtBuilder builder = Jwts.builder();
        String token = builder
                //设置加密的方式
                .signWith(SignatureAlgorithm.HS256, secret)
                //设置生成token的关键信息,可以将关键的信息加密
                .setId(uuid).setSubject(name)
                //设置token的签发时间和实效的时间
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expTime))

                //这个方法实际上是调用的构造方法,so在获取改内容时,就直接将Claims按照map处理
                //与函数getUUIDKey关联，可删
                .addClaims(map)
                //生成秘钥
                .compact();

        return token;
    }

    public static String getRole(String token) {
        String role = getTokenBody(token).getSubject();
//        System.out.println("role====" + role);
        return role;
    }


    public static Integer getUserId(String token) {
        String id = getTokenBody(token).getId();
//        System.out.println("id====" + id);
        return Integer.parseInt(id);
    }


    public static String getUUIDKey(String token, String key) {
        String UUIDkey = (String) getTokenBody(token).get(key);
//        System.out.println("UUIDkey====" + UUIDkey);
        return UUIDkey;
    }

    public static Claims getTokenBody(String token) {
        //这里得到是token中的载荷部分,也是具体信息的所在
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(secret).build()
//                .parseClaimsJws(token).getBody();
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token).getBody();
        return claims;
//        Object uuidkey = claims.get("uuidKey");
//        System.out.println(uuidkey);
    }
}
