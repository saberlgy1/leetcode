//输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
//
// 要求时间复杂度为O(n)。
//
//
//
// 示例1:
//
// 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
//
//
//
// 提示：
//
//
// 1 <= arr.length <= 10^5
// -100 <= arr[i] <= 100
//
//
// 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/
//
//
// Related Topics 数组 分治 动态规划
// 👍 313 👎 0

package com.cute.leetcode.editor.cn;

public class LianXuZiShuZuDeZuiDaHeLcof {
    public static void main(String[] args) {
        Solution solution = new LianXuZiShuZuDeZuiDaHeLcof().new Solution();
        int[] sums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(solution.maxSubArray(sums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：前缀和
        //第一种暴力的方法想到的肯定是前缀和
        //sum[j]-sum[i-1] = a[i]+...+a[j]
        //TLE了
        /*public int maxSubArray(int[] nums) {
            int[] sums = new int[nums.length + 1];
            int sum = 0,max =Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                sums[i + 1] = sum;
            }
            for (int i = 1; i < sums.length; i++) {
                for (int j = i; j < sums.length; j++) {
                    max = Math.max(sums[j] - sums[i-1],max);
                }
            }
            return max;

        }*/
        //思路二：动态规划
        //首先可以确定一个dp思路
        //dp[i] 表示以第i个元素为结尾的最大子元素和
        //j-i号元素的元素和 =
        //dp[i] = Math.max(dp[i-1]+ nums[i], nums[i]);
        //否则dp[i] = dp[i-1]
        /*public int maxSubArray(int[] nums) {
            int[] dp = new int[nums.length + 1];
            int ans = nums[0];
            dp[1] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                //表示前i-1的最大子元素和以及边界到当前位置的元素和
                dp[i + 1] = Math.max(dp[i] + nums[i], nums[i]);
                ans = Math.max(ans, dp[i + 1]);
            }
            return ans;
        }*/
        //思路三：维度优化
        //根据思路二可以发现其实dp[i]只与前一个值有关
        public int maxSubArray(int[] nums) {
            int ans = nums[0], pre = nums[0];
            for (int i = 1; i < nums.length; i++) {
                //表示前i-1的最大子元素和以及边界到当前位置的元素和
                pre = Math.max(pre+ nums[i], nums[i]);
                ans = Math.max(ans, pre);
            }
            return ans;
        }
        //思路四：
    }
//leetcode submit region end(Prohibit modification and deletion)

}