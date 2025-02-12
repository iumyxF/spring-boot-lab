package com.example.practice.leetcode.hot100.linkedlist;

/**
 * @author fzy
 * @description:
 * @date 2025/2/12 9:45
 */
public class SwapPairs {

    /*
    给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。

    输入：head = [1,2,3,4]
    输出：[2,1,4,3]
     */

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode first = new ListNode(-1);
        first.next = head;
        ListNode pre = first;
        while (pre.next != null && pre.next.next != null) {
            ListNode left = pre.next;
            ListNode right = left.next;
            ListNode next = right.next;
            //swap
            right.next = left;
            left.next = next;
            pre.next = right;
            // move
            pre = pre.next.next;
        }
        return first.next;
    }
}
