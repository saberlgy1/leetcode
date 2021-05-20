//给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。 
//
// 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下
//标从 0 开始计数）执行异或运算得到。 
//
// 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。 
//
// 
//
// 示例 1： 
//
// 输入：matrix = [[5,2],[1,6]], k = 1
//输出：7
//解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。 
//
// 示例 2： 
//
// 输入：matrix = [[5,2],[1,6]], k = 2
//输出：5
//解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。 
//
// 示例 3： 
//
// 输入：matrix = [[5,2],[1,6]], k = 3
//输出：4
//解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。 
//
// 示例 4： 
//
// 输入：matrix = [[5,2],[1,6]], k = 4
//输出：0
//解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 1000 
// 0 <= matrix[i][j] <= 106 
// 1 <= k <= m * n 
// 
// Related Topics 数组 
// 👍 43 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class FindKthLargestXorCoordinateValue {
    public static void main(String[] args) {
        Solution solution = new FindKthLargestXorCoordinateValue().new Solution();
        int[][] matrix = new int[][]{{5, 2}, {1, 6}};
        System.out.println(solution.kthLargestValue(matrix, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int kthLargestValue(int[][] matrix, int k) {
            //动态规划
            //dp[i,j] = dp[i,j-1] ^ dp[i-1,j] ^dp[i-1,j-1]
            //初始化dp[i][j]
            int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
            PriorityQueue<Integer> q = new PriorityQueue<>(k, (a, b)->a - b);
            //维护堆
           // PriorityQueue<Integer> q = new PriorityQueue<>(k, (a, b) -> a - b);
//            dp[0][0] = matrix[0][0];
            for (int i = 1; i <= matrix.length; i++) {
                for (int j = 1; j <= matrix[0].length; j++) {
                    dp[i][j] = dp[i - 1][j] ^ dp[i][j - 1] ^ dp[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                    if (q.size() < k) {
                        q.add(dp[i][j]);
                    } else {
                        if (dp[i][j] > q.peek()) {
                            q.poll();
                            q.add(dp[i][j]);
                        }
                    }

                }
            }
            int m = matrix.length, n = matrix[0].length;
            //int[][] dp = new int[m + 1][n + 1];


            return q.peek();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}