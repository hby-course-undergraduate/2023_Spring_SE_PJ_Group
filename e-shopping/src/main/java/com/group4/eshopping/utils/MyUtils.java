package com.group4.eshopping.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.group4.eshopping.exception.CheckException;
import jakarta.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

public class MyUtils {
    public static LocalDateTime getNowTime() {
        //        很逆天，我也不知道为什么要这样写，ChatGPT说的，直接datetime会报错
        DateTime dateTime = new DateTime();
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonthOfYear(),
                dateTime.getDayOfMonth(), dateTime.getHourOfDay(), dateTime.getMinuteOfHour(), dateTime.getSecondOfMinute());
    }


    public static <T> String getJsonStringWithExclude(T t, String[] excludeProperties) {
        if (excludeProperties.length == 0) {
            return JSONObject.toJSONString(t);
        }
//        String[] excludeProperties = {"password", "id", "admin", "isSeller"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter = filters.addFilter();
        excludeFilter.addExcludes(excludeProperties);
        return JSONObject.toJSONString(t, excludeFilter);
    }

    public static <T> String getJsonStringWithDate(T t) {
//        不能序列化对象
        try {
            return JSONObject.toJSONStringWithDateFormat(t, "yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            throw new CheckException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "序列化错误");
        }
    }

    public static <T> String getJsonStringWithDateAndExclude(T t, String[] excludeProperties) {
        String jsonStr = getJsonStringWithExclude(t, excludeProperties);
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        return getJsonStringWithDate(jsonObj);
    }

    public static <T> String getJsonStringWithAll(T t, JSONObject addObject, String[] excludeProperties) {
//        先排除特定项，后合并
        String jsonStr = getJsonStringWithExclude(t, excludeProperties);
        jsonStr += JSONObject.toJSONString(addObject);
        if (jsonStr.contains("}{")) {
            jsonStr = jsonStr.replace("}{", ",");
        }
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        return getJsonStringWithDate(jsonObj);
    }

    //    以下为getJsonObject
    public static <T> JSONObject changeObj(T t, JSONObject addObject, String[] excludeProperties) {
        String jsonStr = getJsonStringWithDateAndExclude(t, excludeProperties);
        jsonStr += JSONObject.toJSONString(addObject);
        if (jsonStr.contains("}{")) {
            jsonStr = jsonStr.replace("}{", ",");
        }
        return JSONObject.parseObject(jsonStr);
    }

    public static <T> JSONObject changeObj(T t, JSONObject addObject) {
        String jsonStr = getJsonStringWithDate(t);
        jsonStr += JSONObject.toJSONString(addObject);
        if (jsonStr.contains("}{")) {
            jsonStr = jsonStr.replace("}{", ",");
        }
        return JSONObject.parseObject(jsonStr);
    }

    public static <T> JSONObject changeObj(T t, String[] excludeProperties) {
//        JSONObject jsonObject;
//        jsonObject.remove()
        String jsonStr = getJsonStringWithExclude(t, excludeProperties);
        return JSONObject.parseObject(jsonStr);
    }

    public static <T> JSONObject getJsonObj(T t) {
        return (JSONObject) JSONObject.toJSON(t);
    }

    public static <T> String getJsonStringWithAll(T t, JSONObject addObject) {
        String jsonStr = JSONObject.toJSONString(t) + JSONObject.toJSONString(addObject);
        if (jsonStr.contains("}{")) {
            jsonStr = jsonStr.replace("}{", ",");
        }
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        return getJsonStringWithDate(jsonObj);
    }


    public static String getBase64Pic(byte[] picByte) throws CheckException {
        return Base64.getEncoder().encodeToString(picByte);
    }

    //    public static String getPicture(MultipartFile picFile) {
//        try {
//            byte[] bytes = picFile.getBytes();
//            Path path = Paths.get("D:/pic/" + picFile.getOriginalFilename()+"sdad");
//            picFile.transferTo(path);
//            return path.toString();
//        } catch (Exception e) {
//            throw new CheckException(HttpServletResponse.SC_BAD_REQUEST, "图片错误");
//        }
//    }


    //  把list逗号连接并返回
    public static <T> String joinByComma(List<T> list) {
        if (list.isEmpty())
            return "null";
        StringBuilder tagStr = new StringBuilder();
        for (T str : list) {
            tagStr.append(str).append(",");
        }
        return tagStr.substring(0, tagStr.length() - 1);
    }

    public static List<Integer> getIntListFromTag(String tag) {
        List<Integer> retTag = new ArrayList<>();

        String[] tags = tag.split(",");
        try {
            for (String thisTag : tags) {
                retTag.add(Integer.valueOf(thisTag));
            }
        } catch (Exception ignored) {
        }
//        去重
        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(retTag);
        return new ArrayList<>(hashSet);
    }

    public static List<String> getStrListFromTag(String tag) {
        String[] tags = tag.split(",");
        List<String> retTag = new ArrayList<>(Arrays.asList(tags));
//        去重
        LinkedHashSet<String> hashSet = new LinkedHashSet<>(retTag);
        return new ArrayList<>(hashSet);
    }


    public static double getMoneyDouble(double a) {
        String str = String.format("%.2f", a);
        return Double.parseDouble(str);
    }

    public static <T> boolean isIntersectionEmpty(List<T> a, List<T> b) {
        a.retainAll(b);
        return a.isEmpty();
    }
}
