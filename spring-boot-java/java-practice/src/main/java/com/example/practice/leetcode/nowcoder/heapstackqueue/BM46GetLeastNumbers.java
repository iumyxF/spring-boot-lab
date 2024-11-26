package com.example.practice.leetcode.nowcoder.heapstackqueue;

import java.util.*;

/**
 * @author fzy
 * @description:
 * @date 2024/11/26 15:57
 */
public class BM46GetLeastNumbers {

    /*
    描述
    给定一个长度为的可能有重复值的数组，找出其中不去重的最小的k个数。例如数组
    元素是4,5,1,6,2,7,3,8 这8个数字，则最小的4个数字是1,2,3,4（任意顺序皆可）.
    数据范围：0 <= k , n <= 10000,数组中每个数的大小 0 <= val <= 1000
    要求：空间复杂度O(n),时间复杂度O(nlogk)

    分析
    1. 保存最小数字的容器要能O(1) 获取当前容器内最小值
    2. 可能会频繁替换元素
     */

    public ArrayList<Integer> solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || input.length == 0 || k == 0) {
            return list;
        }
        // 记录队列中最大值的元素下标
        int maxIndex = 0;
        for (int num : input) {
            if (list.size() < k) {
                list.add(num);
                maxIndex = list.get(maxIndex) > num ? maxIndex : list.size() - 1;
            } else {
                // 只有小于最小值才能进入队列
                if (num < list.get(maxIndex)) {
                    list.set(maxIndex, num);
                    // 重置下标
                    maxIndex = 0;
                    for (int i = 1; i < list.size(); i++) {
                        maxIndex = list.get(maxIndex) > list.get(i) ? maxIndex : i;
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 1, 6, 2, 7, 3, 8};
        //int[] arr = new int[]{4, 5, 1, 2, 3, 8};
        BM46GetLeastNumbers s = new BM46GetLeastNumbers();
        System.out.println(s.solution(arr, 4));
    }
}
