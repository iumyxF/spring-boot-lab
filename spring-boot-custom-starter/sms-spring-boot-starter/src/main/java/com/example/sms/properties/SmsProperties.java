package com.example.sms.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description: 短信配置信息
 * @Date 2023/3/10 10:07
 * @Author iumyxF
 */
@ConfigurationProperties(prefix = "sdk.sms")
public class SmsProperties {
    /**
     * 短信发送接口的账户
     */
    private String accessKeyId;

    /**
     * 短信发送接口的凭证
     */
    private String accessSecret;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }
}