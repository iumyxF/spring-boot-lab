package com.example.practice.leetcode.hot100.linkedlist;

import java.util.LinkedList;

/**
 * @author fzy
 * @description:
 * @date 2025/2/12 11:23
 */
public class ReverseKGroup {

    /*
    给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。

    k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

    你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

    输入：head = [1,2,3,4,5], k = 2
    输出：[2,1,4,3,5]

    输入：head = [1,2,3,4,5], k = 3
    输出：[3,2,1,4,5]

    链表中的节点数目为 n
    1 <= k <= n <= 5000
    0 <= Node.val <= 1000

    进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
     */

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode first = new ListNode(-1);
        first.next = head;
        LinkedList<ListNode> list = new LinkedList<>();
        // 控制遍历
        ListNode cur = head;
        ListNode pre = first;
        while (cur != null) {
            // 进入队列
            int temp = k;
            while (temp > 0 && cur != null) {
                list.addLast(cur);
                cur = cur.next;
                temp--;
            }
            if (temp > 0) {
                // 已经到链表尾部，不变化
                while (!list.isEmpty()) {
                    pre.next = list.pollFirst();
                    pre = pre.next;
                }
            } else {
                while (!list.isEmpty()) {
                    pre.next = list.pollLast();
                    pre = pre.next;
                }
            }
        }
        pre.next = null;
        return first.next;
    }

    /**
     * 想到思路 但是写不出来
     */
    public ListNode reverseKGroup2(ListNode head, int k) {
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        if (n < k) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p0 = dummy;
        ListNode pre = null;
        cur = head;

        for (int i = 0; i < n / k; i++) {
            for (int j = 0; j < k; j++) {
                ListNode nxt = cur.next;
                cur.next = pre;
                pre = cur;
                cur = nxt;
            }
            ListNode p1 = p0.next;
            p0.next.next = cur;
            p0.next = pre;
            p0 = p1;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ReverseKGroup s = new ReverseKGroup();
        //输入：head = [1,2,3,4,5], k = 2
        //输出：[2,1,4,3,5]
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        s.reverseKGroup(n1, 2);
    }
}
