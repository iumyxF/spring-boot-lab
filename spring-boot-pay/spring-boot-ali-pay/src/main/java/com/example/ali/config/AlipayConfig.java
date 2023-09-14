package com.example.ali.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author iumyxF
 * @description: ali支付配置属性
 * @date 2023/6/26 9:29
 */
@Configuration
@ConfigurationProperties("alipay.config")
@Data
public class AlipayConfig {

    /**
     * 应用 APPID
     */
    private String appId;

    /**
     * 商户私钥，即应用私钥
     */
    private String privateKey;

    /**
     * 支付宝公钥
     */
    private String publicKey;

    /**
     * 服务器异步通知页面路径，为 http://格式的完整路径，不能加 ?id=123 这类自定义参数
     */
    private String notifyUrl;

    /**
     * 页面跳转同步通知页面路径，为 http://格式的完整路径，不能加 ?id=123 这类自定义参数
     */
    private String returnUrl;

    /**
     * 签名方式
     */
    private String signType;

    /**
     * 字符编码格式
     */
    private String charset;

    /**
     * 支付宝网关
     */
    private String gatewayUrl;
}
