package com.example.practice.leetcode.nowcoder.dynamicprogramming;

/**
 * @author fzy
 * @description:
 * @date 2024/12/20 9:43
 */
public class BM73GetLongestPalindrome {

    /*
    最长回文子串
    对于长度为n的一个字符串A（仅包含数字，大小写英文字母），请设计一个高效算法，计算其中最长回文子串的长度。

    输入："ababc"
    输出：3
    说明：最长的回文子串为"aba"与"bab"，长度都为3

    解析：
    dp[i] 代表以i位置最长的回文子串？
     */

    public int getLongestPalindrome(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int res = 1;
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i <= sb.length(); i++) {
            for (int j = i + 1; j <= sb.length(); j++) {
                if (isPalindrome(sb.substring(i, j))) {
                    res = Math.max(res, j - i);
                }
            }
        }
        return res;
    }

    public boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        BM73GetLongestPalindrome s = new BM73GetLongestPalindrome();
        //System.out.println(s.getLongestPalindrome("ababc"));
        //System.out.println(s.getLongestPalindrome("abbba"));
        //System.out.println(s.getLongestPalindrome("ccbcabaabba"));

        //System.out.println(s.getLongestPalindromeByDp("ababc"));
        //System.out.println(s.getLongestPalindromeByDp("abbba"));
        //System.out.println(s.getLongestPalindromeByDp("ccbcabaabba"));

        System.out.println(s.test("ccbcabaabba"));

    }

    /**
     * 动态规划
     * dp[i][j]：字符串s在[i, j]范围内最长的回文子序列的长度为dp[i][j]。
     */
    public int getLongestPalindromeByDp(String s) {
        int n = s.length();
        int length = 1; // 最长回文子串的长度
        int start = 0;  // 最长回文子串的起始位置
        boolean[][] dp = new boolean[n][n];    // dp[j][i]表示子串s[j:i]是否为回文串
        for (int i = 0; i < n; i++) {
            // 以i为终点，往回枚举起点j
            for (int j = i; j >= 0; j--) {
                if (i == j) {
                    // 一个字符，一定为回文串
                    dp[j][i] = true;
                } else if (i == j + 1) {
                    // 两个字符，取决于二者是否相等
                    dp[j][i] = (s.charAt(i) == s.charAt(j));
                } else {
                    // 两个字符以上，首先端点两个字符要相等，其次[j+1, i-1]也要为回文串
                    dp[j][i] = (s.charAt(i) == s.charAt(j)) && dp[j + 1][i - 1];
                }
                // [j,i]为回文串且长度更大，更新
                if (dp[j][i] && (i - j + 1) > length) {
                    length = i - j + 1;
                    start = j;
                }
            }
        }
        //return s.substring(start, start + length);
        return length;
    }

    /**
     * dp[i][j]：字符串s在[i, j]范围内最长的回文子序列的长度为dp[i][j]。
     * 遍历的时候判断 1个字符、2个字符、多个字符的情况
     */
    public String test(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int start = 0;
        int startLen = 1;
        for (int i = 0; i < len; i++) {
            for (int j = i; j >= 0; j--) {
                if (i == j) {
                    dp[j][i] = true;
                } else if (i - j == 1) {
                    dp[j][i] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[j][i] = (s.charAt(i) == s.charAt(j)) && dp[j + 1][i - 1];
                }
                if (dp[j][i] && (i - j + 1) > startLen) {
                    startLen = i - j + 1;
                    start = j;
                }
            }
        }
        return s.substring(start, start + startLen);
    }

}
