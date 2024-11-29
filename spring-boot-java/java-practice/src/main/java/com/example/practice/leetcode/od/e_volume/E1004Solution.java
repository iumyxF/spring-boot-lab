package com.example.practice.leetcode.od.e_volume;

import java.util.Scanner;

/**
 * @author fzy
 * @description:
 * @date 2024/11/29 11:26
 */
public class E1004Solution {

    public static void main(String[] args) {
        // 处理输入
        Scanner in = new Scanner(System.in);
        int itemNumber = in.nextInt(); // 商品数量
        int days = in.nextInt(); // 售货天数
        int[] maxItems = new int[itemNumber]; // 每件商品最大持有数量
        for (int i = 0; i < itemNumber; i++) {
            maxItems[i] = in.nextInt();
        }
        int[][] prices = new int[itemNumber][days]; // 商品价格列表
        for (int i = 0; i < itemNumber; i++) {
            for (int j = 0; j < days; j++) {
                prices[i][j] = in.nextInt();
            }
        }

        // 贪心算法
        int maxProfit = 0;
        for (int i = 0; i < itemNumber; i++) { // 遍历每件商品
            for (int j = 1; j < days; j++) { // 遍历商品价格列表，求出每天的利润
                int profit = Math.max(0, prices[i][j] - prices[i][j - 1]);
                // 当前价格减去前一天价格，如果为负数则代表亏本，不计入利润
                maxProfit += profit * maxItems[i]; // 求出当前商品能够获取的最大利润
            }
        }

        System.out.println(maxProfit); // 输出最大利润
    }
}