package com.example.practice.leetcode.nowcoder.dynamicprogramming;

/**
 * @author fzy
 * @description:
 * @date 2024/12/10 14:20
 */
public class BM65Lcs {


    /*
    给定两个字符串str1和str2，输出两个字符串的最长公共子序列。如果最长公共子序列为空，则返回"-1"。
    目前给出的数据，仅仅会存在一个最长的公共子序列

    0 <= |str1|, |str2| <= 2000

    输入 "1A2C3D4B56","B1D23A456A"
    输出 "123456"

    输入 "abc","def"
    输出 "-1"

    输入 "abc","abc"
    输出 "abc"
     */

    public String LCS(String s1, String s2) {
        // 动态规划 记录最长公共子序列的最大长度
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            char c1 = s1.charAt(i - 1);
            for (int j = 1; j <= s2.length(); j++) {
                char c2 = s2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        // 去除字母
        StringBuilder sb = new StringBuilder();
        int i = s1.length();
        int j = s2.length();
        while (i > 0 && j > 0) {
            char c1 = s1.charAt(i - 1);
            char c2 = s2.charAt(j - 1);
            if (c1 == c2) {
                sb.insert(0, c1);
                i--;
                j--;
            } else {
                if (dp[i][j - 1] >= dp[i - 1][j]) {
                    j--;
                } else {
                    i--;
                }
            }
        }
        if (sb.length() < 1) {
            return "-1";
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        BM65Lcs s = new BM65Lcs();
        System.out.println(s.LCS("1A2C3D4B56", "B1D23A456A"));
        //System.out.println(s.LCS("1a2b3c", "1a1b1c"));
    }
}
