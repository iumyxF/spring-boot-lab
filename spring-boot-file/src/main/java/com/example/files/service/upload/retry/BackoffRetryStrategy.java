package com.example.files.service.upload.retry;

import com.github.rholder.retry.*;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author iumyx
 * @description: 指数退避
 * @date 2024/6/27 10:44
 */
public class BackoffRetryStrategy implements RetryStrategy {

    @Override
    public String doRetry(Callable<String> uploadTask) throws Exception {
        Retryer<String> retryer = RetryerBuilder.<String>newBuilder()
                .retryIfExceptionOfType(Exception.class)
                // 指数退避
                .withWaitStrategy(WaitStrategies.exponentialWait(100, 5, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.neverStop())
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        System.out.println("重试次数: " + (attempt.getAttemptNumber() - 1));
                    }
                })
                .build();
        return retryer.call(uploadTask);
    }
}
