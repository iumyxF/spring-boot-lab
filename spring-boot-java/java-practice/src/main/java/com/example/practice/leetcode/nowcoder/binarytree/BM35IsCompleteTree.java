package com.example.practice.leetcode.nowcoder.binarytree;

import java.util.LinkedList;

/**
 * @author fzy
 * @description:
 * @date 2024/11/4 11:26
 */
public class BM35IsCompleteTree {

    /*
    给定一个二叉树，确定他是否是一个完全二叉树。
    完全二叉树的定义：若二叉树的深度为h，除第h层外，其它各层的结点数都达到最大个数，
    第h层所有的叶子结点都连续集中在最左边，这就是完全二叉树。（第h层可能包含[1~2h]个节点）
    数据范围：节点数满足1 <= n <= 100

    层序遍历

     */

    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.addFirst(root);
        // 判断遍历的当前节点的左边是否有空节点
        boolean leftNull = false;
        while (!linkedList.isEmpty()) {
            TreeNode node = linkedList.pollFirst();
            if (node == null) {
                leftNull = true;
            } else {
                if (leftNull) {
                    return false;
                }
                linkedList.addLast(node.left);
                linkedList.addLast(node.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        //n2.right = n5;

        //n3.left = n6;

        BM35IsCompleteTree s = new BM35IsCompleteTree();
        System.out.println(s.isCompleteTree(n1));
    }
}
