package com.example.practice.leetcode.nowcoder.simulation;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author fzy
 * @description:
 * @date 9/1/2025 下午1:46
 */
public class BM100Lru2<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity;
    private LinkedHashMap<K, V> cache;

    public BM100Lru2(int capacity) {
        this.capacity = capacity;
        // 第三个参数为true表示按照访问顺序排序（最近访问的元素放在最后）
        this.cache = new LinkedHashMap<>(capacity, LOAD_FACTOR, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > BM100Lru2.this.capacity;
            }
        };
    }

    public V get(K key) {
        return cache.get(key);
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }
}
