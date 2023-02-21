package com.example.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @description: 整合rabbitMq
 * @Date 2023/2/21 14:31
 * @Author fzy
 */
@EnableAsync
@SpringBootApplication
public class MqApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqApplication.class, args);
    }
}
