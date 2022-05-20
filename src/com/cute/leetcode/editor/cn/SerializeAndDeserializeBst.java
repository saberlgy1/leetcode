//序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。 
//
// 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序
//列化为最初的二叉搜索树。 
//
// 编码的字符串应尽可能紧凑。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [2,1,3]
//输出：[2,1,3]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数范围是 [0, 10⁴] 
// 0 <= Node.val <= 10⁴ 
// 题目数据 保证 输入的树是一棵二叉搜索树。 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 二叉搜索树 字符串 二叉树 👍 315 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class SerializeAndDeserializeBst {
    public static void main(String[] args) {
        Codec solution = new SerializeAndDeserializeBst().new Codec();
        Integer[] data = new Integer[]{2, 1, 3, null, null, null, 5};
        TreeNode treeNode = TreeNode.arrayToTreeNode(data);
        System.out.println(solution.serialize(treeNode));
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

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null)return null;
            List<String> list = new ArrayList<>();
            dfs(root, list);
            return listToString(list);

        }

        void dfs(TreeNode root, List<String> list) {
            if (root == null) return;
            list.add(String.valueOf(root.val));
            dfs(root.left, list);
            dfs(root.right, list);

        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null) return null;
            String[] ss = data.split(",");
            return dfs1(0, ss.length - 1, ss);
        }

        TreeNode dfs1(int l, int r, String[] ss) {
            if (l > r) return null;
            int j = l + 1, val = Integer.parseInt(ss[l]);
            TreeNode ans = new TreeNode(val);
            while (j <= r && Integer.parseInt(ss[j]) <= val) j++;
            ans.left = dfs1(l + 1, j - 1, ss);
            ans.right = dfs1(j, r, ss);
            return ans;
        }

        private String listToString(List<String> list) {
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i));
                if (i != list.size() - 1) sb.append(",");
            }
            return sb.toString();
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;
//leetcode submit region end(Prohibit modification and deletion)

}