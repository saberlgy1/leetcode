//给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[
//j]) 都应当满足：
//
// answer[i] % answer[j] == 0 ，或
// answer[j] % answer[i] == 0
//
//
// 如果存在多个有效解子集，返回其中任何一个均可。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[1,2]
//解释：[1,3] 也会被视为正确答案。
//
//
// 示例 2：
//
//
//输入：nums = [1,2,4,8]
//输出：[1,2,4,8]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 1000
// 1 <= nums[i] <= 2 * 109
// nums 中的所有整数 互不相同
//
// Related Topics 数学 动态规划
// 👍 220 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset {
    public static void main(String[] args) {
        Solution solution = new LargestDivisibleSubset().new Solution();
        int[] nums = new int[]{4,8,10,240};
        System.out.println(solution.largestDivisibleSubset(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> largestDivisibleSubset(int[] nums) {
            //暴力法 存入hashset 然后 =找到长度最大元素，存入res，我估计会超时
            List<Integer> res = new ArrayList<>();
            //corner case
            if (nums.length == 1) {
                res.add(nums[0]);
                return res;
            }
            //区分几种情况
            //如果有1 一定会进入结果集（不确定是否影响运算）,且如果有1 一定为最小元素
            //先排序（nlgn） 排序后的结果集插入则可只考虑末尾元素是否可被整除
            Arrays.sort(nums);
            //动态规划：
            // 动态方程设计
            // int[] dp = new int[nums.length + 1];
            //dp[i] = dp[i-1] + nums[i] % res.get(res.length() - 1) == 0 ? 1 : 0;
            //res.add(nums[i]);
            int[] dp = new int[nums.length];
           // Arrays.fill(dp,1);
            int maxSize = 1,maxVal = nums[0];
            //确认最大子集数量dp方程
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j <= i; j++){
                    if (nums[i] % nums[j] == 0){
                        dp[i] =  Math.max(dp[i], dp[j] + 1);
                    }
                }
                if (dp[i] > maxSize) {
                    maxSize = dp[i];
                    maxVal = nums[i];
                }
            }
            //我这个思路没啥问题，没想到好的倒推最大子集方案
            //所有元素都无除自身元素外自己
            if (maxSize == 1) {
                res.add(nums[0]);
                return res;
            }
            //倒推数组，找到最大子集位置，然后回推res
            for (int i = nums.length - 1; i >= 0 && maxSize > 0; i--) {
                if (dp[i] == maxSize && maxVal % nums[i] == 0) {
                    res.add(nums[i]);
                    maxVal = nums[i];
                    maxSize--;
                }
            }
            //这种递归其实和暴力法区别不大，都需要做二次校验 n*n+nlg(n)

            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}