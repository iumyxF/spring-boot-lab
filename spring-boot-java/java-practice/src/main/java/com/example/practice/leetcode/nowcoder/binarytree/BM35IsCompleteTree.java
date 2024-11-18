package com.example.practice.leetcode.nowcoder.binarytree;

import java.util.Deque;
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
    判断这层的节点数 是不是2h，1层-1，2层-2，3层-6


     */

    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        Deque<TreeNode> list = new LinkedList<>();
        list.addFirst(root);
        int loop = 1;
        int expect;
        int depth = 1;
        while (!list.isEmpty()) {
            expect = (int) Math.pow(2, depth - 1);
            if (expect != loop) {
                return false;
            }
            for (int i = 0; i < loop; i++) {
                TreeNode node = list.pollFirst();
                if (node.left != null) {
                    list.addLast(node.left);
                }
                if (node.right != null) {
                    list.addLast(node.right);
                }
            }
            depth++;
            loop = list.size();
        }
        return true;
    }
}
