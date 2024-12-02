package com.example.practice.leetcode.nowcoder.heapstackqueue;

import java.util.*;

/**
 * @author fzy
 * @description:
 * @date 2024/12/2 16:29
 */
public class BM49ExpressionEvaluation {

    /*
    请写一个整数计算器，支持加减乘三种运算和括号。
    数据范围 0 <= |s| <= 100 保证计算结果始终在整形范围内

    要求：空间复杂度O(n),时间复杂度O(n)

    输入："1+2"
    输出：3

    输入："(2*(3-4))*5"
    输出：-10

    输入："3+2*3*4-1"
    输出：26

    分析，前缀树
    输入的表达式是中序遍历 ==> 构建成 树
    负数？
     */

    public static List<String> operList = new ArrayList<>(4);

    static {
        operList.add("+");
        operList.add("-");
        operList.add("*");
        operList.add("/");
    }


    public int solve(String s) {
        StringBuilder sb = new StringBuilder(s);
        Stack<String> stack = new Stack<>();

        int startIndex = 0;
        // 找出第一个数字
        if (sb.charAt(0) != '(') {
            int firstOperIndex = 0;
            for (int i = 0; i < sb.length(); i++) {
                if (operList.contains(String.valueOf(sb.charAt(i)))) {
                    firstOperIndex = i;
                    break;
                }
            }
            stack.push(sb.substring(0, startIndex));
            startIndex = firstOperIndex;
        }

        int leftCount = 0;

        for (int i = startIndex; i < sb.length(); i++) {
            String value = String.valueOf(sb.charAt(i));
            // 处理括号值
            if (value.equals("(")) {
                leftCount++;
            }
            if (value.equals(")")) {
                StringBuilder exp = new StringBuilder();
                while (!stack.isEmpty()) {
                    String pop = stack.pop();
                    exp.append(pop);
                    if (pop.equals("(")) {
                        int compute = compute(exp.reverse().toString());
                        stack.push(String.valueOf(compute));
                        leftCount--;
                    }
                }
            }

        }

        return -1;
    }

    public int compute(String exp) {
        // 获取运算符号
        String oper = "";
        int operIndex = 0;
        for (int i = 1; i < exp.length(); i++) {
            String value = String.valueOf(exp.charAt(i));
            if (operList.contains(value)) {
                oper = value;
                operIndex = i;
                break;
            }
        }
        int a = Integer.parseInt(exp.substring(0, operIndex));
        int b = Integer.parseInt(exp.substring(operIndex + 1));
        switch (oper) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        BM49ExpressionEvaluation s = new BM49ExpressionEvaluation();
        StringBuilder sb = new StringBuilder("-2");
        System.out.println(sb.substring(0, 1));
        System.out.println(sb.substring(0, 2));
    }

}
