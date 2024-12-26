package com.example.practice.leetcode.nowcoder.dynamicprogramming;

/**
 * @author fzy
 * @description:
 * @date 2024/12/26 11:00
 */
public class BM75EditDistance {

    /*
    给定两个字符串 str1 和 str2 ，请你算出将 str1 转为 str2 的最少操作数。
    你可以对字符串进行3种操作：
    1.插入一个字符
    2.删除一个字符
    3.修改一个字符。

    输入："nowcoder","new"
    输出：6
    说明："nowcoder"=>"newcoder"(将'o'替换为'e')，修改操作1次
         "nowcoder"=>"new"(删除"coder")，删除操作5次

    动态规划
    dp[i][j] 的含义，让str1(0,i) = str2(0,j)的最少操作数

    递推公式
    如果相等则看dp[i-1][j-1]
    如果不相等：
        1. 修改一个字符：dp[i][j] = dp[i-1][j-1]+1, 比较str1的i和str2的j是否相同，要看str1的i-1和str2的j-1是否相同
        2. 删除一个字符：dp[i][j] = dp[i-1][j]+1
        3. 增加一个字符：dp[i][j] = dp[i][j-1]+1
     */

    public int editDistance(String str1, String str2) {
        int l1 = str1.length();
        int l2 = str2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        // 初始化 空字符串想变成str1/str2 只能每次加一个字符
        for (int i = 0; i <= l1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= l2; i++) {
            dp[0][i] = i;
        }

        // 递归
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 获取三个的最小值
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[l1][l2];
    }

    public static void main(String[] args) {
        BM75EditDistance s = new BM75EditDistance();
        System.out.println(s.editDistance("nowcoder", "new"));
        System.out.println(s.editDistance("owkkyhiddq", "scdx"));
    }
}
