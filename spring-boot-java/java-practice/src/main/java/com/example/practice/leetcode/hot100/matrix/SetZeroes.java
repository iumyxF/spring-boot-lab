package com.example.practice.leetcode.hot100.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/2/8 9:38
 */
public class SetZeroes {

    /*
    给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。

    输入：matrix = [
                    [1,1,1],
                    [1,0,1],
                    [1,1,1]
    ]
    输出：[
                    [1,0,1],
                    [0,0,0],
                    [1,0,1]
    ]

    输入：matrix = [
                    [0,1,2,0],
                    [3,4,5,2],
                    [1,3,1,5]
    ]
    输出：[
                    [0,0,0,0],
                    [0,4,5,0],
                    [0,3,1,0]
    ]
     */

    /**
     * 1. 两个set 一个保存 0的行，一个保存 0的列 (遍历一次数组)
     * 2. 修改原数组，如果当前在0行或者0列就把数据置为0
     */
    public void setZeroes(int[][] matrix) {
        int width = matrix.length;
        int len = matrix[0].length;
        HashSet<Integer> wset = new HashSet<>(width);
        HashSet<Integer> lset = new HashSet<>(len);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < len; j++) {
                if (matrix[i][j] == 0) {
                    wset.add(i);
                    lset.add(j);
                }
            }
        }
        if (wset.isEmpty() && lset.isEmpty()) {
            return;
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < len; j++) {
                if (wset.contains(i) || lset.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        SetZeroes s = new SetZeroes();
        ArrayList<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1, 1, 2, 1));
        //list.add(Arrays.asList(3, 4, 5, 2));
        //list.add(Arrays.asList(1, 3, 1, 5));
        int[][] matrix = new int[list.size()][list.get(0).size()];
        for (int i = 0; i < list.size(); i++) {
            List<Integer> integers = list.get(i);
            for (int j = 0; j < integers.size(); j++) {
                matrix[i][j] = integers.get(j);
            }
        }
        s.setZeroes(matrix);
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
