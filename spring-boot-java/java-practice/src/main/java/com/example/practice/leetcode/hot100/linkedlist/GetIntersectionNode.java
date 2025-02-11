package com.example.practice.leetcode.hot100.linkedlist;

/**
 * @author fzy
 * @description:
 * @date 2025/2/11 9:19
 */
public class GetIntersectionNode {

    /*
    相交链表
    给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     */

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA;
        ListNode tempB = headB;
        // 获取a b 长度
        int lenA = 0;
        int lenB = 0;
        while (tempA != null) {
            lenA++;
            tempA = tempA.next;
        }
        while (tempB != null) {
            lenB++;
            tempB = tempB.next;
        }
        // 让长的为tempA
        int gap = 0;
        if (lenA >= lenB) {
            tempA = headA;
            tempB = headB;
            gap = lenA - lenB;
        } else {
            tempA = headB;
            tempB = headA;
            gap = lenB - lenA;
        }

        for (int i = 0; i < gap; i++) {
            tempA = tempA.next;
        }

        while (tempA != null && tempB != null) {
            if (tempA == tempB) {
                return tempA;
            } else {
                tempA = tempA.next;
                tempB = tempB.next;
            }
        }
        return null;
    }
}
