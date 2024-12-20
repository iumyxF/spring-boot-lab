package com.example.practice.leetcode.nowcoder.dynamicprogramming;

/**
 * @author fzy
 * @description:
 * @date 2024/12/16 14:26
 */
public class BM70MinMoney {

    /*
    给定数组arr，arr中所有的值都为正整数且不重复。
    每个值代表一种面值的货币，每种面值的货币可以使用任意张，再给定一个aim，代表要找的钱数，求组成aim的最少货币数。
    如果无解，请返回-1.

    输入：[5,2,3],20
    输出：4

    输入：[5,2,3],0
    输出：0

    输入：[3,5],2
    输出：-1

    step1: 可以用dp[i]表示要凑出 i 元钱需要最小的货币数。
    step2: 一开始都设置为最大值 aim + 1，因此货币最小1元，即货币数不会超过 aim.
    step3: 初始化dp[0] = 0。
    step4: 后续遍历1元到aim元，枚举每种面值的货币都可能组成的情况，取每次的最小值即可，转移方程为dp[i] = min(dp[i],dp[i - arr[j]]+1)
    step5: 最后比较dp[aim]的值是否超过aim,如果超过说明无解，否则返回即可。
     */

    public int minMoney(int[] arr, int aim) {
        return -1;
    }

    public static void main(String[] args) {
        BM70MinMoney s = new BM70MinMoney();
        int[] arr = new int[]{5, 2, 3};
        //System.out.println(s.minMoney(arr, 20));
    }
}
