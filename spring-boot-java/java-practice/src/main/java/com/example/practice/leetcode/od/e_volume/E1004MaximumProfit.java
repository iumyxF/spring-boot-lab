package com.example.practice.leetcode.od.e_volume;

import java.util.Scanner;

/**
 * @author fzy
 * @description:
 * @date 2024/11/29 10:38
 */
public class E1004MaximumProfit {

    /**
     * <a href="https://blog.csdn.net/banxia_frontend/article/details/141390711"/>
     * <p>
     * 输入:
     * 3 商品数量
     * 3 商品售货天数
     * 4 5 6 商品持有量
     * 1 2 3 第一件商品每天的价格
     * 4 3 2 第二件商品每天的价格
     * 1 5 2 第三件商品每天的价格
     * <p>
     * 输出:
     * 32
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int goodCount = in.nextInt();
        int salesDays = in.nextInt();

        int[] holding = new int[goodCount];
        for (int i = 0; i < holding.length; i++) {
            holding[i] = in.nextInt();
        }
        int[][] prices = new int[goodCount][salesDays];
        for (int i = 0; i < prices.length; i++) {
            for (int j = 0; j < prices[i].length; j++) {
                prices[i][j] = in.nextInt();
            }
        }
        solution(holding, prices);
    }

    public static void solution(int[] holding, int[][] prices) {
        int res = 0;
        for (int i = 0; i < holding.length; i++) {
            res += getProfit(holding[i], prices[i]);
        }
        System.out.println(res);
    }

    /**
     * 单个商品的最大利润
     */
    public static int getProfit(int maxHolding, int[] prices) {
        int[][] dp = new int[prices.length][2];
        // 不持有商品
        dp[0][0] = 0;
        // 持有商品 花费金币买商品
        dp[0][1] = -prices[0] * maxHolding;
        for (int i = 1; i < prices.length; i++) {
            // 不持有状态转移方程：1.要么昨天是不持有 2.要么昨天持有，今天卖出。就变成不持有，取最大值
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] * maxHolding);
            // 持有状态转移方程：1.昨天持有，今日继续持有 2.昨天不持有，今日买入
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] * maxHolding);
        }
        return dp[prices.length - 1][0];
    }
}
