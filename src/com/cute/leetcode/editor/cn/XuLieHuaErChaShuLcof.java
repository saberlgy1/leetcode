//请实现两个函数，分别用来序列化和反序列化二叉树。 
//
// 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字
//符串反序列化为原始的树结构。 
//
// 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方
//法解决这个问题。 
//
// 
//
// 示例： 
//
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
// 
//
// 
//
// 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-b
//inary-tree/ 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 
// 👍 178 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class XuLieHuaErChaShuLcof {
    public static void main(String[] args) {
        Codec solution = new XuLieHuaErChaShuLcof().new Codec();
//        TreeNode t1 = new TreeNode(10);
//        TreeNode t2 = new TreeNode(5);
//        TreeNode t3 = new TreeNode(3);
//        TreeNode t4 = new TreeNode(7);
//        TreeNode t5 = new TreeNode(1);
//        TreeNode t6 = new TreeNode(6);
//        TreeNode t7 = new TreeNode(15);
//        TreeNode t8 = new TreeNode(13);
//        TreeNode t9 = new TreeNode(18);
//        t1.left = t2;
//        t1.right = t7;
//        t2.left = t3;
//        t2.right = t4;
//        t3.left = t5;
//        t4.left = t6;
//        t7.left = t8;
//        t7.right = t9;
        TreeNode t1 = new TreeNode(-1);
        t1.left = new TreeNode(0);
        t1.right = new TreeNode(1);
        //System.out.println(solution.serialize(t1));
        String data = solution.serialize(t1);
        System.out.println(solution.deserialize(data));

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
    //思路一：BFS
    //BFS按层级依次序列化，然后再通过层级依次反序列化
    /*public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            String serialization = "";
            //corner case
            if (root == null) {
                return serialization;
            }
            Deque<TreeNode> deque = new LinkedList<>();
            deque.add(root);
            while (!deque.isEmpty()) {
                TreeNode temp = deque.poll();
                if (temp == null) {
                    serialization += "null,";
                    continue;
                }
                serialization += (temp.val + ",");
                deque.add(temp.left);
                deque.add(temp.right);
            }
            return serialization.substring(0, serialization.length() - 1);
        }

        // Decodes your encoded data to tree.
        //根据BFS结果反推树节点
        public TreeNode deserialize(String data) {
            //corner case
            if ("".equals(data)){
                return null;
            }
            String[] trees = data.split(",");
            if (trees.length == 1) {
                return new TreeNode(Integer.parseInt(data));
            }
            TreeNode root = new TreeNode(Integer.parseInt(trees[0]));
            TreeNode cur = root;
            Deque<TreeNode> deque = new LinkedList<>();
            deque.add(root);
            int step = 1;
            while (!deque.isEmpty() && step < trees.length) {
                TreeNode temp = deque.poll();
                if ("null".equals(trees[step])){
                    temp.left = null;
                }else{
                    temp.left = new TreeNode(Integer.parseInt(trees[step]));
                    deque.add(temp.left);
                }
                step++;
                if (step == trees.length){
                    return root;
                }
                if ("null".equals(trees[step])){
                    temp.right = null;
                }else{
                    temp.right = new TreeNode(Integer.parseInt(trees[step]));
                    deque.add(temp.right);
                }
                step++;
            }
            return cur;
        }


    }*/
    //思路二：DFS
    //深度优先遍历
    public class Codec {
        String serialization = "";
        int i = 0;
        public String serialize(TreeNode root) {
            dfs(root);
            return serialization.substring(0, serialization.length() - 1);
        }

        public TreeNode deserialize(String data) {
            //corner case
            if ("".equals(data)) {
                return null;
            }
            String[] trees = data.split(",");
            if ("null".equals(trees[0])){
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(trees[0]));
            i++;
            deserializeDfs(root, trees);
            return root;
        }

        public void deserializeDfs(TreeNode root, String[] trees) {
            int n = trees.length;
            if (i == n) {
                return;
            }
            if ("null".equals(trees[i])) {
                root.left = null;
                i++;
            } else {
                root.left = new TreeNode(Integer.parseInt(trees[i]));
                i++;
                deserializeDfs(root.left, trees);
            }
            if (i == n) {
                return;
            }
            if ("null".equals(trees[i])){
                root.right = null;
                i++;
            }else{
                root.right = new TreeNode(Integer.parseInt(trees[i]));
                i++;
                deserializeDfs(root.right, trees);
            }
        }

        public void dfs(TreeNode root) {
            if (root == null) {
                serialization += "null,";
                return;
            }
            serialization += (root.val + ",");
            if (root.left == null) {
                serialization += "null,";
            }else{
                dfs(root.left);
            }
            if (root.right == null){
                serialization += "null,";
            }else{
                dfs(root.right);
            }
        }

    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

}