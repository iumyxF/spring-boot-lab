package com.example.sign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @description: 接口签名认证示例，分别使用拦截器和aop实现
 * @Date 2023/3/8 10:32
 * @author iumyxF
 */
@SpringBootApplication
public class SignApplication {
    public static void main(String[] args) {
        SpringApplication.run(SignApplication.class, args);
    }
}
