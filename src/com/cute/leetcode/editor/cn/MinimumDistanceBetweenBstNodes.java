//给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。 
//
// 注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bs
//t/ 相同 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：root = [4,2,6,1,3]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：root = [1,0,48,null,null,12,49]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [2, 100] 内 
// 0 <= Node.val <= 105 
// 
// 
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 130 👎 0

package com.cute.leetcode.editor.cn;

public class MinimumDistanceBetweenBstNodes {
    public static void main(String[] args) {
        MinimumDistanceBetweenBstNodes bts = new MinimumDistanceBetweenBstNodes();
        Solution solution = bts.new Solution();
        TreeNode root = bts.new TreeNode(90);
        TreeNode node1 = bts.new TreeNode(69);
        TreeNode node2 = bts.new TreeNode(49);
        TreeNode node3 = bts.new TreeNode(89);
        TreeNode node4 = bts.new TreeNode(52);
        TreeNode node5 = bts.new TreeNode();
        TreeNode node6 = bts.new TreeNode();
        root.left = node1;
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        System.out.println(solution.minDiffInBST(root));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    //Definition for a binary tree node.
    class TreeNode {
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

        public int minDiffInBST(TreeNode root) {
            if (root == null || (root.left == null && root.right == null)) {
                return 0;
            }
            int res = 0;
            //与档期节点差值最小应该是如果存在的话左子树的最右子节点、右子树的最左子节点，否则则是左右子树的最小值
            return backTrack(root, res);
        }

        private int backTrack(TreeNode root, int res) {
            if (root == null || (root.left == null && root.right == null)) {
                return res;
            }
            //先查左子树的最右子节点
            if (root.left != null){
                TreeNode temp = root.left;
                while (temp.right!= null){
                    temp = temp.right;
                }
                res = res ==0?root.val - temp.val:Math.min(res, root.val - temp.val);
                res = backTrack(root.left,res);
            }
            if (root.right != null){
                TreeNode temp = root.right;
                while (temp.left!= null){
                    temp = temp.left;
                }
                res = res ==0 ?temp.val - root.val:Math.min(res,  temp.val - root.val);
                res = backTrack(root.right,res);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}