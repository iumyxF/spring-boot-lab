package com.example.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description: 短信配置文件
 * @Date 2023/2/15 9:49
 * @Author fzy
 */
@Data
@Component
@ConfigurationProperties(prefix = "sms")
public class SmsProperties {

    private Boolean enable;

    /**
     * 配置节点
     * 腾讯云 sms.tencentcloudapi.com
     */
    private String endpoint;

    /**
     * key
     */
    private String accessKeyId;

    /**
     * 密匙
     */
    private String accessKeySecret;

    /**
     * 短信签名
     */
    private String signName;

    /**
     * 短信应用ID (腾讯专属)
     */
    private String sdkAppId;
}
