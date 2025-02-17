package com.example.practice.leetcode.hot100.linkedlist;

import java.util.*;

/**
 * @author fzy
 * @description:
 * @date 2025/2/14 16:12
 */
public class LFUCache {

    /*
    请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。

    实现 LFUCache 类：

    LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
    int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
    void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最久未使用 的键。
    为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。

    当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。

    函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。

    设计：
    keyToVal：用于存储键到值的映射。
    keyToFreq：用于存储键到访问频率的映射。
    freqToKeys：用于存储访问频率到键列表的映射，使用 LinkedHashSet 保证插入顺序，方便在淘汰时选择最久未使用的元素。
     */

    // 缓存容量
    private final int capacity;
    // 当前缓存的大小
    private int size;

    private final Map<Integer, Integer> keyToVal;

    private final Map<Integer, Integer> keyToFreq;
    // 用TreeMap可以快速找到最小频率的entry
    private final TreeMap<Integer, LinkedHashSet<Integer>> freqToKeys;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.keyToVal = new HashMap<>(capacity);
        this.keyToFreq = new HashMap<>(capacity);
        this.freqToKeys = new TreeMap<>();
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        // 增加 key 对应的 freq
        increaseFreq(key);
        return keyToVal.get(key);
    }

    private void increaseFreq(int key) {
        int freq = keyToFreq.get(key);
        // 频率+1
        keyToFreq.put(key, freq + 1);
        // 更新 freqToKeys
        freqToKeys.get(freq).remove(key);
        // 如果旧频率的链表为空则移除
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);
        }
        // 移动到新的频率链表中
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);
    }

    public void put(int key, int value) {
        if (this.capacity == 0) {
            return;
        }
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            // 更新频率
            increaseFreq(key);
        } else {
            // 若 key 不存在，需要插入
            // 容量已满的话需要淘汰一个 freq 最小的 key
            if (this.size >= this.capacity) {
                removeMinFreqKey();
            }
            // 插入新的key value
            keyToVal.put(key, value);
            keyToFreq.put(key, 1);
            freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
            freqToKeys.get(1).add(key);
            // 插入新 key 后最小的 freq 肯定是 1
            //this.minFreq = 1;
            this.size++;
        }
    }

    /**
     * 淘汰使用频率最少的元素
     */
    private void removeMinFreqKey() {
        // freq 最小的 key 列表
        Map.Entry<Integer, LinkedHashSet<Integer>> minEntry = freqToKeys.firstEntry();
        LinkedHashSet<Integer> keyList = minEntry.getValue();
        // 最先被插入的那个 key 就是该被淘汰的 key
        int deletedKey = keyList.iterator().next();
        // 更新 FK 表
        keyList.remove(deletedKey);
        if (keyList.isEmpty()) {
            freqToKeys.remove(minEntry.getKey());
        }
        keyToVal.remove(deletedKey);
        keyToFreq.remove(deletedKey);
        this.size--;
    }

}
