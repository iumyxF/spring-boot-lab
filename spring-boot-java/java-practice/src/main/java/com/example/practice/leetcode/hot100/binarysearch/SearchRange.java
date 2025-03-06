package com.example.practice.leetcode.hot100.binarysearch;

/**
 * @author fzy
 * @description:
 * @date 2025/3/6 13:54
 */
public class SearchRange {

    /*
    给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
    如果数组中不存在目标值 target，返回 [-1, -1]。
    你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。

    输入：nums = [5,7,7,8,8,10], target = 8
    输出：[3,4]

    输入：nums = [5,7,7,8,8,10], target = 6
    输出：[-1,-1]

    输入：nums = [], target = 0
    输出：[-1,-1]
     */

    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int targetIndex = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                targetIndex = mid;
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (targetIndex == -1) {
            return new int[]{-1, -1};
        }
        int leftIndex = targetIndex - 1;
        while (leftIndex >= 0 && nums[leftIndex] == target) {
            leftIndex--;
        }
        int rightIndex = targetIndex + 1;
        while (rightIndex <= nums.length - 1 && nums[rightIndex] == target) {
            rightIndex++;
        }
        return new int[]{leftIndex + 1, rightIndex - 1};
    }

    public static void main(String[] args) {
        SearchRange s = new SearchRange();
        s.searchRange(new int[]{1}, 1);
    }
}
