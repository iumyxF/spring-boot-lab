package com.example.practice.leetcode.hot100.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/2/10 15:23
 */
public class SearchMatrix {

    /*
    编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
    每行的元素从左到右升序排列。
    每列的元素从上到下升序排列。

    输入：matrix = [
    [1,4,7,11,15],
    [2,5,8,12,19],
    [3,6,9,16,22],
    [10,13,14,17,24],
    [18,21,23,26,30]
    ],
    target = 5
    输出：true

    输入：matrix = [
    [1 ,4 ,7 ,11,15],
    [2 ,5 ,8 ,12,19],
    [3 ,6 ,9 ,16,22],
    [10,13,14,17,24],
    [18,21,23,26,30]
    ],
    target = 20
    输出：false
     */

    public boolean searchMatrix(int[][] matrix, int target) {
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        if (matrix[bottom][right] < target) {
            return false;
        }
        while (top <= bottom) {
            int[] ints = matrix[top];
            if (ints[left] <= target && ints[right] >= target) {
                if (binarySearch(ints, left, right, target)) {
                    return true;
                }
            }
            top++;
        }
        return false;
    }

    private boolean binarySearch(int[] arr, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        // [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
        list.add(Arrays.asList(1, 4, 7, 11, 15));
        list.add(Arrays.asList(2, 5, 8, 12, 19));
        list.add(Arrays.asList(3, 6, 9, 16, 22));
        list.add(Arrays.asList(10, 13, 14, 17, 24));
        list.add(Arrays.asList(18, 21, 23, 26, 30));
        int[][] matrix = new int[list.size()][list.get(0).size()];
        for (int i = 0; i < list.size(); i++) {
            List<Integer> integers = list.get(i);
            for (int j = 0; j < integers.size(); j++) {
                matrix[i][j] = integers.get(j);
            }
        }

        SearchMatrix s = new SearchMatrix();
        System.out.println(s.searchMatrix(matrix, 5));
    }
}
