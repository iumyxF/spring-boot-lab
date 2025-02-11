package com.example.practice.leetcode.hot100.linkedlist;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2025/2/11 14:54
 */
public class IsPalindrome {

    /*
    给你一个单链表的头节点 head ，请你判断该链表是否为 回文链表 。如果是，返回 true ；否则，返回 false 。

    输入：head = [1,2,2,1]
    输出：true

    输入：head = [1,2]
    输出：false
     */

    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        cur = head;
        while (!stack.isEmpty()) {
            if (cur.val != stack.pop()) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }
}
