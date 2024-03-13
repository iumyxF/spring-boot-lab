package com.example.leetcode;

/**
 * @author fzy
 * @description:
 * @date 2024/3/13 15:12
 */
public class detectCycle {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(-4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2;
        ListNode node = solution(n1);
        System.out.println(node.val);
    }

    public static ListNode solution(ListNode head) {
        if (null == head || null == head.next.next) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    public static ListNode wrongSolution(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        //这种写法错误在于，初始化的时候fast已经移动了，但是slow没有移动
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            if (slow != fast) {
                slow = slow.next;
                fast = fast.next.next;
            } else {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return fast;
            }
        }
        return null;
    }
}
