//写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下： 
//
// 
//F(0) = 0,   F(1) = 1
//F(N) = F(N - 1) + F(N - 2), 其中 N > 1. 
//
// 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。 
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 2
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：n = 5
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 100 
// 
// Related Topics 记忆化搜索 数学 动态规划 👍 195 👎 0

package com.cute.leetcode.editor.cn;

public class FeiBoNaQiShuLieLcof {
    public static void main(String[] args) {
        Solution solution = new FeiBoNaQiShuLieLcof().new Solution();
        System.out.println(solution.fib(48));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：动态规划
        /*int[] f = new int[]{0, 1};
        int mod = (int) 1e9 + 7;

        public int fib(int n) {
            if (n < 2) {
                return f[n];
            }
            for (int i = 2; i <= n; i++) {
                f[i & 1] = (f[0] + f[1]) % mod;
            }
            return f[n & 1] % mod;
        }*/

        int mod = (int)1e9+7;
        long[][] mul(long[][] a, long[][] b) {
            int r = a.length, c = b[0].length, z = b.length;
            long[][] ans = new long[r][c];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    for (int k = 0; k < z; k++) {
                        ans[i][j] += a[i][k] * b[k][j];
                        ans[i][j] %= mod;
                    }
                }
            }
            return ans;
        }
        public int fib(int n) {
            if (n <= 1) return n;
            long[][] mat = new long[][]{
                    {1, 1},
                    {1, 0}
            };
            long[][] ans = new long[][]{
                    {1},
                    {0}
            };
            int x = n - 1;
            while (x != 0) {
                if ((x & 1) != 0) ans = mul(mat, ans);
                mat = mul(mat, mat);
                x >>= 1;
            }
            return (int)(ans[0][0] % mod);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}