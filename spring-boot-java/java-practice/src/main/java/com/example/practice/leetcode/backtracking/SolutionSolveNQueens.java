package com.example.practice.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fzy
 * @description: N皇后
 * @date 2024/7/12 14:21
 */
public class SolutionSolveNQueens {

    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] chessboard = new char[n][n];
        for (char[] c : chessboard) {
            Arrays.fill(c, '.');
        }
        backtracking(n, 0, chessboard);
        return res;
    }

    /**
     * @param n
     * @param row        第几行
     * @param chessboard
     */
    public void backtracking(int n, int row, char[][] chessboard) {
        // 终止条件
        if (row == n) {
            res.add(Array2List(chessboard));
            return;
        }
        // 递归
        for (int i = 0; i < n; i++) {
            if (isValid(row, i, chessboard, n)) {
                chessboard[row][i] = 'Q';
                backtracking(n, row + 1, chessboard);
                chessboard[row][i] = '.';
            }
        }
    }

    /**
     * 三个方向即可
     */
    public boolean isValid(int row, int col, char[][] chessboard, int n) {
        // 90
        for (int i = 0; i < row; i++) {
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }
        // 45
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        // 135
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public List<String> Array2List(char[][] chessboard) {
        List<String> list = new ArrayList<>();
        for (char[] c : chessboard) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }

    public static void main(String[] args) {
        SolutionSolveNQueens s = new SolutionSolveNQueens();
        List<List<String>> res = s.solveNQueens(4);
        for (List<String> re : res) {
            System.out.println(re);
        }
    }
}
