package com.example.sms.entities;

import lombok.Builder;
import lombok.Data;

/**
 * @description: 短信上传返回实体
 * @Date 2023/2/15 10:25
 * @author iumyxF
 */
@Data
@Builder
public class SmsResult {
    /**
     * 是否成功
     */
    private boolean isSuccess;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 实际响应体
     */
    private Object response;
}
