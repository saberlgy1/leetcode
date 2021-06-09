//集团里有 n 名员工，他们可以完成各种各样的工作创造利润。 
//
// 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。 
//
// 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。 
//
// 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
//输出：2
//解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
//总的来说，有两种计划。 
//
// 示例 2： 
//
// 
//输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
//输出：7
//解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
//有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。 
// 
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 100 
// 0 <= minProfit <= 100 
// 1 <= group.length <= 100 
// 1 <= group[i] <= 100 
// profit.length == group.length 
// 0 <= profit[i] <= 100 
// 
// Related Topics 动态规划 
// 👍 116 👎 0

package com.cute.leetcode.editor.cn;

public class ProfitableSchemes {
    public static void main(String[] args) {
        Solution solution = new ProfitableSchemes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：背包问题的动态规划解法
        //人力成本可以转化为重量
        //每个答案的效益则为profit数组元素的值
        //最后求出一个target>=min的分配解的数量
        //也就是说一共三个可变参数
        //人数i，利润pro，当前项目g
        //定义dp方程 dp[i][j][k] 表示 在执行前i个任务的时候，使用不超过j的人数，最少不超过k利润的方案数
        //dp[i][j][k] = dp[i-1][j][k] + dp[i-1][j-group[i]][Math.max(0,k - profit[i])]
        //Math.max防止数组越界
        //优化可以考虑降维处理，逆向遍历
        public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
            int len = profit.length, C = 1000000007;
            int[][][] dp = new int[len + 1][n + 1][minProfit + 1];
            //边界条件，表示 在0个项目使用0人利润不低于0的时候方案数有1此
            dp[0][0][0] = 1;
            for (int i = 1; i <= len; i++) {
                for (int j = 0; j <= n; j++){
                    for (int k =0; k <= minProfit; k++){
                        //人数满足可选此项业务做获得利润
                        dp[i][j][k] = dp[i - 1][j][k];
                        if (j >= group[i - 1]){
                            dp[i][j][k] = (dp[i][j][k] + dp[i-1][j-group[i-1]][Math.max(0,k - profit[i-1])])%C;
                        }
                    }
                }
            }
            int sum = 0;
            //遍历使用最多项目，最少利润，使用人数不同的方案
            for (int j = 0; j <= n; j++) {
                sum = (sum + dp[len][j][minProfit]) % C;
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}