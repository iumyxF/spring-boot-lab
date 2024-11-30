package com.example.practice.leetcode.od.e_volume;

import java.util.Scanner;

/**
 * @author fzy
 * @description:
 * @date 2024/11/30 9:28
 */
public class E1043PhotovoltaicSiteConstructionPlanning {

    /**
     * https://blog.csdn.net/banxia_frontend/article/details/141950433
     * 用例
     * 1. 4
     * 2. 4
     * 3. 9
     * 4. 3
     * 5. 16
     * 6. 4
     * 7. 8
     * 8. 6
     * 9. 4
     * 10. 9
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入地区长r，宽c，电站边长s，最低发电量min
        int r = scanner.nextInt();
        int c = scanner.nextInt();
        int s = scanner.nextInt();
        int min = scanner.nextInt();

        // 输入每个区域每平方公里的发电量，存入矩阵matrix中
        int[][] matrix = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (getPower(matrix, s, i, j) >= min) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static int getPower(int[][] matrix, int size, int x, int y) {
        if (x + size > matrix.length || y + size > matrix[x].length) {
            return -1;
        }
        int res = 0;
        for (int i = x; i < size; i++) {
            for (int j = y; j < size; j++) {
                res += matrix[i][j];
            }
        }
        return res;
    }
}
