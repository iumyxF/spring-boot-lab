package com.example.leetcode;

import java.util.*;

/**
 * @author fzy
 * @description:
 * @date 2024/3/12 11:39
 */
public class MergeKLists {

    public static void main(String[] args) {
    }

    public static ListNode solution(ListNode[] lists) {
        int length = lists.length;
        //使用小顶堆保存临时元素
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (int i = 0; i < length; i++) {
            queue.add(lists[i]);
        }
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            ListNode next = node.next;
            if (null != next) {
                queue.add(next);
            }
            cur.next = node;
            cur = cur.next;
        }
        return dummy.next;
    }
}
