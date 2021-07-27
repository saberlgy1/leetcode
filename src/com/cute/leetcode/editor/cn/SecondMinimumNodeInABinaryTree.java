//给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一
//个。 
//
// 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。 
//
// 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [2,2,5,null,null,5,7]
//输出：5
//解释：最小的值是 2 ，第二小的值是 5 。
// 
//
// 示例 2： 
//
// 
//输入：root = [2,2,2]
//输出：-1
//解释：最小的值是 2, 但是不存在第二小的值。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [1, 25] 内 
// 1 <= Node.val <= 231 - 1 
// 对于树中每个节点 root.val == min(root.left.val, root.right.val) 
// 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 159 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class SecondMinimumNodeInABinaryTree {
    public static void main(String[] args) {
        Solution solution = new SecondMinimumNodeInABinaryTree().new Solution();
        Integer[] array = new Integer[]{2,2,5,null,null,5,7};
        TreeNode root = TreeNode.arrayToTreeNode(array);
        System.out.println(solution.findSecondMinimumValue(root));

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
        //思路一：这应该算穷举？
        //从题目分析来看只需要判断第一个根结点的子节点满足不满足要求即可
        //想的有些简单了，较大值不一定满足要求，所以还是dfs遍历+小根堆看吧
        //其实我在想set是不是也可以
        // 不满足则递归遍历，找到满足的节点
        //找不到则返回-1
        /*public int findSecondMinimumValue(TreeNode root) {
            //corner case
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
                //小根堆
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (o1 > o2) return 1;
                    return o1.equals(o2) ? 0 : -1;
                }
            });
            dfs(root, priorityQueue);
            if (priorityQueue.size() >= 2) {
                int min = priorityQueue.poll();
                while (priorityQueue.peek()!=null){
                    if (priorityQueue.peek()>min){
                        return priorityQueue.poll();
                    }else{
                        priorityQueue.poll();
                    }
                }
            }
            return -1;
        }*/
        //set实现
        /*public int findSecondMinimumValue(TreeNode root) {
            //corner case
            Set<Integer> set = new TreeSet<>(new Comparator<Integer>() {
                //小根堆
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (o1 > o2) return 1;
                    return o1.equals(o2) ? 0 : -1;
                }
            });
            dfs(root, set);
            if (set.size() >= 2) {
                int i = 0;
                for (int num:set
                     ) {
                    if (i == 1){
                        return num;
                    }else{
                        i++;
                    }
                }
            }
            return -1;
        }*/
        //思路二：剪枝
        //利用本题二叉树性质剪枝
        //较大元素的子节点是不需要遍历的，所有我们留一个作为备份即可
        //也就是dfs可以不用两边都遍历
        public int findSecondMinimumValue(TreeNode root) {
            //corner case
            if (root.left == null){
                return -1;
            }
            Set<Integer> set = new TreeSet<>(new Comparator<Integer>() {
                //小根堆
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (o1 > o2) return 1;
                    return o1.equals(o2) ? 0 : -1;
                }
            });
            set.add(root.left.val);
            set.add(root.right.val);
            if (root.left.val == root.val) {
                dfs3(root.left, set);
            }
            if (root.right.val == root.val) {
                dfs3(root.right, set);
            }
            if (set.size() >= 2) {
                int i = 0;
                for (int num : set
                ) {
                    if (i == 1) {
                        return num;
                    } else {
                        i++;
                    }
                }
            }
            return -1;
        }

        private void dfs(TreeNode root, PriorityQueue<Integer> queue) {
            if (root == null) {
                return;
            }
            queue.add(root.val);
            dfs(root.left, queue);
            dfs(root.right, queue);
        }

        private void dfs(TreeNode root, Set<Integer> queue) {
            if (root == null) {
                return;
            }
            queue.add(root.val);
            dfs(root.left, queue);
            dfs(root.right, queue);
        }

        private void dfs3(TreeNode root, Set<Integer> set) {
            if (root == null) {
                return;
            }
            if (root.left == null) {
                return;
            }
            set.add(root.left.val);
            set.add(root.right.val);
            if (root.left.val == root.val) {
                dfs3(root.left, set);
            }
            if (root.right.val == root.val) {
                dfs3(root.right, set);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}