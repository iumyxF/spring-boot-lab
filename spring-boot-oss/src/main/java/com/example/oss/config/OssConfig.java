package com.example.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author iumyxF
 * @description:
 * @date 2023/5/29 16:44
 */
@Data
@Component
@ConfigurationProperties(prefix = "oss")
public class OssConfig {

    private String endpoint;

    private String bucketName;

    private String accessKeyId;

    private String accessKeySecret;
}
