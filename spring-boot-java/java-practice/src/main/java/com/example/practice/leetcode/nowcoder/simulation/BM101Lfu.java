package com.example.practice.leetcode.nowcoder.simulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author fzy
 * @description:
 * @date 2025/1/10 14:09
 */
public class BM101Lfu {

    /*
    一个缓存结构需要实现如下功能。
    set(key, value)：将记录(key, value)插入该结构
    get(key)：返回key对应的value值
    但是缓存结构中最多放K条记录，如果新的第K+1条记录要加入，就需要根据策略删掉一条记录，然后才能把新记录加入。
    这个策略为：在缓存结构的K条记录中，哪一个key从进入缓存结构的时刻开始，被调用set或者get的次数最少，就删掉这个key的记录；
    如果调用次数最少的key有多个，上次调用发生最早的key被删除
    这就是LFU缓存替换算法。实现这个结构，K作为参数给出

    若opt=1，接下来两个整数x, y，表示set(x, y)
    若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1
    对于每个操作2，返回一个答案

    [[1,1,1],[1,2,2],[1,3,2],[1,2,4],[1,3,5],[2,2],[1,4,4],[2,1]],3
    [4,-1]
    说明：
    在执行"1 4 4"后，"1 1 1"被删除。因此第二次询问的答案为-1
     */

    HashMap<Integer, Node> map;
    int size;
    AtomicLong sysTime = new AtomicLong(0);

    public int[] LFU(int[][] operators, int k) {
        if (k <= 0) {
            return new int[]{};
        }
        // 初始化
        ArrayList<Integer> list = new ArrayList<>();
        map = new HashMap<>(k);
        size = k;
        // 操作
        for (int i = 0; i < operators.length; i++) {
            int[] operator = operators[i];
            if (operator[0] == 1) {
                set(operator[1], operator[2]);
            } else {
                list.add(get(operator[1]));
            }
        }
        // 转换成数组
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            // 更新
            Node node = map.get(key);
            node.value = value;
            node.count = node.count + 1;
            node.time = sysTime.incrementAndGet();
        } else {
            if (map.size() == size) {
                // 淘汰元素
                eliminate();
            }
            map.put(key, new Node(key, value));
        }
    }

    private void eliminate() {
        Integer minUsedKey = null;
        int minUsedCount = Integer.MAX_VALUE;
        long minUsedTime = Long.MAX_VALUE;
        for (Node node : map.values()) {
            if (node.count < minUsedCount) {
                minUsedCount = node.count;
                minUsedTime = node.time;
                minUsedKey = node.key;
            } else if (node.count == minUsedCount) {
                // 如果使用次数相同 则比较时间
                if (node.time < minUsedTime) {
                    minUsedTime = node.time;
                    minUsedKey = node.key;
                }
            }
        }
        if (null != minUsedKey) {
            map.remove(minUsedKey);
        }
    }

    public Integer get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        node.time = sysTime.incrementAndGet();
        node.count = node.count + 1;
        return node.value;
    }

    class Node {
        int key;
        int value;
        int count;
        long time;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.count = 1;
            this.time = sysTime.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        //[[1,1,1],[1,2,2],[1,3,2],[1,2,4],[1,3,5],[2,2],[1,4,4],[2,1]],3 ==> [4,-1]
        //[[1,1,1],[1,2,2],[1,3,3],[1,4,4],[2,4],[2,3],[2,2],[2,1],[1,5,5],[2,4]],4  ==> [4,3,2,1,-1]
        BM101Lfu s = new BM101Lfu();
        ArrayList<List<Integer>> list = new ArrayList<>();
        // 用例1
        list.add(Arrays.asList(1, 1, 1));
        list.add(Arrays.asList(1, 2, 2));
        list.add(Arrays.asList(1, 3, 2));
        list.add(Arrays.asList(1, 2, 4));
        list.add(Arrays.asList(1, 3, 5));
        list.add(Arrays.asList(2, 2));
        list.add(Arrays.asList(1, 4, 4));
        list.add(Arrays.asList(2, 1));

        //用例2
        //list.add(Arrays.asList(1, 1, 1));
        //list.add(Arrays.asList(1, 2, 2));
        //list.add(Arrays.asList(1, 3, 3));
        //list.add(Arrays.asList(1, 4, 4));
        //list.add(Arrays.asList(2, 4));
        //list.add(Arrays.asList(2, 3));
        //list.add(Arrays.asList(2, 2));
        //list.add(Arrays.asList(2, 1));
        //list.add(Arrays.asList(1, 5, 5));
        //list.add(Arrays.asList(2, 4));

        int[][] operations = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            List<Integer> integers = list.get(i);
            int[] arr = new int[integers.size()];
            for (int j = 0; j < integers.size(); j++) {
                arr[j] = integers.get(j);
            }
            operations[i] = arr;
        }
        String res = Arrays.toString(s.LFU(operations, 3));
        //String res = Arrays.toString(s.LFU(operations, 4));
        System.out.println(res);
    }

}
