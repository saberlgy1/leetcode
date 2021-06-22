//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
// Related Topics 动态规划 
// 👍 841 👎 0

package com.cute.leetcode.editor.catagory.dp.abstractpackage;

public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        Solution solution = new PartitionEqualSubsetSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
       /*public boolean canPartition(int[] nums) {
            int total = 0;
            for (int n : nums
            ) {
                total += n;
            }
            //corner case
            if (total % 2 != 0) {
                return false;
            }
            int target = total / 2 + 1;
            int[][] dp = new int[nums.length][target];
            //初始化边界情况,只取第一个元素的所有情况
            for (int i = 0; i < target; i++) {
                dp[0][i] = nums[0] < i ? nums[0] : 0;
            }
            for (int i = 1; i < nums.length; i++) {
                int val = nums[i];
                for (int j = 0; j < target; j++) {
                    //选或者不选的两种状态
                    dp[i][j] = Math.max(dp[i - 1][j], j >= val ? dp[i - 1][j - val] + val : 0);
                }
            }
            return dp[nums.length - 1][target - 1] == target - 1;
        }*/

        /*public boolean canPartition(int[] nums) {
            int total = 0;
            for (int n : nums
            ) {
                total += n;
            }
            //corner case
            if (total % 2 != 0) {
                return false;
            }
            int target = total / 2 + 1;
            int[][] dp = new int[2][target];
            //初始化边界情况,只取第一个元素的所有情况
            for (int i = 0; i < target; i++) {
                dp[0][i] = nums[0] < i ? nums[0] : 0;
            }
            for (int i = 1; i < nums.length; i++) {
                int val = nums[i];
                for (int j = 0; j < target; j++) {
                    //选或者不选的两种状态
                    dp[i & 1][j] = Math.max(dp[(i - 1) & 1][j], j >= val ? dp[(i - 1) & 1][j - val] + val : 0);
                }
            }
            return dp[(nums.length-1)&1][target - 1] == target - 1;
        }*/
        public boolean canPartition(int[] nums) {
            int total = 0;
            for (int n : nums
            ) {
                total += n;
            }
            //corner case
            if (total % 2 != 0) {
                return false;
            }
            int target = total / 2 + 1;
            int[] dp = new int[target];
            for (int i = 0; i < nums.length; i++) {
                int val = nums[i];
                for (int j = target - 1; j >= 0; j--) {
                    int no = dp[j];
                    // 选第 i 件物品
                    int yes = j >= val ? dp[j - val] + val : 0;
                    dp[j] = Math.max(no, yes);
                }
            }
            return dp[target - 1] == target - 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}