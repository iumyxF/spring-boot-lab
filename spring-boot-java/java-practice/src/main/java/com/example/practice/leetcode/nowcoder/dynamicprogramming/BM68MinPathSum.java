package com.example.practice.leetcode.nowcoder.dynamicprogramming;

/**
 * @author fzy
 * @description:
 * @date 2024/12/13 8:53
 */
public class BM68MinPathSum {

    /*
    给定一个 n * m 的矩阵 a，从左上角开始每次只能向右或者向下走，最后到达右下角的位置，
    路径上所有的数字累加起来就是路径和，输出所有的路径中最小的路径和。

    dp[i][j]: 达到i,j的时候最短距离
     */

    public int minPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == 1 && j == 1) {
                    dp[i][j] = matrix[i - 1][j - 1];
                } else {
                    if (i == 1) {
                        dp[i][j] = dp[i][j - 1] + matrix[i - 1][j - 1];
                    } else if (j == 1) {
                        dp[i][j] = dp[i - 1][j] + matrix[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        // [[1,3,5,9],[8,1,3,4],[5,0,6,1],[8,8,4,0]]
        int[] arr1 = new int[]{1, 3, 5, 9};
        int[] arr2 = new int[]{8, 1, 3, 4};
        int[] arr3 = new int[]{5, 0, 6, 1};
        int[] arr4 = new int[]{8, 8, 4, 0};
        int[][] arr = new int[4][4];
        arr[0] = arr1;
        arr[1] = arr2;
        arr[2] = arr3;
        arr[3] = arr4;
        BM68MinPathSum s = new BM68MinPathSum();
        System.out.println(s.minPathSum(arr));
    }
}
