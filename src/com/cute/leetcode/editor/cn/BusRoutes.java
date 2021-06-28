//给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
//
//
// 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1
//-> ... 这样的车站路线行驶。
//
//
// 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
//
// 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
//
//
//
// 示例 1：
//
//
//输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
//输出：2
//解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
//
//
// 示例 2：
//
//
//输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
//输出：-1
//
//
//
//
// 提示：
//
//
// 1 <= routes.length <= 500.
// 1 <= routes[i].length <= 105
// routes[i] 中的所有值 互不相同
// sum(routes[i].length) <= 105
// 0 <= routes[i][j] < 106
// 0 <= source, target < 106
//
// Related Topics 广度优先搜索 数组 哈希表
// 👍 146 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class BusRoutes {
    public static void main(String[] args) {
        Solution solution = new BusRoutes().new Solution();
        int[][] routes = new int[][]{
                {25,33},
                {3,5,13,22,23,29,37,45,49},
                {15,16,41,47},
                {5,11,17,23,33},
                {10,11,12,29,30,39,45},
                {2,5,23,24,33},
                {1,2,9,19,20,21,23,32,34,44},
                {7,18,23,24},
                {1,2,7,27,36,44},
                {7,14,33}};
        System.out.println(solution.numBusesToDestination(routes, 7, 47));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numBusesToDestination(int[][] routes, int source, int target) {

            //确立所有线路的包含关系
            Map<Integer, Set<Integer>> map = new HashMap<>();
            for (int i = 0; i < routes.length; i++) {
                Set<Integer> set = new HashSet<>();
                for (int j = 0; j < routes[i].length; j++) {
                    set.add(routes[i][j]);
                }
                map.put(i, set);
            }

            //存储线路索引
            Deque<Integer> deque = new LinkedList<>();
            //记录到当前站点的线路数量
            Map<Integer, Integer> res = new HashMap<>();
            //确立初始站台
            for (Integer i : map.keySet()) {
                if (map.get(i).contains(source)) {
                    deque.add(i);
                    res.put(i, 1);
                }
            }
            //corner case
            if (deque.isEmpty()){
                return -1;
            }
            if (target==source){
                return 0;
            }
            while (!deque.isEmpty()) {
                int temp = deque.poll();
                int step = res.get(temp);
                for (int i : map.get(temp)) {
                    if (i == target) {
                        return step;
                    }
                    for (int line : map.keySet()) {
                        //包含当前节点的线路全部加入到deque中继续广度优先遍历
                        if (res.containsKey(line)) {
                            continue;
                        }
                        if (map.get(line).contains(i)) {
                            deque.add(line);
                            res.put(line, step + 1);
                        }
                    }

                }
            }
            return -1;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}