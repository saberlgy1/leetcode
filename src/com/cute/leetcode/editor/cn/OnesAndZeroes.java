//给你一个二进制字符串数组 strs 和两个整数 m 和 n 。 
//
// 
// 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。 
//
// 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
//输出：4
//解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
//其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 
//n 的值 3 。
// 
//
// 示例 2： 
//
// 
//输入：strs = ["10", "0", "1"], m = 1, n = 1
//输出：2
//解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 600 
// 1 <= strs[i].length <= 100 
// strs[i] 仅由 '0' 和 '1' 组成 
// 1 <= m, n <= 100 
// 
// Related Topics 动态规划 
// 👍 505 👎 0

package com.cute.leetcode.editor.cn;

public class OnesAndZeroes {
    public static void main(String[] args) {
        Solution solution = new OnesAndZeroes().new Solution();
        String[] strsStrings = new String[]{"10", "0001", "111001", "1", "0"};
        System.out.println(solution.findMaxForm(strsStrings, 5, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：动态规划（三维）
        //难点在于如何将此题转化为背包问题，从而使用动态规划求解
        //本题概念可转化为，每个数组的价值为1 也就是每个背包的价值为1，成本包括两部分组成，一部分为1的数量，一部分为0的数量
        //也就是说可以定义三维数组dp[i][j][k]使用前i个数组元素，在满足不超过j个1和k个0的情况下价值最大化（最大子集）
        //由此可推出dp方程
        //dp[i][j][k] = Math.max(dp[i-1][j][k],dp[i-1][j-cnt0(i)][k-cnt1(i) + 1])
        /*public int findMaxForm(String[] strs, int m, int n) {
            //dp数组
            int[][][] dp = new int[strs.length][m + 1][n + 1];
            //成本数组cnt
            int[][] cnt = new int[strs.length][2];
            for (int i = 0; i < strs.length; i++) {
                int c0 = 0, c1 = 0;

                for (char c : strs[i].toCharArray()
                ) {
                    if (c == '1') {
                        c1 += 1;
                    } else {
                        c0 += 1;
                    }
                }
                cnt[i][0] = c0;
                cnt[i][1] = c1;
            }

            //dp边界情况,只处理其中一件
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    dp[0][i][j] = (cnt[0][0] <= i && cnt[0][1] <= j) ? 1 : 0;
                }
            }
            for (int i = 1; i < strs.length; i++) {
                int[] temp = cnt[i];
                for (int j = 0; j <= m; j++) {
                    for (int k = 0; k <= n; k++) {
                        dp[i][j][k] = dp[i - 1][j][k];
                        int b = (j >= temp[0] && k >= temp[1]) ? dp[i - 1][j - temp[0]][k - temp[1]] + 1 : 0;
                        dp[i][j][k] = Math.max(dp[i][j][k], b);
                    }
                }
            }

            return dp[strs.length - 1][m][n];

        }*/
        //思路二：动态规划（二维）
        //由于只需要知道前一个状态就可以求出当前状态，所以可以使用滚动数组的方式记录
        //也就是说将dp的第一维度变更为2
      /*  public int findMaxForm(String[] strs, int m, int n) {
            //dp数组
            int[][][] dp = new int[2][m + 1][n + 1];
            //成本数组cnt
            int[][] cnt = new int[strs.length][2];
            for (int i = 0; i < strs.length; i++) {
                int c0 = 0, c1 = 0;

                for (char c : strs[i].toCharArray()
                ) {
                    if (c == '1') {
                        c1 += 1;
                    } else {
                        c0 += 1;
                    }
                }
                cnt[i][0] = c0;
                cnt[i][1] = c1;
            }

            //dp边界情况,只处理其中一件
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    dp[0][i][j] = (cnt[0][0] <= i && cnt[0][1] <= j) ? 1 : 0;
                }
            }
            for (int i = 1; i < strs.length; i++) {
                int[] temp = cnt[i];
                for (int j = 0; j <= m; j++) {
                    for (int k = 0; k <= n; k++) {
                        dp[i & 1][j][k] = dp[(i - 1) & 1][j][k];
                        int b = (j >= temp[0] && k >= temp[1]) ? dp[(i - 1) & 1][j - temp[0]][k - temp[1]] + 1 : 0;
                        dp[i & 1][j][k] = Math.max(dp[i & 1][j][k], b);
                    }
                }
            }

            return dp[(strs.length - 1) & 1][m][n];

        }*/
        //思路三：动态规划（一维）
        //从原先的加法遍历，变成减法遍历，可对维度再次进行优化
        public int findMaxForm(String[] strs, int m, int n) {
            //成本数组cnt
            int[][] cnt = new int[strs.length][2];
            for (int i = 0; i < strs.length; i++) {
                int c0 = 0, c1 = 0;

                for (char c : strs[i].toCharArray()
                ) {
                    if (c == '1') {
                        c1 += 1;
                    } else {
                        c0 += 1;
                    }
                }
                cnt[i][0] = c0;
                cnt[i][1] = c1;
            }
            //二维数组，背包问题
            int[][] f = new int[m + 1][n + 1];
            for (int k = 0; k < strs.length; k++) {
                int zero = cnt[k][0], one = cnt[k][1];
                for (int i = m; i >= zero; i--) {
                    for (int j = n; j >= one; j--) {
                        f[i][j] = Math.max(f[i][j], f[i - zero][j - one] + 1);
                    }
                }
            }
            return f[m][n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}