package com.example.practice.leetcode.nowcoder.dynamicprogramming;

/**
 * @author fzy
 * @description:
 * @date 2024/12/27 15:23
 */
public class BM79Rob2 {

    /*
    你是一个经验丰富的小偷，准备偷沿湖的一排房间，每个房间都存有一定的现金，为了防止被发现，你不能偷相邻的两家，
    即，如果偷了第一家，就不能再偷第二家，如果偷了第二家，那么就不能偷第一家和第三家。
    沿湖的房间组成一个闭合的圆形，即第一个房间和最后一个房间视为相邻。
    给定一个长度为n的整数数组nums，数组中的元素表示每个房间存有的现金数额，请你计算在不被发现的前提下最多的偷窃金额。
     */
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[1];
        }
        // 一个去头，一个去尾
        int[] a1 = new int[nums.length - 1];
        int[] a2 = new int[nums.length - 1];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                a1[i] = nums[i];
            } else if (i == nums.length - 1) {
                a2[i - 1] = nums[i];
            } else {
                a1[i] = nums[i];
                a2[i - 1] = nums[i];
            }
        }
        return Math.max(dp(a1), dp(a2));
    }

    public int dp(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        System.out.println(dp[len]);
        return dp[len];
    }


    public static void main(String[] args) {
        BM79Rob2 s = new BM79Rob2();
        //System.out.println(s.rob(new int[]{10, 2, 3, 4, 5}));
        System.out.println(s.rob(new int[]{1, 2}));
    }
}
