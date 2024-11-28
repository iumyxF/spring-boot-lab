package com.example.practice.leetcode.nowcoder.acm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author fzy
 * @description:
 * @date 2024/11/28 10:10
 */
public class InputOutPutTest {

    /*
    ACM 模式要自己编写输入
    https://ac.nowcoder.com/acm/contest/5657
     */

    /**
     * 字符串 连续输入的处理
     */
    public static void mainOfString(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder sb;
        while (in.hasNext()) {
            sb = new StringBuilder();
            String line = in.nextLine();
            String[] split = line.split(",");
            Arrays.sort(split);
            sb.append(split[0]);
            for (int i = 1; i < split.length; i++) {
                sb.append(",").append(split[i]);
            }
            System.out.println(sb);
        }
    }

    /**
     * 输入有多组测试用例，每组空格隔开两个整数
     * 对于每组数据输出一行两个整数的和
     * <p>
     * input: 1 1
     * output : 2
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            System.out.println(in.nextLong() + in.nextLong());
        }
    }
}
