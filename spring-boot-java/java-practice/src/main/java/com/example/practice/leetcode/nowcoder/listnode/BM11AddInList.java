package com.example.practice.leetcode.nowcoder.listnode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2024/10/19 9:22
 */
public class BM11AddInList {

    /*
    链表相加(二)
    假设链表中每一个节点的值都在0-9之间，那么链表整体就可以代表一个整数。
    给定两个这种链表，请生成代表两个整数相勖加值的结果链表。
    数据范围：0 <= n，m <= 1000000,链表任意值 0 <= val <=9
    要求：空间复杂度O(m),时间复杂度O(m)
    例如：链表1为9->3->7，链表2为6->3，最后生成新的结果链表为1->0->0->0。
            9   ->  3   ->  7
        +           6   ->  3
        ----------------------
        1-> 0   ->  0   ->  0
     */
    public ListNode addInList(ListNode head1, ListNode head2) {
        Deque<Integer> s1 = new LinkedList<>();
        Deque<Integer> s2 = new LinkedList<>();
        Stack<Integer> s3 = new Stack<>();
        while (head1 != null || head2 != null) {
            if (head1 == null) {
                s1.addFirst(0);
            } else {
                s1.addLast(head1.val);
                head1 = head1.next;
            }

            if (head2 == null) {
                s2.addFirst(0);
            } else {
                s2.addLast(head2.val);
                head2 = head2.next;
            }
        }
        int up = 0;
        while (!s1.isEmpty()) {
            Integer v1 = s1.pollLast();
            Integer v2 = s2.pollLast();
            int value = v1 + v2 + up;
            s3.push(value % 10);
            up = value / 10;
        }
        if (up != 0) {
            s3.push(up);
        }
        // 构建链表
        ListNode pre = new ListNode(-1);
        ListNode temp = pre;
        while (!s3.isEmpty()) {
            temp.next = new ListNode(s3.pop());
            temp = temp.next;
        }
        return pre.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(9);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(7);
        n1.next = n2;
        n2.next = n3;

        ListNode n4 = new ListNode(6);
        ListNode n5 = new ListNode(3);
        n4.next = n5;

        BM11AddInList s = new BM11AddInList();
        s.addInList(n1, n4);
    }
}
