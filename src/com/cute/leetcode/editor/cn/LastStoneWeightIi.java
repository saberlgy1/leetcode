//有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。 
//
// 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下： 
//
// 
// 如果 x == y，那么两块石头都会被完全粉碎； 
// 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。 
// 
//
// 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。 
//
// 
//
// 示例 1： 
//
// 
//输入：stones = [2,7,4,1,8,1]
//输出：1
//解释：
//组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
//组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
//组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
//组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
// 
//
// 示例 2： 
//
// 
//输入：stones = [31,26,33,21,40]
//输出：5
// 
//
// 示例 3： 
//
// 
//输入：stones = [1,2]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= stones.length <= 30 
// 1 <= stones[i] <= 100 
// 
// Related Topics 动态规划 
// 👍 214 👎 0

package com.cute.leetcode.editor.cn;

import java.util.PriorityQueue;

public class LastStoneWeightIi {
    public static void main(String[] args) {
        Solution solution = new LastStoneWeightIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一:动态规划
        //题目分析，从正整数数组中找到和差target的最小值
        //根据leetcode-494-目标和 可以思考得出
        //和差值即为绝对值的最小差值
        //也就是说将整体数组平分，获得的总和不超过数组所有元素和/2的最大平分数组和
        //也就是说又转换成了背包问题
        //假设寻找到的决策结果为res,元素绝对值总和为sum，最后求得的最小价值差，也就是结果 = sum - res - res
        //定义一个dp方程
        //dp[i][j]表示前i个元素，凑成绝对值j的最大价值（边界范围sum/2）
        public int lastStoneWeightII(int[] stones) {
            int sum = 0, n = stones.length;

            //corner case
            if (n == 1) {
                return stones[0];
            }
            //求数组所有元素和
            for (int i : stones
            ) {
                sum += i;
            }
            int edge = sum / 2 + 1;
            int[][] dp = new int[n + 1][edge];
            for (int i = 1; i <= n; i++) {
                int temp = stones[i - 1];
                for (int j = 0; j < edge; j++) {
                    //记录数组
                    dp[i][j] = dp[i - 1][j];
                    if (j >= temp) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - temp] + temp);
                    }
                }
            }
            return Math.abs(sum - 2 * dp[n][edge-1]);

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}