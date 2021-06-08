//给你一个整数数组 nums 和一个整数 target 。 
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ： 
//
// 
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。 
// 
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], target = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 20 
// 0 <= nums[i] <= 1000 
// 0 <= sum(nums[i]) <= 1000 
// -1000 <= target <= 100 
// 
// Related Topics 深度优先搜索 动态规划 
// 👍 795 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class TargetSum {
    public static void main(String[] args) {
        Solution solution = new TargetSum().new Solution();
        int[] nums = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1};
        System.out.println(solution.findTargetSumWays(nums, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路1
        //递推为0，每一次只有两种运算结果，所以可以考虑像二叉树深度优先从结果倒推最后求出子节点为0的情况
        //时间复杂度2^nums.length
        /*int res = 0;

        public int findTargetSumWays(int[] nums, int target) {
            int n = nums.length - 1;
            return backTrack(nums, target, n);
        }

        private int backTrack(int[] nums, int target, int n) {
            if (n == 0) {
                if (target - nums[0] == 0) {
                    res += 1;
                }
                if (target + nums[0] == 0) {
                    res += 1;
                }
                return res;
            }
            backTrack(nums, target - nums[n], n - 1);
            backTrack(nums, target + nums[n], n - 1);
            return res;
        }*/
        //思路二：优化一
        //上述递归过程可以创建一个map存储，类似斐波那契数列的优化过程，减少运算次数
        //时间复杂度2^nums.length
       /* int res = 0;
        Map<String, Integer> map = new HashMap<>();

        public int findTargetSumWays(int[] nums, int target) {
            return backTrack(nums, target, 0, 0);
        }

        private int backTrack( int[] nums, int target,int cur, int n) {
            if (map.containsKey(cur + "+" + n)) {
                return map.get(cur + "+" + n);
            }
            if (n == nums.length) {
                map.put(cur + "+" + n, cur == target ? 1 : 0);
                return map.get(cur + "+" + n);
            }
            int left = backTrack( nums, target,cur - nums[n], n + 1);
            int right = backTrack( nums, target,cur + nums[n],n + 1);
            map.put(cur + "+" + n, left+ right);
            return  map.get(cur + "+" + n);
        }*/
        //思路三：动态规划，根据思路一的递归参数确定dp方程
        //可变参数 n,target
        //定义一个dp[i][j] i <= n
        //最后求得dp[n][target]
        //dp[0][0] = 1;
        //dp[i][j] = dp[i-1][j-nums[i-1]] + dp[i-1][j+nums[i-1]]
        //i的大小范围很好界定nums.length
        //target 的范围界定则需要判断所有数组和了，也就是说 sum >= target >= -sum
        /*public int findTargetSumWays(int[] nums, int target) {
            int sum = 0, n = nums.length;
            for (int i : nums
            ) {
                sum += i;
            }
            //cornrer case
            if (target > sum) {
                return 0;
            }
            int[][] dp = new int[n + 1][2 * sum + 1];
            //target可能为负数，所以为了dp方便， y轴右移sum位，划分出2sum + 1的空间
            dp[0][sum] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = -sum; j <= sum; j++) {
                    if ((j - nums[i - 1]) + sum >= 0) {
                        dp[i][j + sum] += dp[i - 1][(j - nums[i - 1]) + sum];
                    }
                    if ((j + nums[i - 1]) + sum <= 2 * sum) {
                        dp[i][j + sum] += dp[i - 1][(j + nums[i - 1]) + sum];
                    }
                }
            }
            return dp[n][target+sum];
        }*/
        //思路四：问题转换+动态规划
        //把所有元素变为绝对值，然后负数就变成了减法
        //数组每个值求和为sum，减掉[m]之后为target也就是说
        //sum - m -m = target
        //m = (sum - target)/2
        //最后就变成了只是用加法
        //dp方程还是如上定义
        //dp[i][j] += dp[i - 1][j - temp];
        public int findTargetSumWays(int[] nums, int target) {
            int n = nums.length, sum = 0;
            for (int i : nums
            ) {
                sum += Math.abs(i);
            }
            //corner case
            if (target > sum || (sum - target) % 2 != 0) {
                return 0;
            }
            int m = (sum - target) / 2;
            int[][] dp = new int[n + 1][m + 1];
            //初始状态
            dp[0][0] = 1;
            for (int i = 1; i <= n; i++) {
                int temp = nums[i - 1];
                for (int j = 0; j <= m; j++) {
                    //存储之前结果
                    dp[i][j] += dp[i - 1][j];
                    if (j >= temp) {
                        dp[i][j] += dp[i - 1][j - temp];
                    }
                }
            }
            return dp[n][m];

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}