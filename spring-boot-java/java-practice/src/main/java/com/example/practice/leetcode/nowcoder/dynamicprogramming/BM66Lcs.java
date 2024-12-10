package com.example.practice.leetcode.nowcoder.dynamicprogramming;

/**
 * @author fzy
 * @description:
 * @date 2024/12/10 15:42
 */
public class BM66Lcs {

    /*
    给定两个字符串str1和str2,输出两个字符串的最长公共子串
    题目保证str1和str2的最长公共子串存在且唯一。

    "1AB2345CD","12345EF"
    "2345"

     */

    public String LCS(String str1, String str2) {
        // 最长字串的长度
        int maxLen = 0;
        // 最长子串的长度的最后一个字符的下标
        int maxIndex = 0;
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    int value = dp[i - 1][j - 1] + 1;
                    dp[i][j] = value;
                    if (value > maxLen) {
                        maxLen = value;
                        maxIndex = i;
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return str1.substring(maxIndex - maxLen, maxIndex);
    }

    public static void main(String[] args) {
        BM66Lcs s = new BM66Lcs();
        System.out.println(s.LCS("1AB2345CD", "12345EF"));
        System.out.println(s.LCS("123456abced", "abcd123456"));
    }

}
