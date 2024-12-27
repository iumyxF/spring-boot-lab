package com.example.practice.leetcode.nowcoder.dynamicprogramming;

/**
 * @author fzy
 * @description:
 * @date 2024/12/27 13:50
 */
public class BM78Rob {

    /*
    你是一个经验丰富的小偷，准备偷沿街的一排房间，每个房间都存有一定的现金，为了防止被发现，你不能偷相邻的两家，
    即，如果偷了第一家，就不能再偷第二家；
    如果偷了第二家，那么就不能偷第一家和第三家。
    给定一个整数数组nums，数组中的元素表示每个房间存有的现金数额，请你计算在不被发现的前提下最多的偷窃金额。

    输入 [1,2,3,4]
    输出 6
    说明 最优方案是偷第 2，4 个房间

    状态转移方程：
    dp[i] = dp[i-2]+dp[i] dp[i-1]
    dp[i] = dp[i-1]
     */

    public int rob(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        return dp[len];
    }

    public static void main(String[] args) {
        BM78Rob s = new BM78Rob();
        System.out.println(s.rob(new int[]{1, 2, 3, 4}));
    }
}
