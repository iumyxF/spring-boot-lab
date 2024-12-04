package com.example.practice.leetcode.nowcoder.hash;

import java.util.HashSet;

/**
 * @author feng
 * @description:
 * @date 2024/12/3 20:23
 */
public class BM52FindNumsAppearOnce {

    /*
    一个整型数组里除了两个数字只出现一次，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字
     */

    public int[] FindNumsAppearOnce(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (set.contains(value)) {
                set.remove(value);
            } else {
                set.add(value);
            }
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (Integer v : set) {
            max = Math.max(max, v);
            min = Math.min(min, v);
        }
        int[] res = new int[2];
        res[0] = min;
        res[1] = max;
        return res;
    }
}
