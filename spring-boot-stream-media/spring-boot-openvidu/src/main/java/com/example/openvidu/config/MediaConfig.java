package com.example.openvidu.config;

import io.openvidu.java.client.OpenVidu;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author fzy
 * @description:
 * @date 2024/11/19 10:43
 */
@Configuration
public class MediaConfig {

    @Resource
    private OpenviduProperties openviduProperties;

    @Bean
    public OpenVidu openVidu() {
        return new OpenVidu(openviduProperties.getUrl(), openviduProperties.getSecret());
    }
}
