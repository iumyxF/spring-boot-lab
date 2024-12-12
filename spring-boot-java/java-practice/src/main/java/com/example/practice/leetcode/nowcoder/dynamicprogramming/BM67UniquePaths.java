package com.example.practice.leetcode.nowcoder.dynamicprogramming;

/**
 * @author fzy
 * @description:
 * @date 2024/12/12 15:02
 */
public class BM67UniquePaths {

    /*
    一个机器人在 m * n 大小的地图的左上角（起点）。
    机器人每次可以向下或向右移动。机器人要到达地图的右下角（终点）。
    可以有多少种不同的路径从起点走到终点？

    dp[i][j] 到达i j 的时候有 x 种方法

    dp[i][j] = Math.max(dp[i][j-1] + 1,dp[i-1][j] + 1)

     */

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (dp[i - 1][j] == 0 && dp[i][j - 1] == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        BM67UniquePaths s = new BM67UniquePaths();
        //System.out.println(s.uniquePaths(2,1)); // 1
        System.out.println(s.uniquePaths(2, 2)); // 2
        //System.out.println(s.uniquePaths(1, 10)); // 1
    }
}
