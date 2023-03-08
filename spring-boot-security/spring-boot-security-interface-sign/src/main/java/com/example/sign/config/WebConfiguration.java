package com.example.sign.config;

import com.example.sign.handler.InterfaceSignInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @Date 2023/3/8 11:04
 * @Author fzy
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(signInterceptor());
    }

    @Bean
    public InterfaceSignInterceptor signInterceptor() {
        return new InterfaceSignInterceptor();
    }
}
