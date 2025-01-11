package com.example.practice.leetcode.nowcoder.listnode;

import java.util.*;

/**
 * @author fzy
 * @description:
 * @date 2025/1/11 14:18
 */
public class BM5MergeKLists {

    /*
      合并 k 个升序的链表并将结果作为一个升序的链表返回其头节点。
    */
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;

        // 初始化map，保存所有头节点
        HashMap<Integer, ListNode> nodeMap = new HashMap<>(lists.size());
        for (int i = 0; i < lists.size(); i++) {
            ListNode listNode = lists.get(i);
            if (null != listNode) {
                nodeMap.put(i, listNode);
            }
        }

        while (nodeMap.size() > 1) {
            Integer key = getMinNodeKey(nodeMap);
            ListNode minNode = nodeMap.get(key);
            cur.next = minNode;
            cur = cur.next;
            if (minNode.next != null) {
                nodeMap.put(key, minNode.next);
            } else {
                nodeMap.remove(key);
            }
        }
        ListNode mapLastNode = null;
        for (Map.Entry<Integer, ListNode> entry : nodeMap.entrySet()) {
            mapLastNode = entry.getValue();
        }
        cur.next = mapLastNode;
        return head.next;
    }

    private Integer getMinNodeKey(HashMap<Integer, ListNode> nodeMap) {
        int key = -1;
        int minValue = Integer.MAX_VALUE;
        for (Map.Entry<Integer, ListNode> entry : nodeMap.entrySet()) {
            if (entry.getValue().val < minValue) {
                key = entry.getKey();
                minValue = entry.getValue().val;
            }
        }
        return key;
    }

    /**
     * 优先级队列
     * 写法简单，但是执行效率却没有上面的屎山快
     */
    public ListNode mergeKLists2(ArrayList<ListNode> lists) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            if (null != list) {
                priorityQueue.add(list);
            }
        }
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (!priorityQueue.isEmpty()) {
            ListNode node = priorityQueue.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                priorityQueue.add(node.next);
            }
        }
        return head.next;
    }

    public static void main(String[] args) {
        //    [{1,2,3},{4,5,6,7}]
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);

        n1.next = n2;
        n2.next = n3;

        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        BM5MergeKLists s = new BM5MergeKLists();

        ArrayList<ListNode> list = new ArrayList<>();
        list.add(n1);
        list.add(n4);
        System.out.println(s.mergeKLists(list));
    }
}
