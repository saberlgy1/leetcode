//给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。 
//
// 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//输出：3
//解释：和等于 8 的路径有 3 条，如图所示。
// 
//
// 示例 2： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：3
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [0,1000] 
// -10⁹ <= Node.val <= 10⁹ 
// -1000 <= targetSum <= 1000 
// 
// Related Topics 树 深度优先搜索 二叉树 👍 1035 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class PathSumIii {
    public static void main(String[] args) {
        Solution solution = new PathSumIii().new Solution();
        Integer[] num = new Integer[]{10, 5, -3, 3, 2, null, 11, 3, -2, null, 1};
        TreeNode root = TreeNode.arrayToTreeNode(num);
        System.out.println(solution.pathSum(root, 8));

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
        //思路一：DFS
        //常规往下路径递归遍历
        //需要注意dfs的参数

        /*
                int res = 0, target = 0;

        public int pathSum(TreeNode root, int targetSum) {
            target = targetSum;
            dfsNode(root);
            return res;
        }

        public void dfsNode(TreeNode root) {
            if (root == null) {
                return;
            }
            dfs(root, root.val);
            dfsNode(root.left);
            dfsNode(root.right);
        }

        public void dfs(TreeNode root, int tmp) {
            if (tmp == target) {
                res += 1;
            }
            if (root.left != null) {
                dfs(root.left, tmp + root.left.val);
            }
            if (root.right != null) {
                dfs(root.right, tmp + root.right.val);
            }
        }*/
        //思路二：前缀和
        //根据前缀和判断
        //求出每条路径的前缀和，然后根据前缀和
        //最后需要回溯处理每条跨左右子树的路径
        int res = 0, target = 0;
        Map<Integer, Integer> map = new HashMap<>();

        public int pathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return 0;
            }
            target = targetSum;
            map.put(0, 1);
            dfs(root, map, 0);
            return res;
        }

        public void dfs(TreeNode root, Map<Integer, Integer> map, int tmp) {
            if (root == null) {
                return;
            }
            tmp += root.val;
            res += map.getOrDefault(tmp - target, 0);
            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
            if (root.left != null) dfs(root.left, map, tmp);
            if (root.right != null) dfs(root.right, map, tmp );
            //回溯
            map.put(tmp, map.getOrDefault(tmp, 0) - 1);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}