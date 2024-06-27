package com.example.files.service.upload.retry;

/**
 * @author iumyx
 * @description:
 * @date 2024/6/27 11:10
 */
public enum RetryStrategyEnum {

    /**
     * 不重试
     */
    NO,

    /**
     * 指数退避策略重试
     */
    BACK_OFF
}
