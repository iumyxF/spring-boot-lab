package com.example.practice.leetcode.nowcoder.heapstackqueue;

import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2024/11/26 15:19
 */
public class BM42ImplTheQueueWithTwoStacks {

    /*
    用两个栈实现队列
    用两个栈来实现一个队列，使用n个元素来完成n次在队列尾部插入整数(push)和n次在
    队列头部删除整数(pop)的功能。队列中的元素为int类型。保证操作合法，即保证pop操
    作时队列内已有元素。
    数据范围：n <= 1000
    要求：存储个元素的空间复杂度为O(n)，插入与删除的时间复杂度都是O(1)

    分析：
    1. 栈 先进后出 ； 队列 先进先出
     */

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        } else {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
