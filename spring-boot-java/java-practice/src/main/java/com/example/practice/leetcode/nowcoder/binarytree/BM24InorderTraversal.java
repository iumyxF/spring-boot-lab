package com.example.practice.leetcode.nowcoder.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2024/10/30 16:00
 */
public class BM24InorderTraversal {

    /*
    中序遍历
    左 根 右
     */
    public int[] inorderTraversal(TreeNode root) {
        // write code here
        //solutionByStack(root);
        return solutionByStack(root);
    }

    /**
     * 递归方式
     */
    public int[] solutionByRecursion(TreeNode root) {
        if (null == root) {
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
        if (null == root) {
            return;
        }
        if (root.left != null) {
            recursion(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            recursion(root.right, list);
        }
    }


    /**
     * 使用栈方式
     */
    public int[] solutionByStack(TreeNode root) {
        if (null == root) {
            return new int[]{};
        }
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            list.add(node.val);
            root = node.right;
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
