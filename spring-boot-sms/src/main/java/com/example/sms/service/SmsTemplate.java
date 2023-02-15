package com.example.sms.service;

import com.example.sms.entities.SmsResult;

import java.util.Map;

/**
 * @description: 短信模板
 * @Date 2023/2/15 10:25
 * @Author fzy
 */
public interface SmsTemplate {

    /**
     * 发送短信
     *
     * @param phones     电话号(多个逗号分割)
     * @param templateId 模板id
     * @param param      模板对应参数
     *                   腾讯 需使用 模板变量顺序对应内容 例如: 1=1234, 1为模板内第一个参数
     * @return 返回体
     */
    SmsResult send(String phones, String templateId, Map<String, String> param);

}
