package com.example.leetcode;

import org.junit.Test;

/**
 * @author fzy
 * @description:
 * @date 2024/3/20 17:32
 */
public class LCS {

    /**
     * 给定两个字符串str1和str2,输出两个字符串的最长公共子串
     * 题目保证str1和str2的最长公共子串存在且唯一。
     * "1AB2345CD","12345EF"
     * "2345"
     */
    @Test
    public void test() {
        String s = solution("1AB2345CD", "12345EF");
        System.out.println(s);
    }

    /**
     * 状态方程：
     * - str1.charAt(i-1) == str2.charAt(j-1)
     * -
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String solution(String str1, String str2) {
        int index = 0;
        int temp = 0;
        int l1 = str1.length();
        int l2 = str2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                char s1 = str1.charAt(i - 1);
                char s2 = str2.charAt(j - 1);
                if (s1 == s2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > temp) {
                        temp = dp[i][j];
                        index = i;
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return str1.substring(index - temp, index);
    }
}
