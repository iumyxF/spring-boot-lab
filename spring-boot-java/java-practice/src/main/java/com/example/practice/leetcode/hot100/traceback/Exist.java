package com.example.practice.leetcode.hot100.traceback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/3/4 9:22
 */
public class Exist {

    /*
    给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
    单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

    输入：board = [
        ["A","B","C","E"],
        ["S","F","C","S"],
        ["A","D","E","E"]
        ],
        word = "ABCCED"
    输出：true

    输入：board = [
        ["A","B","C","E"],
        ["S","F","C","S"],
        ["A","D","E","E"]
        ],
        word = "SEE"
    输出：true

    输入：board = [
        ["A","B","C","E"],
        ["S","F","C","S"],
        ["A","D","E","E"]
        ],
        word = "ABCB"
    输出：false
     */


    int[][] direction = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean[][] used = new boolean[rows][cols];
                    // 从当前单元格开始搜索
                    if (doSearch(board, i, j, used, word, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean doSearch(char[][] board, int i, int j, boolean[][] used, String word, int index) {
        // 判断当前单元格是否越界、是否已使用或字符不匹配
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length
                || used[i][j] || word.charAt(index) != board[i][j]) {
            return false;
        }
        // 标记当前单元格已使用
        used[i][j] = true;
        index++;
        // 判断是否已经匹配完整个单词
        if (index == word.length()) {
            return true;
        }
        // 尝试向四个方向继续搜索
        for (int[] d : direction) {
            if (doSearch(board, i + d[0], j + d[1], used, word, index)) {
                return true;
            }
        }
        used[i][j] = false;
        return false;
    }

    public static void main(String[] args) {
        Exist s = new Exist();

        ArrayList<List<Character>> list = new ArrayList<>();
        //board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
        list.add(Arrays.asList('A', 'B', 'C', 'E'));
        list.add(Arrays.asList('S', 'F', 'C', 'S'));
        list.add(Arrays.asList('A', 'D', 'E', 'E'));
        String target = "ABCCED";
        char[][] board = new char[list.size()][list.get(0).size()];
        for (int i = 0; i < list.size(); i++) {
            List<Character> strings = list.get(i);
            for (int j = 0; j < strings.size(); j++) {
                board[i][j] = strings.get(j);
            }
        }
        System.out.println(s.exist(board, target));
    }
}
