//给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元格内（可以
//穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。 
//
// 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 
//109 + 7 取余 后的结果。 
//
// 
//
// 示例 1： 
//
// 
//输入：m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
//输出：6
// 
//
// 示例 2： 
//
// 
//输入：m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// 1 <= m, n <= 50 
// 0 <= maxMove <= 50 
// 0 <= startRow < m 
// 0 <= startColumn < n 
// 
// Related Topics 动态规划 
// 👍 139 👎 0

package com.cute.leetcode.editor.cn;

public class OutOfBoundaryPaths {
    public static void main(String[] args) {
        Solution solution = new OutOfBoundaryPaths().new Solution();
        System.out.println(solution.findPaths(1, 3, 3, 0, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：dfs +暴力递归
        //时间复杂度$4^{m}$
        //TLE了
        int[][] dir = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        int res = 0, max = 0, ex = 0, ey = 0;

        /*public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
            //定义四个移动方向
            int mod = (int) 1e10 + 7, x = startRow, y = startColumn, idx = 0;
            max = maxMove;
            ex = m;
            ey = n;
            dfs(x, y, idx);
            return res % mod;
        }

        public void dfs(int x, int y, int idx) {
            //边界情况
            if (idx >= max) {
                return;
            }
            int temp = idx;
            for (int i = 0; i < 4; i++) {
                if (x + dir[i][0] >= ex || x + dir[i][0] < 0 || y + dir[i][1] >= ey || y + dir[i][1] < 0) {
                    res += 1;
                    continue;
                }
                dfs(x + dir[i][0], y + dir[i][1], temp + 1);
            }
        }*/
        //思路二：动态规划
        //看来这道题对于时间复杂度还是有限制的
        //我们可以发现思路一的递归函数有三个变量x,y,idx
        //其中idx是标记移动步数
        //x，y是初始的移动坐标
        //我们可以定义一个dp方程
        //dp[idx][j][k] 表示移动idx次从x,y使用idx步数可以移动到i,j坐标的方案数
        //dp[0][x][y] = 1;其余情况dp[0][j][k] = 0;
        //dp[idx+1][j`][k`] = dp[idx][j-1][k] + dp[idx][j+1][k] + dp[idx][j][k-1] + dp[idx][j][k+1];

        public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
            int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
            int x = startRow, y = startColumn, mod = (int) 1e9 + 7;
            int res = 0;
            int[][][] dp = new int[maxMove + 1][m][n];
            //初始化
            dp[0][x][y] = 1;
            for (int i = 0; i < maxMove; i++) {
                for (int j = 0; j < m; j++) {
                    for (int k = 0; k < n; k++) {
                        int cnt = dp[i][j][k];
                        if (cnt > 0) {
                            for (int[] dir : dirs
                            ) {
                                int mx = j + dir[0], my = k + dir[1];
                                if (mx >= 0 && mx < m && my >= 0 && my < n) {
                                    dp[i + 1][mx][my] = (dp[i + 1][mx][my] + cnt) % mod;
                                } else {
                                    res = (res + cnt) % mod;
                                }
                            }
                        }
                    }
                }
            }
            return res;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}