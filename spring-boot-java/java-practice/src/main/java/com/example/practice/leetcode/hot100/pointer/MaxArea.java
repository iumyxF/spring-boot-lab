package com.example.practice.leetcode.hot100.pointer;

/**
 * @author fzy
 * @description:
 * @date 2025/1/20 9:37
 */
public class MaxArea {

    /*
    给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
    找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
    返回容器可以储存的最大水量。

    输入：[1,8,6,2,5,4,8,3,7]
    输出：49
    解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

    分析：
    1. 双指针
    2. 指针移动规则，每次保留大的指针。
     */

    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int area = Integer.MIN_VALUE;
        while (left < right) {
            int tempArea = (right - left) * Math.min(height[left], height[right]);
            if (tempArea > area) {
                area = tempArea;
            }
            if (height[left] >= height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return area;
    }

    public static void main(String[] args) {
        MaxArea s = new MaxArea();
        System.out.println(s.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(s.maxArea(new int[]{3, 6, 1}));
        System.out.println(s.maxArea(new int[]{1, 2, 4, 3}));
    }
}
