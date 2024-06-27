package com.example.files.service.upload.retry;

import java.util.EnumMap;

/**
 * @author iumyx
 * @description:
 * @date 2024/6/27 10:57
 */
public class RetryStrategyFactory {

    private final static EnumMap<RetryStrategyEnum, RetryStrategy> retryMap = new EnumMap<>(RetryStrategyEnum.class);

    static {
        retryMap.put(RetryStrategyEnum.NO, new NotRetryStrategy());
        retryMap.put(RetryStrategyEnum.BACK_OFF, new BackoffRetryStrategy());
    }

    public static RetryStrategy getDefaultInstance() {
        return getInstance(RetryStrategyEnum.BACK_OFF);
    }

    public static RetryStrategy getInstance(RetryStrategyEnum type) {
        return retryMap.get(type);
    }
}
