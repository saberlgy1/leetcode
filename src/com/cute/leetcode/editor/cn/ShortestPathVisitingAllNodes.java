//存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。 
//
// 给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。 
//
// 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：graph = [[1,2,3],[0],[0],[0]]
//输出：4
//解释：一种可能的路径为 [1,0,2,0,3] 
//
// 示例 2： 
//
// 
//
// 
//输入：graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
//输出：4
//解释：一种可能的路径为 [0,1,4,2,3]
// 
//
// 
//
// 提示： 
//
// 
// n == graph.length 
// 1 <= n <= 12 
// 0 <= graph[i].length < n 
// graph[i] 不包含 i 
// 如果 graph[a] 包含 b ，那么 graph[b] 也包含 a 
// 输入的图总是连通图 
// 
// Related Topics 位运算 广度优先搜索 图 动态规划 状态压缩 
// 👍 158 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class ShortestPathVisitingAllNodes {
    public static void main(String[] args) {
        Solution solution = new ShortestPathVisitingAllNodes().new Solution();
        int[][] grapgh = new int[][]{{1, 2, 3}, {0}, {0}, {0}};
        System.out.println(solution.shortestPathLength(grapgh));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：无向图+BFS+二进制
        //给出来的输入数组已经是无向图了，还在想怎么构建无向图
        //如果能想到官方题解设立的三元组还是容易得出来bfs的搜索方法的
        //可惜想不到
        //定义一个对象结构 u mask dist
        //  - u 表示当前节点索引
        //  - mask 表示访问过的节点序列（对应访问过的节点索引位置为1，其余为0）
        //  - dist 当前节点经过的路径长度
        //BFS遍历这个对象结构体
        // 直到mask所有位都为1
        // 则dist作为答案
        //同时需要使用hash或visited数组记录遍历过的节点，防止无效的重复遍历
        /*class Node {
            int idx;
            int mask;
            int dist;

            public Node(int idx, int mask, int dist) {
                this.idx = idx;
                this.mask = mask;
                this.dist = dist;
            }
        }

        public int shortestPathLength(int[][] graph) {
            Deque<Node> dq = new ArrayDeque<>();
            boolean[][] visited = new boolean[graph.length][1 << graph.length];
            for (int i = 0; i < graph.length; i++) {
                dq.add(new Node(i, 1 << i, 0));
                visited[i][1 << i] = true;
            }
            int ans = 0;
            while (!dq.isEmpty()) {
                Node temp = dq.pollFirst();
                if (temp.mask == (1 << graph.length) - 1) {
                    ans = temp.dist;
                    break;
                }
                for (int num : graph[temp.idx]) {
                    int newMask = temp.mask | (1 << num);
                    if (!visited[num][newMask]) {
                        dq.add(new Node(num, newMask, temp.dist + 1));
                        visited[num][newMask] = true;
                    }
                }
            }
            return ans;
        }*/
        //思路二：状态压缩+二元数组
        //三叶大佬的二元数组节省了定义结构体
        //dist[u][state] 表示u节点到其他节点的相关访问状态
        /*public int shortestPathLength(int[][] graph) {
            int INF = 0x3f3f3f3f, mask = 1 << graph.length, n = graph.length;
            int[][] dist = new int[n][mask];
            //初始化所有数组
            for (int i = 0; i < n; i++) {
                Arrays.fill(dist[i], INF);
            }
            Deque<int[]> deque = new ArrayDeque<>();
            //每个节点都能出发
            for (int i = 0; i < n; i++) {
                dist[i][1 << i] = 0;
                deque.addLast(new int[]{i, 1 << i});
            }
            while (!deque.isEmpty()) {
                int[] temp = deque.poll();
                int u = temp[0], state = temp[1], step = dist[u][state];
                if (state == mask - 1) {
                    return step;
                }
                for (int num : graph[u]
                ) {
                    if (dist[num][state | (1 << num)] == INF) {
                        dist[num][state | (1 << num)] = step + 1;
                        deque.add(new int[]{ num,state | (1 << num)});
                    }
                }
            }
            return -1;
        }*/
        //思路三：Floyd+dp+状态压缩
        //毫无疑问三叶大佬的思维是真的好呜呜呜
        //这个二元组很像dp
        //只不过与常规dp不同的是
        //常规dp不会重复计算（也就是对应图中环的概念）
        //本题存在环，也就是会重复计算，所以无法使用常规dp求解
        //第一步初始化距离数组dist[i][j] 表示从i 触发到达j 节点的步骤每个值为INF（最大值）
        //第二步扫描每个节点到可到达的第一个节点dist为1
        //第三部floyd求最短路径
        //k节点为相关节点，判断每一个从i->k ->j 还是直接从i->j最短
        //设立dp数组f[i][state] 表示状态如果能从i到j的话使用最短路程来转移
        //i 表示节点 state表示到达状态
        //
        public int shortestPathLength(int[][] graph) {
            int n = graph.length, mask = 1 << n, INF = 0x3f3f3f3f;
            int[][] dist = new int[n][n];
            //floyd 求最短路径
            //初始化数组
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = INF;
                }
            }
            //从所有节点移动到可移动的j节点也就是从i移动到j点移动了一步初始化状态
            for (int i = 0; i < n; i++) {
                for (int j : graph[i]) dist[i][j] = 1;
            }
            //求最短路径
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
            //开始动态规划
            int[][] f = new int[n][mask];
            //初始化状态方程
            for (int i = 0; i < n; i++) {
                Arrays.fill(f[i], INF);
            }
            //初始化初始节点状态当前状态二进制位数为0
            for (int i = 0; i < n; i++) {
                f[i][1 << i] = 0;
            }
            for (int state = 0; state < mask; state++) {
                for (int i = 0; i < n; i++) {
                    //当前状态为初始节点状态不需要重复扫描初始节点
                    if ((state & (1 << i)) == 0) continue;
                    for (int k = 0; k < n; k++) {
                        //下一个节点对应当前状态也被扫描过
                        if ((state & (1 << k)) == 1) continue;
                        //枚举当前state未被扫描的状态
                        f[k][state | (1 << k)] = Math.min(f[k][state | (1 << k)], f[i][state] + dist[i][k]);
                    }
                }
            }
            int ans = INF;
            for (int i = 0; i < n; i++) ans = Math.min(ans, f[i][mask - 1]);
            return ans;

        }

    }
//leetcode submit region end(Prohibit modification and deletion)
}