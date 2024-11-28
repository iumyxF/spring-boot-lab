package com.example.practice.leetcode.other;

/**
 * @author fzy
 * @description: <a href="https://juejin.cn/post/7420251289703137315"/>
 * @date 2024/10/14 8:57
 */
public class SolutionMinimumSteps {

    /*
    AB 实验同学每天都很苦恼如何可以更好地进行 AB 实验,每一步的流程很重要,我们目标为了缩短所需的步数。
    我们假设每一步对应到每一个位置。
    从一个整数位置 x 走到另外一个整数位置 y,每一步的长度是正整数,每步的值等于上一步的值 -1, +0,+1。
    求 x 到 y 最少走几步。
    并且第一步必须是 1,最后一步必须是 1,从 x 到 y 最少需要多少步。

    整数位置 x 为 12,另外一个整数位置 y 为 6,我们需要从 x 走到 y,最小的步数为：1,2,2,1,所以我们需要走 4 步。
    整数位置 x 为 34,另外一个整数位置 y 为 45,我们需要从 x 走到 y,最小的步数为：1,2,3,2,2,1,所以我们需要走 6 步。
    整数位置 x 为 50,另外一个整数位置 y 为 30,我们需要从 x 走到 y,最小的步数为：1,2,3,4,4,3,2,1,所以我们需要走 8 步。

    1 2 2 3 2 2 1 = 16
    1 2 3 4 3 2 1 = 16

    1 2 2 3 2 1 = 11
    1 2 3 2 2 1

    输入格式
    输入包含 2 个整数 x,y（0<=x<=y<2^31）

    输出格式
    对于每一组数据,输出一行,仅包含一个整数,从 x 到 y 所需最小步数。

    输入样例
    12 6
    34 45
    50 30
     */
    public static void main(String[] args) {
        System.out.println(minSteps(12, 6));
        System.out.println(minSteps(34, 45));
        System.out.println(minSteps(50, 30));
    }

    public static int minSteps(int x, int y) {
        return -1;
    }
}
