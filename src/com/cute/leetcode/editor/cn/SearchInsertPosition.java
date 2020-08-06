//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。 
//
// 你可以假设数组中无重复元素。 
//
// 示例 1: 
//
// 输入: [1,3,5,6], 5
//输出: 2
// 
//
// 示例 2: 
//
// 输入: [1,3,5,6], 2
//输出: 1
// 
//
// 示例 3: 
//
// 输入: [1,3,5,6], 7
//输出: 4
// 
//
// 示例 4: 
//
// 输入: [1,3,5,6], 0
//输出: 0
// 
// Related Topics 数组 二分查找 
// 👍 632 👎 0

package com.cute.leetcode.editor.cn;

public class SearchInsertPosition {
    public static void main(String[] args) {
        Solution solution = new SearchInsertPosition().new Solution();
        int[] nums = new int[]{1, 2, 4, 6, 7};
        System.out.println(solution.searchInsert(nums, 3));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int searchInsert(int[] nums, int target) {
            //二分查找
            int len = nums.length;
            if (len == 0) {
                return 0;
            }
            // 特判
            if (nums[len - 1] < target) {
                return len;
            }
            int left = 0;
            int right = len - 1;
            while (left < right) {
                //防止越界
                int mid = (left + right) / 2;
                // 严格小于 target 的元素一定不是解
                if (nums[mid] < target) {
                    // 下一轮搜索区间是 [mid + 1, right]
                    left = mid + 1;
                } else {
                    // 下一轮搜索区间是 [left, mid]
                    right = mid;
                }
            }
            return left;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}