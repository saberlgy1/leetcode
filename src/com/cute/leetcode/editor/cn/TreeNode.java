package com.cute.leetcode.editor.cn;

/**
 * @program: leetcode
 * @description:
 * @author: lgy
 * @create: 2021-04-27 08:54
 **/

public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
