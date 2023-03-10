package com.example.sms.config;

import com.example.sms.properties.SmsProperties;
import com.example.sms.service.SmsService;
import com.example.sms.service.impl.SmsServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @description: 自动配置类
 * 1. @Configuration： 定义一个配置类
 * 2. @EnableConfigurationProperties注解的作用是@ConfigurationProperties注解生效。
 * 如果只配置@ConfigurationProperties注解，在IOC容器中是获取不到properties配置文件转化的bean的
 * @Date 2023/3/10 10:15
 * @Author iumyxF
 */
@Configuration
@EnableConfigurationProperties({SmsProperties.class})
public class SmsAutoConfiguration {

    @Resource
    private SmsProperties smsProperties;

    @Bean
    @ConditionalOnMissingBean
    public SmsService smsService() {
        return new SmsServiceImpl(smsProperties.getAccessKeyId(), smsProperties.getAccessSecret());
    }

}
