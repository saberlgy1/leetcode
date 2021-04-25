//给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
//输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
// 
//
// 示例 2： 
//
// 
//输入：root = [5,1,7]
//输出：[1,null,5,null,7]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数的取值范围是 [1, 100] 
// 0 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 169 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class IncreasingOrderSearchTree {
    public static void main(String[] args) {
        Solution solution = new IncreasingOrderSearchTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    //Definition for a binary tree node.
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

    class Solution {
        public TreeNode increasingBST(TreeNode root) {

            //一般这种递归都能解 先往下写吧
            if (root == null){
                return null;
            }
           //中序遍历后重新构建二叉树
            List<TreeNode> list = new ArrayList<>();
            TreeNode dummy = new TreeNode(-1);
            TreeNode res = dummy;
            dfs(list,root);
            for (TreeNode treeNode: list){
                treeNode.left = null;
                dummy.right = treeNode;
                //左子节点置空
                dummy.left = null;
                dummy = dummy.right;
            }
            return res.right;
        }
        private void dfs(List<TreeNode> list, TreeNode root){
            if (root == null){
                return;
            }
            dfs(list,root.left);
            list.add(root);
            dfs(list, root.right);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}