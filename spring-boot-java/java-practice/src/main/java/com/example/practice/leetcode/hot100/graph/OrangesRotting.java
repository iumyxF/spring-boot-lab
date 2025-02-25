package com.example.practice.leetcode.hot100.graph;

import java.util.*;

/**
 * @author fzy
 * @description:
 * @date 2025/2/25 11:48
 */
public class OrangesRotting {

    /*
    在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：

    值 0 代表空单元格；
    值 1 代表新鲜橘子；
    值 2 代表腐烂的橘子。
    每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。

    返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。

    输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
    输出：4
     */

    public int orangesRotting(int[][] grid) {
        // 统计新鲜的橘子数量
        int m = grid.length;
        int n = grid[0].length;
        int freshCount = 0;
        // 保存储时烂橘子的坐标
        LinkedList<int[]> linkedList = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    linkedList.addLast(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }
        // 四个方向
        int[][] direction = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // 模拟腐烂
        int day = 0;
        while (freshCount > 0 && !linkedList.isEmpty()) {
            int size = linkedList.size();
            // 每次遍历size，因为腐烂可能从多个点开始
            for (int i = 0; i < size; i++) {
                int[] cur = linkedList.pollFirst();
                int x = cur[0];
                int y = cur[1];
                for (int[] dir : direction) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    if (newX >= 0 && newX <= m - 1 && newY >= 0 && newY <= n - 1 && grid[newX][newY] == 1) {
                        grid[newX][newY] = 2;
                        linkedList.addLast(new int[]{newX, newY});
                        freshCount--;
                    }
                }
            }
            day++;
        }
        return freshCount == 0 ? day : -1;
    }

    public static void main(String[] args) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        //[[2,1,1],[1,1,0],[0,1,1]]
        list.add(Arrays.asList(2, 1, 1));
        list.add(Arrays.asList(1, 1, 0));
        list.add(Arrays.asList(0, 1, 1));

        //[[2,1,1],[0,1,1],[1,0,1]]
        //list.add(Arrays.asList(2, 1, 1));
        //list.add(Arrays.asList(0, 1, 1));
        //list.add(Arrays.asList(1, 0, 1));
        int[][] arr = new int[list.size()][list.get(0).size()];

        for (int i = 0; i < list.size(); i++) {
            List<Integer> cl = list.get(i);
            for (int j = 0; j < list.get(i).size(); j++) {
                arr[i][j] = cl.get(j);
            }
        }

        OrangesRotting s = new OrangesRotting();
        System.out.println(s.orangesRotting(arr));
    }
}
