package com.example.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: 聊天室案例
 * Tomcat WebSocket使用的是Session作为会话,
 * 而Spring WebSocket使用的是WebSocketSession作为会话
 * @Date 2023/2/27 14:12
 * @Author fzy
 */
@SpringBootApplication
public class ImProApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImProApplication.class, args);
    }
}
