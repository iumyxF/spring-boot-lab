package com.example.sdk.config;

import com.example.sdk.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author feng
 * @date 2023/3/9 20:36
 */
@Configuration
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {
    private final HelloProperties helloProperties;

    /**
     * 通过构造方法注入配置属性对象HelloProperties
     *
     * @param helloProperties 配置属性
     */
    public HelloServiceAutoConfiguration(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    /**
     * 实例化HelloService并载入Spring IoC容器
     *
     * @return service
     */
    @Bean
    @ConditionalOnMissingBean
    public HelloService helloService() {
        return new HelloService(helloProperties.getName(), helloProperties.getAddress());
    }
}
