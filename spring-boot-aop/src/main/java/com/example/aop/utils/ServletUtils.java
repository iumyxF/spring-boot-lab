package com.example.aop.utils;

import cn.hutool.extra.servlet.ServletUtil;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 客户端工具类
 * @Date 2023/2/13 10:50
 * @author iumyxF
 */
public class ServletUtils extends ServletUtil {

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取客户端IP地址
     */
    public static String getClientIp() {
        return getClientIP(getRequest());
    }
}
