package com.example.practice.leetcode.nowcoder.simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author fzy
 * @description:
 * @date 9/1/2025 上午10:54
 */
public class BM100Lru {

    /*
    设计LRU(最近最少使用)缓存结构，该结构在构造时确定大小，假设大小为 capacity ，操作次数是 n ，并有如下功能:
    1. Solution(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
    2. get(key)：如果关键字 key 存在于缓存中，则返回key对应的value值，否则返回 -1 。
    3. set(key, value)：将记录(key, value)插入该结构，如果关键字 key 已经存在，则变更其数据值 value，
    如果不存在，则向缓存中插入该组 key-value ，如果key-value的数量超过capacity，弹出最久未使用的key-value

    1. 方式1，下面自己写的这种
    2. 方式2，用LinkedHashMap实现 BM100Lru2.class


     */
    Map<Integer, Integer> dataMap;

    Map<Integer, Long> usedCountMap = new HashMap<>();

    AtomicLong count = new AtomicLong(0);

    Integer capacity;

    public BM100Lru(int capacity) {
        this.capacity = capacity;
        this.dataMap = new HashMap<>(capacity);
    }

    public int get(int key) {
        Integer value = dataMap.get(key);
        if (null == value) {
            return -1;
        } else {
            // update
            usedCountMap.put(key, count.incrementAndGet());
            return value;
        }
    }

    public void set(int key, int value) {
        if (dataMap.size() == capacity) {
            weedOut();
        }
        dataMap.put(key, value);
        usedCountMap.put(key, count.incrementAndGet());
    }

    private void weedOut() {
        Long minValue = Long.MAX_VALUE;
        Integer minValueKey = null;
        for (Map.Entry<Integer, Long> entry : usedCountMap.entrySet()) {
            if (entry.getValue() < minValue) {
                minValueKey = entry.getKey();
                minValue = entry.getValue();
            }
        }
        if (null != minValueKey) {
            usedCountMap.remove(minValueKey);
            dataMap.remove(minValueKey);
        }
    }

    public static void main(String[] args) {
        BM100Lru lru = new BM100Lru(2);
        lru.set(2, 2);
        lru.set(1, 1);
        //lru.get(1);
        lru.set(3, 3);
        //lru.set(2,0);
        //lru.set(3,0);
        //lru.set(4,0);
        System.out.println();
    }
}
