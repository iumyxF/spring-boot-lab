package com.example.practice.leetcode.greedy;

/**
 * @author fzy
 * @description:
 * @date 2024/7/24 15:35
 */
public class SolutionMaxSubArray {

    /*
    给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
    子数组 是数组中的一个连续部分。

    示例 1：
    输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
    输出：6
    解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。

    示例 2：
    输入：nums = [1]
    输出：1

    示例 3：
    输入：nums = [5,4,-1,7,8]
    输出：23

    提示：
    1 <= nums.length <= 105
    -104 <= nums[i] <= 104

    动态规划
    dp[i][j] 当前和最大值
    方程:
     */

    /**
     * dp[i - 1] + nums[i]，即：nums[i]加入当前连续子序列和
     * nums[i]，即：从头开始计算当前连续子序列和
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int res = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(dp[i], res);
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
