//小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
//
//
// 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
// 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
// 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
//
//
// 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号
//为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
//
// 示例 1：
//
//
// 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
//
// 输出：3
//
// 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->
//3->4。
//
//
// 示例 2：
//
//
// 输入：n = 3, relation = [[0,2],[2,1]], k = 2
//
// 输出：0
//
// 解释：信息不能从小 A 处经过 2 轮传递到编号 2
//
//
// 限制：
//
//
// 2 <= n <= 10
// 1 <= k <= 5
// 1 <= relation.length <= 90, 且 relation[i].length == 2
// 0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
//
// Related Topics 深度优先搜索 广度优先搜索 图 动态规划
// 👍 71 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class ChuanDiXinXi {
    public static void main(String[] args) {
        Solution solution = new ChuanDiXinXi().new Solution();
        int[][] nums = new int[][]{{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}};
        System.out.println(solution.numWays(5, nums, 3));
        String s;
        System.out.println("".split(",").length);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：BFS
        //这道题easy，昨天hard就挺奇怪的
        //广度优先搜索，遍历到层级step=k的时候统计结果中包含target的结果集（target = n-1）
        //首先要确立每两个点之间的边的连线（确认）
        //定义一个map存储所有节点可到达的节点
        //然后进行BFS，记录step
        /*public int numWays(int n, int[][] relation, int k) {
            Map<Integer, Set<Integer>> map = new HashMap<>();
            //确立节点间连线
            for (int[] des : relation
            ) {
                Set<Integer> set = map.getOrDefault(des[0], new HashSet<>());
                set.add(des[1]);
                map.put(des[0], set);
            }
            Deque<String> deque = new LinkedList<>();
            //从第一个节点出发
            deque.add("0");
            //通过路径长度表示step层级以及step层级下可到达target的数量
            int res = 0;
            while (!deque.isEmpty() && deque.peek().length() <= k) {
                String temp = deque.poll();
                int tempValue = Integer.parseInt(temp.substring(temp.length()-1));
                int step = temp.length();
                for (int num : map.getOrDefault(tempValue,new HashSet<>())) {
                    deque.add(temp+num);
                    if (step == k && num == n - 1) {
                        res += 1;
                    }
                }
            }
            return res;
        }*/
        //思路二：DFS
        //DFS到k层级之后退出遍历，通过set记录所有层级位k，最后节点位n-1的路径，返回set的大小
        Set<String> res = new HashSet<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();

        /*public int numWays(int n, int[][] relation, int k) {
            for (int[] des : relation
            ) {
                Set<Integer> set = map.getOrDefault(des[0], new HashSet<>());
                set.add(des[1]);
                map.put(des[0], set);
            }
            String temp = "0";
            //corner case
            if (map.get(0) == null) {
                return 0;
            }
            dfs(temp, k, n);
            return res.size();
        }

        private void dfs(String str, int k, int n) {
            int tempValue = Integer.parseInt(str.substring(str.length() - 1));
            if (str.length() > k + 1) {
                return;
            }
            if (str.length() == k + 1 && tempValue == n - 1) {
                res.add(str);
                return;
            }
            for (int num : map.getOrDefault(tempValue,new HashSet<>())
            ) {
                String temp = str;
                dfs(temp += num, k, n);
            }
        }*/
        //思路三：动态规划
        //在有限k步数下到达n-1节点的方案数取决于（k-1步到达能一步到达n-1节点的所有节点）的方案数
        //定义动态规划数组dp[i][j]表示经过i步到达j节点的方案数
        //dp[0][0] = 1;
        //dp[i][j] = sum[dp[i-1][relations[k][1]==n-1?relations[k][0]:-1]]
        public int numWays(int n, int[][] relation, int k) {
            int dp[][] = new int[k + 1][n];
            dp[0][0] = 1;
            for (int i = 1; i <= k; i++) {
                for (int[] edge : relation) {
                    dp[i][edge[1]] += dp[i - 1][edge[0]];
                }
            }
            return dp[k][n - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}