package com.example.practice.leetcode.od.e_volume;

/**
 * @author fzy
 * @description:
 * @date 2024/11/29 10:21
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class E1003Solution {
    public static void main(String[] args) {
        // 创建Scanner对象用于读取输入
        Scanner scanner = new Scanner(System.in);
        // 读取输入的字符串
        String chars = scanner.nextLine();
        // 定义大雁叫声的字符串
        String quack = "quack";
        // 定义一个数组，用于存储每个字符的状态
        int[] states = new int[quack.length()];
        // 定义一个ArrayList，用于存储每只大雁的叫声数量
        ArrayList<Integer> dp = new ArrayList<>();
        // 初始化最大值为0
        int max_ = 0;

        // 遍历输入的字符串
        for (int i = 0; i < chars.length(); i++) {
            // 获取当前字符在"quack"中的索引
            int index = quack.indexOf(chars.charAt(i));
            // 如果索引为-1，表示输入的字符串包含非法字符，输出-1并退出程序
            if (index == -1) {
                System.out.println(-1);
                System.exit(0);
            }

            // 如果索引为0，表示当前字符是'q'，更新状态数组
            if (index == 0) {
                states[index] += 1;
            } else {
                // 如果当前字符的前一个字符的状态大于0，更新状态数组
                if (states[index - 1] > 0) {
                    states[index - 1] -= 1;
                    states[index] += 1;
                }

                // 如果当前字符是'k'，表示一个完整的"quack"叫声已经结束
                if (quack.charAt(quack.length() - 1) == chars.charAt(i)) {
                    // 如果状态数组的最后一个元素不为0，表示有大雁正在叫
                    if (states[states.length - 1] != 0) {
                        // 创建一个临时数组，用于计算当前大雁的叫声数量
                        int[] temp = Arrays.copyOf(states, states.length);
                        temp[states.length - 1] = 0;
                        max_ = Math.max(max_, Arrays.stream(temp).sum());
                        // 遍历剩余的字符，更新临时数组
                        for (int j = i; j < chars.length(); j++) {
                            index = quack.indexOf(chars.charAt(j));
                            if (index > 0 && temp[index - 1] > 0) {
                                temp[index - 1] -= 1;
                                temp[index] += 1;
                            }
                            if (temp[states.length - 1] == max_) {
                                break;
                            }
                        }
                        // 将当前大雁的叫声数量添加到ArrayList中
                        dp.add(temp[states.length - 1] + 1);
                        // 更新状态数组
                        states[states.length - 1] -= 1;
                    }
                }
            }
        }

        // 输出结果，如果dp为空，表示没有找到一只大雁，输出-1；否则输出最大值
        System.out.println(dp.isEmpty() ? -1 : (int) Collections.max(dp));
    }
}




