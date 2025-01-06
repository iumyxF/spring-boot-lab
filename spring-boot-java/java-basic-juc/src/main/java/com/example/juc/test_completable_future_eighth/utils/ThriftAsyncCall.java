package com.example.juc.test_completable_future_eighth.utils;

import com.example.juc.test_completable_future_eighth.utils.exception.TException;

/**
 * @author iumyxF
 * @description:
 * @date 2023/11/30 9:37
 */
@FunctionalInterface
public interface ThriftAsyncCall {
    void invoke() throws TException;
}
