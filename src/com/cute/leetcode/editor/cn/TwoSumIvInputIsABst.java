//给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。 
//
// 案例 1: 
//
// 
//输入: 
//    5
//   / \
//  3   6
// / \   \
//2   4   7
//
//Target = 9
//
//输出: True
// 
//
// 
//
// 案例 2: 
//
// 
//输入: 
//    5
//   / \
//  3   6
// / \   \
//2   4   7
//
//Target = 28
//
//输出: False
// 
//
// 
// Related Topics 树

package com.cute.leetcode.editor.cn;

import java.util.*;

public class TwoSumIvInputIsABst {
    public static void main(String[] args) {
        Solution solution = new TwoSumIvInputIsABst().new Solution();
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
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public boolean findTarget(TreeNode root, int k) {
            Set<Integer> set = new HashSet<>();
            List<TreeNode> list = new ArrayList<>();
            inorder(root,list);
            int i = 0, r = list.size() - 1;
            while (i < r){
                if ((list.get(i).val + list.get(r).val )>k){
                    r--;
                }
                else if ((list.get(i).val + list.get(r).val )<k){
                   i++;
                }else if ((list.get(i).val + list.get(r).val ) == k){
                    return true;
                }
            }
            return false;
//            Queue<TreeNode> queue = new LinkedList<>();
//            queue.add(root);
//
//            while (!queue.isEmpty()) {
//                if (queue.peek()!=null){
//                    TreeNode node = queue.remove();
//                    if (set.contains(k - node.val)) {
//                        return true;
//                    } else {
//                        set.add(node.val);
//                        queue.add(node.right);
//                        queue.add(node.left);
//                    }
//                }else{
//                    queue.remove();
//                }
//
//            }
            //return false;
//            return findTree(root,set,k);
        }

        private boolean findTree(TreeNode root, Set<Integer> set, int k) {
            if (root == null) {
                return false;
            }
            if (set.contains(k - root.val)) {
                return true;
            }
            set.add(root.val);
            return findTree(root.left, set, k) || findTree(root.right, set, k);
        }

        private void inorder(TreeNode root, List<TreeNode> list){
            if (root == null) {
                return;
            }
            else{
                inorder(root.left,list);
                list.add(root);
                inorder(root.right,list);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}