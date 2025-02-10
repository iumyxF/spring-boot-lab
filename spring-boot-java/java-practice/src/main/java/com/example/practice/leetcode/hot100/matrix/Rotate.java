package com.example.practice.leetcode.hot100.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/2/10 14:18
 */
public class Rotate {
    /*
    给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
    你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。

    输入：matrix = [
    [1,2,3],
    [4,5,6],
    [7,8,9]
    ]
    输出：[
    [7,4,1],
    [8,5,2],
    [9,6,3]
    ]

    输入：matrix = [
    [5,1,9,11],
    [2,4,8,10],
    [13,3,6,7],
    [15,14,12,16]
    ]
    输出：[
    [15,13,2,5],
    [14,3,4,1],
    [12,6,8,9],
    [16,7,10,11]
    ]
     */

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n <= 1) {
            return;
        }
        int loop = n % 2 == 0 ? n / 2 : n / 2 + 1;

        for (int i = 0; i < loop; i++) {
            int left = i;
            int right = matrix[0].length - 1 - i;
            int top = i;
            int bottom = matrix.length - 1 - i;

            int tempLeft = left;
            int tempTop = top;
            int tempRight = right;
            int tempBottom = bottom;

            for (int j = 0; j < (right - left); j++) {
                // 交换3次
                swap(matrix, top, tempLeft, tempTop, right);
                swap(matrix, top, tempLeft, bottom, tempRight);
                swap(matrix, top, tempLeft, tempBottom, left);
                tempLeft++;
                tempTop++;
                tempRight--;
                tempBottom--;
            }
        }
    }

    private void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int temp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = temp;
    }

    public static void main(String[] args) {
        Rotate s = new Rotate();
        ArrayList<List<Integer>> list = new ArrayList<>();
        // [1,2,3],[4,5,6],[7,8,9]
        list.add(Arrays.asList(1, 2, 3));
        list.add(Arrays.asList(4, 5, 6));
        list.add(Arrays.asList(7, 8, 9));

        // [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
        //list.add(Arrays.asList(5, 1, 9, 11));
        //list.add(Arrays.asList(2, 4, 8, 10));
        //list.add(Arrays.asList(13, 3, 6, 7));
        //list.add(Arrays.asList(15, 14, 12, 16));

        int[][] matrix = new int[list.size()][list.get(0).size()];
        for (int i = 0; i < list.size(); i++) {
            List<Integer> integers = list.get(i);
            for (int j = 0; j < integers.size(); j++) {
                matrix[i][j] = integers.get(j);
            }
        }
        s.rotate(matrix);
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
