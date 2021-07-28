//给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。 
//
// 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
//输出：[7,4,1]
//解释：
//所求结点为与目标结点（值为 5）距离为 2 的结点，
//值分别为 7，4，以及 1
//
//
//
//注意，输入的 "root" 和 "target" 实际上是树上的结点。
//上面的输入仅仅是对这些对象进行了序列化描述。
// 
//
// 
//
// 提示： 
//
// 
// 给定的树是非空的。 
// 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。 
// 目标结点 target 是树上的结点。 
// 0 <= K <= 1000. 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 304 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class AllNodesDistanceKInBinaryTree {
    public static void main(String[] args) {
        Solution solution = new AllNodesDistanceKInBinaryTree().new Solution();
        Integer[] array = new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        //Integer[] array = new Integer[]{0, 2, 1, null, null, 3};
        System.out.println(solution.distanceK(TreeNode.arrayToTreeNode(array), new TreeNode(5), 2));
        //System.out.println(solution.distanceK(TreeNode.arrayToTreeNode(array), new TreeNode(3), 3));

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
        //思路一：DFS+hash
        //思路最开始是没问题的，写法还是有些问题，考虑的复杂了，与其正向查找，不如反向遍历
        //第一种可能解：该节点作为父节点往下遍历k次
        //然后其父节点往另外一个子节点方向遍历k-1
        //最简单的是遍历所有节点找到距离为k的数量
        //通过广度优先遍历确定target节点层数
        //父子节点对应关系
        /*Map<Integer, TreeNode> keyMap = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            findKeyMap(root);
            dfs(target, 0, null, k);
            return res;
        }

        public void findKeyMap(TreeNode root) {
            if (root == null) {
                return;
            }
            if (root.left != null) {
                keyMap.put(root.left.val, root);
                findKeyMap(root.left);
            }
            if (root.right != null) {
                keyMap.put(root.right.val, root);
                findKeyMap(root.right);
            }
        }

        public void dfs(TreeNode target, int dis, TreeNode from, int k) {
            if (target == null) {
                return;
            }
            if (dis == k) {
                res.add(target.val);
            }
            if (target.left != from) {
                dfs(target.left, dis + 1, target, k);
            }
            if (target.right != from) {
                dfs(target.right, dis + 1, target, k);
            }
            if (keyMap.get(target.val) != from) {
                dfs(keyMap.get(target.val), dis + 1, target, k);
            }
        }*/
        //思路二：图
        //三叶大佬还是强
        //定义一个无向图
        //这里的关键是add函数
        //截取@meteordream的非常好的讲解
        //三叶姐建立的是一个邻接表，数组 he 的下标表示结点，值是一个索引 ind，e[ind] 表示 对应一条边，ne[ind] 表示下一个连接结点的索引，假设与 结点a 相连的结点有 b, c, 那么通过 he[a]取得一个索引 ind1 后，通过 e[ind1] = b 可以得到与 a 相连的第一个结点是 b，然后通过 ne[ind1] 可以获得下一个结点的索引 ind2 ，通过 e[ind2] = c 可以得到与 a 相连的第二个结点是 c，最后 ne[ind2] = -1 说明没有下一个结点了
        //add函数有点类似链表的头插法，假设 结点a 已经有一个相连的结点 b，那么就有 he[a]=ind, e[ind]=b ，此时再给 a 增加一个相连的结点 c，那么就要建立由b的索引到新结点c的索引 ne[new_ind] = he[a] = ind ，然后新建一条边 e[new_ind], 最后更新 he[a] = new_ind ，就完成了由 a -> b 到 a -> c -> b 的添加操作
        //可以理解为 he 是邻接表的表头，key是结点val是一个指向存有相邻结点的链表头指针，e是链表结点的val即相邻结点，ne是链表结点的next指针
        //bfs的逻辑就是通过deque 做bfs
        //定义一个访问数组记录是否遍历过该节点
        //每遍历一层则k--，当k==0的时候也就是查到起始节点，插入进结果集
        //这种思路需要一定的数据结构基础，我理解起来比较费劲，大家如果有需求可以自行查找图的构建与搜索
        int N = 1010, M = N * 2;
        int[] he = new int[N], e = new int[M], ne = new int[M];
        int idx;
        void add(int a, int b) {
            e[idx] = b;
            ne[idx] = he[a];
            he[a] = idx++;
        }
        boolean[] vis = new boolean[N];
        public List<Integer> distanceK(TreeNode root, TreeNode t, int k) {
            List<Integer> ans = new ArrayList<>();
            Arrays.fill(he, -1);
            dfs(root);
            Deque<Integer> d = new ArrayDeque<>();
            d.addLast(t.val);
            vis[t.val] = true;
            while (!d.isEmpty() && k >= 0) {
                int size = d.size();
                while (size-- > 0) {
                    int poll = d.pollFirst();
                    if (k == 0) {
                        ans.add(poll);
                        continue;
                    }
                    for (int i = he[poll]; i != -1 ; i = ne[i]) {
                        int j = e[i];
                        if (!vis[j]) {
                            d.addLast(j);
                            vis[j] = true;
                        }
                    }
                }
                k--;
            }
            return ans;
        }

        private void dfs(TreeNode root){
            if (root == null){
                return;
            }
            if (root.left!=null){
                add(root.val,root.left.val);
                add(root.left.val,root.val);
                dfs(root.left);
            }
            if (root.right!=null){
                add(root.val,root.right.val);
                add(root.right.val,root.val);
                dfs(root.right);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}