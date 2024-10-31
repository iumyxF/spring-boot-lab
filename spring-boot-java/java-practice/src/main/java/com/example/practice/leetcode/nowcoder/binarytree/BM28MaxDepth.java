package com.example.practice.leetcode.nowcoder.binarytree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2024/10/31 9:43
 */
public class BM28MaxDepth {

    /*
    求给定二叉树的最大深度，
    深度是指树的根节点到任一叶子节点路径上节点的数星。
    最大深度是所有叶子节点的深度的最大值。
    (注：叶子节点是指没有子节点的节点。)
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.addFirst(root);
        int depth = 0;
        while (!stack.isEmpty()) {
            int loop = stack.size();
            for (int i = 0; i < loop; i++) {
                TreeNode node = stack.pollFirst();
                if (node.left != null) {
                    stack.addLast(node.left);
                }
                if (node.right != null) {
                    stack.addLast(node.right);
                }
            }
            depth++;
        }
        return depth;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n3.right = n5;
        BM28MaxDepth s = new BM28MaxDepth();
        s.maxDepth(n1);
    }
}
