package com.example.practice.leetcode.hot100.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author fzy
 * @description:
 * @date 2025/2/14 15:47
 */
public class MergeKLists {

    /*
    给你一个链表数组，每个链表都已经按升序排列。

    请你将所有链表合并到一个升序链表中，返回合并后的链表。

    输入：lists = [[1,4,5],[1,3,4],[2,6]]
    输出：[1,1,2,3,4,4,5,6]
    解释：链表数组如下：
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    将它们合并到一个有序链表中得到。
    1->1->2->3->4->4->5->6
     */

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode first = new ListNode(-1);
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (int i = 0; i < lists.length; i++) {
            ListNode cur = lists[i];
            while (cur != null) {
                queue.add(cur);
                cur = cur.next;
            }
        }
        ListNode temp = first;
        while (!queue.isEmpty()) {
            temp.next = queue.poll();
            temp = temp.next;
        }
        temp.next = null;
        return first.next;
    }
}
