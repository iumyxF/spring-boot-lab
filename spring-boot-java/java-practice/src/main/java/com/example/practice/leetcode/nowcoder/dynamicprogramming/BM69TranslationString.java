package com.example.practice.leetcode.nowcoder.dynamicprogramming;

/**
 * @author fzy
 * @description:
 * @date 2024/12/13 10:14
 */
public class BM69TranslationString {

    /*
    有一种将字母编码成数字的方式：'a'->1, 'b->2', ... , 'z->26'。
    现在给一串数字，返回有多少种可能的译码结果

    输入 "12"
    输出 2
    2种可能的译码结果（”ab” 或”l”）


    输入 "31717126241541717"
    输出 192
    192种可能的译码结果
     */

    public int solve(String nums) {
        int len = nums.length();
        if (len == 0 || nums.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < nums.length(); i++) {
            if (nums.charAt(i) != '0') {
                dp[i] = dp[i - 1];
            }
            // 如果 10<=num<=26  如果i等于1，直接dp[i]++; 如果大于1, 则dp[i] += dp[i-2];
            int num = (nums.charAt(i - 1) - '0') * 10 + (nums.charAt(i) - '0');
            if (num >= 10 && num <= 26) {
                if (i == 1) {
                    dp[i] += 1;
                } else {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[len - 1];
    }

    public static void main(String[] args) {
        BM69TranslationString s = new BM69TranslationString();
        System.out.println(s.solve("31717126241541717"));
    }
}
