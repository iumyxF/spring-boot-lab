package com.example.practice.leetcode.greedy;

/**
 * @author fzy
 * @description:
 * @date 2024/10/11 14:26
 */
public class SolutionMaxProfit {

    /*
    给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
    在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
    返回 你能获得的 最大 利润 。

    示例 1：
    输入：prices = [7,1,5,3,6,4]
    输出：7
    解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4。
    随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3。
    最大总利润为 4 + 3 = 7 。

    示例 2：
    输入：prices = [1,2,3,4,5]
    输出：4
    解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4。
    最大总利润为 4 。

    示例 3：
    输入：prices = [7,6,4,3,1]
    输出：0
    解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0。

    提示：
    1 <= prices.length <= 3 * 104
    0 <= prices[i] <= 104
     */

    /**
     * 贪心算法，只求正收益部分
     */
    public int maxProfitByGreedy(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length - 1; i++) {
            res += Math.max(0, prices[i] - prices[i - 1]);
        }
        return res;
    }


    /**
     * 动态规划
     * dp[n][0] 表示第i天不持有股票所得最多现金
     * 对于今天不持有，可以从两个状态转移过来：1. 昨天也不持有；2. 昨天持有，今天卖出。两者取较大值。
     * <p>
     * dp[n][1] 表示第i天持有股票所得现金。
     * 对于今天持有，可以从两个状态转移过来：1. 昨天也持有；2. 昨天不持有，今天买入。两者取较大值。
     */
    public int maxProfitByDynamic(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }

    public static void main(String[] args) {
        int arr[] = {7, 1, 5, 3, 6, 4};
        SolutionMaxProfit s = new SolutionMaxProfit();
        System.out.println(s.maxProfitByGreedy(arr));
        System.out.println(s.maxProfitByDynamic(arr));
    }

}
