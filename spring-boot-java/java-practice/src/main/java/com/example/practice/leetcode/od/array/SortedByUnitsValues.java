package com.example.practice.leetcode.od.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author fzy
 * @description:
 * @date 2024/11/28 15:03
 */
public class SortedByUnitsValues {

    /*
    给定一个非空数组(列表)，其元素数据类型为整型，请按照数组元素十进制最低位从小到大进行排序，十进制最低位相同的元素，相对位置保持不变。
    当数组元素为负值时，十进制最低位等同于去除符号位后对应十进制值最低位。
    二、输入描述
    给定一个非空数组，其元素数据类型为32位有符号整数，数组长度[1,1000]
    三、输出描述
    输出排序后的数组
    输入: 1,2,5,-21,22,11,55,-101,42,8,7,32
    输出: 1,-21,11,-101,2,22,42,32,5,55,7,8
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] split = line.split(",");
        solution(split);
    }

    public static void solution(String[] list) {
        // 直接用ascii的值来比大小 1 = 49
        Arrays.sort(list, Comparator.comparingInt(o -> o.charAt(o.length() - 1)));
        System.out.println(Arrays.toString(list));
    }

}
