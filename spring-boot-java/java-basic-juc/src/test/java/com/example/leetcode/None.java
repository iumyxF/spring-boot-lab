package com.example.leetcode;

import org.junit.Test;

/**
 * @author iumyx
 * @description:
 * @date 2024/3/22 10:50
 */
public class None {

    /**
     * 描述
     * 有一种将字母编码成数字的方式：'a'->1, 'b->2', ... , 'z->26'。
     * 现在给一串数字，返回有多少种可能的译码结果
     * <p>
     * "12"
     * 2
     * <p>
     * "31717126241541717"
     * 192
     */

    public static char[] arr = new char[]{'a', 'b', 'c', 'd', 'e',
            'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 's', 'y', 'z'};

    /**
     * 状态方程：
     * lastChar <= 26 dp[i-2]+dp[i-1] = dp
     * lastChar > 26  dp[i-1] + 1 = dp
     *
     * @param nums
     * @return
     */
    public int solve(String nums) {

        char[] chars = nums.toCharArray();
        return 0;
    }

    @Test
    public void solveSolution() {
        System.out.println(arr[22 - 1]);
    }


}
