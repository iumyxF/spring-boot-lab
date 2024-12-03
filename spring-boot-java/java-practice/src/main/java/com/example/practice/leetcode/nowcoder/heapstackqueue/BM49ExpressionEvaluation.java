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

    分析
     */


    //public int solve(String s) {
    //    Deque<Character> q = new LinkedList<>();
    //    for (char c : s.toCharArray()) {
    //        q.offerLast(c);
    //    }
    //    return dfs(q);
    //}
    //
    //private int dfs(Deque<Character> q) {
    //    Stack<Integer> stack = new Stack<>();
    //    // 当前运算符的前一个运算符
    //    char op = '+';
    //    // op后面的数
    //    int num = 0;
    //    int res = 0;
    //    while (!q.isEmpty()) {
    //        char c = q.pollFirst();
    //        // c是数字就更新数字
    //        if (Character.isDigit(c)) {
    //            num = num * 10 + c - '0';
    //        }
    //        // 左括号就进入递归
    //        if (c == '(') {
    //            num = dfs(q);
    //        }
    //        // c是运算符
    //        if (!Character.isDigit(c) && c != ' ' || q.isEmpty()) {
    //            // 加减法直接进栈保存，乘除要先处理上一个元素和当前元素再进栈
    //            if (op == '+') {
    //                stack.push(num);
    //            } else if (op == '-') {
    //                stack.push(-num);
    //            } else if (op == '*') {
    //                stack.push(stack.pop() * num);
    //            } else if (op == '/') {
    //                stack.push(stack.pop() / num);
    //            }
    //            num = 0;
    //            op = c;
    //        }
    //        // 是右括号就退出循环 直接返回结果
    //        if (c == ')') {
    //            break;
    //        }
    //    }
    //    // System.out.println(stack.toString());
    //    for (int i : stack) {
    //        res += i;
    //    }
    //    return res;
    //}


    public static void main(String[] args) {
        BM49ExpressionEvaluation s = new BM49ExpressionEvaluation();
        // System.out.println(s.solve("1+2"));
        System.out.println(s.solve("(2*(3-4))*5"));
        //System.out.println(s.solve("3+2*3*4-1"));
    }

    public int solve(String s) {
        s = s.replace(" ", "");
        LinkedList<Character> linkedList = new LinkedList<>();
        for (char c : s.toCharArray()) {
            linkedList.offerLast(c);
        }
        return dfs(linkedList);
    }

    private int dfs(LinkedList<Character> linkedList) {
        Stack<Integer> stack = new Stack<>();
        char oper = '+';
        int num = 0;
        int res = 0;
        while (!linkedList.isEmpty()){
            char c = linkedList.pollFirst();
            // 转换成数字
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            // 递归
            if (c == '(') {
                num = dfs(linkedList);
            }
            // 运算
            if (!Character.isDigit(c) || linkedList.isEmpty()) {
                if (oper == '+') {
                    stack.push(num);
                } else if (oper == '-') {
                    stack.push(-num);
                } else if (oper == '*') {
                    stack.push(stack.pop() * num);
                } else if (oper == '/') {
                    stack.push(stack.pop() / num);
                }
                // 重置值
                oper = c;
                num = 0;
            }
            // 结束递归
            if (c == ')') {
                break;
            }
        }
        for (Integer item : stack) {
            res += item;
        }
        return res;
    }

}
