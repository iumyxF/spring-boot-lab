package com.example.practice.leetcode.hot100.pointer;

/**
 * @author fzy
 * @description:
 * @date 2025/1/20 10:30
 */
public class Trap {

    /*
    给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

    输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
    输出：6
    解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。

    分析

    实现1: 超时了
    一行一行看
    假设获取 x 行的接水面积
    找到num[n]值大于等于的下标作为开始,在继续往后遍历找到第二个num[m],计算距离
    计算某一行的储水面积
    left 在左边定位大于等于line的下标
    right 在右边定位大于等于line的下标
    统计[left,right]之间小于line的个数就是面积

    实现2:
    动态规划
    两个数组 分别从左/右 两个方向保存最大值
    当前height[i] 比 min(left[i],right[i]) 大，就存储不了水
    如： 234，计算 3 的位置时是存不了的，
    而如果是 324，计算2的时候，看3，水量就是 3-2=1

     */

    public int trap(int[] height) {
        int len = height.length;
        // 从左向右的左边最大值
        int[] maxLeft = new int[len];
        maxLeft[0] = height[0];
        for (int i = 1; i < len; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
        }
        // 从右向左的右边最大值
        int[] maxRight = new int[len];
        maxRight[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            maxRight[i] = Math.max(height[i], maxRight[i + 1]);
        }
        int sum = 0;
        for (int i = 0; i < len; i++) {
            int min = Math.min(maxLeft[i], maxRight[i]);
            // 只有中间值小于min值才能储水
            if (height[i] < min) {
                sum += min - height[i];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Trap s = new Trap();
        System.out.println(s.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(s.trap(new int[]{0, 0, 1}));
    }
}
