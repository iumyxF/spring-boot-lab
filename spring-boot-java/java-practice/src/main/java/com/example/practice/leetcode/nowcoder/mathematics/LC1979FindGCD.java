package com.example.practice.leetcode.nowcoder.mathematics;

/**
 * @author fzy
 * @description:
 * @date 2024/11/28 10:34
 */
public class LC1979FindGCD {

    /*
    难度 easy
    给你一个整数数组 nums ，返回数组中最大数和最小数的 最大公约数 。
    两个数的 最大公约数 是能够被两个数整除的最大正整数。

    示例 1：
    输入：nums = [2,5,6,9,10]
    输出：2
    解释：
    nums 中最小的数是 2
    nums 中最大的数是 10
    2 和 10 的最大公约数是 2

    示例 2：
    输入：nums = [7,5,6,8,3]
    输出：1
    解释：
    nums 中最小的数是 3
    nums 中最大的数是 8
    3 和 8 的最大公约数是 1

    示例 3：
    输入：nums = [3,3]
    输出：3
    解释：
    nums 中最小的数是 3
    nums 中最大的数是 3
    3 和 3 的最大公约数是 3

    提示：
    2 <= nums.length <= 1000
    1 <= nums[i] <= 1000
     */

    public int findGCD(int[] nums) {
        // 找出数组最小值和最大值
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        // 辗转相除
        return gcd(min, max);
    }

    public int gcd(int min, int max) {
        return max % min == 0 ? min : gcd(max % min, min);
    }
}
