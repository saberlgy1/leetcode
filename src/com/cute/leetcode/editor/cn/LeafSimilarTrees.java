//请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。 
//
// 
//
// 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。 
//
// 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。 
//
// 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,nul
//l,null,null,null,9,8]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root1 = [1], root2 = [1]
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：root1 = [1], root2 = [2]
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：root1 = [1,2], root2 = [2,2]
//输出：true
// 
//
// 示例 5： 
//
// 
//
// 
//输入：root1 = [1,2,3], root2 = [1,3,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 给定的两棵树可能会有 1 到 200 个结点。 
// 给定的两棵树上的值介于 0 到 200 之间。 
// 
// Related Topics 树 深度优先搜索 
// 👍 110 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class LeafSimilarTrees {
    public static void main(String[] args) {
        Solution solution = new LeafSimilarTrees().new Solution();
        String url = "http://www.baidu.com";
        System.out.println(url.concat("?").concat("1=1"));

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
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        public boolean leafSimilar(TreeNode root1, TreeNode root2) {
            //两次深度优先求出所有叶子节点判断hash存储判断是否为空即可
            //观察题目发现顺序不能换所以要用list而非Hash

            //dfs root1 叶子结点存入set
            dfs(root1, list);
            dfs(root2, list2);
            //dfs root2 从list中remove 最后如果为空则返回true，如果抛出异常则返回false
            for (int i = 0; i < list.size() && list.size() == list2.size(); i++){
                if (!list.get(i).equals(list2.get(i))){
                    return false;
                }
            }
            return list.size() == list2.size();
        }

        public void dfs(TreeNode root, List<Integer> list) {
            if (root == null) {
                return;
            }
            //叶子结点
            if (root.left == null && root.right == null) {
                list.add(root.val);
            }
            if (root.left != null) {
                dfs(root.left, list);
            }
            if (root.right != null) {
                dfs(root.right, list);
            }
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}