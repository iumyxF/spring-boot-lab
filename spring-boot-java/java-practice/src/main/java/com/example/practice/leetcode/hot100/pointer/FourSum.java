package com.example.practice.leetcode.hot100.pointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/1/16 14:58
 */
public class FourSum {

    /*
    给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
    请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：

    输入：nums = [1,0,-1,0,-2,2], target = 0
    输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     */

    public List<List<Integer>> fourSum(int[] nums, int target) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            //三数之和
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int start = i + 1;
            for (int j = start; j < nums.length; j++) {
                if (j > start && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    //防止精度溢出
                    long value = (long) nums[i] + (long) nums[j] + (long) nums[left] + (long) nums[right];
                    if (value < target) {
                        left++;
                    } else if (value > target) {
                        right--;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FourSum s = new FourSum();
        //System.out.println(s.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        //System.out.println(s.fourSum(new int[]{-3, -1, 0, 2, 4, 5}, 0));
        //System.out.println(s.fourSum(new int[]{-3, -1, 0, 2, 4, 5}, 2)); // [-3,-1,2,4]

        // expect [[-3,-2,2,3],[-3,-1,1,3],[-3,0,0,3],[-3,0,1,2],[-2,-1,0,3],[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
        //System.out.println(s.fourSum(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 0));
        System.out.println(s.fourSum(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296));
    }
}
