package com.example.practice.leetcode.hot100.pointer;

import java.util.*;

/**
 * @author fzy
 * @description:
 * @date 2025/1/16 14:18
 */
public class ThreeSum {

    /*
    给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
    同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。

    注意：答案中不可以包含重复的三元组。

    输入：nums = [-1,0,1,2,-1,-4]
    输出：[[-1,-1,2],[-1,0,1]]
    解释：
    nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
    nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
    nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
    不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
    注意，输出的顺序和三元组的顺序并不重要。

    3 <= nums.length <= 3000
    -10^5 <= nums[i] <= 10^5
     */

    public List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < nums.length; i++) {
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    res.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
                    while (left < len && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else {
                    right--;
                }
            }
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSum s = new ThreeSum();
        System.out.println(s.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
