package com.example.practice.leetcode.hot100.treenode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/2/22 14:20
 */
public class LowestCommonAncestor {

    /*
    236. 二叉树的最近公共祖先

    给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
    百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
    满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

    递归?
    后序遍历?
     */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<List<TreeNode>> pathList = new ArrayList<>();
        findNode(root, p, q, new ArrayList<>(), pathList);
        if (pathList.size() < 2) {
            return null;
        }
        List<TreeNode> pList = pathList.get(0);
        List<TreeNode> qList = pathList.get(1);
        int index = Math.min(pList.size(), qList.size());
        TreeNode res = null;
        for (int i = 0; i < index; i++) {
            if (pList.get(i) == qList.get(i)) {
                res = pList.get(i);
            }
        }
        return res;
    }

    public void findNode(TreeNode root, TreeNode p, TreeNode q, List<TreeNode> path, List<List<TreeNode>> res) {
        if (root == null) {
            return;
        }
        path.add(root);
        if (root == p || root == q) {
            res.add(new ArrayList<>(path));
        }
        findNode(root.left, p, q, path, res);
        findNode(root.right, p, q, path, res);
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);

        n3.left = n5;
        n3.right = n1;

        n5.left = n6;
        n5.right = n2;
        n2.left = n7;
        n2.right = n4;

        n1.left = n0;
        n1.right = n8;

        LowestCommonAncestor s = new LowestCommonAncestor();
        TreeNode node = s.lowestCommonAncestor(n3, n5, n4);
        System.out.println(node.val);
    }
}
