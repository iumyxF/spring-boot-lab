package com.example.practice.leetcode.od.e_volume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author fzy
 * @description:
 * @date 2024/11/30 14:23
 */
public class E2047Solution {

    /*
        6 30
        81 87 47 59 81 18

        57

        6 20
        81 87 47 59 81 18

        12

        4 10
        40 51 62 73

        -1

        pairs 数组 保存能匹对成功的数量队伍

     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取队伍数量 n 和最大允许差距 d
        int n = scanner.nextInt();
        int d = scanner.nextInt();

        // 用来存储各队伍的实力值
        List<Integer> nums = new ArrayList<>();

        // 读取每个队伍的实力值并添加到 nums 列表中
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            nums.add(num);
        }

        // 对队伍实力值进行升序排序，方便后续贪心和动态规划处理
        Collections.sort(nums);

        // 初始化 pairs 列表，存储能匹配的对的数量，n+1个元素，初始值为 0
        List<Integer> pairs = new ArrayList<>(Collections.nCopies(n + 1, 0));

        // 初始化 min_sum 列表，存储最小的差值总和，n+1个元素，初始值为 0
        List<Integer> min_sum = new ArrayList<>(Collections.nCopies(n + 1, 0));

        // 从第3个元素开始考虑配对（因为要两两配对，所以从2开始）
        for (int i = 2; i < n + 1; i++) {
            int tmp = 0;

            // 如果当前两支队伍的实力差距在允许范围内，tmp置为1，表示可以匹配
            if (nums.get(i - 1) - nums.get(i - 2) <= d) {
                tmp += 1;
            }

            // 比较是否选择配对前两支队伍能获得更多的匹配数量
            if (pairs.get(i - 2) + tmp > pairs.get(i - 1)) {
                // 如果配对前两支队伍能得到更多的配对数量，更新 pairs 和 min_sum
                pairs.set(i, pairs.get(i - 2) + tmp);
                min_sum.set(i, min_sum.get(i - 2) + nums.get(i - 1) - nums.get(i - 2));
            }
            // 如果不配对前两支队伍能保持或增加匹配数量，则选择不配对
            else if (pairs.get(i - 2) + tmp < pairs.get(i - 1)) {
                // 配对数量不增加，保持原有的状态
                pairs.set(i, pairs.get(i - 1));
                min_sum.set(i, min_sum.get(i - 1));
            }
            // 如果配对数量相同，则选择差距更小的配对策略
            else {
                if (tmp == 1) {
                    // 如果配对，选择较小的差值总和
                    min_sum.set(i, Math.min(min_sum.get(i - 1), min_sum.get(i - 2) + nums.get(i - 1) - nums.get(i - 2)));
                } else {
                    // 如果不配对，选择保持原有的差值总和
                    min_sum.set(i, Math.min(min_sum.get(i - 1), min_sum.get(i - 2)));
                }
                pairs.set(i, pairs.get(i - 1));  // 匹配数量保持不变
            }
        }

        // 最终结果：如果没有任何队伍配对成功，输出 -1，否则输出最小的差值总和
        if (pairs.get(n) == 0) {
            System.out.println(-1);  // 无法配对
        } else {
            System.out.println(min_sum.get(n));  // 输出最小的差值总和
        }
    }
}
