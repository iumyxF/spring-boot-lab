package com.example.practice.leetcode.nowcoder.binarytree;

/**
 * @author fzy
 * @description:
 * @date 2024/11/1 10:08
 */
public class BM33Mirror {

    /*
        操作给定的二叉树，将其变换为源二叉树的镜像。
        数据范围：二叉树的节点数0 <= n <= 1000，二叉树每个节点的值0 <= val <= 1000
        要求：空间复杂度O()。本题也有原地操作，即空间复杂度O(1)的解
        法，时间复杂度O(n)

        左子树和右子树对换位置
     */

    public TreeNode mirror(TreeNode pRoot) {
        switchTreeNode(pRoot);
        return pRoot;
    }

    public void switchTreeNode(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        if (root.left != null) {
            switchTreeNode(root.left);
        }
        if (root.right != null) {
            switchTreeNode(root.right);
        }
    }

    public static void main(String[] args) {
        BM33Mirror s = new BM33Mirror();
        s.mirror(null);
    }
}
