package com.example.practice.leetcode.nowcoder.simulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 8/1/2025 下午1:56
 */
public class BM99RotateMatrix {

    /*
    有一个NxN整数矩阵，请编写一个算法，将矩阵顺时针旋转90度。
    给定一个NxN的矩阵，和矩阵的阶数N,请返回旋转后的NxN矩阵。

    [
        [1,2,3],
        [4,5,6],
        [7,8,9]
    ]
    ,3

    [
        [7,4,1],
        [8,5,2],
        [9,6,3]
    ]
     */
    public int[][] rotateMatrix(int[][] mat, int n) {
        int[][] res = new int[mat.length][mat[0].length];
        int loop = n % 2 == 0 ? n / 2 : n / 2 + 1;
        for (int i = 0; i < loop; i++) {
            rotate(mat, res, n, i);
        }
        if (n % 2 != 0) {
            res[n / 2][n / 2] = mat[n / 2][n / 2];
        }
        return res;
    }

    /**
     * 单圈的旋转
     */
    public void rotate(int[][] mat, int[][] res, int n, int m) {
        int len = n - m - 1;
        if (len <= 0) {
            return;
        }
        // (m,m) (n-m-1,m),(n-m-1,n-m-1),(n-m-1,m)
        // x1,y1 mat
        int x1 = m;
        int y1 = m;
        // x2,y2 res
        int x2 = m;
        int y2 = len;
        for (int i = m; i < len; i++) {
            res[x2++][y2] = mat[x1][y1++];
        }

        x1 = m;
        y1 = len;
        x2 = len;
        y2 = len;
        for (int i = m; i < len; i++) {
            res[x2][y2--] = mat[x1++][y1];
        }

        x1 = len;
        y1 = len;
        x2 = len;
        y2 = m;
        for (int i = m; i < len; i++) {
            res[x2--][y2] = mat[x1][y1--];
        }

        x1 = len;
        y1 = m;
        x2 = m;
        y2 = m;
        for (int i = m; i < len; i++) {
            res[x2][y2++] = mat[x1--][y1];
        }
    }

    /**
     * 1. 把 行 --转成--> 列
     * 2. 把 每一行反转
     */
    public int[][] rotateMatrix2(int[][] matrix, int n) {
        // 先进行转置操作
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 再对每一行进行反转
        for (int i = 0; i < n; i++) {
            for (int j = 0, k = n - 1; j < k; j++, k--) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][k];
                matrix[i][k] = temp;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        BM99RotateMatrix s = new BM99RotateMatrix();
        ArrayList<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1, 2, 3));
        list.add(Arrays.asList(4, 5, 6));
        list.add(Arrays.asList(7, 8, 9));
        //list.add(Arrays.asList(1, 2, 3, 4));
        //list.add(Arrays.asList(5, 6, 7, 8));
        //list.add(Arrays.asList(9, 10, 11, 12));
        //list.add(Arrays.asList(13, 14, 15, 16));
        int[][] arr = new int[list.size()][list.size()];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                arr[i][j] = list.get(i).get(j);
            }
        }
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println();
        int[][] ints = s.rotateMatrix2(arr, list.size());
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }
}
