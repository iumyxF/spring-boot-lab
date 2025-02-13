package com.example.practice.leetcode.hot100.linkedlist;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author fzy
 * @description:
 * @date 2025/2/13 13:52
 */
public class CopyRandomList {

    /*
    给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
    构造这个链表的 深拷贝。
    深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
    新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
    复制链表中的指针都不应指向原链表中的节点 。

    例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
    返回复制链表的头节点。

    用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
    val：一个表示 Node.val 的整数。

    random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
    你的代码 只 接受原链表的头节点 head 作为传入参数。

    输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
    输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]

    输入：head = [[1,1],[2,1]]
    输出：[[1,1],[2,1]]

    输入：head = [[3,null],[3,0],[3,null]]
    输出：[[3,null],[3,0],[3,null]]

    [value, index]
     */

    public Node copyRandomList(Node head) {
        Node first = new Node(-1);
        Node original = head;
        Node cur = first;
        // node,index
        HashMap<Node, Integer> nodeIndexMap = new HashMap<>();
        ArrayList<Node> list = new ArrayList<>();
        int index = 0;
        while (original != null) {
            Node node = new Node(original.val);
            list.add(index, node);
            nodeIndexMap.put(original, index);

            cur.next = node;
            cur = cur.next;
            original = original.next;
            index++;
        }
        original = head;
        HashMap<Node, Node> nodeRandomMap = new HashMap<>();
        while (original != null) {
            nodeRandomMap.put(original, original.random);
            original = original.next;
        }

        original = head;
        index = 0;
        while (original != null) {
            Node node = list.get(index);
            if (original.random != null) {
                // 先获取当前节点的 random节点，再获取random在list的下标
                node.random = list.get(nodeIndexMap.get(nodeRandomMap.get(original)));
            }
            index++;
            original = original.next;
        }
        return first.next;
    }

    public static void main(String[] args) {
        //[[7,null],[13,0],[11,4],[10,2],[1,0]]
        Node n1 = new Node(7);
        Node n2 = new Node(13);
        Node n3 = new Node(11);
        Node n4 = new Node(10);
        Node n5 = new Node(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        n1.random = null;
        n2.random = n1;
        n3.random = n5;
        n4.random = n3;
        n5.random = n1;


        CopyRandomList s = new CopyRandomList();
        s.copyRandomList(n1);

    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}