package com.example.practice.leetcode.nowcoder;

/**
 * @author fzy
 * @description:
 * @date 2024/10/19 16:46
 */
public class BM14OddEvenList {

    /*
    给定一个单链表，请设定一个函数，将链表的奇数位节点和偶数位节点分别放在一起，重排后输出。
    注意是节点的编号而非节点的数值。

    example1:
    {1,2,3,4,5,6}
    {1,3,5,2,4,6}
    说明：
    1->2->3->4->5->6->NULL
    重排后为
    1->3->5->2->4->6->NULL

    example2:
    输入：{1,4,6,3,7}
    返回值：{1,6,7,4,3}
    说明：
    1->4->6->3->7->NULL
    重排后为
    1->6->7->4->3->NULL
    奇数位节点有1,6,7，偶数位节点有4,3。重排后为1,6,7,4,3
     */

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode oddHead = head;
        ListNode evenHead = head.next;

        ListNode o = oddHead;
        ListNode e = evenHead;
        while (o != null && o.next != null && e != null && e.next != null) {
            o.next = e.next;
            e.next = e.next.next;

            o = o.next;
            e = e.next;
        }
        o.next = evenHead;
        return oddHead;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        //ListNode n6 = new ListNode(6);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        //n5.next = n6;
        BM14OddEvenList s = new BM14OddEvenList();
        s.oddEvenList(n1);
    }
}
