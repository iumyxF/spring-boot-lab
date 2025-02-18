package com.example.practice.leetcode.hot100.treenode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2025/2/18 9:23
 */
public class InorderTraversal {

    /*
    给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。

    左根右
     */

    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode pop = stack.pop();
            res.add(pop.val);
            root = pop.right;
        }
        return res;
    }
}
