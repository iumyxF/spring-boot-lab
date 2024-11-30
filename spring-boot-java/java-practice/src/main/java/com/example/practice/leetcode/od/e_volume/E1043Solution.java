package com.example.practice.leetcode.od.e_volume;

import java.util.Scanner;

/**
 * @author fzy
 * @description:
 * @date 2024/11/30 9:37
 */
public class E1043Solution {
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

        // 遍历所有可能的电站位置，计算该位置的矩形区域发电量
        int ans = 0;
        for (int i = s; i <= r; i++) {
            for (int j = s; j <= c; j++) {
                int square = 0;
                for (int x = i - s; x < i; x++) {
                    for (int y = j - s; y < j; y++) {
                        square += matrix[x][y];
                    }
                }
                if (square >= min) ans++;
            }
        }

        // 输出结果
        System.out.println(ans);
    }
}
