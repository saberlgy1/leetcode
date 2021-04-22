//给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。 
//
// 题目数据保证总会存在一个数值和不超过 k 的矩形区域。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,0,1],[0,-2,3]], k = 2
//输出：2
//解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[2,2,-1]], k = 3
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 100 
// -100 <= matrix[i][j] <= 100 
// -105 <= k <= 105 
// 
//
// 
//
// 进阶：如果行数远大于列数，该如何设计解决方案？ 
// Related Topics 队列 二分查找 动态规划 
// 👍 211 👎 0

package com.cute.leetcode.editor.cn;

public class MaxSumOfRectangleNoLargerThanK {
    public static void main(String[] args) {
        Solution solution = new MaxSumOfRectangleNoLargerThanK().new Solution();
        // int[][] matrix = new int[][]{{1, 0, 1}, {0, -2, 3}};
        int[][] matrix = new int[][]{{2, 2, -1}};
        System.out.println(solution.maxSumSubmatrix(matrix, 0));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSumSubmatrix(int[][] matrix, int k) {


            //听说暴力java不会超时，那就先暴力吧
            int m = matrix.length, n = matrix[0].length;
            //数组大小变大了，所以扫描需要-1
            int[][] sum = new int[m + 1][n + 1];
            //每个位置求其到左上角的和
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }
            //开始查找最小值
            //四层for循环。前两层遍历ij位置总大小，后两层遍历删掉[m,n]到[i,j]的矩形大小
            int max = Integer.MIN_VALUE;
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    for (int l = 1; l <= i; l++) {
                        for (int r = 1; r <= j; r++) {
                            int temp = sum[i][j] - sum[i][r - 1] - sum[l - 1][j] + sum[l - 1][r - 1];
                            if (temp <= k) {
                                max = Math.max(temp, max);
                            }
                        }
                    }
                }
            }
            return max;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}