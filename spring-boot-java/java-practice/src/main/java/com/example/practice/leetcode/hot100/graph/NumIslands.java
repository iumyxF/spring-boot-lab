package com.example.practice.leetcode.hot100.graph;

/**
 * @author fzy
 * @description:
 * @date 2025/2/25 11:14
 */
public class NumIslands {

    /*
    200. 岛屿数量 mid
    给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
    此外，你可以假设该网格的四条边均被水包围。

    输入：grid = [
      ["1","1","1","1","0"],
      ["1","1","0","1","0"],
      ["1","1","0","0","0"],
      ["0","0","0","0","0"]
    ]
    输出：1

    输入：grid = [
      ["1","1","0","0","0"],
      ["1","1","0","0","0"],
      ["0","0","1","0","0"],
      ["0","0","0","1","1"]
    ]
    输出：3
     */

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                }
                handle(grid, i, j);
            }
        }
        return count;
    }

    /**
     * 把相邻的1变成0
     */
    private void handle(char[][] grid, int i, int j) {
        if (i >= 0 && j >= 0 && i < grid.length && j < grid[i].length && grid[i][j] == '1') {
            grid[i][j] = 0;
            handle(grid, i - 1, j);
            handle(grid, i + 1, j);
            handle(grid, i, j - 1);
            handle(grid, i, j + 1);
        }
    }
}
