//给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 
//
// 
//
// 
// 
//
// 示例 1: 
//
// 输入: amount = 5, coins = [1, 2, 5]
//输出: 4
//解释: 有四种方式可以凑成总金额:
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
// 
//
// 示例 2: 
//
// 输入: amount = 3, coins = [2]
//输出: 0
//解释: 只用面额2的硬币不能凑成总金额3。
// 
//
// 示例 3: 
//
// 输入: amount = 10, coins = [10] 
//输出: 1
// 
//
// 
//
// 注意: 
//
// 你可以假设： 
//
// 
// 0 <= amount (总金额) <= 5000 
// 1 <= coin (硬币面额) <= 5000 
// 硬币种类不超过 500 种 
// 结果符合 32 位符号整数 
// 
// 👍 426 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;

public class CoinChange2 {
    public static void main(String[] args) {
        Solution solution = new CoinChange2().new Solution();
        int[] coins = new int[]{2, 5, 1};

        System.out.println(solution.change(5, coins));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：递归
        //根据上一道零钱兑换可以求出最小解，同样的也可以求得方案总数
        //当取完硬币后amount = 0 的时候也就可以代表此种方案满足题目要求res+=1
        //也就是我们深度遍历所有可能性，对于所有amount=0的结果进行组合
        //这种方案没有去重
        //可以通过增加排序数进行去重处理（定义一个坐标，只允许往右取硬币，而不许往左取硬币）
        //嘿嘿果然TLE
        /*public int change(int amount, int[] coins) {
            Arrays.sort(coins);
            if (amount < 0) {
                return 0;
            }
            if (amount == 0) {
                return 1;
            }
            return dfs(coins, amount, 0,0);
        }

        public int dfs(int[] coins, int amount, int res, int index) {
            if (amount == 0) {
                return res += 1;
            }
            if (amount < 0) {
                return res;
            }
            for (int i = index; i< coins.length; i++) {
                res = dfs(coins, amount - coins[i], res,i);
            }
            return res;
        }*/
        //思路二：递归+记忆化存储（尝试失败）
        //本题的记忆化存储难点在于每一次的节点数并不是固定的，而且需要对相同方案进行去重处理
        //也就是说记忆化的不应该仅仅是剩余amount还有对于节点的计算
        //可以尝试将两个拼接为key然后进行记忆化存储搭建
        //接下来是证明记忆化存储如何增加到结果集里
        //没有想到如何证明，反而这个思路引导出来了动态规划的思路
        //思路三：动态规划
        //可以根据递归方程发现可变参数有两个，dp[i][j] 表示使用前i个硬币满足j价值的方案数
        //首先定义边界情况当j = 0 的时候 dp[i][0] = 1
        //当i = 0时 dp[0][j] = 0(初始化值)
        //递推方程dp[i][j] = dp[i-1][j] + sum(dp[i-1][j-(j/coins[i-1])*coins[i-1]])
        /*public int change(int amount, int[] coins) {
            int[][] dp = new int[coins.length + 1][amount + 1];
            for (int i = 0; i <= coins.length; i++) {
                dp[i][0] = 1;
            }
            for (int i = 1; i <= coins.length; i++) {
                int val = coins[i - 1];
                for (int j = 0; j <= amount; j++) {
                    dp[i][j] = dp[i - 1][j];
                    for (int k = 1; k <= (j / val); k++) {
                        dp[i][j] += dp[i - 1][j - k * val];
                    }
                }

            }
            return dp[coins.length][amount];
        }*/
        //思路三：动态规划（一维优化）
        //物品维度遍历了k次其实是为了保证所有元素都可以被遍历到我们可以取消j的遍历，而使用value作为遍历处理
        //定义一个dp方程dp[i]表示达成容量为i的方案数
        //dp[i] = sum(dp[i-(val->j)] )
        //方程意义表示为用前i个硬币，分别满足j从0-到amount-coins[i]的数量和
        //也就是计算了所有j-coins[0-length-1]的值然后求和（也就是了减掉所有val*k）
        //推导出如下方程
        //初始化变量dp[0] = 1
        public int change(int amount, int[] coins) {
            int[] dp = new int[amount + 1];
            dp[0] = 1;
            for (int i = 1; i <= coins.length; i++) {
                int val = coins[i - 1];
                for (int j = val; j <= amount; j++) {
                    dp[j] += dp[j - val];
                }
            }
            return dp[amount];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}