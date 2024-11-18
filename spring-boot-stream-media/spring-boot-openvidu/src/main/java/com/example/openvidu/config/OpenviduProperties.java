package com.example.openvidu.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author fzy
 * @description:
 * @date 2024/11/18 15:59
 */
@Data
@Configuration
public class OpenviduProperties {

    @Value("${openvidu.url}")
    private String url;

    @Value("${openvidu.secret}")
    private String secret;
}
