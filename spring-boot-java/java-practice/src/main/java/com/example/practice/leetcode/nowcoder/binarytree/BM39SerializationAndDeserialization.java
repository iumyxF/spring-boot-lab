package com.example.practice.leetcode.nowcoder.binarytree;

import java.util.LinkedList;

/**
 * @author fzy
 * @description:
 * @date 2025/1/11 17:20
 */
public class BM39SerializationAndDeserialization {

    /*
        二叉树的反序列化(Deserialize)是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。

        层序序列化(即用函数Serialize转化)如上的二叉树转为"{1,2,3,#,#,6,7}"，再能够调用反序列化(Deserialize)将"{1,2,3,#,#,6,7}"构造成如上的二叉树。

     */

    public String serialize(TreeNode root) {
        if (null == root) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        LinkedList<TreeNode> list = new LinkedList<>();
        list.addLast(root);
        while (!list.isEmpty()) {
            TreeNode node = list.pollFirst();
            if (node == null) {
                sb.append("#").append(",");
            } else {
                sb.append(node.val).append(",");
                list.addLast(node.left);
                list.addLast(node.right);
            }
        }
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if (null == data || data.length() == 0) {
            return null;
        }
        String[] split = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(split[0]));
        LinkedList<TreeNode> list = new LinkedList<>();
        list.addLast(root);
        int i = 1;
        while (i < split.length && !list.isEmpty()) {
            TreeNode node = list.pollFirst();
            if (!split[i].equals("#")) {
                node.left = new TreeNode(Integer.parseInt(split[i]));
                list.addLast(node.left);
            }
            i++;
            if (i < split.length && !split[i].equals("#")) {
                node.right = new TreeNode(Integer.parseInt(split[i]));
                list.addLast(node.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        BM39SerializationAndDeserialization s = new BM39SerializationAndDeserialization();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        //n1.left = n2;
        //n1.right = n3;
        //n3.left = n6;
        //n3.right = n7;
        //
        //String serialize = s.serialize(n1);
        //System.out.println(serialize);
        //TreeNode deserialize = s.deserialize(serialize);
        //System.out.println();

        //{5,4,#,3,#,2}
        n5.left = n4;
        n4.left = n3;
        n3.left = n2;

        String serialize = s.serialize(n5);
        System.out.println(serialize);
        TreeNode deserialize = s.deserialize(serialize);
        System.out.println();
    }
}
