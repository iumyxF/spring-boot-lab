package com.example.practice.leetcode.nowcoder;

/**
 * @author fzy
 * @description:
 * @date 2024/10/18 11:28
 */
public class BM8FindKthToTail {

    /*
    输入一个长度为n的链表，设链表中的元素的值为Ai返回该链表中倒数第k个节点。
    如果该链表长度小于k,请返回一个长度为0的链表。
    例如输入{1,2,3,4,5},2时，对应的链表结构如下图所示：
    input : 1 -> 2 -> 3 -> 4 -> 5
    output: 4 -> 5
     */

    public ListNode FindKthToTail(ListNode pHead, int k) {
        if (k < 1) {
            return null;
        }
        ListNode left = pHead;
        ListNode right = pHead;
        for (int i = 1; i < k; i++) {
            if (null == right) {
                return null;
            }
            right = right.next;
        }
        if (null == right) {
            return null;
        }
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        return left;
    }
}
