package com.example.sdk.service;

/**
 * @author feng
 * @date 2023/3/9 20:36
 */
public class HelloService {
    private final String name;
    private final String address;

    public HelloService(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String sayHello() {
        return "你好！我的名字叫 " + name + "，我来自 " + address;
    }
}
