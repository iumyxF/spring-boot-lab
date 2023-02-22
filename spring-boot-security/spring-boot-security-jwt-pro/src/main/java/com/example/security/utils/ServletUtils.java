package com.example.security.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: servlet工具类
 * @Date 2023/2/22 9:50
 * @Author fzy
 */
public class ServletUtils {

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     */
    public static void renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
