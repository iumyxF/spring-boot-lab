package com.example.files.service.upload.retry;

import java.util.concurrent.Callable;

/**
 * @author iumyx
 * @description: 不重试
 * @date 2024/6/27 10:44
 */
public class NotRetryStrategy implements RetryStrategy {
    @Override
    public String doRetry(Callable<String> uploadTask) throws Exception {
        return uploadTask.call();
    }
}
