package com.example.practice.leetcode.nowcoder.recursionbacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2024/12/9 11:09
 */
public class BM61LongestIncreasingPathOfMatrix {

    /*
    给定一个 n 行 m 列矩阵 matrix ，矩阵内所有数均为非负整数。 你需要在矩阵中找到一条最长路径，使这条路径上的元素是递增的。并输出这条最长路径的长度。
    这个路径必须满足以下条件：

    1. 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外。
    2. 你不能走重复的单元格。即每个格子最多只能走一次。

    数据范围：1 <= n,m <= 1000, 0 <= matrix[i][j] <= 1000
    进阶：空间复杂度O(nm),时间复杂度O(nm)

    例如：当输入为[[1,2,3],[4,5,6],[7,8,9]]时，对应的输出为5，
    其中的一条最长递增路径如下图所示：
    [
    [1,2,3]
    [4,5,6]
    [7,8,9]
    ]
    1->2->3->6->9即可

    输入：[[1,2,3],[4,5,6],[7,8,9]]
    输出：5
     */

    int maxPath = 0;

    public int solve(int[][] matrix) {
        boolean[][] used = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                dfs(matrix, i, j, 0, used);
            }
        }
        return maxPath;
    }

    public void dfs(int[][] arr, int i, int j, int tempPath, boolean[][] used) {
        if (i < 0 || i >= arr.length - 1 || j < 0 || j >= arr.length - 1) {
            maxPath = Math.max(tempPath, maxPath);
            return;
        }
        int base = arr[i][j];
        // 上下左右
        if (i > 0 && !used[i - 1][j] && base < arr[i - 1][j]) {
            used[i][j] = true;
            dfs(arr, i - 1, j, tempPath + 1, used);
            used[i][j] = false;
        }
        if (i + 1 < arr.length && !used[i + 1][j] && base < arr[i + 1][j]) {
            used[i][j] = true;
            dfs(arr, i + 1, j, tempPath + 1, used);
            used[i][j] = false;
        }
        if (j > 0 && !used[i][j - 1] && base < arr[i][j - 1]) {
            used[i][j] = true;
            dfs(arr, i, j - 1, tempPath + 1, used);
            used[i][j] = false;
        }
        if (j + 1 < arr[i].length && !used[i][j + 1] && base < arr[i][j + 1]) {
            used[i][j] = true;
            dfs(arr, i, j + 1, tempPath + 1, used);
            used[i][j] = false;
        } else {
            // 无路可走
            maxPath = Math.max(tempPath + 1, maxPath);
        }
    }

    public static void main(String[] args) {
        BM61LongestIncreasingPathOfMatrix s = new BM61LongestIncreasingPathOfMatrix();
        //int[][] m = new int[3][3];
        //int index = 1;
        //for (int i = 0; i < m.length; i++) {
        //    for (int j = 0; j < m[i].length; j++) {
        //        m[i][j] = index++;
        //    }
        //}

        ArrayList<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(4, 3, 3, 6, 6, 3, 2, 1, 0, 7));
        list.add(Arrays.asList(1, 8, 2, 8, 5, 9, 2, 8, 3, 1));
        list.add(Arrays.asList(8, 0, 9, 2, 4, 3, 2, 4, 3, 7));

        list.add(Arrays.asList(1, 2, 2, 6, 3, 0, 3, 9, 7, 0));
        list.add(Arrays.asList(7, 4, 3, 8, 8, 3, 2, 4, 6, 8));
        list.add(Arrays.asList(2, 8, 9, 2, 9, 3, 0, 8, 7, 8));

        list.add(Arrays.asList(8, 9, 9, 4, 6, 3, 3, 4, 9, 6));
        list.add(Arrays.asList(2, 8, 3, 8, 1, 3, 7, 3, 0, 7));
        list.add(Arrays.asList(2, 1, 1, 6, 4, 1, 0, 8, 1, 6));
        list.add(Arrays.asList(4, 1, 3, 6, 3, 4, 4, 4, 0, 3));
        int[][] arr = new int[list.size()][list.get(0).size()];
        for (int i = 0; i < list.size(); i++) {
            List<Integer> integers = list.get(i);
            for (int j = 0; j < list.get(i).size(); j++) {
                arr[i][j] = integers.get(j);
            }
        }
        System.out.println(s.solve(arr));
    }
}
