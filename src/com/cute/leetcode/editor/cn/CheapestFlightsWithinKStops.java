//有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，表示该航班都从城
//市 fromi 开始，以价格 toi 抵达 pricei。
//
// 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便
//宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。
//
//
//
// 示例 1：
//
//
//输入:
//n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
//src = 0, dst = 2, k = 1
//输出: 200
//解释:
//城市航班图如下
//
//
//从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
//
// 示例 2：
//
//
//输入:
//n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
//src = 0, dst = 2, k = 0
//输出: 500
//解释:
//城市航班图如下
//
//
//从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。
//
//
//
// 提示：
//
//
// 1 <= n <= 100
// 0 <= flights.length <= (n * (n - 1) / 2)
// flights[i].length == 3
// 0 <= fromi, toi < n
// fromi != toi
// 1 <= pricei <= 10⁴
// 航班没有重复，且不存在自环
// 0 <= src, dst, k < n
// src != dst
//
// Related Topics 深度优先搜索 广度优先搜索 图 动态规划 最短路 堆（优先队列） 👍 307 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class CheapestFlightsWithinKStops {
    public static void main(String[] args) {
        Solution solution = new CheapestFlightsWithinKStops().new Solution();
        int n = 7, src = 2, dst = 4, k = 1;
        int[][] flights = new int[][]{{0, 3, 7}, {4, 5, 3}, {6, 4, 8}, {2, 0, 10}, {6, 5, 6}, {1, 2, 2}, {2, 5, 9}, {2, 6, 8}, {3, 6, 3}, {4, 0, 10}, {4, 6, 8}, {5, 2, 6}, {1, 4, 3}, {4, 1, 6}, {0, 5, 10}, {3, 1, 5}, {4, 3, 1}, {5, 4, 10}, {0, 1, 6}};
        System.out.println(solution.findCheapestPrice(n, flights, src, dst, k));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //最常见的最短路算法
        //思路一：dfs+暴力
        //从src节点开始不断遍历
        //记录遍历层数
        //同时使用map存储每个src节点对应的元素集，简化搜索
        /*Map<Integer, List<Integer>> map = new HashMap<>();
        int des, maxStep, min;
        int[][] temp;

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            for (int i = 0; i < flights.length; i++) {
                List<Integer> list = map.getOrDefault(flights[i][0], new ArrayList<>());
                list.add(i);
                map.put(flights[i][0], list);
            }
            des = dst;
            maxStep = k;
            min = Integer.MAX_VALUE;
            temp = flights;
            dfs(src, 0, 0);
            return min == Integer.MAX_VALUE ? -1 : min;
        }


        public void dfs(int src, int step, int price) {
            //边界条件
            if (step > maxStep && src != des) {
                return;
            }
            if (src == des) {
                min = Math.min(price, min);
                return;
            }
            for (int num : map.getOrDefault(src, new ArrayList<>())
            ) {
                dfs(temp[num][1], step + 1, price + temp[num][2]);
            }
        }*/
        //思路二：动态规划
        //记忆化dfs写不明白了，先写dp吧
        //定义dp方程dp[k][i]表示经过k个节点到达i节点的最小花费
        //dp[k][i] = min(f((k-1),j) + price(j,i));

        /*public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            int INF = 0x3f3f3f3f;
            int[][] dp = new int[k + 2][n];
            for (int i = 0; i < k + 2; ++i) {
                Arrays.fill(dp[i], INF);
            }
            dp[0][src] = 0;
            for (int i = 1; i <= k + 1; i++) {
                for (int[] num : flights
                ) {
                    dp[i][num[1]] = Math.min(dp[i - 1][num[0]] + num[2], dp[i][num[1]]);
                }

            }
            int res = INF;
            for (int i = 0; i <= k+1; i++) {
                res = Math.min(res, dp[i][dst]);
            }
            return res ==INF ? -1 : res;
        }*/
        //思路三：降维处理
        //dp[i]表示到i点的最小花费
        /*public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            int INF = 0x3f3f3f3f;
            int[] dp = new int[n];

            Arrays.fill(dp, INF);
            dp[src] = 0;

            int res = INF;
            for (int i = 1; i <= k + 1; i++) {
                int[] g = new int[n];
                Arrays.fill(g, INF);
                for (int[] num : flights
                ) {
                    g[num[1]] = Math.min(dp[num[0]] + num[2], g[num[1]]);
                }
                dp = g;
                res = Math.min(res, dp[dst]);
            }
            return res == INF ? -1 : res;
        }*/
        //思路四：记忆化递归
        //该写记忆化递归了
        //这种记忆化依旧是非常的慢非常的慢
        /*Map<Integer, List<Integer>> map = new HashMap<>();
        int des;
        int[][] temp;
        int[][] cache;
        int INF = 0x3f3f3f3f;
        int res = INF;

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            for (int i = 0; i < flights.length; i++) {
                List<Integer> list = map.getOrDefault(flights[i][0], new ArrayList<>());
                list.add(i);
                map.put(flights[i][0], list);
            }
            des = dst;
            temp = flights;
            cache = new int[k + 2][n];
            for (int i = 0; i <= k + 1; i++) {
                for (int j = 0; j < n; j++) {
                    cache[i][j] = j == src ? 0 : INF;
                }
            }
            dfs(src, 0, 0, k);
            return res == INF ? -1 : res;
        }


        public void dfs(int src, int step, int price, int k) {
            //边界条件
            if (des == src) {
                res = Math.min(res, price);
                return;
            }
            if (k < 0) {
                return;
            }
            for (int num : map.getOrDefault(src, new ArrayList<>())
            ) {
                if (cache[step + 1][temp[num][1]] <= price + temp[num][2]) {
                    continue;
                }
                cache[step + 1][temp[num][1]] = price + temp[num][2];
                dfs(temp[num][1], step + 1, price + temp[num][2], k - 1);
            }
        }*/
        //思路五：BFS+记忆化
        //相对dfs来说bfs的优化似乎更好理解
/*        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            int INF = 0x3f3f3f3f;
            List<int[]>[] edge = new List[n];
            int[] vis = new int[n];
            for(int i = 0; i < n; ++i){
                edge[i] = new ArrayList<>();
            }
            for (int[] flight : flights) {
                edge[flight[0]].add(new int[]{flight[1], flight[2]});
            }
            Arrays.fill(vis, INF);
            vis[src] = 0;
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{src, 0, vis[src]});
            while (!queue.isEmpty()) {
                int[] poll = queue.poll();
                if (poll[1] > k) break;
                for (int[] next : edge[poll[0]]) {
                    if (vis[next[0]] > poll[2] + next[1]) {
                        vis[next[0]] = poll[2] + next[1];
                        queue.add(new int[]{next[0], poll[1] + 1, vis[next[0]]});
                    }
                }
            }
            return vis[dst] == INF ? -1 : vis[dst];
        }*/
        //思路六：BF算法-维度优化
        //本体毫无以为是有限制的最短路径算法
        //其实bf算法也可以看做dp的另一种方案实现
        int N = 110, INF = 0x3f3f3f3f;
        int[][] g = new int[N][N];
        int[] dist = new int[N];
        int n, m, s, t, k;
        public int findCheapestPrice(int _n, int[][] flights, int _src, int _dst, int _k) {
            n = _n; s = _src; t = _dst; k = _k + 1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    g[i][j] = i == j ? 0 : INF;
                }
            }
            for (int[] f : flights) {
                g[f[0]][f[1]] = f[2];
            }
            int ans = bf();
            return ans > INF / 2 ? -1 : ans;
        }
        int bf() {
            Arrays.fill(dist, INF);
            dist[s] = 0;
            for (int limit = 0; limit < k; limit++) {
                //这一步非常关键
                int[] clone = dist.clone();
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        dist[j] = Math.min(dist[j], clone[i] + g[i][j]);
                    }
                }
            }
            return dist[t];
        }
        //思路七：BF算法维度优化
        /*public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            int N = 110, INF = 0x3f3f3f3f;
            //初始化访问到当前节点的最短路径
            int[] vis = new int[N];
            Arrays.fill(vis, INF);
            vis[src] = 0;
            for (int i = 0; i < k + 1; i++) {
                int[] g = vis.clone();
                for (int[] f: flights
                     ) {
                    int x = f[0], y = f[1], w = f[2];
                    vis[y] = Math.min(vis[y], g[x] + w);
                }
            }
            return vis[dst] > INF / 2 ? -1 : vis[dst];
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}