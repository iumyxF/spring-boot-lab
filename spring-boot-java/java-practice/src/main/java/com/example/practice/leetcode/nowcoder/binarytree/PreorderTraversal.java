package com.example.practice.leetcode.nowcoder.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2024/10/30 9:29
 */
public class PreorderTraversal {

    /*
    二叉树前序遍历
    根 左 右
     */

    public int[] preorderTraversal(TreeNode root) {
        // write code here
        //solutionByRecursion(root);
        return solutionByStack(root);
    }

    /**
     * 递归方式
     */
    public int[] solutionByRecursion(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        ArrayList<Integer> list = new ArrayList<>();
        recursion(root, list);
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public void recursion(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        recursion(root.left, list);
        recursion(root.right, list);
    }


    /**
     * 使用栈方式
     */
    public int[] solutionByStack(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
