package com.example.practice.leetcode.hot100.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/3/5 11:19
 */
public class SearchMatrix {

    /*
    给你一个满足下述两条属性的 m x n 整数矩阵：

    每行中的整数从左到右按非严格递增顺序排列。
    每行的第一个整数大于前一行的最后一个整数。
    给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。

    输入：matrix = [
        [1 ,3 ,5 ,7 ],
        [10,11,16,20],
        [23,30,34,60]
        ], target = 3
    输出：true
     */

    public boolean searchMatrix(int[][] matrix, int target) {
        int len = matrix.length;
        int width = matrix[0].length;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = matrix[i][width - 1];
        }
        int row = binarySearch(arr, target);
        if (row >= len) {
            return false;
        }
        if (matrix[row][width - 1] == target) {
            return true;
        }
        arr = new int[width];
        for (int i = 0; i < width; i++) {
            arr[i] = matrix[row][i];
        }
        int index = binarySearch(arr, target);
        if (index >= width) {
            return false;
        }
        return matrix[row][index] == target;
    }

    public int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1, 3, 5, 7));
        list.add(Arrays.asList(10, 11, 16, 20));
        list.add(Arrays.asList(23, 30, 34, 60));
        int[][] matrix = new int[list.size()][list.get(0).size()];
        for (int i = 0; i < list.size(); i++) {
            List<Integer> integers = list.get(i);
            for (int j = 0; j < integers.size(); j++) {
                matrix[i][j] = integers.get(j);
            }
        }
        SearchMatrix s = new SearchMatrix();
        System.out.println(s.searchMatrix(matrix, 21));
    }
}
