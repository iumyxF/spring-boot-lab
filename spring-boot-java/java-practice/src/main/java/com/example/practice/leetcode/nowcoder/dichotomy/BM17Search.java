package com.example.practice.leetcode.nowcoder.dichotomy;

/**
 * @author fzy
 * @description:
 * @date 2024/10/22 14:41
 */
public class BM17Search {

    /*
    请实现无重复数字的升序数组的二分查找
    给定一个元素升序的、无重复数字的整型数组nums
    和一个目标值target,
    写一个函数搜索nums中的target,如果目标值存在返回下标（下标从0开始)，香则返回-1
     */
    public int search(int[] nums, int target) {
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
        return -1;
    }
}
