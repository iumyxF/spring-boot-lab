package com.example.security.context;

import org.springframework.security.core.Authentication;

/**
 * @description: 身份认证信息ThreadLocal
 * @Date 2023/2/22 10:24
 * @Author fzy
 */
public class AuthenticationContextHolder {

    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();

    public static Authentication getContext() {
        return contextHolder.get();
    }

    public static void setContext(Authentication context) {
        contextHolder.set(context);
    }

    public static void clearContext() {
        contextHolder.remove();
    }

}
