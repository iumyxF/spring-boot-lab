package com.example.practice.leetcode.nowcoder.listnode;

/**
 * @author fzy
 * @description:
 * @date 2024/10/21 11:10
 */
public class BM15DeleteDuplicates {

    /*
    删除给出链表中的重复元素（链表中元素从小到大有序），使链表中的所有元素都只出现一次
    例如：
    给出的链表为1 -> 1 -> 2，返回1 -> 2.
    给出的链表为1 -> 1 -> 2 -> 3 -> 3，返回1 ->2 -> 3.

    示例
    输入：{1,1,2}
    输出：{1,2}

    输入：{}
    输出：{}
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode left = head;
        ListNode right = head.next;
        while (right != null) {
            if (left.val != right.val) {
                left.next = right;
                left = right;
            }
            right = right.next;
        }
        left.next = right;
        return head;
    }
}
