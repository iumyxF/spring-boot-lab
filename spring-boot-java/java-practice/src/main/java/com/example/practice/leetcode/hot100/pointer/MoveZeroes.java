package com.example.practice.leetcode.hot100.pointer;

import java.util.Arrays;

/**
 * @author fzy
 * @description:
 * @date 2025/1/17 15:02
 */
public class MoveZeroes {

    /*
    给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

    输入: nums = [0,1,0,3,12]
    输出: [1,3,12,0,0]
     */

    public void moveZeroes(int[] nums) {
        int left = 0;
        int right = 0;
        while (left < nums.length && right < nums.length) {
            // nums[left] = 0
            while (left < nums.length && nums[left] != 0) {
                left++;
            }
            // nums[right] != 0
            while (right < nums.length && nums[right] == 0) {
                right++;
            }
            if (left < right && left < nums.length && right < nums.length) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
            right++;
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        MoveZeroes s = new MoveZeroes();
        //s.moveZeroes(new int[]{0, 1, 0, 3, 12});
        //s.moveZeroes(new int[]{1, 3, 12, 0, 0});
        //s.moveZeroes(new int[]{0, 0, 12, 0, 0});
        s.moveZeroes(new int[]{0, 0, 12, 0, 1});
        //s.moveZeroes(new int[]{1, 0, 1});
        //s.moveZeroes(new int[]{1, 0});
    }
}
