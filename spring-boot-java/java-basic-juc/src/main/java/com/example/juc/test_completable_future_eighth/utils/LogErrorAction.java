package com.example.juc.test_completable_future_eighth.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.function.BiConsumer;

/**
 * @author iumyxF
 * @description: 发生异常时，根据是否为业务异常打印日志。
 * 跟CompletableFuture.whenComplete配合使用，不改变completableFuture的结果（正常OR异常）
 * @date 2023/11/30 9:36
 */
@Slf4j
public class LogErrorAction<R> extends AbstractLogAction<R> implements BiConsumer<R, Throwable> {

    public LogErrorAction(String methodName, Object... args) {
        super(methodName, args);
    }

    @Override
    public void accept(R result, Throwable throwable) {
        logResult(result, throwable);
    }
}
