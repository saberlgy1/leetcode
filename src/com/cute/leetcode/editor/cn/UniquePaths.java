//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。 
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
//
// 问总共有多少条不同的路径？ 
//
// 
//
// 例如，上图是一个7 x 3 的网格。有多少可能的路径？ 
//
// 
//
// 示例 1: 
//
// 输入: m = 3, n = 2
//输出: 3
//解释:
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向右 -> 向下
//2. 向右 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向右
// 
//
// 示例 2: 
//
// 输入: m = 7, n = 3
//输出: 28 
//
// 
//
// 提示： 
//
// 
// 1 <= m, n <= 100 
// 题目数据保证答案小于等于 2 * 10 ^ 9 
// 
// Related Topics 数组 动态规划 
// 👍 687 👎 0

package com.cute.leetcode.editor.cn;

public class UniquePaths {
    public static void main(String[] args) {
        Solution solution = new UniquePaths().new Solution();
        System.out.println(solution.factorial(23));
        System.out.println(solution.uniquePaths(23, 12));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int uniquePaths(int m, int n) {
            //方法一排列组合 一共m-1+n-1个元素，取m-1 或者n-1 个位置
            //corner case 阶乘算晚边界回越界
            //return combination(n - 1, m - 1 + n - 1);
            //return C(Math.min(m-1,n-1),m+n-2);
            //方法2 动态规划dp
            //dp需要考虑边界情况以及递推规律
            //边界情况 在同列或者同行
            int[][] dp = new int[m][n];
            //第一列都是1
            for (int i = 0; i < m; i++) {
                dp[i][0] = 1;
            }
            //第一行都是1
            for (int i = 0; i < n; i++) {
                dp[0][i] = 1;
            }
            for(int i = 1; i < m; i++){
                for (int j = 1; j < n ; j++){
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
            return dp[m-1][n-1];

        }

        public int C(int up, int down){
            long son = 1, parent = 1;
            for (int i = 1 ; i <= up; i++){
                son *= i;
            }
            for (int j = down - up + 1; j <= down; j++){
                parent *= j;
            }
            return (int) (parent / son);
        }


        /**
         * 排列组合
         * 要考虑整型越界
         * @param m
         * @param n
         * @return
         */
        public  int combination(int m, int n) {
            if (m < 0 || n < 0) {
                return 0;
            }
            if (m > n) {
                return combination(n, m);
            }

            return (int)( factorial(n)/factorial(n - m )/ factorial(m));
        }

        /**
         * 阶乘
         *
         * @param i
         * @return
         */
        public  long factorial(long i) {
            if (i == 0 || i == 1) {
                return 1;
            }
            return i * factorial(i - 1);
        }


    }




//leetcode submit region end(Prohibit modification and deletion)

}