package com.example.practice.leetcode.hot100.binarysearch;

/**
 * @author fzy
 * @description:
 * @date 2025/3/7 9:33
 */
public class FindMedianSortedArrays {

    /*
    4. 寻找两个正序数组的中位数

    给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
    算法的时间复杂度应该为 O(log (m+n)) 。

    输入：nums1 = [1,3], nums2 = [2]
    输出：2.00000
    解释：合并数组 = [1,2,3] ，中位数 2

    输入：nums1 = [1,2], nums2 = [3,4]
    输出：2.50000
    解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     */

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        if (l1 == 0 && l2 == 0) {
            return 0;
        }
        int len = l1 + l2;
        int mid = len / 2;
        int arrLen = mid + 1;
        // 两个拆分的数组 按顺序获取下标
        int[] arr = new int[arrLen];
        int idx1 = 0;
        int idx2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (idx1 < l1 && idx2 < l2) {
                if (nums1[idx1] <= nums2[idx2]) {
                    arr[i] = nums1[idx1++];
                } else {
                    arr[i] = nums2[idx2++];
                }
            } else if (idx1 < l1) {
                arr[i] = nums1[idx1++];
            } else if (idx2 < l2) {
                arr[i] = nums2[idx2++];
            }
        }
        if (len % 2 != 0) {
            return arr[arr.length - 1];
        } else {
            return (arr[arr.length - 1] + arr[arr.length - 2]) / 2.00000;
        }
    }

    public static void main(String[] args) {
        FindMedianSortedArrays s = new FindMedianSortedArrays();
        //System.out.println(s.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        //System.out.println(s.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        System.out.println(s.findMedianSortedArrays(new int[]{}, new int[]{}));
    }
}
