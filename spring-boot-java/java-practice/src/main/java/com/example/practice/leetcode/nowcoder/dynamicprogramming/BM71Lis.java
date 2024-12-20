package com.example.practice.leetcode.nowcoder.dynamicprogramming;

import java.util.Arrays;

/**
 * @author fzy
 * @description:
 * @date 2024/12/19 9:40
 */
public class BM71Lis {

    /*
    给定一个长度为 n 的数组 arr，求它的最长严格上升子序列的长度。
    所谓子序列，指一个数组删掉一些数（也可以不删）之后，形成的新数组。例如 [1,5,3,7,3] 数组，
    其子序列有：[1,3,3]、[7] 等。但 [1,6]、[1,3,5] 则不是它的子序列。
    我们定义一个序列是 严格上升 的，当且仅当该序列不存在两个下标 i 和 j 满足 i<j && arr[i] >= arr[j]

    input: [6,3,1,5,2,3,7]
    output:4
    该数组最长上升子序列为 [1,2,3,7] ，长度为4

    dp[i]:
    递推公式:
    初始化:
    遍历顺序:
     */

    public int LIS(int[] arr) {
        if (arr == null) {
            return 0;
        }
        if (arr.length <= 1) {
            return arr.length;
        }
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            int index = findIndex(arr, dp, i);
            if (index == -1) {
                dp[i] = 1;
            } else {
                dp[i] = dp[index] + 1;
            }
        }
        Arrays.sort(dp);
        return dp[arr.length - 1];
    }

    /**
     * 要寻找的下标，要求
     * 1. arr[cur] > arr[x]
     * 2. arr[x] 是 arr[i..0] 最大值
     */
    public int findIndex(int[] arr, int[] dp, int curIndex) {
        int maxDpValue = 0;
        int x = -1;

        int curValue = arr[curIndex];
        for (int i = curIndex - 1; i >= 0; i--) {
            if (curValue > arr[i] && maxDpValue < dp[i]) {
                x = i;
                maxDpValue = dp[i];
            }
        }
        return x;
    }

    public static void main(String[] args) {
        //int[] arr = new int[]{6, 3, 1, 5, 2, 3, 7};
        //int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        //int[] arr = new int[]{4, 5, 6, 7, 1, 2, 3};
        int[] arr = new int[]{};
        BM71Lis s = new BM71Lis();
        System.out.println(s.LIS(arr));
    }

    public int LISStandardAnswer(int[] arr) {
        int[] dp = new int[arr.length];
        //设置数组长度大小的动态规划辅助数组
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                //可能j不是所需要的最大的，因此需要dp[i] < dp[j] + 1
                if (arr[i] > arr[j] && dp[i] < dp[j] + 1) {
                    //i点比j点大，理论上dp要加1
                    dp[i] = dp[j] + 1;
                    //找到最大长度
                    res = Math.max(res, dp[i]);
                }
            }
        }
        return res;
    }
}
