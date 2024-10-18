package com.example.practice.leetcode.nowcoder;

/**
 * @author fzy
 * @description:
 * @date 2024/10/18 10:26
 */
public class BM7EntryNodeOfLoop {

    /*
    给一个长度为n链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。
     */
    public ListNode entryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }
        ListNode slow = pHead.next;
        ListNode fast = pHead.next.next;
        // 获取环内相交点
        while (slow != fast && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 无环判断
        if (slow != fast) {
            return null;
        }

        // 将其中一个节点返回开始节点
        slow = pHead;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        //ListNode n2 = new ListNode(2);
        //ListNode n3 = new ListNode(3);
        //ListNode n4 = new ListNode(4);
        //ListNode n5 = new ListNode(5);

        //n1.next = n2;
        //n2.next = n3;
        //n3.next = n4;
        //n4.next = n5;
        //n5.next = n3;

        //n1.next = n1;

        BM7EntryNodeOfLoop s = new BM7EntryNodeOfLoop();
        s.entryNodeOfLoop(n1);
    }
}
