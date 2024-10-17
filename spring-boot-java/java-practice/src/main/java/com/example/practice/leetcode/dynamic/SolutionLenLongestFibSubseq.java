package com.example.practice.leetcode.dynamic;

import java.util.HashMap;

/**
 * @author fzy
 * @description: 动态规划 公式
 * 1. 确定dp数组（dp table）以及下标的含义
 * 2. 确定递推公式
 * 3. dp数组如何初始化
 * 4. 确定遍历顺序
 * 5. 举例推导dp数组
 * @date 2024/10/12 16:06
 */
public class SolutionLenLongestFibSubseq {

    /*
    最长的斐波那契子序列的长度

    如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
    n >= 3
    对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
    给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回 0 。
    （回想一下，子序列是从原序列 arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。
    例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）

    示例 1：
    输入: arr = [1,2,3,4,5,6,7,8]
    输出: 5
    解释: 最长的斐波那契式子序列为 [1,2,3,5,8] 。

    示例 2：
    输入: arr = [1,3,7,11,12,14,18]
    输出: 3
    解释: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。

    3 <= arr.length <= 1000
    1 <= arr[i] < arr[i + 1] <= 10^9
     */

    /**
     * 当固定斐波那契数列的两个起始值，或固定两个结束值的时候，整个数列都是唯一确定的(在输入数组中)。
     * 这里选择从起始开始往后推，我们遍历这两个起点i,j，如果他们的和在数列中，记为坐标k，即arr[i]+arr[j]=arr[k]。
     * 显然以j,k作为起点的序列是和以i,j作为起点的序列完全重合的，且长度多了一个数。
     * 即dp[j][k]=dp[i][j]+1。
     * 找到最大的两个点即可
     *
     * 公式
     * 1. 一般dp[i][j] 就是所求值，所以 dp[i][j]的含义 是 这个点的最长斐波那契数列长度
     * 2. 递推公式：
     * 假设 dp[i] + dp[j] = nxt，如果nxt存在 则 dp[i][j] = Math.max(dp[j][k] + 1)
     *
     */
    public int lenLongestFibSubseq(int[] arr) {
        int len = arr.length;
        int ans = 0;
        // key = arr[x] ; value = x
        HashMap<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            valueIndexMap.put(arr[i], i);
        }
        int[][] dp = new int[len][len];
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Math.max(dp[i][j], 2);
                int nxt = arr[i] + arr[j];
                if (valueIndexMap.containsKey(nxt)) {
                    int k = valueIndexMap.get(nxt);
                    dp[j][k] = dp[i][j] + 1;
                    ans = Math.max(ans, dp[j][k]);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionLenLongestFibSubseq s = new SolutionLenLongestFibSubseq();
        System.out.println(s.lenLongestFibSubseq(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }

}
