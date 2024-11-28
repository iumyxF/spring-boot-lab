package com.example.practice.leetcode.od.e_volume;

/**
 * @author feng
 * @description:
 * @date 2024/11/28 21:27
 */

import java.util.*;

public class E1001Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 发动机的总个数
        int e = sc.nextInt(); // 计划手动启动的发动机总个数

        int[] launches = new int[n]; // 记录每个发动机的最终启动时刻
        Arrays.fill(launches, 1001); // 初始化为极大值

        Queue<int[]> queue = new LinkedList<>(); // 用于BFS的队列

        for (int i = 0; i < e; i++) {
            int t = sc.nextInt(); // 手动启动时刻
            int p = sc.nextInt(); // 发动机的位置编号
            launches[p] = t; // 手动启动时刻
            queue.offer(new int[]{p, t}); // 将手动启动的发动机加入队列
        }

        // 进行BFS，计算所有发动机的最早启动时间
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int pos = current[0];
            int time = current[1];

            // 计算相邻的发动机位置
            int[] neighbors = {(pos - 1 + n) % n, (pos + 1) % n}; // 考虑环状结构

            for (int neighbor : neighbors) {
                if (launches[neighbor] > time + 1) { // 更新更早的启动时刻
                    launches[neighbor] = time + 1;
                    queue.offer(new int[]{neighbor, time + 1});
                }
            }
        }

        int maxT = 0; // 最晚启动时刻
        ArrayList<Integer> lastEngines = new ArrayList<>(); // 最晚启动的发动机集合

        for (int i = 0; i < n; i++) {
            if (launches[i] > maxT) { // 找到更晚启动的时刻
                maxT = launches[i];
                lastEngines.clear();
                lastEngines.add(i);
            } else if (launches[i] == maxT) { // 相同的最晚启动时刻
                lastEngines.add(i);
            }
        }

        // 输出结果
        System.out.println(lastEngines.size());
        Collections.sort(lastEngines); // 排序发动机编号
        for (int engine : lastEngines) {
            System.out.print(engine + " ");
        }
    }
}

