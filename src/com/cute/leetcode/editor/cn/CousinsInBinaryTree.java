//在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。 
//
// 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。 
//
// 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。 
//
// 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。 
//
// 
//
// 示例 1： 
// 
//
// 
//输入：root = [1,2,3,4], x = 4, y = 3
//输出：false
// 
//
// 示例 2： 
// 
//
// 
//输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
//输出：true
// 
//
// 示例 3： 
//
// 
//
// 
//输入：root = [1,2,3,null,4], x = 2, y = 3
//输出：false 
//
// 
//
// 提示： 
//
// 
// 二叉树的节点数介于 2 到 100 之间。 
// 每个节点的值都是唯一的、范围为 1 到 100 的整数。 
// 
//
// 
// Related Topics 树 广度优先搜索 
// 👍 189 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class CousinsInBinaryTree {


    public static void main(String[] args) {
        Integer[] data = new Integer[]{1, 2, 3, 11, 9, 5, 4, null, 12, 17, 16, 7, 38, null, 6, 15, 18, 20, null, null, 19, 14, 10, null, 40, 8, 13, 22, 35, 25, 36, null, null, 30, null, 26, 24, 41, 21, null, null, 23, null, 29, null, null, null, 37, 42, null, null, null, null, 47, null, 48, 28, 31, 27, null, null, null, null, null, 46, 34, 32, 43, 39, 50, null, null, null, null, null, null, null, null, null, 44, null, 49, null, 45, null, null, 33};
        TreeNode treeNode = TreeNode.arrayToTreeNode(data);

        Solution solution = new CousinsInBinaryTree().new Solution();
        System.out.println(solution.isCousins(treeNode, 20, 19));
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
        private Map<Integer, int[]> map = new HashMap<>();

        public boolean isCousins(TreeNode root, int x, int y) {
            //第一种深度优先搜索
            //一次便利统计高度和父节点数值即可

            int high = 1, tag = -1;
            //corner case
            if (root.val == x || root.val == y) {
                return false;
            }
            dfs(high, root, x, y, tag);
            if (map.size() != 2) {
                return false;
            }
            return map.get(x)[0] == map.get(y)[0] && map.get(x)[1] != map.get(y)[1];

        }

        private void dfs(int high, TreeNode root, int x, int y, int tag) {
            if (map.size() == 2) {
                return;
            }
            if (root == null) {
                return;
            }
            //找到第一个节点
            if (root.val == x) {
                map.put(x, new int[]{high, tag});
            }
            //找到第二个节点
            if (root.val == y) {
                map.put(y, new int[]{high, tag});
            }
            if (root.left != null) {
                dfs(high + 1, root.left, x, y, root.val);
            }

            if (root.right != null) {
                dfs(high + 1, root.right, x, y, root.val);
            }

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}