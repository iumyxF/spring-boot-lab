package com.example.practice.leetcode.hot100.linkedlist;

/**
 * @author fzy
 * @description:
 * @date 2025/2/11 16:20
 */
public class AddTwoNumbers {
    /*

    给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
    请你将两个数相加，并以相同形式返回一个表示和的链表。
    你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

    输入：l1 = [2,4,3], l2 = [5,6,4]
    输出：[7,0,8]
    解释：342 + 465 = 807.
        2 4 3
      + 5 6 4
      = 8 0 7
     */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode first = new ListNode(0);
        ListNode cur = first;
        int up = 0;
        while (l1 != null && l2 != null) {
            int value = l1.val + l2.val + up;
            if (value >= 10) {
                up = value / 10;
                value = value % 10;
            } else {
                up = 0;
            }
            l1 = l1.next;
            l2 = l2.next;
            cur.next = new ListNode(value);
            cur = cur.next;
        }
        while (l1 != null) {
            int value = l1.val + up;
            if (value >= 10) {
                up = value / 10;
                value = value % 10;
            } else {
                up = 0;
            }
            l1 = l1.next;
            cur.next = new ListNode(value);
            cur = cur.next;
        }
        while (l2 != null) {
            int value = l2.val + up;
            if (value >= 10) {
                up = value / 10;
                value = value % 10;
            } else {
                up = 0;
            }
            l2 = l2.next;
            cur.next = new ListNode(value);
            cur = cur.next;
        }
        if (up > 0) {
            cur.next = new ListNode(up);
        }
        return first.next;
    }

}
