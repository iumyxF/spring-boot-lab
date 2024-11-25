package com.example.practice.leetcode.nowcoder.binarytree;

/**
 * @author fzy
 * @description:
 * @date 2024/11/25 10:18
 */
public class BM38LowestCommonAncestor {

    /*
    给定一棵二叉树（保证非空）以及这棵树上的两个节点对应的val值o1和o2，请找到o1和o2的最近公共祖先节点。
    数据范围：树上节点数满足 1 < n < 10的5次方,节点值va满足区间[O,n)
    要求：时间复杂度O(n)
    注：本题保证二叉树中每个节点的val值均不相同.
     */

    public int lowestCommonAncestor(TreeNode root, int o1, int o2) {
        return dfs(root, o1, o2).val;
    }

    public TreeNode dfs(TreeNode root, int o1, int o2) {
        // 递归 定位左右节点
        if (root == null || root.val == o1 || root.val == o2) {
            return root;
        }
        TreeNode left = dfs(root.left, o1, o2);
        TreeNode right = dfs(root.right, o1, o2);
        if (left != null && right != null) {
            // 左右不为空，说明两个节点分散在root的左右
            return root;
        } else if (left == null && right != null) {
            // 两个节点都在右遍
            return right;
        } else {
            // 剩下一种情况 都在左边
            return left;
        }
    }
}
