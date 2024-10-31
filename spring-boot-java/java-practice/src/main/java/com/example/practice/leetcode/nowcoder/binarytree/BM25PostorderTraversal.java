package com.example.practice.leetcode.nowcoder.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2024/10/30 16:38
 */
public class BM25PostorderTraversal {

    /*
    后序遍历
    左 右 根
     */
    public int[] postorderTraversal(TreeNode root) {
        // write code here
        return solutionByRecursion(root);
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
        if (root == null) {
            return;
        }
        if (root.left != null) {
            recursion(root.left, list);
        }
        if (root.right != null) {
            recursion(root.right, list);
        }
        list.add(root.val);
    }

    /**
     * 使用栈方式
     */
    public int[] solutionByStack(TreeNode root) {
        if (null == root) {
            return new int[]{};
        }
        // 将前序遍历的结果放到preStack中，然后输出到stack
        Stack<Integer> stack = new Stack<>();

        Stack<TreeNode> preStack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        preStack.push(root);
        while (!preStack.isEmpty()) {
            TreeNode node = preStack.pop();
            stack.push(node.val);
            if (node.left != null) {
                preStack.push(node.left);
            }
            if (node.right != null) {
                preStack.push(node.right);
            }
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
