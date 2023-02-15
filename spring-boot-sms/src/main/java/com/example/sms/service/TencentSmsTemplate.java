package com.example.sms.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import com.example.sms.config.SmsProperties;
import com.example.sms.entities.SmsResult;
import com.example.sms.exception.SmsException;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20210111.models.SendStatus;
import lombok.SneakyThrows;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: 腾讯短信实现
 * @Date 2023/2/15 10:27
 * @Author fzy
 */
@Service
public class TencentSmsTemplate implements SmsTemplate {

    /**
     * 配置信息
     */
    private final SmsProperties properties;

    /**
     * 腾讯云短信发送端
     */
    private final SmsClient client;

    /**
     * 腾讯短信配置初始化
     *
     * @param smsProperties 短信配置信息
     */
    @SneakyThrows(Exception.class)
    public TencentSmsTemplate(SmsProperties smsProperties) {
        this.properties = smsProperties;
        Credential credential = new Credential(smsProperties.getAccessKeyId(), smsProperties.getAccessKeySecret());
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setReqMethod("POST");
        httpProfile.setConnTimeout(60);
        httpProfile.setEndpoint(smsProperties.getEndpoint());
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setSignMethod("HmacSHA256");
        clientProfile.setHttpProfile(httpProfile);
        this.client = new SmsClient(credential, "ap-guangzhou", clientProfile);
    }

    /**
     * @param phones     电话号(多个逗号分割)
     * @param templateId 模板id
     * @param param      模板对应参数
     *                   腾讯 需使用 模板变量顺序对应内容 例如: 1=1234, 1为模板内第一个参数
     * @return 短信返回实体
     */
    public SmsResult send(String phones, String templateId, Map<String, String> param) {
        if (StringUtils.isBlank(phones)) {
            throw new SmsException("手机号不能为空");
        }
        if (StringUtils.isBlank(templateId)) {
            throw new SmsException("模板ID不能为空");
        }
        SendSmsRequest req = new SendSmsRequest();
        //每个手机号添加+86
        Set<String> set = Arrays.stream(phones.split(",")).map(p -> "+86" + p).collect(Collectors.toSet());
        req.setPhoneNumberSet(ArrayUtil.toArray(set, String.class));
        //将模板参数转成数组
        if (CollUtil.isNotEmpty(param)) {
            req.setTemplateParamSet(ArrayUtil.toArray(param.values(), String.class));
        }
        req.setTemplateId(templateId);
        req.setSignName(properties.getSignName());
        req.setSmsSdkAppId(properties.getSdkAppId());
        try {
            SendSmsResponse resp = client.SendSms(req);
            SmsResult.SmsResultBuilder smsResultBuilder = SmsResult.builder()
                    .isSuccess(true)
                    .message("send success")
                    .response(resp);
            for (SendStatus sendStatus : resp.getSendStatusSet()) {
                if (!"Ok".equals(sendStatus.getCode())) {
                    smsResultBuilder.isSuccess(false).message(sendStatus.getMessage());
                    break;
                }
            }
            return smsResultBuilder.build();
        } catch (Exception e) {
            throw new SmsException(e.getMessage());
        }
    }

}
