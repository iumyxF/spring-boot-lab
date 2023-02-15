package com.example.sms.config;

import com.example.sms.service.SmsTemplate;
import com.example.sms.service.TencentSmsTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 短信配置类
 * @Date 2023/2/15 10:23
 * @Author fzy
 */

@Configuration
@ConditionalOnProperty(value = "sms.enabled", havingValue = "true")
public class SmsConfig {

    /**
     * 配置腾讯短信模板
     *
     * @param smsProperties 短信配置信息
     * @return 短信模板
     */
    @ConditionalOnClass(com.tencentcloudapi.sms.v20190711.SmsClient.class)
    public SmsTemplate tencentSmsTemplate(SmsProperties smsProperties) {
        return new TencentSmsTemplate(smsProperties);
    }
}
