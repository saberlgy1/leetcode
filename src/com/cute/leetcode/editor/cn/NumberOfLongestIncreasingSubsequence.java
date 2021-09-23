//给定一个未排序的整数数组，找到最长递增子序列的个数。 
//
// 示例 1: 
//
// 
//输入: [1,3,5,4,7]
//输出: 2
//解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
// 
//
// 示例 2: 
//
// 
//输入: [2,2,2,2,2]
//输出: 5
//解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
// 
//
// 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。 
// Related Topics 树状数组 线段树 数组 动态规划 👍 431 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;

public class NumberOfLongestIncreasingSubsequence {
    public static void main(String[] args) {
        Solution solution = new NumberOfLongestIncreasingSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //LIS问题
        //动态规划
        //序列dp
        //第一个定义一个dp数组dp[i]记录以nums[i]为结尾的最长子序列长度
        //第二个定义一个cnt数组cnt[i]记录以nums[i]为结尾的最长子序列的个数
        //dp[i]的转移过程分析：
        //初始化dp[0] = 1
        //当nums[i] > nums[j]的时候 dp[i] = dp[j]+1;
        //当nums[i]《<= nums[i-1]的时候 dp[i] = 1
        //同时考虑cnt的转移过程
        //cnt[0] = 1
        //激素i>=1的时候需要根据dp数组进行分类讨论
        //当dp[i]==dp[j]+1 也就是当前i可以作为以j为结尾的更长子序列的新的结尾元素，可以直接加cnt[j] cnt[i] += cnt[j]
        //当 dp[i]<dp[j]+1    当前i不满足以j为结尾的递增子序列 ，直接赋值即可cnt[i] = cnt[j]
        //最后返回dp中最长元素的对应的cnt数量即可
        public int findNumberOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            int max = 1;
            int[] cnt = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                dp[i] = cnt[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        if (dp[i] < dp[j] + 1) {
                            cnt[i] = cnt[j];
                            dp[i] = dp[j] + 1;
                        } else if (dp[i] == dp[j] + 1) {
                            cnt[i] += cnt[j];
                        }
                    }
                }
                max = Math.max(dp[i], max);
            }
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                if (dp[i] == max) {
                    res += cnt[i];
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}