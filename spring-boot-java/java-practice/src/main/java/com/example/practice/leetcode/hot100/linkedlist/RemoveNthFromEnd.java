package com.example.practice.leetcode.hot100.linkedlist;

/**
 * @author fzy
 * @description:
 * @date 2025/2/12 9:19
 */
public class RemoveNthFromEnd {

    /*
    给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

    输入：head = [1,2,3,4,5], n = 2
    输出：[1,2,3,5]

    输入：head = [1], n = 1
    输出：[]

    输入：head = [1,2], n = 1
    输出：[1]
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode first = new ListNode(-1);
        first.next = head;
        // left 定位到删除元素的前一个元素
        ListNode left = first;
        ListNode right = first;
        for (int i = 0; i < n; i++) {
            right = right.next;
        }
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;

        return first.next;
    }
}
