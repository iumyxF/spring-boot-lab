package com.example.juc.test_completable_future_eighth.supplier;

import java.util.function.Supplier;

/**
 * @author iumyxF
 * @description:
 * @date 2023/11/28 16:55
 */
public class MySupplier implements Supplier<String> {

    @Override
    public String get() {
        String str1 = "A";
        String str2 = "B";
        String str3 = "C";
        String str4 = "D";
        return str1 + str2 + str3 + str4;
    }
}
