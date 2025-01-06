package com.example.juc.test_completable_future_eighth.utils;

import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

/**
 * @author iumyxF
 * @description:
 * @date 2023/11/30 9:35
 */
public class ExceptionUtils {
    /**
     * 提取真正的异常
     */
    public static Throwable extractRealException(Throwable throwable) {
        if (throwable instanceof CompletionException || throwable instanceof ExecutionException) {
            if (throwable.getCause() != null) {
                return throwable.getCause();
            }
        }
        return throwable;
    }
}
