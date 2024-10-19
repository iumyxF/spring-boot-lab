package com.example.practice.leetcode.nowcoder;

import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2024/10/19 16:29
 */
public class BM13IsPail {

    /*
    给定一个链表，请判断该链表是否为回文结构。
    回文是指该字符串正序逆序完全一致。
     */
    public boolean isPail(ListNode head) {
        if (head == null) {
            return false;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (!stack.isEmpty()) {
            if (stack.pop().val != cur.val) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

}
