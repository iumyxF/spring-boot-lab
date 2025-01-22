package com.example.practice.leetcode.hot100.substring;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fzy
 * @description:
 * 1. 数组不是单调的话，不要用滑动窗口，考虑用前缀和
 * 2. 前缀和数组preSum，首位是0
 * 3. sum(left,right)=preSum[right+1]−preSum[left]
 * @date 2025/1/22 10:33
 */
public class SubarraySum {

    /*
    给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
    子数组: 连续的数组才是子数组
    子数组是数组中元素的连续非空序列。

    1 <= nums.length <= 2 * 104
    -1000 <= nums[i] <= 1000
    -107 <= k <= 107

    输入：nums = [1,1,1], k = 2
    输出：2
    说明：下标0和1，下标1和2，两个子数组

    输入：nums = [1,2,3], k = 3
    输出：2
     */

    public int subarraySum(int[] nums, int k) {
        int length = nums.length;
        // 计算前缀和
        int[] prefixSum = new int[length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= length; i++) {
            prefixSum[i] = nums[i - 1] + prefixSum[i - 1];
        }
        // 根据前缀和 计算 区间和 sum(left,right)=preSum[right+1]−preSum[left]
        int res = 0;
        for (int left = 0; left < length; left++) {
            for (int right = left; right < length; right++) {
                if (prefixSum[right + 1] - prefixSum[left] == k) {
                    res++;
                }
            }
        }
        return res;
    }

    public int subarraySum2(int[] nums, int k) {
        // key：前缀和，value：key 对应的前缀和的个数
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1
        preSumFreq.put(0, 1);
        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;
            // 先获得前缀和为 preSum - k 的个数，加到计数变量里
            if (preSumFreq.containsKey(preSum - k)) {
                count += preSumFreq.get(preSum - k);
            }
            // 然后维护 preSumFreq 的定义
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        SubarraySum s = new SubarraySum();
        // exp : 3 [0-1-2] [1-2] [3]
        System.out.println(s.subarraySum(new int[]{-1, 0, 1, 2, 3}, 3));
    }
}
