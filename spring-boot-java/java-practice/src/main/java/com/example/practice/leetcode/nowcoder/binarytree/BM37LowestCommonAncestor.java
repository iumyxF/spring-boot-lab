package com.example.practice.leetcode.nowcoder.binarytree;

/**
 * @author fzy
 * @description:
 * @date 2024/11/25 9:48
 */
public class BM37LowestCommonAncestor {

    /*
    给定一个二叉搜索树，找到该树中两个指定节点的最近公共祖先。
    1.对于该题的最近的公共祖先定义：对于有根树T的两个节点p、q,最近公共祖先LCA(T,p,q)表示一个节点
    x,满足x是p和q的祖先且x的深度尽可能大。在这里，一个节点也可以是它自己的祖先
    2.二叉搜索树是若它的左子树不空，则左子树上所有节点的值均小于它的根节点的值；若它的右子树不
    空，则右子树上所有节点的值均大于它的根节点的值
    3.所有节点的值都是唯一的。
    4.p、q为不同节点且均存在于给定的二叉搜索树中。
    数据范围：
    3 <= 节点总数 <= 10000
    0 <= 节点值 <= 10000
     */
    public int lowestCommonAncestor(TreeNode root, int p, int q) {
        // p q 和 root 三种情况 qp都在r的左，pq都在r的右，pq在root的两边（直接返回）
        int rv = root.val;
        if (p < rv && q < rv) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (p > rv && q > rv) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root.val;
        }
    }
}
