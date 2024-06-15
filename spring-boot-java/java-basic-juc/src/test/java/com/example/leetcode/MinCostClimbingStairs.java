package com.example.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author iumyx
 * @description: 爬楼梯
 * @date 2024/3/18 9:07
 */
public class MinCostClimbingStairs {

    @Test
    public void main() {
        //int[] cost = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        //int solution = solution(cost);
        //System.out.println(solution);

        //3行 5列
        int[][] dp = new int[3][5];
        System.out.println(Arrays.toString(dp));
    }

    /**
     * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
     * 每当爬上一个阶梯都要花费对应的体力值，一旦支付了相应的体力值，就可以选择向上爬一个阶梯或者爬两个阶梯。
     * 请找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
     * <p>
     * dp[x] 到达第x个点最小体力（不包括这个点的体力）
     * x 到达的点
     */
    public static int solution(int[] cost) {
        int l = cost.length;
        int[] dp = new int[l + 1];
        System.out.println(dp.length);
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[l];
    }

}
