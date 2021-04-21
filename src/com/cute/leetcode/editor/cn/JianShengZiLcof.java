//给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
//请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18
//。
//
// 示例 1：
//
// 输入: 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1
//
// 示例 2:
//
// 输入: 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
//
// 提示：
//
//
// 2 <= n <= 58
//
//
// 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/
// Related Topics 数学 动态规划
// 👍 216 👎 0

package com.cute.leetcode.editor.cn;

public class JianShengZiLcof {
    public static void main(String[] args) {
        Solution solution = new JianShengZiLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int cuttingRope(int n) {
            if (n == 2){
                return 1;
            }
            if (n == 3){
                return 2;
            }
            int[] dp = new int[n+1];
            dp[2] = 1;
            dp[3] = 2;
            //先看题解是看晕了
            //这应该是类似于数学推理的办法
/*        if(n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if(b == 0) return (int)Math.pow(3, a);
        if(b == 1) return (int)Math.pow(3, a - 1) * 4;
        return (int)Math.pow(3, a) * 2;*/
            //咱还是dp吧
            //dp方程，剪掉一段长度和剩余长度dp值做乘法
            //所以 两段迭代，第一段迭代所有长度，第二段迭代所有剪掉长度
            //j为截断长度
            //边界 dp[2] = 1 dp[3] = 2;
            //状态转移方程 dp[n] = Math.max(dp[n], j * dp[n - j], j(n - j))
            for (int i = 3; i < n+1; i++) {
                for (int j = 2 ; j < i; j++){
                    dp[i] = Math.max(Math.max(j* dp[i-j], dp[i]), j* (i - j));

                }
            }
            return dp[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}