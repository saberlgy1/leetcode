//给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。 
//
// 请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。 
//
// 如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。 
//
// 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。 
//
// 
//
// 示例 1： 
//
// 
//输入：mat = 
//[[1,1,0,0,0],
// [1,1,1,1,0],
// [1,0,0,0,0],
// [1,1,0,0,0],
// [1,1,1,1,1]], 
//k = 3
//输出：[2,0,3]
//解释：
//每行中的军人数目：
//行 0 -> 2 
//行 1 -> 4 
//行 2 -> 1 
//行 3 -> 2 
//行 4 -> 5 
//从最弱到最强对这些行排序后得到 [2,0,3,1,4]
// 
//
// 示例 2： 
//
// 
//输入：mat = 
//[[1,0,0,0],
// [1,1,1,1],
// [1,0,0,0],
// [1,0,0,0]], 
//k = 2
//输出：[0,2]
//解释： 
//每行中的军人数目：
//行 0 -> 1 
//行 1 -> 4 
//行 2 -> 1 
//行 3 -> 1 
//从最弱到最强对这些行排序后得到 [0,2,3,1]
// 
//
// 
//
// 提示： 
//
// 
// m == mat.length 
// n == mat[i].length 
// 2 <= n, m <= 100 
// 1 <= k <= m 
// matrix[i][j] 不是 0 就是 1 
// 
// Related Topics 数组 二分查找 矩阵 排序 堆（优先队列） 
// 👍 70 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class TheKWeakestRowsInAMatrix {
    public static void main(String[] args) {
        Solution solution = new TheKWeakestRowsInAMatrix().new Solution();
        int[][] mat = new int[][]{{1, 1, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 1, 1, 1, 1}};
        System.out.println(solution.kWeakestRows(mat, 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力扫描
        //获取每行战斗之排序取前k个即可
        public int[] kWeakestRows(int[][] mat, int k) {
            Map<Integer, PriorityQueue<Integer>> map = new TreeMap<>((a, b) -> a - b);
            for (int i = 0; i < mat.length; i++) {
                int j = 0, temp = 0;
                while (j < mat[i].length && mat[i][j++] == 1) {
                    temp++;
                }
                PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                });
                priorityQueue = map.getOrDefault(temp, priorityQueue);
                priorityQueue.add(i);
                map.put(temp, priorityQueue);
            }
            int[] res = new int[k];
            int i = 0;
            for (int key : map.keySet()
            ) {
                while (i < k && !map.get(key).isEmpty()) {
                    res[i++] = map.get(key).poll();
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}