//设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。 
//
// 如果指定节点没有对应的“下一个”节点，则返回null。 
//
// 示例 1: 
//
// 输入: root = [2,1,3], p = 1
//
//  2
// / \
//1   3
//
//输出: 2 
//
// 示例 2: 
//
// 输入: root = [5,3,6,2,4,null,null,1], p = 6
//
//      5
//     / \
//    3   6
//   / \
//  2   4
// /   
//1
//
//输出: null 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 144 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SuccessorLcci {
    public static void main(String[] args) {
        Solution solution = new SuccessorLcci().new Solution();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(solution.inorderSuccessor(root, new TreeNode(1)));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {

        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            if (root == null) return null;
            if (root.val <= p.val) return inorderSuccessor(root.right, p);
            TreeNode ans = inorderSuccessor(root.left, p);
            return ans == null ? root : ans;
        }
/*        List<TreeNode> list = new ArrayList<>();
        int temp = 0;

        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            inOrder(root);
            for (TreeNode node : list
            ) {
                temp++;
                if (node.val == p.val) {
                    break;
                }
            }
            return temp == list.size() ? null : list.get(temp);
        }

        public void inOrder(TreeNode root) {
            if (root == null) {
                return;
            }
            if (root.left != null) {
                inOrder(root.left);
            }
            list.add(root);
            if (root.right != null) {
                inOrder(root.right);
            }
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}