//给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
//
// 二维数组的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些节点，空就是没有下一个结点了。
//
// 译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a 。
//
//
//
// 示例 1：
//
//
//
//
//输入：graph = [[1,2],[3],[3],[]]
//输出：[[0,1,3],[0,2,3]]
//解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
//
//
// 示例 2：
//
//
//
//
//输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
//输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
//
//
// 示例 3：
//
//
//输入：graph = [[1],[]]
//输出：[[0,1]]
//
//
// 示例 4：
//
//
//输入：graph = [[1,2,3],[2],[3],[]]
//输出：[[0,1,2,3],[0,2,3],[0,3]]
//
//
// 示例 5：
//
//
//输入：graph = [[1,3],[2],[3],[]]
//输出：[[0,1,2,3],[0,3]]
//
//
//
//
// 提示：
//
//
// n == graph.length
// 2 <= n <= 15
// 0 <= graph[i][j] < n
// graph[i][j] != i（即，不存在自环）
// graph[i] 中的所有元素 互不相同
// 保证输入为 有向无环图（DAG）
//
// Related Topics 深度优先搜索 广度优先搜索 图 回溯 👍 154 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class AllPathsFromSourceToTarget {
    public static void main(String[] args) {
        Solution solution = new AllPathsFromSourceToTarget().new Solution();
        int[][] graph = new int[][]{{1, 2}, {3}, {3}, {}};
        System.out.println(solution.allPathsSourceTarget(graph));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：dfs
        //这道题没有什么特殊限制整体还是有很多思路的
        //首先dfs遍历
        //Set<List<Integer>> set = new HashSet<>();
        List<List<Integer>> res = new ArrayList<>();
        int[][] g;
        int n;
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            g = graph;
            n = graph.length;
            List<Integer> list = new ArrayList<>();
            dfs(list, 0);
            return res;
        }
        public void dfs(List<Integer> list, int num) {
            list.add(num);
            if (num == n - 1) {
                res.add(list);
                return;
            }
            for (int temp: g[num]
                 ) {
                List<Integer> tempList = new ArrayList<>(list);
                dfs(tempList,temp);
            }
        }
        //思路二：BFS
        //bfs当然也可以解
        /*public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            int n = graph.length;
            Queue<List<Integer>> q = new LinkedList<>();
            q.add(new ArrayList<Integer>() {{
                add(0);
            }});
            List<List<Integer>> res = new ArrayList<>();
            while (!q.isEmpty()) {
                List<Integer> poll = q.poll();
                int temp = poll.get(poll.size() - 1);
                if (temp == n - 1) {
                    res.add(poll);
                } else {
                    for (int val: graph[temp]
                         ) {
                        List<Integer> newList = new ArrayList<>(poll);
                        newList.add(val);
                        q.add(newList);
                    }
                }
            }
            return res;
        }*/
        //思路三：dfs+记忆化
        //dfs的递归毫无疑问是可以做记忆化搜索的
        /*int[][] g;
        int n;
        Map<Integer,List<List<Integer>>> cache = new HashMap<>();
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            g = graph;
            n = graph.length;
            List<Integer> list = new ArrayList<>();
            return dfs(0);
        }
        public List<List<Integer>> dfs( int num) {
            if (cache.containsKey(num)){
                return cache.get(num);
            }
            List<List<Integer>> res = new ArrayList<>();
            if (num == n-1){
                List<Integer> cur = new ArrayList<Integer>(){{add(num);}};
                res.add(cur);
            }else{
                for (int temp: g[num]
                ) {
                    for (List<Integer> next: dfs(temp)
                         ) {
                        List<Integer> cur = new ArrayList<>(next);
                        cur.add(0,num);
                        res.add(cur);
                    }
                }
            }
            cache.put(num,res);
            return res;
        }*/

    }
//leetcode submit region end(Prohibit modification and deletion)

}