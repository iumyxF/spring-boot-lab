package com.example.practice.leetcode.hot100.linkedlist;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author fzy
 * @description:
 * @date 2025/2/13 14:53
 */
public class SortList {

    /*
    给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

    输入：head = [4,2,1,3]
    输出：[1,2,3,4]

    输入：head = [-1,5,3,4,0]
    输出：[-1,0,3,4,5]

    进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
     */

    public ListNode sortList(ListNode head) {
        ListNode cur = head;
        ListNode first = new ListNode(-1);
        ArrayList<ListNode> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        list.sort(Comparator.comparingInt(o -> o.val));
        ListNode temp = first;
        for (ListNode node : list) {
            temp.next = node;
            node.next = null;
            temp = temp.next;
        }
        return first.next;
    }
}
