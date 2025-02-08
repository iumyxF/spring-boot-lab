package com.example.practice.leetcode.hot100.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/2/8 10:03
 */
public class SpiralOrder {

    /*
    给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素

    输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
    输出：[1,2,3,6,9,8,7,4,5]

    输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
    输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     */

    public List<Integer> spiralOrder(int[][] matrix) {
        int width = matrix.length;
        ArrayList<Integer> res = new ArrayList<>();
        if (width == 0) {
            return res;
        }
        int len = matrix[0].length;

        int loop = getLoop(width, len);
        for (int i = 0; i < loop; i++) {
            print(matrix, res, i, i, width, len, i);
        }
        return res;
    }

    private int getLoop(int width, int len) {
        int temp = Math.min(width, len);
        return temp % 2 == 0 ? temp / 2 : (temp + 1) / 2;
    }

    public void print(int[][] matrix, List<Integer> res, int startX, int startY, int width, int len, int loop) {
        // 四个点 顺时针 x是上下，y是左右
        int top = startX;
        int bottom = width - loop - 1;
        int left = startY;
        int right = len - loop - 1;

        if (top == bottom && left == right) {
            res.add(matrix[startX][startY]);
            return;
        } else if (top == bottom) {
            // 一行
            while (left <= right) {
                res.add(matrix[top][left++]);
            }
            return;
        } else if (left == right) {
            // 一列
            while (top <= bottom) {
                res.add(matrix[top++][left]);
            }
            return;
        }

        int i = startX;
        int j = startY;

        while (j < right) {
            res.add(matrix[i][j++]);
        }
        while (i < bottom) {
            res.add(matrix[i++][j]);
        }
        while (j > left) {
            res.add(matrix[i][j--]);
        }
        while (i > top) {
            res.add(matrix[i--][j]);
        }
    }

    /**
     * 简洁版
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null) return list;
        //定义矩阵四个角
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int buttom = matrix.length - 1;

        //循环遍历
        while (left <= right && top <= buttom) {
            //从左到右
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            top++;
            //从上到下
            for (int i = top; i <= buttom; i++) {
                list.add(matrix[i][right]);
            }
            right--;
            //从右到左
            for (int i = right; i >= left && top <= buttom; i--) {
                list.add(matrix[buttom][i]);
            }
            buttom--;
            //从下到上
            for (int i = buttom; i >= top && left <= right; i--) {
                list.add(matrix[i][left]);
            }
            left++;
        }
        return list;
    }

    public static void main(String[] args) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        // [1,2,3],[4,5,6],[7,8,9] ==> 1,2,3,6,9,8,7,4,5
        //list.add(Arrays.asList(1, 2, 3));
        //list.add(Arrays.asList(4, 5, 6));
        //list.add(Arrays.asList(7, 8, 9));

        // [1,2,3,4],[5,6,7,8],[9,10,11,12] ==> 1,2,3,4,8,12,11,10,9,5,6,7
        //list.add(Arrays.asList(1, 2, 3, 4));
        //list.add(Arrays.asList(5, 6, 7, 8));
        //list.add(Arrays.asList(9, 10, 11, 12));

        // [1,2,3],[4,5,6],[7,8,9],[10,11,12] ==> 1,2,3,6,9,12,11,10,7,4,5,8
        //list.add(Arrays.asList(1, 2, 3));
        //list.add(Arrays.asList(4, 5, 6));
        //list.add(Arrays.asList(7, 8, 9));
        //list.add(Arrays.asList(10, 11, 12));

        // 1,2,3,4 ==> 1,2,3,4
        //list.add(Arrays.asList(1, 2, 3, 4));

        //list.add(Arrays.asList(1,2));
        //list.add(Arrays.asList(3,4));
        //list.add(Arrays.asList(5,6));
        //list.add(Arrays.asList(7,8));

        list.add(Arrays.asList(1, 2, 3, 4));
        list.add(Arrays.asList(5, 6, 7, 8));
        int[][] matrix = new int[list.size()][list.get(0).size()];
        for (int i = 0; i < list.size(); i++) {
            List<Integer> integers = list.get(i);
            for (int j = 0; j < integers.size(); j++) {
                matrix[i][j] = integers.get(j);
            }
        }
        SpiralOrder s = new SpiralOrder();
        System.out.println(s.spiralOrder(matrix));
    }
}
