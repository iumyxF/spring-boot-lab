package com.example.practice.leetcode.nowcoder.dynamicprogramming;

/**
 * @author fzy
 * @description:
 * @date 2024/12/10 14:20
 */
public class BM65Lcs {


    /*
    hard!!!
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
        // dp数组只获取到最长子序列的长度
        // 这里根据dp获得最长子序列的字符串
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

    public String lcs2(String a, String b) {
        int l1 = a.length();
        int l2 = b.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        for (int i = 1; i <= l1; i++) {
            char c1 = a.charAt(i - 1);
            for (int j = 1; j <= l2; j++) {
                char c2 = b.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        int i = l1;
        int j = l2;
        StringBuilder res = new StringBuilder();
        while (i > 0 && j > 0) {
            char c1 = a.charAt(i - 1);
            char c2 = b.charAt(j - 1);
            if (c1 == c2) {
                res.insert(0, c1);
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

        return res.toString();
    }

    public static void main(String[] args) {
        BM65Lcs s = new BM65Lcs();
        //System.out.println(s.LCS("1A2C3D4B56", "B1D23A456A"));
        System.out.println(s.lcs2("1A2C3D4B56", "B1D23A456A"));
        //System.out.println(s.LCS("1a2b3c", "1a1b1c"));
    }
}
