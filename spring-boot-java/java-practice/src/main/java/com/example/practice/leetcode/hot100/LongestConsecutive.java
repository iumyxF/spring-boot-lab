package com.example.practice.leetcode.hot100;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author fzy
 * @description:
 * @date 2025/1/17 11:27
 */
public class LongestConsecutive {

    /*
    给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

    请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

    输入：nums = [100,4,200,1,3,2]
    输出：4
    解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     */


    /**
     * 排序时间复杂度是O（nlogn）
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        int res = 1;
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                dp[i] = dp[i - 1] + 1;
                res = Math.max(dp[i], res);
            } else if (nums[i] == nums[i - 1]) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = 1;
            }
        }
        return res;
    }

    public int longestConsecutiveNonSorted(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        HashSet<Integer> set = new HashSet<>(nums.length);
        for (int num : nums) {
            set.add(num);
        }
        int res = 0;
        for (Integer value : set) {
            // 找到开始位置
            if (set.contains(value - 1)) {
                continue;
            }
            int sum = 1;
            while (set.contains(value + 1)) {
                value++;
                sum++;
            }
            res = Math.max(sum, res);
        }
        return res;
    }

    public static void main(String[] args) {
        LongestConsecutive s = new LongestConsecutive();
        //System.out.println(s.longestConsecutive(new int[]{1, 2, 3, 4}));
        System.out.println(s.longestConsecutiveNonSorted(new int[]{1, 1, 1, 1}));
    }
}
