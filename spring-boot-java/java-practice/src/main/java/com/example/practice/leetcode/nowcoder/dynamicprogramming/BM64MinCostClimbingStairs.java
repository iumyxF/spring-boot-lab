package com.example.practice.leetcode.nowcoder.dynamicprogramming;

/**
 * @author fzy
 * @description:
 * @date 2024/12/10 9:16
 */
public class BM64MinCostClimbingStairs {

    /*
    给定一个整数数组cost，其中cost[i]是从楼梯第i个台阶向上爬需要支付的费用，
    下标从0开始。一旦你支付此费用，即可选择向上爬一个或者两个台阶。

    你可以选译从下标为0或下标为1的台阶开始爬楼梯。

    请你计算并返回达到楼梯顶部的最低花费。

    数据范围：数组长度满足1 <= n <= 10的5次方，数组中的值满足1 <= cost(i) <= 10的4次方
     */

    public int minCostClimbingStairs(int[] cost) {
        // 到达i层 可能是 i-2层 + 2 或者 i-1层 + 1
        if (cost.length <= 2) {
            return cost[cost.length - 1];
        }
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[cost.length];
    }

    public static void main(String[] args) {
        BM64MinCostClimbingStairs s = new BM64MinCostClimbingStairs();
        //int[] arr = new int[]{2, 5, 20};
        int[] arr = new int[]{1, 100, 1, 1, 1, 90, 1, 1, 80, 1};
        System.out.println(s.minCostClimbingStairs(arr));
    }
}
