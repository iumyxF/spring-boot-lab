package com.example.practice.leetcode.nowcoder.recursionbacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2024/12/6 16:28
 */
public class BM57Solve {

    /*
    给一个01矩阵，1代表是陆地，0代表海洋， 如果两个1相邻，那么这两个1属于同一个岛。我们只考虑上下左右为相邻。
    岛屿: 相邻陆地可以组成一个岛屿（相邻:上下左右） 判断岛屿个数。
    例如：
    输入
    [
    [1,1,0,0,0],
    [0,1,0,1,1],
    [0,0,0,1,1],
    [0,0,0,0,0],
    [0,0,1,1,1]
    ]
    输出 3
     */

    public int solve(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    recursion(grid, i, j);
                }
            }
        }
        return res;
    }

    public void recursion(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        recursion(grid, i, j - 1);
        recursion(grid, i, j + 1);
        recursion(grid, i - 1, j);
        recursion(grid, i + 1, j);
    }

    public static void main(String[] args) {
        BM57Solve s = new BM57Solve();
        ArrayList<List<Character>> grid = new ArrayList<>();
        grid.add(Arrays.asList('1', '1', '0', '0', '0'));
        grid.add(Arrays.asList('0', '1', '0', '1', '1'));
        grid.add(Arrays.asList('0', '0', '0', '1', '1'));
        grid.add(Arrays.asList('0', '0', '0', '0', '0'));
        grid.add(Arrays.asList('0', '0', '1', '1', '1'));
        char[][] gridArr = new char[grid.size()][grid.get(0).size()];
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                gridArr[i][j] = grid.get(i).get(j);
            }
        }
        System.out.println(s.solve(gridArr));
    }
}
