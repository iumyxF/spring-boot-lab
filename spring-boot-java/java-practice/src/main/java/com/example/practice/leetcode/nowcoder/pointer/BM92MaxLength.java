package com.example.practice.leetcode.nowcoder.pointer;

import java.util.LinkedList;

/**
 * @author fzy
 * @description:
 * @date 3/1/2025 上午9:30
 */
public class BM92MaxLength {

    /*
    给定一个长度为n的数组arr，返回arr的最长无重复元素子数组的长度，无重复指的是所有数字都不相同。
    子数组是连续的，比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组

    input [2,3,4,5]
    output 4
    [2,3,4,5]是最长子数组
     */
    public int maxLength(int[] arr) {
        LinkedList<Integer> list = new LinkedList<>();
        int res = 0;
        for (int i : arr) {
            while (list.contains(i)) {
                list.pollFirst();
            }
            list.addLast(i);
            res = Math.max(res, list.size());
        }
        return res;
    }
}
