package com.group4.eshopping.utils;

import com.alibaba.fastjson.JSONObject;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.result.Result;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class WriteStrToRes {
    public static void writeStr(HttpServletResponse httpServletResponse, String str) throws IOException {
        if (str == null) {
            return;
        }
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json");
        PrintWriter out = httpServletResponse.getWriter();
        out.println(str);
        out.close();
    }

    public static void writeStatus(HttpServletResponse httpServletResponse, int code) {
        httpServletResponse.setStatus(code);
        //获取输出流并将对象写入响应体
    }

    //exception->str
    public static void writeStatusAndStr(HttpServletResponse httpServletResponse, CheckException checkException) throws IOException {
        writeStatus(httpServletResponse, checkException.getCode());
        JSONObject wrongObject = new JSONObject();
        wrongObject.put("msg", checkException.getWrongMsg());
        writeStr(httpServletResponse, wrongObject.toJSONString());
    }

    //result->all
    public static void writeResultAll(HttpServletResponse httpServletResponse, Result result) throws IOException {
        int code = result.getCode();
        if (code != 0)
            writeStatus(httpServletResponse, code);
        writeStr(httpServletResponse, result.jsonObjectToString());
    }
//    String agent = (String)request.getHeader("USER-AGENT");
//
//					 if (agent != null && agent.indexOf("MSIE") == -1) {// FF
//        String enableFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(filename.getBytes("UTF-8")))) + "?=";
//        response.setHeader("Content-Disposition", "inline; filename=" + enableFileName);
//    } else { // IE
//        String enableFileName = new String(filename.getBytes("GBK"), "ISO-8859-1");
//        response.setHeader("Content-Disposition", "inline; filename=" + enableFileName);
//    }
//
//					 response.setContentType("application/pdf");
//
//    // 读出文件到response
//    // 这里是先需要把要把文件内容先读到缓冲区
//    // 再把缓冲区的内容写到response的输出流供用户下载
//    FileInputStream fileInputStream = new FileInputStream(f);
//    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
//    byte[] b = new byte[bufferedInputStream.available()];
//					bufferedInputStream.read(b);
//    OutputStream outputStream = response.getOutputStream();
//					outputStream.write(b);
//    // 人走带门
//					bufferedInputStream.close();
//					outputStream.flush();
//					outputStream.close();


//    //result->str
//    public static void writeReturnStringAndStatus(HttpServletResponse httpServletResponse, Result result) throws IOException {
//        writeStatus(httpServletResponse, result.getCode());
//        writeStr(httpServletResponse, result.getReturnString());
//    }
//
//    //result->jsonObject
//    public static void writeStatusAndStr(HttpServletResponse httpServletResponse, Result result) throws IOException {
//        writeStatus(httpServletResponse, result.getCode());
//        writeStr(httpServletResponse, result.jsonObjectToString());
//    }


//        if (!result.getJsonObject().isEmpty())
//            writeStr(httpServletResponse, result.jsonObjectToString());
//        if (!result.getReturnString().isEmpty())
//            writeStr(httpServletResponse, result.getReturnString());

}
