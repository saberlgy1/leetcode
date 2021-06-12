//给你一个整数数组 cost 和一个整数 target 。请你返回满足如下规则可以得到的 最大 整数：
//
//
// 给当前结果添加一个数位（i + 1）的成本为 cost[i] （cost 数组下标从 0 开始）。
// 总成本必须恰好等于 target 。
// 添加的数位中没有数字 0 。
//
//
// 由于答案可能会很大，请你以字符串形式返回。
//
// 如果按照上述要求无法得到任何整数，请你返回 "0" 。
//
//
//
// 示例 1：
//
//
//输入：cost = [4,3,2,5,6,7,2,5,5], target = 9
//输出："7772"
//解释：添加数位 '7' 的成本为 2 ，添加数位 '2' 的成本为 3 。所以 "7772" 的代价为 2*3+ 3*1 = 9 。 "977" 也是满足要
//求的数字，但 "7772" 是较大的数字。
// 数字     成本
//  1  ->   4
//  2  ->   3
//  3  ->   2
//  4  ->   5
//  5  ->   6
//  6  ->   7
//  7  ->   2
//  8  ->   5
//  9  ->   5
//
//
// 示例 2：
//
//
//输入：cost = [7,6,5,5,5,6,8,7,8], target = 12
//输出："85"
//解释：添加数位 '8' 的成本是 7 ，添加数位 '5' 的成本是 5 。"85" 的成本为 7 + 5 = 12 。
//
//
// 示例 3：
//
//
//输入：cost = [2,4,6,2,4,6,4,4,4], target = 5
//输出："0"
//解释：总成本是 target 的条件下，无法生成任何整数。
//
//
// 示例 4：
//
//
//输入：cost = [6,10,15,40,40,40,40,40,40], target = 47
//输出："32211"
//
//
//
//
// 提示：
//
//
// cost.length == 9
// 1 <= cost[i] <= 5000
// 1 <= target <= 5000
//
// Related Topics 字符串 动态规划
// 👍 65 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;

public class FormLargestIntegerWithDigitsThatAddUpToTarget {
    public static void main(String[] args) {
        Solution solution = new FormLargestIntegerWithDigitsThatAddUpToTarget().new Solution();
        int[] cost = new int[]{1, 1, 1, 1, 1, 1, 1, 3, 2};
        int[] cost1 = new int[]{7, 6, 5, 5, 5, 6, 8, 7, 8};
        int[] cost2 = new int[]{2, 4, 6, 2, 4, 6, 4, 4, 4};
        int[] cost3 = new int[]{6, 10, 15, 40, 40, 40, 40, 40, 40};
        int[] cost4 = new int[]{4, 3, 2, 5, 6, 7, 2, 5, 5};
        int[] cost5 = new int[]{5, 6, 7, 3, 4, 6, 7, 4, 8};

        System.out.println(solution.largestNumber(cost, 10));
        System.out.println(solution.largestNumber(cost1, 12));
        System.out.println(solution.largestNumber(cost2, 5));
        System.out.println(solution.largestNumber(cost3, 47));
        System.out.println(solution.largestNumber(cost4, 9));
        System.out.println(solution.largestNumber(cost5, 29));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：
        //根据题意：得到数位较大，则位数要求越多越好，相同位数情况下满足物品索引越大越好
        //位数要求越多，可以转换成一个背包问题
        //即每一个物品的价值为1，容量为数组元素值，找到能满足背包容量的最多元素解（每一个物品均只有一个）
        //求解方案可以参照背包问题写入动态规划
        //定义dp方程dp[i]表示满足j容量的最多元素解
        //dp[i] = max(dp[i-cost[0->cost.length] + 1])
        public String largestNumber(int[] cost, int target) {
            int[] dp = new int[target + 1];
            Arrays.fill(dp, Integer.MIN_VALUE);
            dp[0] = 0;
            for (int i = 1; i < 10; i++) {
                int val = cost[i - 1];
                for (int j = val; j <= target; j++) {
                    dp[j] = Math.max(dp[j], dp[j - val] + 1);
                }
            }
            if (dp[target] < 0) {
                return "0";
            }
            StringBuilder ans = new StringBuilder();
            //这个思路真的很好，通过dp值反推一定是当前最优解 遍历所有值 val dp[j] == dp[j - u] + 1 这个条件满足了必是获得最优解的条件
            for (int i = 9, j = target; i >= 1; i--) {
                int val = cost[i - 1];
                while (j >= val && dp[j] == dp[j - val] + 1) {
                    ans.append(String.valueOf(i));
                    j -= val;
                }
            }
            return ans.toString();

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}