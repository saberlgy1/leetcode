//给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。 
//
// 
//
// 示例 1: 
//
// 输入: [1,2,0]
//输出: 3
// 
//
// 示例 2: 
//
// 输入: [3,4,-1,1]
//输出: 2
// 
//
// 示例 3: 
//
// 输入: [7,8,9,11,12]
//输出: 1
// 
//
// 
//
// 提示： 
//
// 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。 
// Related Topics 数组 
// 👍 754 👎 0

package com.cute.leetcode.editor.cn;

public class FirstMissingPositive {
    public static void main(String[] args) {
        Solution solution = new FirstMissingPositive().new Solution();
        int[] nums = new int[]{-3,9,16,4,5,16,-4,9,26,2,1,19,-1,25,7,22,2,-7,14,2,5,-6,1,17,3,24,-4,17,15};
        System.out.println(solution.firstMissingPositive(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int firstMissingPositive(int[] nums) {
            if (nums.length == 0) {
                return 1;
            }
            //学到了 in-place
            //遍历第一次 大小放进对应数组位置的地方
            //遍历第二次找到第一个位置与序号不同的输出
            for (int i = 0; i < nums.length; i++) {
                while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                    // 满足在指定范围内、并且没有放在正确的位置上，才交换
                    // 例如：数值 3 应该放在索引 2 的位置上
                    swap(nums, nums[i] - 1, i);
                }

            }
            for(int i = 0; i< nums.length; i++){
                if (i + 1 !=nums[i]){
                    return i+1;
                }
            }
            return nums.length + 1;
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}