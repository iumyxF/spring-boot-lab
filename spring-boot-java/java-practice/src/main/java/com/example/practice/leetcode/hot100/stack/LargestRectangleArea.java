package com.example.practice.leetcode.hot100.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2025/3/10 9:03
 */
public class LargestRectangleArea {

    /*
    84. 柱状图中最大的矩形
    给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
    求在该柱状图中，能够勾勒出来的矩形的最大面积。

    heights[i] * (r[i] - l[i] - 1)
    r[i]: heights[i]右侧最近比他小的下标
    l[i]: heights[i]左侧侧最近比他小的下标
     */

    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int[] left = new int[len];
        Arrays.fill(left, -1);
        int[] right = new int[len];
        Arrays.fill(right, len);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                right[stack.pop()] = i;
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                left[stack.pop()] = i;
            }
            stack.push(i);
        }
        int area = 0;
        for (int i = 0; i < len; i++) {
            area = Math.max(area, heights[i] * (right[i] - left[i] - 1));
        }
        return area;
    }

    public static void main(String[] args) {
        LargestRectangleArea s = new LargestRectangleArea();
        s.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3});
    }
}
