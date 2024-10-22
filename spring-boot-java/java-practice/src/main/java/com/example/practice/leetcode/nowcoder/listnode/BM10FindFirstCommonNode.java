package com.example.practice.leetcode.nowcoder.listnode;

import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2024/10/18 11:41
 */
public class BM10FindFirstCommonNode {

    /*
        两个链表的第一个公共结点
     */

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();
        while (pHead1 != null) {
            s1.push(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            s2.push(pHead2);
            pHead2 = pHead2.next;
        }
        ListNode popNode = null;
        while (!s1.isEmpty() && !s2.isEmpty()) {
            ListNode p1 = s1.pop();
            ListNode p2 = s2.pop();
            if (p1 != p2) {
                return popNode;
            } else {
                popNode = p1;
            }
        }
        return popNode;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        BM10FindFirstCommonNode s = new BM10FindFirstCommonNode();
        s.FindFirstCommonNode(n1, n1);
    }
}
