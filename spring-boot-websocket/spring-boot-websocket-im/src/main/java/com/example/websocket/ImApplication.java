package com.example.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: 聊天室案例 基于tomcat session的websocket
 * @Date 2023/2/27 14:12
 * @author iumyxF
 */
@SpringBootApplication
public class ImApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImApplication.class, args);
    }
}
