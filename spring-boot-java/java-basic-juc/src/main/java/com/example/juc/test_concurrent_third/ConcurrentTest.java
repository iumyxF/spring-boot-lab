package com.example.juc.test_concurrent_third;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author iumyxF
 * @description:
 * @date 2024/4/18 13:48
 */
public class ConcurrentTest {

    public static void main(String[] args) {
        // 线程安全的HashMap\TreeMap\TreeSet
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        ConcurrentSkipListMap<String, Object> skipListMap = new ConcurrentSkipListMap<>();
        ConcurrentSkipListSet<String> skipListSet = new ConcurrentSkipListSet<>();
    }
}
