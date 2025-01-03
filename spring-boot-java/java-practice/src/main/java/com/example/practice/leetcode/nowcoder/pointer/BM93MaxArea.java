package com.example.practice.leetcode.nowcoder.pointer;

/**
 * @author fzy
 * @description:
 * @date 3/1/2025 上午11:34
 */
public class BM93MaxArea {

    /*
    给定一个数组height，长度为n，每个数代表坐标轴中的一个点的高度，height[i]是在第i点的高度，
    请问，从中选2个高度与x轴组成的容器最多能容纳多少水

    1.你不能倾斜容器
    2.当n小于2时，视为不能形成容器，请返回0
    3.数据保证能容纳最多的水不会超过整形范围，即不会超过(2^31)-1

    input height = [1,7,3,2,4,5,8,2,7]
    output 49


     */

    public int maxArea(int[] height) {
        if (height.length <= 1) {
            return 0;
        }
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            maxArea = Math.max((right - left) * Math.min(height[left], height[right]), maxArea);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        BM93MaxArea s = new BM93MaxArea();
        System.out.println(s.maxArea(new int[]{1, 7, 3, 2, 4, 5, 8, 2, 7}));
        System.out.println(s.maxArea(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }
}
