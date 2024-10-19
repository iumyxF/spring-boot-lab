package com.example.practice.leetcode.nowcoder;

import com.example.practice.leetcode.base.MergeSort;

import java.util.*;

/**
 * @author fzy
 * @description:
 * @date 2024/10/19 10:15
 */
public class BM12SortInList {

    /*
    给定一个节点数为n的无序单链表，对其按升序排序。

    input:  [1,3,2,4,5]
    output: {1,2,3,4,5}
     */

    public ListNode sortInList(ListNode head) {
        //solution1(head);
        //solution2(head);

        return solution3(head);
    }

    /**
     * 用List 然后排序
     */
    public ListNode solution1(ListNode head) {
        ArrayList<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.sort(Comparator.comparingInt(o -> o.val));
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        for (ListNode node : list) {
            cur.next = node;
            cur = cur.next;
        }
        // 要把之前的关联删掉
        cur.next = null;
        return pre.next;
    }

    /**
     * 和上面的方法差不多，List换成优先级队列
     */
    public ListNode solution2(ListNode head) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        while (head != null) {
            priorityQueue.offer(head);
            head = head.next;
        }
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        while (priorityQueue.size() > 0) {
            cur.next = priorityQueue.peek();
            priorityQueue.poll();
            cur = cur.next;
        }
        cur.next = null;
        return pre.next;
    }

    public ListNode solution3(ListNode head) {
        return mergeSort(head);
    }

    public ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 获取中节点
        ListNode mid = getMidNode(head);
        System.out.println(mid.val);
        // 拆分成两个链表
        ListNode head2 = mid.next;
        mid.next = null;
        // 递归
        ListNode n1 = mergeSort(head);
        ListNode n2 = mergeSort(head2);
        return merge(n1, n2);
    }

    /**
     * 合并两个链表 使链表有序
     */
    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode newHead = new ListNode(-1);
        ListNode temp = newHead;
        ListNode t1 = head1;
        ListNode t2 = head2;
        while (t1 != null && t2 != null) {
            if (t1.val <= t2.val) {
                temp.next = t1;
                t1 = t1.next;
            } else {
                temp.next = t2;
                t2 = t2.next;
            }
            temp = temp.next;
        }
        if (t1 != null) {
            temp.next = t1;
        }
        if (t2 != null) {
            temp.next = t2;
        }
        return newHead.next;
    }

    private ListNode getMidNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        BM12SortInList s = new BM12SortInList();
        s.sortInList(n1);
    }
}
