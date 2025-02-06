package com.example.practice.leetcode.hot100.array;

/**
 * @author fzy
 * @description:
 * @date 2025/2/6 9:48
 */
public class MaxSubArray {

    /*
    给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

    子数组 : 是数组中的一个连续部分。

    1 <= nums.length <= 10^5
    -10^4 <= nums[i] <= 10^4

    输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
    输出：6
    解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。

    输入：nums = [1]
    输出：1

    输入：nums = [5,4,-1,7,8]
    输出：23
     */

    /**
     * 超时了
     * 前缀和 sum[left,right] =  preSum[right+1] - preSum[left]
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] preSum = new int[len + 1];
        preSum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        int max = Integer.MIN_VALUE;
        for (int left = 0; left < len; left++) {
            for (int right = left; right < len; right++) {
                max = Math.max(max, preSum[right + 1] - preSum[left]);
            }
        }
        return max;
    }

    /**
     * 动态规划
     * dp[i] 在 i 时 的最大值
     * dp[i] = Math.max(dp[i-1]+nums[i],nums[i])
     */
    public int maxSubArray2(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        MaxSubArray s = new MaxSubArray();
        System.out.println(s.maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(s.maxSubArray2(new int[]{1}));
        System.out.println(s.maxSubArray2(new int[]{5, 4, -1, 7, 8}));
    }
}
