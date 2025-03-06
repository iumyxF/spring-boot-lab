package com.example.practice.leetcode.hot100.binarysearch;

/**
 * @author fzy
 * @description:
 * @date 2025/3/6 14:06
 */
public class SearchRotate {

    /*
    33. 搜索旋转排序数组

    整数数组 nums 按升序排列，数组中的值 互不相同 。
    在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
    使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
    例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
    给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
    你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     */

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] == target) {
            return left;
        }
        if (nums[right] == target) {
            return right;
        }
        int rp = findRotatePoint(nums);
        if (nums[left] > target) {
            left = rp;
        } else {
            right = rp - 1;
        }

        // 二分法
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

    private int findRotatePoint(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = left;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > nums[left]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return mid + 1;
    }

    public static void main(String[] args) {
        SearchRotate s = new SearchRotate();
        System.out.println(s.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    }
}
