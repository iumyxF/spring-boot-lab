package com.example.practice.leetcode.nowcoder;

/**
 * @author fzy
 * @description:
 * @date 2024/10/17 16:34
 */
public class BM1ReverseList {

    /*
    反转联表
     */
    public ListNode ReverseList(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
