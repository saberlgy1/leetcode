//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 示例: 
//
// 输入: [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
// 
//
// 进阶: 
//
// 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。 
// Related Topics 数组 分治算法 动态规划 
// 👍 2271 👎 0

package com.cute.leetcode.editor.cn;

public class MaximumSubarray {
    public static void main(String[] args) {
        Solution solution = new MaximumSubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray(int[] nums) {
            //O(n) dp 想复杂了 不考虑后面 只考虑前面就可以了
/*            if (nums.length == 0){
                return 0;
            }
            int res = nums[0];
            int sum = 0;
            for (int i: nums
                 ) {
                if (sum > 0 ){
                    sum = sum + i;
                }else{
                    sum = i;
                }
                res = Math.max(sum, res);
            }
            return res;*/
            // 分治法 nlogn 有点复杂

        return processor(nums, 0 , nums.length - 1);

        }

        public int processor(int[] nums, int start, int end){
            if (start == end) {
                // 只有一个元素，也就是递归的结束情况
                return nums[start];
            }

            // 计算中间值
            int center = (start + end) / 2;
            int leftMax = processor(nums, start, center); // 计算左侧子序列最大值
            int rightMax = processor(nums, center + 1, end); // 计算右侧子序列最大值

            // 下面计算横跨两个子序列的最大值

            // 计算包含左侧子序列最后一个元素的子序列最大值
            int leftCrossMax = Integer.MIN_VALUE; // 初始化一个值
            int leftCrossSum = 0;
            for (int i = center ; i >= start ; i --) {
                leftCrossSum += nums[i];
                leftCrossMax = Math.max(leftCrossSum, leftCrossMax);
            }

            // 计算包含右侧子序列最后一个元素的子序列最大值
            int rightCrossMax = nums[center+1];
            int rightCrossSum = 0;
            for (int i = center + 1; i <= end ; i ++) {
                rightCrossSum += nums[i];
                rightCrossMax = Math.max(rightCrossSum, rightCrossMax);
            }

            // 计算跨中心的子序列的最大值
            int crossMax = leftCrossMax + rightCrossMax;

            // 比较三者，返回最大值
            return Math.max(crossMax, Math.max(leftMax, rightMax));
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}