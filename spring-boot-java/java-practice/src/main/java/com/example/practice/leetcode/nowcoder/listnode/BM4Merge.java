package com.example.practice.leetcode.nowcoder.listnode;

/**
 * @author fzy
 * @description:
 * @date 2024/10/18 9:22
 */
public class BM4Merge {

    /*
    输入两个递增的链表，单个链表的长度为,合并这两个链表并使新链表中的节点仍然是递增排序的。
    数据范围：0 <= n <= 1000,-1000 <= 节点值 <= 1000
    要求：空间复杂度O(1),时间复杂度O(m)
    如输入{1,3,5},{2,4,6}时，台并后的链表为{1,2,3,4,5,6}，所对应的输出为{1,2,3,4,5,6}
    或输入{-1,2,4},{1,3,4}时，合并后的链表为{-1,1,2,3,4,4}，所以对应的输出为{-1,1,2,3,4,4}
     */

    public ListNode merge(ListNode pHead1, ListNode pHead2) {
        if (null == pHead1) {
            return pHead2;
        }
        if (null == pHead2) {
            return pHead1;
        }

        ListNode n1 = pHead1;
        ListNode n2 = pHead2;
        ListNode pre = new ListNode(-1);
        ListNode cur = pre;
        while (n1 != null && n2 != null) {
            if (n1.val <= n2.val) {
                cur.next = n1;
                n1 = n1.next;
            } else {
                cur.next = n2;
                n2 = n2.next;
            }
            cur = cur.next;
        }
        if (n1 != null) {
            cur.next = n1;
        }
        if (n2 != null) {
            cur.next = n2;
        }
        return pre.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);

    }
}
