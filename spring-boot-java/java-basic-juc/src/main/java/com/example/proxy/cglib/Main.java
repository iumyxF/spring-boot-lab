package com.example.proxy.cglib;

/**
 * @author fzy
 * @description:
 * @date 2024/3/9 14:26
 */
public class Main {
    public static void main(String[] args) {
        SmsService proxy = (SmsService) CglibProxyFactory.getProxy(MicrosoftSmsServiceImpl.class);
        proxy.send("microsoft");
    }
}
