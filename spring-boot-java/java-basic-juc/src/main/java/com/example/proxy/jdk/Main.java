package com.example.proxy.jdk;

/**
 * @author iumyx
 * @description:
 * @date 2024/3/9 14:16
 */
public class Main {

    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("java");
    }
}
