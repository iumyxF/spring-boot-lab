package com.example.practice.leetcode.base;

import java.util.Arrays;

/**
 * @author fzy
 * @description:
 * @date 2024/10/19 13:52
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 3, 5, 2};
        MergeSort s = new MergeSort();
        s.mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 归并排序
     * 分而治之
     *
     * @param arr 数组
     */
    public void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        // 二分
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        // 聚合
        merge(arr, left, mid, right);
    }

    /**
     * 合并左数组和右数组
     * 左数组 [left,mid]
     * 右数组 [mid+1,right]
     */
    public void merge(int[] arr, int left, int mid, int right) {
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
        // 修改元素组
        for (int k = 0; k < temp.length; k++) {
            arr[left + k] = temp[k];
        }
    }
}
