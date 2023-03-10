package com.example.sms.service.impl;

import com.example.sms.service.SmsService;

/**
 * @description: 接口实现
 * @Date 2023/3/10 10:13
 * @Author iumyxF
 */
public class SmsServiceImpl implements SmsService {

    /**
     * 短信发送接口的账户
     */
    private String accessKeyId;
    /**
     * 短信发送接口的凭证
     */
    private String accessSecret;

    public SmsServiceImpl(String accessKeyId, String accessSecret) {
        this.accessKeyId = accessKeyId;
        this.accessSecret = accessSecret;
    }

    @Override
    public void send(String phone, String signName, String templateCode, String msg) {
        System.out.println("接入短信系统，accessKeyId = " + accessKeyId + ",accessSecret = " + accessSecret);
        System.out.println("短信发送，phone=" + phone + ",signName=" + signName + ",templateCode=" + templateCode + ",msg=" + msg);
    }
}
