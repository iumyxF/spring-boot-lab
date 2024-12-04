package com.example.practice.leetcode.base;

import java.util.Arrays;

/**
 * @author fzy
 * @description:
 * @date 2024/12/4 11:12
 */
public class MergeSortTest {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 3, 5, 2};
        MergeSortTest s = new MergeSortTest();
        s.mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 归并排序 部分排序最后合并
     */
    private void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int index = 0;
        int i = left;
        int j = mid + 1;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[index++] = arr[i++];
            } else {
                temp[index++] = arr[j++];
            }
        }
        // 处理剩余元素
        while (i <= mid) {
            temp[index++] = arr[i++];
        }
        while (j <= right) {
            temp[index++] = arr[j++];
        }
        // 替换原数组
        for (int k = 0; k < temp.length; k++) {
            arr[left + k] = temp[k];
        }
    }

}