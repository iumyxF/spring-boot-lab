package com.example.practice.leetcode.nowcoder.listnode;

/**
 * @author fzy
 * @description:
 * @date 2024/10/17 16:43
 */
public class BM2ReverseBetween {

    /*
    链表内指定区间反转
    将一个节点数为size链表m位置到n位置之间的区间反转，要求时间复杂度O(m),空间复杂度0(1).
    例如：
    给出的链表为 1 → 2 → 3 → 4 → 5 → NULL,m=2,m=4,
    返回 1 → 4 → 3 → 2 → 5 → NULL
    数据范围：链表长度 0 < size < 1000, 0 < m < n < size,链表中每个节点
    的值满足|val| ≤ 1000
    要求：时间复杂度O(m),空间复杂度O(m)
    进阶：时间复杂度O(m),空间复杂度O(1)
     */

    public ListNode reverseBetween(ListNode head, int m, int n) {
        // left: 替换区间的左节点的前一个节点
        ListNode left = new ListNode(-1);
        left.next = head;
        ListNode myHead = left;
        for (int i = 1; i < m; i++) {
            left = left.next;
        }
        // right: 替换区间的右节点
        ListNode right = head;
        for (int i = 1; i < n; i++) {
            right = right.next;
        }
        ListNode rr = right.next;
        ListNode pre = rr;
        ListNode cur = left.next;
        while (cur != rr) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        left.next = pre;
        return myHead.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        BM2ReverseBetween s = new BM2ReverseBetween();
        ListNode listNode = s.reverseBetween(n1, 2, 4);
        System.out.println(listNode);
    }
}
