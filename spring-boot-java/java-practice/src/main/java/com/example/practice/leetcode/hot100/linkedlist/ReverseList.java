package com.example.practice.leetcode.hot100.linkedlist;

import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/2/11 9:29
 */
public class ReverseList {

    /*
    给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     */

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        ReverseList s = new ReverseList();
        s.reverseList(n1);
    }
}
