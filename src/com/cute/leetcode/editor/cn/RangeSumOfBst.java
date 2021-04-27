//给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
//输出：32
// 
//
// 示例 2： 
//
// 
//输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [1, 2 * 104] 内 
// 1 <= Node.val <= 105 
// 1 <= low <= high <= 105 
// 所有 Node.val 互不相同 
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 180 👎 0

package com.cute.leetcode.editor.cn;

public class RangeSumOfBst {
    public static void main(String[] args) {
        Solution solution = new RangeSumOfBst().new Solution();
        TreeNode t1 = new TreeNode(10);
        TreeNode t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(7);
        TreeNode t5 = new TreeNode(1);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(15);
        TreeNode t8 = new TreeNode(13);
        TreeNode t9 = new TreeNode(18);
        t1.left = t2;
        t1.right = t7;
        t2.left = t3;
        t2.right = t4;
        t3.left = t5;
        t4.left = t6;
        t7.left = t8;
        t7.right = t9;
        System.out.println(solution.rangeSumBST(t1, 6, 10));
        ;
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        int res = 0;

        public int rangeSumBST(TreeNode root, int low, int high) {
            //二叉搜索树
        /*//先搜索到最大值，然后遍历其左子树，找到最小值以及其中间值
        if (root == null){
            return 0;
        }
        int res = 0;
        //确认首位节点
        while (root!= null && root.val!= high){
             if(root.val > high){
                root = root.left;
            }else{
                root = root.right;
            }
        }
        return dfs(root, low, res);*/
            // method 1深度优先遍历暴力法， o(n), o(1)
            // method 2前序遍历一遍 然后求解数组和 o(2n) o(n)
            // method 1 的优化
            //  遍历时考虑一下当前节点值，判断是否遍历左子树或者右子树


            dfs(root, low, high);
            return res;
        }

        //method 1
        /*private void dfs(TreeNode root, int low, int high) {
            if (root == null) {
                return;
            }
            if (root.val <= high && root.val >= low) {
                res += root.val;

            }
            dfs(root.left, low, high);
            dfs(root.right, low, high);
        }*/
        //优化 1
        private void dfs(TreeNode root, int low, int high) {
            if (root == null) {
                return;
            }
            if (root.val <= high && root.val >= low) {
                res += root.val;
                dfs(root.left, low, high);
                dfs(root.right, low, high);
            }
            if (root.val < low) {
                dfs(root.right, low, high);
            }
            if (root.val > high) {
                dfs(root.left, low, high);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}