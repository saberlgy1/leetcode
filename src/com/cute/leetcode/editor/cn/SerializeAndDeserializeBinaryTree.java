//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。 
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。 
//
// 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的
//方法解决这个问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 104] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 
// 👍 589 👎 0

package com.cute.leetcode.editor.cn;

public class SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        Codec solution = new SerializeAndDeserializeBinaryTree().new Codec();
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
            if ("null".equals(trees[0])) {
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
            if ("null".equals(trees[i])) {
                root.right = null;
                i++;
            } else {
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
            } else {
                dfs(root.left);
            }
            if (root.right == null) {
                serialization += "null,";
            } else {
                dfs(root.right);
            }
        }

    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

}