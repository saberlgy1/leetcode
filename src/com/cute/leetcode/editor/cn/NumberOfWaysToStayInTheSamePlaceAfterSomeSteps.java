//有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。 
//
// 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。 
//
// 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。 
//
// 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。 
//
// 
//
// 示例 1： 
//
// 输入：steps = 3, arrLen = 2
//输出：4
//解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
//向右，向左，不动
//不动，向右，向左
//向右，不动，向左
//不动，不动，不动
// 
//
// 示例 2： 
//
// 输入：steps = 2, arrLen = 4
//输出：2
//解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
//向右，向左
//不动，不动
// 
//
// 示例 3： 
//
// 输入：steps = 4, arrLen = 2
//输出：8
// 
//
// 
//
// 提示： 
//
// 
// 1 <= steps <= 500 
// 1 <= arrLen <= 10^6 
// 
// Related Topics 动态规划 
// 👍 102 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class NumberOfWaysToStayInTheSamePlaceAfterSomeSteps {
    public static void main(String[] args) {
        Solution solution = new NumberOfWaysToStayInTheSamePlaceAfterSomeSteps().new Solution();
        System.out.println(solution.numWays(27, 7));
        //System.out.println(solution.numWays(4,2));
        //System.out.println(solution.numWays(2,4));
        System.out.println("127784505");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int mod = (int) 1e9 + 7;
        Map<String, Integer> map = new HashMap<>();

        //这和斐波那契数列有啥区别没太看出来0- 0
        //先试试吧
        //暴力确实超时
        //可能难是难在优化上
        //优化1 map存储结果(逻辑没有问题)
        //读题不认证 方案 要取余
        //优化2 dp
        //定义动态规划方程
        //dp[i][j] 表示 i步操作之后 位于j坐标的方案书
        //分别对应三种做法，i-1原地不动、i- 1 左移， i- 1 右移
        //dp[i][j] = dp[i - 1][j] + dp[i - 1][ j + 1] + dp[i - 1][j - 1];
        //边界条件dp[0][0] = 1
        //当j>=1 切小于等于arr - 1 dp[0][j] = 0
        //j = 0 dp[i-1][j -1] = 0
        //hashmap内部还是会超时
        //持续优化
        //如果index>step/2 就回不来了
        public int numWays(int steps, int arrLen) {
            //corner case
            if (steps == 0 || steps == 1) {
                return 1;
            }

            //dp
            /*int[][] dp = new int[steps + 1][max + 1];
            dp[0][0] = 1;
            int max = Math.min(steps / 2 + 1, arrLen - 1);
            for (int i = 1; i <= steps; i++) {
                //优化4 再度剪枝
                int edge = Math.min(i, max);

                for (int j = 0; j <= edge; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j - 1 >= 0) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % mod;
                    }
                    if (j + 1 <= max) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % mod;
                    }
                }
            }
            return dp[steps][0];*/

            //递归
            return backTrack(steps, 0, arrLen - 1);
        }

        public int backTrack(int steps, int index, int max) {
            long res = 0;
            if (steps == 0) {
                return index == 0 ? 1 : 0;
            }
            /*if (steps < index) {
                map.put(steps + "+" + index, res % mod);
                return res %= mod;
            }*/
            if (map.containsKey(steps + "+" + index)) {
                return map.get(steps + "+" + index);
            }


            res += backTrack(steps - 1, index, max) % mod;
            if (index < max) {
                res += (backTrack(steps - 1, index + 1, max) % mod);
            }
            if (index > 0) {
                res += (backTrack(steps - 1, index - 1, max) % mod);
            }
            res %= mod;
            map.put(steps + "+" + index, (int)res);
            return (int)res;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}