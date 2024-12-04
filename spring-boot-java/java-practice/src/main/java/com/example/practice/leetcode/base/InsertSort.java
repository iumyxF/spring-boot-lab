package com.example.practice.leetcode.base;

import java.util.Arrays;

/**
 * @author fzy
 * @description:
 * @date 2024/12/4 17:31
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 3, 5, 2};
        InsertSort s = new InsertSort();
        s.insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > value) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = value;
        }
    }
}
