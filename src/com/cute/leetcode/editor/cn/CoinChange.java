//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
// -1。
//
// 你可以认为每种硬币的数量是无限的。
//
//
//
// 示例 1：
//
//
//输入：coins = [1, 2, 5], amount = 11
//输出：3
//解释：11 = 5 + 5 + 1
//
// 示例 2：
//
//
//输入：coins = [2], amount = 3
//输出：-1
//
// 示例 3：
//
//
//输入：coins = [1], amount = 0
//输出：0
//
//
// 示例 4：
//
//
//输入：coins = [1], amount = 1
//输出：1
//
//
// 示例 5：
//
//
//输入：coins = [1], amount = 2
//输出：2
//
//
//
//
// 提示：
//
//
// 1 <= coins.length <= 12
// 1 <= coins[i] <= 231 - 1
// 0 <= amount <= 104
//
// Related Topics 动态规划
// 👍 1302 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class CoinChange {
    public static void main(String[] args) {
        Solution solution = new CoinChange().new Solution();
        int[] coins = new int[]{2, 5, 10, 1};
        System.out.println(solution.coinChange(coins, 11));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：递归+记忆化搜索
        //dfs(S)表示当前S价值需要最少的硬币数
        //dfs(S) = min(dfs(S-c[0-c.lent-1]) + 1)
        //先不考虑优化问题，计算所有结果
        //结果毫无疑问会TLE（O(S^n)）
        /*public int coinChange(int[] coins, int amount) {
            //corner case
            if (amount < 0) {
                return -1;
            }
            if (amount == 0) {
                return 0;
            }
            int min = Integer.MAX_VALUE;
            for (int coin : coins
            ) {
                //当前总数量满足
                int res = coinChange(coins, amount - coin);
                if (res >= 0 && res <= min) {
                    min = res + 1;
                }
            }
            //如果未找到合适res，min并未改变代表无对应方案满足amount装配逻辑，所以返回-1
            return min==Integer.MAX_VALUE ? -1 : min;
        }*/
        //思路二：缓存计算结果（记忆化+dfs）
        //由上述题解可以观察到
        //coinChange其实只和amount有关，并且包含大量重复计算
        //通过map缓存减少计算成本
        /*Map<Integer, Integer> map = new HashMap<>();

        public int coinChange(int[] coins, int amount) {
            //cornre case
            if (amount < 0) {
                return -1;
            }
            return dfs(coins, amount);
        }

        public int dfs(int[] coins, int amount) {

            if (amount < 0) {
                return -1;
            }
            if (amount == 0) {
                return 0;
            }
            if (map.containsKey(amount)) {
                return map.get(amount);
            }
            int min = Integer.MAX_VALUE;
            for (int coin : coins
            ) {
                //当前总数量满足
                int res = dfs(coins, amount - coin);
                if (res >= 0 && res < min) {
                    min = res + 1;
                }
            }
            //如果未找到合适res，min并未改变代表无对应方案满足amount装配逻辑，所以返回-1
            min = min == Integer.MAX_VALUE ? -1 : min;
            map.put(amount, min);
            return map.get(amount);
        }*/
        //思路三：动态规划
        //根据上述递归表达式可以看出可变参数只有一个就是amount
        //所以定义一个dp方程 dp[i]表示当容量为i的时候用到的最小硬币数量
        //dp[i] = min(dp[i-coin[0……j]])+1
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            //corner case
            dp[0] = 0;
            for (int i = 1; i <= amount; i++) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < coins.length; j++) {
                    if (i - coins[j] >= 0 && dp[i - coins[j]] != -1) {
                        min = Math.min(dp[i - coins[j]], min);
                    }
                }
                dp[i] = min == Integer.MAX_VALUE ? -1 : min + 1;
            }
            return dp[amount];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}