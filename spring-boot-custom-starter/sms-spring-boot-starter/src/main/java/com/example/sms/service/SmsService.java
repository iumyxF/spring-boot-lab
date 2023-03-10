package com.example.sms.service;

/**
 * @description: 短信服务
 * @Date 2023/3/10 10:12
 * @Author iumyxF
 */
public interface SmsService {

    /**
     * 发送短信
     *
     * @param phone        手机号码
     * @param signName     短信签名
     * @param templateCode 短信模板
     * @param msg          短信内容
     */
    void send(String phone, String signName, String templateCode, String msg);
}
