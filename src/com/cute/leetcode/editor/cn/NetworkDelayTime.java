//有 n 个网络节点，标记为 1 到 n。 
//
// 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， w
//i 是一个信号从源节点传递到目标节点的时间。 
//
// 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：times = [[1,2,1]], n = 2, k = 1
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：times = [[1,2,1]], n = 2, k = 2
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= n <= 100 
// 1 <= times.length <= 6000 
// times[i].length == 3 
// 1 <= ui, vi <= n 
// ui != vi 
// 0 <= wi <= 100 
// 所有 (ui, vi) 对都 互不相同（即，不含重复边） 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 最短路 堆（优先队列） 
// 👍 298 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class NetworkDelayTime {
    public static void main(String[] args) {
        Solution solution = new NetworkDelayTime().new Solution();
        // int[][] times = new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        //int[][] times = new int[][]{{1,2,1},{2,1,3}};
        //int[][] times = new int[][]{{2, 4, 10}, {5, 2, 38}, {3, 4, 33}, {4, 2, 76}, {3, 2, 64}, {1, 5, 54}, {1, 4, 98}, {2, 3, 61}, {2, 1, 0}, {3, 5, 77}, {5, 1, 34}, {3, 1, 79}, {5, 3, 2}, {1, 2, 59}, {4, 3, 46}, {5, 4, 44}, {2, 5, 89}, {4, 5, 21}, {1, 3, 86}, {4, 1, 95}};
        int[][] times = new int[][]{{4, 2, 76}, {1, 3, 79}, {3, 1, 81}, {4, 3, 30}, {2, 1, 47}, {1, 5, 61}, {1, 4, 99}, {3, 4, 68}, {3, 5, 46}, {4, 1, 6}, {5, 4, 7}, {5, 3, 44}, {4, 5, 19}, {2, 3, 13}, {3, 2, 18}, {1, 2, 0}, {5, 1, 25}, {2, 5, 58}, {2, 4, 77}, {5, 2, 74}};
        System.out.println(solution.networkDelayTime(times, 5, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：DFS+暴力+hash
//        深度遍历出k节点开始的所有边和时长
//        所有边都不重复
//        TLE了
//        单纯的DFS太容易搜索空间爆炸了
        /*Map<Integer, Integer> map = new HashMap<>();

        public int networkDelayTime(int[][] times, int n, int k) {
            map.put(k, 0);
            Arrays.sort(times, (a, b) -> {
                if (a[0] == b[0]) {
                    if (a[1] == b[1]) {
                        return a[2] - b[2];
                    } else {
                        return a[1] - b[1];
                    }
                }
                return a[0] - b[0];
            });
            dfs(times, k,0);
            if (map.size() != n) {
                return -1;
            }
            int max = 0;
            for (int key : map.keySet()
            ) {
                max = Math.max(max, map.get(key));
            }
            return max;
        }


        public void dfs(int[][] times, int k, int index) {
            for (int i = index; i < times.length; i++) {
                if (times[i][0] == k) {
                    //当前元素有更短路径
                    if (map.containsKey(times[i][1])) {
                        map.put(times[i][1], Math.min(map.get(k) + times[i][2], map.get(times[i][1])));
                    } else {
                        map.put(times[i][1], map.get(k) + times[i][2]);
                    }
                    dfs(times, times[i][1], index + 1);
                }
            }
        }*/
        //思路二：floyd建图搜索
//        初始化有向图大小为最多N个节点,每个节点值大于6010即可
//        初始化每条边的值
//        通过floyd函数计算每个点到其余点的最短路径
//        返回k点到其余点的最短路径
        /*int N = 110, m = 6010;
        int[][] w = new int[N][N];
        int INF = 0x3f3f3f3f;
        int n, k;

        public int networkDelayTime(int[][] times, int _n, int _k) {
            n = _n;
            k = _k;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    w[i][j] = w[j][i] = i == j ? 0 : INF;
                }
            }
            for (int[] time : times
            ) {
                w[time[0]][time[1]] = time[2];
            }
            floyd();
            int ans = 0;
            for (int i = 1; i <= n; i++) {
                ans = Math.max(ans, w[k][i]);
            }
            return ans >= INF ? -1 : ans;
        }

        public void floyd() {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    for (int l = 1; l <= n; l++) {
                        w[j][l] = Math.min(w[j][l], w[j][i] + w[i][l]);
                    }
                }
            }
        }*/
        //思路三：朴素 Dijkstra（邻接表）
//        三叶大佬懂得真多，实名制羡慕
//        优化了部分floyd算法，但是整体时间复杂度没什么变化
//        初始化访问数组，记录访问状态
//        这个算法的核心思路是
//        - 定义两种节点，一种是未确定节点，一种是已确定节点
//          - 未确定节点：并未确定起点k到当前节点的最短距离
//          - 已确定节点：已确定起点k到当前节点的最短距离
//        1、遍历所有节点
//        2、每一次弹出一个未确定节点，将其归类为已确定节点
//        - 弹出条件：
//          - 当前节点没有被扫描过
//          - 起点到当前节点的距离值比之前计算的短｜｜未被扫描的第一个节点（满足一个即可）
//        通过已确定的最短值，更新未确定的最小值
        /*int N = 110, m = 6010;
        int[][] w = new int[N][N];
        int INF = 0x3f3f3f3f;
        int n, k;
        int[] dist = new int[N];
        boolean[] vis = new boolean[N];

        public int networkDelayTime(int[][] times, int _n, int _k) {
            n = _n;
            k = _k;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    w[i][j] = w[j][i] = i == j ? 0 : INF;
                }
            }
            for (int[] time : times
            ) {
                w[time[0]][time[1]] = time[2];
            }
            int ans = 0;
            dijkstra();
            for (int i = 1; i <= n; i++) {
                ans = Math.max(ans, dist[i]);
            }
            return ans >= INF ? -1 : ans;
        }

        public void dijkstra() {
            Arrays.fill(vis, false);
            Arrays.fill(dist, INF);
            dist[k] = 0;
            for (int p = 1; p <= n; p++) {
                int t = -1;
                for (int i = 1; i <= n; i++) {
                    if (!vis[i] && (t == -1 || dist[i] < dist[t])) t = i;
                }
                vis[t] = true;
                for (int i = 1; i <= n; i++) {
                    dist[i] = Math.min(dist[i], dist[t] + w[t][i]);
                }
            }
        }*/
        //思路四：堆优化 Dijkstra算法
//        首先通过链式建图（前两天遇到过）头插法建图
//        he表示某个节点对应的边的集合的头节点，e表示当前边指向的节点，ne表示当前节点的下一条边，w表示耗时，dist表示起点到每个节点的最短路径，index表示边的编号
//        add函数表示的即是
//        当前无节点的时候add
//        - 新增一条边的关系指向b节点 e[index] = b
//        - 当前节点的下一条边ne 指向的是h[a]对应的下一条边（初始化的时候不存在=-1）
//        - 将当前头节点h[a] 赋值第一条关联边index关联到e[index]上
//        - index++
//        当前有节点的时候
//        - 新增一条边的关系指向b节点 e[index] = b
//        - 下一条边索引ne[index] = 当前头节点a对应的下一条边关系h[a]
//        - 当前头节点h[a] 关联的第一条边转换为最新的边索引index
//        - index++
//        初始 null
//        初始化 第一条边 a->b->null
//        添加一个新的节点 a->b`->b->null
        /*int N = 110,M = 6010;
        boolean[] vis = new boolean[N];
        int[] he = new int[N],e = new int[M],ne = new int[M],w = new int[M], dist = new int[N];
        int INF = 0x3f3f3f3f;
        int n,k, index;
        void add(int a, int b , int c){
            e[index] = b;
            ne[index] = he[a];
            he[a] = index;
            w[index] = c;
            index++;
        }
        public int networkDelayTime(int[][] times, int _n, int _k) {
            n = _n; k = _k;
            Arrays.fill(he,-1);
            for (int[] time: times
                 ) {
                add(time[0],time[1], time[2]);
            }
            dijkstra();
            int ans = 0;
            for (int i = 1; i <= n; i++) {
                ans = Math.max(ans, dist[i]);
            }
            return ans > INF / 2 ? -1 : ans;
        }
        private void dijkstra(){
            Arrays.fill(vis,false);
            Arrays.fill(dist,INF);
            dist[k] = 0;
            PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a,b)->a[1]-b[1]);
            priorityQueue.add(new int[]{k,0});
            while (!priorityQueue.isEmpty()){
                int[] temp = priorityQueue.poll();
                int node = temp[0];
                if (vis[node]){
                    continue;
                }
                for (int i = he[node]; i != -1; i= ne[i]) {
                    int j = e[i];
                    if (dist[j] > dist[node]+w[i]){
                        dist[j] = dist[node]+w[i];
                        priorityQueue.add(new int[]{j,dist[j]});
                    }
                }
            }
        }*/

        //思路五：Bellman Ford（邻接表）
        //u1s1 这些算法就够难记的了，这些名字三叶大佬是怎么记住的
        //同样是链表作图
        //我已经完全看不懂这个和上一个的区别了呜呜呜 大家去看三叶大佬的题解吧
        int N = 110, M = 6010;
        int[] he = new int[N], e = new int[M], ne = new int[M], dist = new int[N], w = new int[M];
        int INF = 0x3f3f3f3f, index = 0, n, k,m;

        void add(int a, int b, int c) {
            e[index] = b;
            ne[index] = he[a];
            he[a] = index;
            w[index] = c;
            index++;
        }

        public int networkDelayTime(int[][] times, int _n, int _k) {
            n = _n;
            k = _k;
            m = times.length;
            Arrays.fill(he, -1);
            for (int[] time : times
            ) {
                add(time[0], time[1], time[2]);
            }
            bf();
            int ans = 0;
            for (int i = 1; i <= n; i++) {
                ans = Math.max(ans, dist[i]);
            }
            return ans > INF / 2 ? -1 : ans;
        }
        void bf(){
            Arrays.fill(dist, INF);
            dist[k] = 0;
            for (int p = 1; p <= m; p++) {
                int[] prev = dist.clone();
                for (int a = 1; a <= n; a++) {
                    for (int i = he[a]; i != -1; i = ne[i]) {
                        int b = e[i];
                        dist[b] = Math.min(dist[b], prev[a] + w[i]);
                    }
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}