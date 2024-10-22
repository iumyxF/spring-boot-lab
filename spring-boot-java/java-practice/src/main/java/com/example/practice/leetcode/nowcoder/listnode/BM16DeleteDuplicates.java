package com.example.practice.leetcode.nowcoder.listnode;

/**
 * @author fzy
 * @description:
 * @date 2024/10/21 11:31
 */
public class BM16DeleteDuplicates {

    /*
    给出一个升序排序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素。
    例如：
    给出的链表为1->2->3->3->4->4->5，返回1->2->5.
    给出的链表为1->1->1->2->3，返回2->3.
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }
        ListNode newHead = new ListNode(Integer.MAX_VALUE);
        newHead.next = head;

        ListNode left = newHead;
        ListNode right = head.next;
        while (right != null) {
            if (left.next.val != right.val) {
                left = left.next;
                right = right.next;
            } else {
                while (right != null && left.next.val == right.val) {
                    right = right.next;
                }
                left.next = right;
                if (right != null) {
                    right = right.next;
                }
            }
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(4);
        ListNode n6 = new ListNode(4);
        ListNode n7 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        BM16DeleteDuplicates s = new BM16DeleteDuplicates();
        s.deleteDuplicates(n1);
    }
}
