//夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
//
// 商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。Tony 一共有 coin
//s 现金可以用于消费，他想要买尽可能多的雪糕。
//
// 给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。
//
// 注意：Tony 可以按任意顺序购买雪糕。
//
//
//
// 示例 1：
//
// 输入：costs = [1,3,2,4,1], coins = 7
//输出：4
//解释：Tony 可以买下标为 0、1、2、4 的雪糕，总价为 1 + 3 + 2 + 1 = 7
//
//
// 示例 2：
//
// 输入：costs = [10,6,8,7,7,8], coins = 5
//输出：0
//解释：Tony 没有足够的钱买任何一支雪糕。
//
//
// 示例 3：
//
// 输入：costs = [1,6,3,1,2,5], coins = 20
//输出：6
//解释：Tony 可以买下所有的雪糕，总价为 1 + 6 + 3 + 1 + 2 + 5 = 18 。
//
//
//
//
// 提示：
//
//
// costs.length == n
// 1 <= n <= 105
// 1 <= costs[i] <= 105
// 1 <= coins <= 108
//
// Related Topics 贪心 数组 排序
// 👍 23 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaximumIceCreamBars {
    public static void main(String[] args) {
        Solution solution = new MaximumIceCreamBars().new Solution();
        int[] costs = new int[]{1, 3, 2, 4, 1};
        System.out.println(solution.maxIceCream(costs, 7));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：排序，排序后取到前n个最小值和小于coins即可即可
        /*public int maxIceCream(int[] costs, int coins) {
            Arrays.sort(costs);
            if (costs[0] > coins) {
                return 0;
            }
            int sum = 0, res = 0;
            for (int i = 0; i < costs.length; i++) {
                if (sum + costs[i] <= coins) {
                    sum += costs[i];
                    res++;
                }
            }
            return res;
        }*/
        //思路二：动态规划
        //本题思路可以很快速的转变为背包问题
        //题目转换为，存在容量为coins的背包，每个物品的重量为costs数组对应的值
        //每个物品价值为1，求出满足背包容量前提下放入物品的最大价值
        //定义dp方程 dp[i][j]  表示在取前i个物品满足容量小于等于j的前提下取到的物品的最多数量
        //可以发现dp[i][j]只和dp[i-1][j-cost[i]]相关，如果
        //同时需要记录当前容量占用情况
        //运算发现，这种因为物品的数量过多，相对于第一种贪心排序反而会TLE
        public int maxIceCream(int[] costs, int coins) {
            int[][] dp = new int[costs.length + 1][coins + 1];
            for (int i = 1; i <= costs.length; i++) {
                int weight = costs[i - 1];
                for (int j = 0; j <= coins; j++) {
                    dp[i][j] = j >= weight ? Math.max(dp[i - 1][j - weight] + 1, dp[i - 1][j]) : dp[i - 1][j];
                }
            }
            return dp[costs.length][coins];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}