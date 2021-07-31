//给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。 
//
// 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的
//根结点位于 (0, 0) 。 
//
// 二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则
//按结点的值从小到大进行排序。 
//
// 返回二叉树的 垂序遍历 序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[9],[3,15],[20],[7]]
//解释：
//列 -1 ：只有结点 9 在此列中。
//列  0 ：只有结点 3 和 15 在此列中，按从上到下顺序。
//列  1 ：只有结点 20 在此列中。
//列  2 ：只有结点 7 在此列中。 
//
// 示例 2： 
//
// 
//输入：root = [1,2,3,4,5,6,7]
//输出：[[4],[2],[1,5,6],[3],[7]]
//解释：
//列 -2 ：只有结点 4 在此列中。
//列 -1 ：只有结点 2 在此列中。
//列  0 ：结点 1 、5 和 6 都在此列中。
//          1 在上面，所以它出现在前面。
//          5 和 6 位置都是 (2, 0) ，所以按值从小到大排序，5 在 6 的前面。
//列  1 ：只有结点 3 在此列中。
//列  2 ：只有结点 7 在此列中。
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2,3,4,6,5,7]
//输出：[[4],[2],[1,5,6],[3],[7]]
//解释：
//这个示例实际上与示例 2 完全相同，只是结点 5 和 6 在树中的位置发生了交换。
//因为 5 和 6 的位置仍然相同，所以答案保持不变，仍然按值从小到大排序。 
//
// 
//
// 提示： 
//
// 
// 树中结点数目总数在范围 [1, 1000] 内 
// 0 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 哈希表 二叉树 
// 👍 125 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class VerticalOrderTraversalOfABinaryTree {
    public static void main(String[] args) {
        Solution solution = new VerticalOrderTraversalOfABinaryTree().new Solution();
        Integer[] nums = new Integer[]{1, 2, 3, 4, 6, 5, 7};
        TreeNode root = TreeNode.arrayToTreeNode(nums);
        System.out.println(solution.verticalTraversal(root));
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
        //思路一：dfs+坐标记录+排序
        //dfs遍历所有节点，根据根节点坐标计算子节点列坐标
        //依次存入不同list中
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, PriorityQueue<Index>> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        public List<List<Integer>> verticalTraversal(TreeNode root) {

            //corner case
            if (root == null) {
                return null;
            }
            dfs(root, 0, 0);
            for (Integer key : map.keySet()
            ) {
                PriorityQueue<Index> priorityQueue = map.get(key);

                List<Integer> ans = new ArrayList<Integer>();
               while (!priorityQueue.isEmpty()){
                   ans.add(priorityQueue.poll().val);
               }
                res.add(ans);
            }
            return res;
        }

        private void dfs(TreeNode root, int col, int row) {
            if (root == null) {
                return;
            }
            //小顶堆
            PriorityQueue<Index> priorityQueue = new PriorityQueue<>(new Comparator<Index>() {
                @Override
                public int compare(Index o1, Index o2) {
                    if (o1.x == o2.x) {
                        return o1.val - o2.val;
                    } else {
                        return o1.x - o2.x;
                    }
                }
            });
            if (map.containsKey(col)) {
                priorityQueue = map.get(col);
            }
            priorityQueue.add(new Index(row, root.val));
            map.put(col, priorityQueue);
            dfs(root.left, col - 1, row + 1);
            dfs(root.right, col + 1, row + 1);
        }
    }

    class Index {
        int x;
        int val;

        public Index(int x, int val) {
            this.x = x;
            this.val = val;
        }

        public int getX() {
            return x;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public void setX(int x) {
            this.x = x;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}