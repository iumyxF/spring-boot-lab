package com.example.practice.leetcode.base;

import java.util.Arrays;

/**
 * @author fzy
 * @description:
 * @date 2024/12/4 11:07
 */
public class QuickSortTest1 {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 3, 5, 2};
        QuickSortTest1 s = new QuickSortTest1();
        s.quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 快速排序 分而治之 找基准，移动两边下标，替换
     */
    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = sort(arr, left, right);
        quickSort(arr, left, mid - 1);
        quickSort(arr, mid + 1, right);
    }

    private int sort(int[] arr, int left, int right) {
        int med = getMed(arr, left, left + (right - left) / 2, right);
        swap(arr, left, med);
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && arr[left] <= arr[j]) {
                j--;
            }
            while (i < j && arr[left] >= arr[i]) {
                i++;
            }
            swap(arr, i, j);
        }
        // 最后替换基数位置
        swap(arr, left, i);
        return i;
    }

    private int getMed(int[] arr, int left, int mid, int right) {
        int l = arr[left];
        int r = arr[right];
        int m = arr[mid];
        if ((l <= m) && (m <= r)) {
            return mid;
        } else if ((m <= l) && (l <= r)) {
            return left;
        } else {
            return right;
        }
    }

    private void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

}
