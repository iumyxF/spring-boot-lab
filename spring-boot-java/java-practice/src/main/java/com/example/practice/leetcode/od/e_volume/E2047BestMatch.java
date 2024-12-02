package com.example.practice.leetcode.od.e_volume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author fzy
 * @description:
 *
 * @date 2024/11/30 14:22
 */
public class E2047BestMatch {

    /**
     * <a href="https://blog.csdn.net/banxia_frontend/article/details/142751910"/>
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 队伍数量 n
        int n = scanner.nextInt();
        // 最大允许差距 d
        int d = scanner.nextInt();
        // 用各队伍的实力值
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            nums.add(num);
        }
        // 排序
        Collections.sort(nums);
        solution(n, d, nums);
    }

    public static void solution(int teamCount, int maxGap, List<Integer> nums) {
        // 保存能匹配的队伍的数量
        List<Integer> matchCountList = new ArrayList<>(Collections.nCopies(teamCount + 1, 0));
        // 保存匹配队伍的累计差值
        List<Integer> totalGap = new ArrayList<>(Collections.nCopies(teamCount + 1, 0));

        for (int i = 2; i <= teamCount; i++) {
            // 记录当前队伍和前面的队伍能否匹配
            int m = 0;
            if (nums.get(i - 1) - nums.get(i - 2) <= maxGap) {
                m++;
            }
            if (matchCountList.get(i - 2) + m > matchCountList.get(i - 1)) {
                // 匹配数量比上次更多,直接更新
                matchCountList.set(i, matchCountList.get(i - 2) + m);
                // 差值直接用当前差值
                totalGap.set(i, totalGap.get(i - 2) + nums.get(i - 1) - nums.get(i - 2));
            } else if (matchCountList.get(i - 2) + m < matchCountList.get(i - 1)) {
                // 匹配数量比上次少，直接用前面的值
                matchCountList.set(i, matchCountList.get(i - 1));
                totalGap.set(i, totalGap.get(i - 1));
            } else {
                // 匹配数量相同, 和上一个匹配数量一样 复用即可
                matchCountList.set(i, matchCountList.get(i - 1));
                // 匹配数量相同，计算最少差值
                if (m == 1) {
                    totalGap.set(i, Math.min(totalGap.get(i - 1), totalGap.get(i - 2) + nums.get(i - 1) - nums.get(i - 2)));
                } else {
                    totalGap.set(i, Math.min(totalGap.get(i - 1), totalGap.get(i - 2)));
                }
            }
        }
        if (totalGap.get(teamCount) == 0) {
            System.out.println(-1);
        } else {
            System.out.println(totalGap.get(teamCount));
        }
    }
}
