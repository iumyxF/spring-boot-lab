package com.example.practice.leetcode.nowcoder.binarytree;

/**
 * @author fzy
 * @description:
 * @date 2024/11/1 10:17
 */
public class BM34IsValidBST {

    /*
    给定一个二叉树根节点，请你判断这棵树是不是二叉搜索树。
    二叉搜索树满足每个节点的左子树上的所有节点均小于当前节点,且右子树上的所有节点均大于当前节点。

    note:
    判断根节点和左右节点，前序遍历?

     */

    public boolean isValidBST(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }


    /**
     * @param node  当前节点
     * @param left  数值左区间
     * @param right 数值右区间
     * @return
     */
    public boolean dfs(TreeNode node, int left, int right) {
        if (null == node) {
            return true;
        }
        if (node.val < left || node.val > right) {
            return false;
        }
        return dfs(node.left, left, node.val) && dfs(node.right, node.val, right);
    }

    public static void main(String[] args) {
        TreeNode n5 = new TreeNode(3);
        TreeNode n3 = new TreeNode(2);
        TreeNode n7 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        TreeNode n4 = new TreeNode(4);
        //TreeNode n2 = new TreeNode(2);
        //TreeNode n9 = new TreeNode(9);

        n5.left = n3;
        n5.right = n7;

        n3.left = n1;
        n3.right = n4;

        //n7.left = n2;
        //n7.right = n9;

        BM34IsValidBST s = new BM34IsValidBST();
        System.out.println(s.isValidBST(n5));
    }
}
