package com.example.practice.leetcode.nowcoder.hash;

import java.util.Arrays;

/**
 * @author feng
 * @description:
 * @date 2024/12/3 20:53
 */
public class BM53MinNumberDisappeared {

    public int minNumberDisappeared(int[] nums) {
        int[] sorted = Arrays.stream(nums)
                .filter(value -> value > 0)
                .sorted()
                .toArray();
        for (int i = 0; i < sorted.length; i++) {
            if (i + 1 != sorted[i]) {
                return i + 1;
            }
        }
        return sorted.length + 1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 3};
        BM53MinNumberDisappeared s = new BM53MinNumberDisappeared();
        System.out.println(s.minNumberDisappeared(arr));
    }

}
