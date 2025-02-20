package com.example.practice.leetcode.hot100.treenode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2025/2/20 13:58
 */
public class KthSmallest {

    /*
    二叉搜索树中第 K 小的元素

    给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。

    二叉搜索树: 根节点的值大于等于左子树的所有节点的值，小于等于右子树的所有节点的值。
    中序遍历 左根右
     */

    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode pop = stack.pop();
            list.add(pop.val);
            if (list.size() == k) {
                return list.get(k - 1);
            }
            root = pop.right;
        }
        return -1;
    }
}
