package com.example.practice.leetcode.hot100.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2025/3/8 16:54
 */
public class DailyTemperatures {

    /*
    739. 每日温度
    给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
    其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。

    输入: temperatures = [73,74,75,71,69,72,76,73]
    输出: [1,1,4,2,1,1,0,0]

    输入: temperatures = [30,40,50,60]
    输出: [1,1,1,0]

    输入: temperatures = [30,60,90]
    输出: [1,1,0]

    answer[i] = 下一个大于answer[i]的下标 - i
     */

    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        // 单调栈
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            int base = temperatures[i];
            if (stack.isEmpty()) {
                res[i] = 0;
                stack.push(i);
            } else {
                while (!stack.isEmpty() && temperatures[stack.peek()] <= base) {
                    stack.pop();
                }
                res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
                stack.push(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        DailyTemperatures s = new DailyTemperatures();
        System.out.println(Arrays.toString(s.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
        System.out.println(Arrays.toString(s.dailyTemperatures(new int[]{1, 2})));
        System.out.println(Arrays.toString(s.dailyTemperatures(new int[]{2, 1})));
        System.out.println(Arrays.toString(s.dailyTemperatures(new int[]{1})));
    }

}
