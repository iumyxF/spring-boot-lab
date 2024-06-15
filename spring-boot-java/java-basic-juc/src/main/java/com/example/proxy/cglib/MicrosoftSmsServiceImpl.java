package com.example.proxy.cglib;

/**
 * @author iumyx
 * @description:
 * @date 2024/3/9 14:22
 */
public class MicrosoftSmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("send message ...");
        return message;
    }
}
