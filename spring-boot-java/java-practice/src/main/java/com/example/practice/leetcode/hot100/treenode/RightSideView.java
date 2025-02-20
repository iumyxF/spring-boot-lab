package com.example.practice.leetcode.hot100.treenode;

import java.util.*;

/**
 * @author fzy
 * @description:
 * @date 2025/2/20 14:22
 */
public class RightSideView {

    /*
    199. 二叉树的右视图

    给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

     */

    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> linkedList = new LinkedList<>();
        linkedList.addLast(root);
        while (!linkedList.isEmpty()) {
            int len = linkedList.size();
            for (int i = 0; i < len; i++) {
                TreeNode pop = linkedList.pollFirst();
                if (pop.left != null) {
                    linkedList.addLast(pop.left);
                }
                if (pop.right != null) {
                    linkedList.addLast(pop.right);
                }
                if (i == len - 1) {
                    res.add(pop.val);
                }
            }
        }
        return res;
    }
}
