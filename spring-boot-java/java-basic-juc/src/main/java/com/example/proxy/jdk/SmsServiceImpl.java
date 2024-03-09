package com.example.proxy.jdk;

/**
 * @author fzy
 * @description:
 * @date 2024/3/9 14:14
 */
public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("send message ...");
        return message;
    }
}
