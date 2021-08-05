//在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。 
//
// 对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。
//
// 返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。 
//
// 该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，
//满足 (i, j) 是图的一条有向边。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
//输出：[2,4,5,6]
//解释：示意图如上。
// 
//
// 示例 2： 
//
// 
//输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
//输出：[4]
// 
//
// 
//
// 提示： 
//
// 
// n == graph.length 
// 1 <= n <= 104 
// 0 <= graph[i].length <= n 
// graph[i] 按严格递增顺序排列。 
// 图中可能包含自环。 
// 图中边的数目在范围 [1, 4 * 104] 内。 
// 
// 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 
// 👍 180 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class FindEventualSafeStates {
    public static void main(String[] args) {
        Solution solution = new FindEventualSafeStates().new Solution();
        int[][] graph = new int[][]{{}, {0, 2, 3, 4}, {3}, {4}, {}};
        System.out.println(solution.eventualSafeNodes(graph));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：dfs+暴力
        //dfs遍历所有节点
        //每一次dfs的节点不会遍历相同节点，一旦相同，则非安全
        //可以通过hash来做
        //TLE了
       /* Set<Integer> set = new HashSet<>();
        public List<Integer> eventualSafeNodes(int[][] graph) {
            int[] arr = new int[graph.length];
            for (int i = 0; i < graph.length; i++) {
                set = new HashSet<>();
                set.add(i);
                arr[i] = dfs(graph, i,set) ? 1 : 0;
            }
            List<Integer> res = new ArrayList<>();
            for (int i = 0 ; i < arr.length;i++){
                if (arr[i]>0){
                    res.add(i);
                }
            }
            return res;
        }

        public boolean dfs(int[][] graph, int i,Set<Integer> set) {
            if (i>= graph.length){
                return false;
            }
            for (int tar: graph[i]
                 ) {
                if (set.contains(tar)){
                    return false;
                }
                Set<Integer> temp = new HashSet<>();
                temp.addAll(set);
                temp.add(tar);
                boolean flag = dfs(graph,tar,temp);
                if (!flag){
                    return false;
                }
            }
            return true;
        }*/
        //思路二：记忆化存储
        //dfs 某些点可以直接判断是否满足安全条件
        //通过security记录已经遍历过的可以安全到达终点的节点，减少遍历次数
/*        Set<Integer> security = new HashSet<>();
        public List<Integer> eventualSafeNodes(int[][] graph) {
            int[] arr = new int[graph.length];
            for (int i = 0; i < graph.length; i++) {
                Set<Integer> set = new HashSet<>();
                set.add(i);
                arr[i] = dfs(graph, i, set) ? 1 : 0;
            }
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > 0) {
                    res.add(i);
                }
            }
            return res;
        }

        public boolean dfs(int[][] graph, int i, Set<Integer> set) {
            if (i >= graph.length) {
                return false;
            }
            for (int tar : graph[i]
            ) {
                if (security.contains(tar)) {
                    continue;
                }
                if (set.contains(tar)) {
                    return false;
                }
                Set<Integer> temp = new HashSet<>();
                temp.addAll(set);
                temp.add(tar);
                boolean flag = dfs(graph, tar, temp);
                if (!flag) {
                    return false;
                } else {
                    security.add(tar);
                }
            }
            return true;
        }*/
        //思路三：拓扑排序
//        有趣的新知识又增加了
//        这是每日一题系列第一次引入拓扑排序的概念
//        首先说一下本题和拓扑排序的关联吧
//        本题根据题意可以分析出来所求的结果序列是指，序列内所有节点都保证有向路径内无环
//        拓扑排序的性质有一条也很简单：
//        若存在两个节点(u,v)有一条u->v的有向通路，则v一定不会存在一条到u的有向路（无环）
//        能够求得「某个有向无环图的拓扑序」的前提是：我们必然能够找到（至少）一个「入度为 0 的点」，在起始时将其入队。
//        性质刚好和题意吻合
//        然后再说明一下两个定义吧
//        入度：所有能够到达本节点的边数
//        出度：所有从本节点出发的边数
//        本题可以现将所有入度为0（没有能够到达此节点的方式）的节点放进结果集，将这些节点放入拓扑排序的头部不会影响拓扑序列的定义
//        对于当前弹出的节点 x，遍历 x的所有出度
//         即遍历所有由 x 直接指向的节点 y
//         对 y 做入度减一操作
//         因为 x 节点已经从队列中弹出，被添加到拓扑序中
//         等价于从 x 节点从有向图中被移除
//         相应的由 x 发出的边也应当被删除
//         带来的影响是与 x 相连的节点 y 的入度减一
//        对 y 进行入度减一之后
//         检查 y 的入度是否为 0
//         如果为 0 则将 y 入队
//         当 y 的入度为 0
//         说明有向图中在 y 前面的所有的节点均被添加到拓扑序中
//         此时 y 可以作为拓扑序的某个片段的首部被添加
//         而不违反拓扑序的定义
//         本题用到的反向图和拓扑序列
//         本题如果想要判断某个节点数 x 是否安全
//         起始时将 x 进行入队
//         并跑一遍拓扑排序是不足够的。
//         因为输入并不能确认节点是否入度为0，也就是说不能直接将节点加入拓扑序列中
//        这句话也可以解释为，当x节点不确定入度为0的时候，y节点的入度按上述操作也就未必能减到0
//        所以根据之前的证明过程可以构造反向图，将所有边反向后，入度为0，则对应着原图中出度为0的点
//        原图出度为0代表改点为安全节点，且所有指向该节点的边也都可以代表安全
//        因此整个过程就是将图进行反向
//         再跑一遍拓扑排序
//         如果某个节点出现在拓扑序列
//         说明其进入过队列
//         说明其入度为 0
//         其是安全的
//         其余节点则是在环内非安全节点。
//        链式建图（具体可以参考之前三叶大佬的公众号的解释）
        int N = (int)1e4+10, M = 4 * N, idx = 0;
        int[] e = new int[M], he = new int[N], ne = new int[M];
        int[] cnts = new int[N];
        void add(int a, int b){
            e[idx] = b;
            ne[idx] = he[a];
            he[a] = idx;
            idx++;
        }
        public List<Integer> eventualSafeNodes(int[][] graph) {
            //初始化图
            Arrays.fill(he,-1);
            int n = graph.length;
            //构造反向图
            for (int i = 0; i < n; i++) {
                for (int num: graph[i]
                     ) {
                    //反向构建
                    add(num,i);
                    //统计反向图的入度（原图的出度）
                    cnts[i]++;
                }
            }
            //BFS求拓扑排序
            Deque<Integer> dq = new ArrayDeque<>();
            //将所有反向图入度为0的节点加入拓扑序列
            for (int i = 0 ; i < cnts.length; i++){
                //入度为0的加入bfs队列
                if (cnts[i] == 0)dq.add(i);
            }
            while (!dq.isEmpty()){
                int temp = dq.poll();
                for (int i = he[temp]; i !=-1 ; i = ne[i]) {
                    int j= e[i];
                    //减掉当前入度后为0的可以继续加入bfs队列
                    if (--cnts[j]==0)dq.addLast(j);
                }
            }
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (cnts[i] == 0) ans.add(i);
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}