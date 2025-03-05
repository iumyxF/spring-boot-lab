package com.example.practice.leetcode.hot100.binarysearch;

/**
 * @author fzy
 * @description:
 * @date 2025/3/5 11:07
 */
public class SearchInsert {

    /*
    给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
    请必须使用时间复杂度为 O(log n) 的算法。

    输入: nums = [1,3,5,6], target = 5
    输出: 2

    输入: nums = [1,3,5,6], target = 2
    输出: 1

    输入: nums = [1,3,5,6], target = 7
    输出: 4
     */

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        SearchInsert s = new SearchInsert();
        System.out.println(s.searchInsert(new int[]{1, 3, 5, 6}, 0));
    }
}
