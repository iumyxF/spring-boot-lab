package com.example.files.service.upload.retry;

import java.util.concurrent.Callable;

/**
 * @author iumyx
 * @description:
 * @date 2024/6/27 10:43
 */
public interface RetryStrategy {

    /**
     * 重试
     *
     * @param uploadTask 任务
     * @return
     * @throws Exception
     */
    String doRetry(Callable<String> uploadTask) throws Exception;
}
