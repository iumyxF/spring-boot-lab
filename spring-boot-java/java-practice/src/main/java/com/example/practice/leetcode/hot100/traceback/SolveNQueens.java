package com.example.practice.leetcode.hot100.traceback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/3/5 9:01
 */
public class SolveNQueens {

    /*
    按照国际象棋的规则，皇后可以攻击与之处在 同一行 或 同一列 或 同一斜 线上的棋子。
    n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
    给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
    每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

    输入：n = 4
    输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
     */

    public List<List<String>> solveNQueens(int n) {
        ArrayList<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] chars : board) {
            Arrays.fill(chars, '.');
        }
        //traceback(board, n, 0, 0, res);
        backtracking(n, 0, board, res);
        return res;
    }

    private void traceback(char[][] board, int n, int curRow, int qCount, List<List<String>> res) {
        if (qCount == n) {
            save(board, res);
            return;
        }
        for (int i = curRow; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (canPut(board, i, j)) {
                    board[i][j] = 'Q';
                    qCount++;
                    traceback(board, n, i + 1, qCount, res);
                    qCount--;
                    board[i][j] = '.';
                }
            }
        }
    }

    private void save(char[][] board, List<List<String>> res) {
        ArrayList<String> path = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]);
            }
            path.add(sb.toString());
        }
        res.add(path);
    }

    private boolean canPut(char[][] board, int i, int j) {
        int len = board.length;
        int width = board[i].length;
        // 同一行
        int index = 0;
        while (index < width) {
            if (board[i][index++] == 'Q') {
                return false;
            }
        }
        // 同一列
        index = 0;
        while (index < len) {
            if (board[index++][j] == 'Q') {
                return false;
            }
        }
        // 斜线
        int x = i;
        int y = j;
        while (x >= 0 && y >= 0) {
            if (board[x--][y--] == 'Q') {
                return false;
            }
        }

        x = i;
        y = j;
        while (x >= 0 && y < width) {
            if (board[x--][y++] == 'Q') {
                return false;
            }
        }

        x = i;
        y = j;
        while (x < len && y >= 0) {
            if (board[x++][y--] == 'Q') {
                return false;
            }
        }
        x = i;
        y = j;
        while (x < len && y < width) {
            if (board[x++][y++] == 'Q') {
                return false;
            }
        }
        return true;
    }

    /*
    优化版本
     */

    public void backtracking(int n, int row, char[][] chessboard, List<List<String>> res) {
        // 终止条件
        if (row == n) {
            res.add(Array2List(chessboard));
            return;
        }
        // 递归
        for (int i = 0; i < n; i++) {
            if (isValid(row, i, chessboard, n)) {
                chessboard[row][i] = 'Q';
                backtracking(n, row + 1, chessboard, res);
                chessboard[row][i] = '.';
            }
        }
    }

    /**
     * 三个方向即可
     */
    public boolean isValid(int row, int col, char[][] chessboard, int n) {
        // 90 / 180 度方向
        for (int i = 0; i < row; i++) {
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }
        // 西北方向
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        // 东北方向
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
        SolveNQueens s = new SolveNQueens();
        List<List<String>> lists = s.solveNQueens(4);
        for (List<String> list : lists) {
            System.out.println(list);
        }
    }
}
