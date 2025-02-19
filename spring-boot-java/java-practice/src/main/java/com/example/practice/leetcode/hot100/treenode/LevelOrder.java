package com.example.practice.leetcode.hot100.treenode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/2/19 9:32
 */
public class LevelOrder {

    /*
    给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     */

    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int len = deque.size();
            ArrayList<Integer> level = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                TreeNode pop = deque.pollFirst();
                level.add(pop.val);
                if (pop.left != null) {
                    deque.addLast(pop.left);
                }
                if (pop.right != null) {
                    deque.addLast(pop.right);
                }
            }
            res.add(level);
        }
        return res;
    }
}
