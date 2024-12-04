package com.example.practice.leetcode.base;

import java.util.Arrays;

/**
 * @author fzy
 * @description:
 * 1. 找基准
 * 2. 快排
 * 3. 上次快排就已经整体有序，（左边都是小于某个值，右边都是大于某个值），因此再分别处理左右区间
 * @date 2024/12/4 10:31
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 3, 5, 2};
        QuickSort s = new QuickSort();
        s.quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = sort(arr, left, right);
        quickSort(arr, left, mid - 1);
        quickSort(arr, mid + 1, right);
    }

    public int sort(int[] arr, int left, int right) {
        // 找基准并且放到最左边
        //int med = medianThree(arr, left, left + (right - left) / 2, right);
        //swap(arr, left, med);
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && arr[j] >= arr[left]) {
                j--;
            }
            while (i < j && arr[i] <= arr[left]) {
                i++;
            }
            swap(arr, i, j);
        }

        swap(arr, i, left);
        return i;
    }

    public int medianThree(int[] nums, int left, int mid, int right) {
        int l = nums[left], m = nums[mid], r = nums[right];
        if ((l <= m && m <= r) || (r <= m && m <= l)) {
            // m 在 l 和 r 之间
            return mid;
        }
        if ((m <= l && l <= r) || (r <= l && l <= m)) {
            // l 在 m 和 r 之间
            return left;
        }
        return right;
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
