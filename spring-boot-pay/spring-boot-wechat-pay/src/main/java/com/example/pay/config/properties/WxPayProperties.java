package com.example.pay.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author fzy
 * @description:
 * @date 2024/8/15 11:43
 */
@Data
@ConfigurationProperties(prefix = "wx.pay")
public class WxPayProperties {

    /**
     * 设置微信公众号或者小程序等的appid
     */
    private String appId;

    /**
     * 微信支付商户号
     */
    private String mchId;

    /**
     * 微信支付商户密钥
     */
    private String mchKey;

    /**
     * apiClient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
     */
    private String keyPath;

    /**
     * 回调地址
     */
    private String notifyUrl;
}
