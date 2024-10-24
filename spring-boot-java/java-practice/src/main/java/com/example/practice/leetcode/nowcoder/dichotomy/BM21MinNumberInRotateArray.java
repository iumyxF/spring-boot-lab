package com.example.practice.leetcode.nowcoder.dichotomy;

/**
 * @author fzy
 * @description:
 * @date 2024/10/24 9:43
 */
public class BM21MinNumberInRotateArray {

    /*
    有一个长度为n的非降序数组，比如[1,2,3,4,5],将它进行旋转，即把一个数组最开始的若干个元素搬到数组的末尾，变成一个旋转数组，
    比如变成了[3,4,5,1,2]，或者[4,5,1,2,3]这样的。请问，给定这样一个旋转数组，求数组中的最小值。

    数据范围：1 <= n <= 10000，数组中任意元素的值：0 <= val <= 10000
    要求：空间复杂度：O(1),时间复杂度：O(logn)

    input : [3,4,5,1,2]
    output: 1

    input : [3,100,200,3]
    output: 3
     */

    public int minNumberInRotateArray(int[] nums) {
        // 找到旋转点
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] < nums[right]) {
                right = middle;
            } else if (nums[middle] > nums[right]) {
                left = middle + 1;
            } else {
                right--;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 5, 1, 2};
        //int[] arr = new int[]{3, 100, 200, 3};
        //int[] arr = new int[]{1, 0, 1, 1, 1};
        //int[] arr = new int[]{1, 1, 1, 0, 1};
        BM21MinNumberInRotateArray s = new BM21MinNumberInRotateArray();
        System.out.println(s.minNumberInRotateArray(arr));
    }
}
