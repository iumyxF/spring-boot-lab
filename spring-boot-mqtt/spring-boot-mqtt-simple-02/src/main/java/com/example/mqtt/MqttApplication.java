package com.example.mqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * @author iumyxF
 * @description:
 * @date 2023/10/21 10:30
 */
@SpringBootApplication
public class MqttApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqttApplication.class, args);
    }

    /**
     * 添加 ConfigurableServletWebServerFactory 解决接口传输JSON信息时特殊字符的限制
     * {@see <a href="https://blog.csdn.net/tianzhonghaoqing/article/details/125806385"/>}
     */
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "|{}[]\\"));
        return factory;
    }
}
