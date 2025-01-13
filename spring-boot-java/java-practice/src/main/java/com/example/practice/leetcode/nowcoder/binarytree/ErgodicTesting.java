package com.example.practice.leetcode.nowcoder.binarytree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2025/1/13 9:55
 */
public class ErgodicTesting {

    // 前序遍历 根左右
    public void preorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println();
    }

    // 中序遍历 左根右
    public void inorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            root = node.right;
        }
        System.out.println();
    }

    // 后序遍历 左右根
    public void postorder(TreeNode root) {
        Stack<TreeNode> from = new Stack<>();
        Deque<TreeNode> to = new LinkedList<>();
        from.push(root);
        while (!from.isEmpty()) {
            TreeNode node = from.pop();
            to.addFirst(node);
            if (node.left != null) {
                from.push(node.left);
            }
            if (node.right != null) {
                from.push(node.right);
            }
        }
        while (!to.isEmpty()) {
            System.out.print(to.pop().val + " ");
        }
        System.out.println();
    }

    // 层序遍历
    public void sequence(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            int loop = stack.size();
            for (int i = 0; i < loop; i++) {
                TreeNode node = stack.pollFirst();
                System.out.print(node.val + " ");
                if (node.left != null) {
                    stack.addLast(node.left);
                }
                if (node.right != null) {
                    stack.addLast(node.right);
                }
            }
        }
    }

    public static void main(String[] args) {
        ErgodicTesting s = new ErgodicTesting();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.right = n6;
        s.preorder(n1);
        s.inorder(n1);
        s.postorder(n1);
        s.sequence(n1);
    }
}
