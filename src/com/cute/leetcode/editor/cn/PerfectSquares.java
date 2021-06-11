//给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
//
// 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
//
// 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
//
//
//
//
// 示例 1：
//
//
//输入：n = 12
//输出：3
//解释：12 = 4 + 4 + 4
//
// 示例 2：
//
//
//输入：n = 13
//输出：2
//解释：13 = 4 + 9
//
//
// 提示：
//
//
// 1 <= n <= 104
//
// Related Topics 广度优先搜索 数学 动态规划
// 👍 932 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerfectSquares {
    public static void main(String[] args) {
        Solution solution = new PerfectSquares().new Solution();
        System.out.println(solution.numSquares(12));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
//        思路一：穷举+dfs
//        首先确定完全平方数范围（最大的完全平方数不能超过n）
//        得到所有平方数数组，接下来就转变成了类似（零钱兑换）的背包问题
//        题意转换为 有当前数组重量的物品，每一件物品成本为1，需要达成背包容量n的最小成本，背包数量无限个
//        所以可以先用dfs做
//        毫无疑问这是一个会TLE的做法(用例卡在了50)
        /*public int numSquares(int n) {
            //corner case 已经被题目限制去掉了
            int sum = (int) Math.sqrt(n);
            int[] nums = new int[sum + 1];
            //同时保证了正序数组
            for (int i = 0; i <= sum; i++) {
                nums[i] = i * i;
            }
            int min = Integer.MAX_VALUE;
            return dfs(nums, n, min);
        }

        public int dfs(int[] nums, int n, int min) {
            if (n < 0) {
                return -1;
            }
            if (n == 0) {
                return 0;
            }
            for (int i = 1; i < nums.length; i++) {
                int res = dfs(nums, n - nums[i],min);
                if (res >= 0 && res < min) {
                    min = res + 1;
                }
            }
            return min == Integer.MAX_VALUE? -1 : min;
        }*/
        //思路二：记忆化存储
        //由上述题解可以发现dfs递归函数除了与当前容量n相关外还与min值相关
        //所以key可以设置为min + "_"+ n
        //用力卡在了5156
       /* Map<String, Integer> map = new HashMap<>();
        public int numSquares(int n) {
            //corner case 已经被题目限制去掉了
            int sum = (int) Math.sqrt(n);
            int[] nums = new int[sum + 1];
            //同时保证了正序数组
            for (int i = 0; i <= sum; i++) {
                nums[i] = i * i;
            }
            int min = Integer.MAX_VALUE;
            return dfs(nums, n, min);
        }
        public int dfs(int[] nums, int n, int min) {
            String key = min + "_" + n;
            if (map.containsKey(key)){
                return map.get(key);
            }
            int temp = min;
            if (n < 0) {
                return -1;
            }
            if (n == 0) {
                return 0;
            }
            for (int i = 1; i < nums.length; i++) {
                int res = dfs(nums, n - nums[i],min);
                if (res >= 0 && res < min) {
                    min = res + 1;
                }
            }

            min =  min == Integer.MAX_VALUE? -1 : min;
            map.put(temp + "_" + n,min);
            return min;
        }*/
        //思路三：记忆化存储的优化
        //由上述递归存储发现，min其实和计算结果无相关关系，所以可以不用string转换
        //这次通过了
        /*Map<Integer, Integer> map = new HashMap<>();
        public int numSquares(int n) {
            //corner case 已经被题目限制去掉了
            int sum = (int) Math.sqrt(n);
            int[] nums = new int[sum + 1];
            //同时保证了正序数组
            for (int i = 0; i <= sum; i++) {
                nums[i] = i * i;
            }
            int min = Integer.MAX_VALUE;
            return dfs(nums, n, min);
        }
        public int dfs(int[] nums, int n, int min) {
            if (map.containsKey(n)){
                return map.get(n);
            }

            if (n < 0) {
                return -1;
            }
            if (n == 0) {
                return 0;
            }
            for (int i = 1; i < nums.length; i++) {
                int res = dfs(nums, n - nums[i],min);
                if (res >= 0 && res < min) {
                    min = res + 1;
                }
            }

            min =  min == Integer.MAX_VALUE? -1 : min;
            map.put(n,min);
            return min;
        }*/
        //思路四：动态规划
        //根据递归方程可以发现可变参数有n和i两个参数
        //定义一个dp[i] 表示使用组成i的最小数字数量
        //当i == 0 的时候 dp[0] = 0;
        //因为一定存在1 的完全平方和=1
        //所以当i == 1 的时候 dp[i] = 1;
        //dp[i] = Math.min(dp[i-nums[0,nums.length-1]])
        /*public int numSquares(int n) {
            int sum = (int) Math.sqrt(n);
            int[] nums = new int[sum + 1];
            //同时保证了正序数组
            for (int i = 0; i <= sum; i++) {
                nums[i] = i * i;
            }
            int[] dp = new int[n + 1];
            dp[1] = 1;
            for (int i = 1; i <= n; i++) {
                int min = Integer.MAX_VALUE;
                for (int j = 1; j < nums.length && i >= nums[j]; j++) {
                    min = Math.min(dp[i - nums[j]], min);
                }
                min += 1;
                dp[i] = min;
            }
            return dp[n];
        }*/
        //思路五：数学方法
        //这个方法我是真的不会，能记住就记住吧
        public int numSquares(int n) {
            if (isPerfectSquare(n)) {
                return 1;
            }
            if (checkAnswer4(n)) {
                return 4;
            }
            for (int i = 1; i * i <= n; i++) {
                int j = n - i * i;
                if (isPerfectSquare(j)) {
                    return 2;
                }
            }
            return 3;
        }

        // 判断是否为完全平方数
        public boolean isPerfectSquare(int x) {
            int y = (int) Math.sqrt(x);
            return y * y == x;
        }

        // 判断是否能表示为 4^k*(8m+7)
        public boolean checkAnswer4(int x) {
            while (x % 4 == 0) {
                x /= 4;
            }
            return x % 8 == 7;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}