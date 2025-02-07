package com.example.practice.leetcode.hot100.array;

import java.util.Arrays;

/**
 * @author fzy
 * @description:
 * @date 2025/2/7 9:48
 */
public class Rotate {

    /*
    给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。

    输入: nums = [1,2,3,4,5,6,7], k = 3
    输出: [5,6,7,1,2,3,4]
    解释:
    向右轮转 1 步: [7,1,2,3,4,5,6]
    向右轮转 2 步: [6,7,1,2,3,4,5]
    向右轮转 3 步: [5,6,7,1,2,3,4]

    输入：nums = [-1,-100,3,99], k = 2
    输出：[3,99,-1,-100]
    解释:
    向右轮转 1 步: [99,-1,-100,3]
    向右轮转 2 步: [3,99,-1,-100]
     */
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        if (len <= 1) {
            return;
        }
        // k = 3 和 k = 10 是一样的效果
        if (k >= len) {
            k = k % len;
        }
        if (k == 0) {
            return;
        }
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
        System.out.println(Arrays.toString(nums));
    }

    public void reverse(int[] nums, int start, int end) {
        int left = start;
        int right = end;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        Rotate s = new Rotate();
        s.rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        s.rotate(new int[]{-1, -100, 3, 99}, 2);
        s.rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 10);
    }
}
