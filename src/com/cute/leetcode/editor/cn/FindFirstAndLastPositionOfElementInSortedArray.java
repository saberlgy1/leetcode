//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 你的算法时间复杂度必须是 O(log n) 级别。 
//
// 如果数组中不存在目标值，返回 [-1, -1]。 
//
// 示例 1: 
//
// 输入: nums = [5,7,7,8,8,10], target = 8
//输出: [3,4] 
//
// 示例 2: 
//
// 输入: nums = [5,7,7,8,8,10], target = 6
//输出: [-1,-1] 
// Related Topics 数组 二分查找 
// 👍 569 👎 0

package com.cute.leetcode.editor.cn;

public class FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        Solution solution = new FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        int[] nums = new int[]{1,3};
        for (int i : solution.searchRange(nums, 3)) {
            System.out.println(i);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            //题的含义是开始位置和结束为止
//            int len = nums.length;
//            //corner case
//            if (len == 0) {
//                return new int[]{-1, -1};
//            }
//            return findTargetFlag(nums, target, 0, len - 1);
            int[] targetRange = {-1, -1};

            int leftIdx = extremeInsertionIndex(nums, target, true);

            // assert that `leftIdx` is within the array bounds and that `target`
            // is actually in `nums`.
            if (leftIdx == nums.length || nums[leftIdx] != target) {
                return targetRange;
            }

            targetRange[0] = leftIdx;
            targetRange[1] = extremeInsertionIndex(nums, target, false)-1;

            return targetRange;


        }
        private int extremeInsertionIndex(int[] nums, int target, boolean left) {
            int lo = 0;
            int hi = nums.length;

            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (nums[mid] > target || (left && target == nums[mid])) {
                    hi = mid;
                }
                else {
                    lo = mid+1;
                }
            }

            return lo;
        }



        public int[] findTargetFlag(int[] nums, int target, int left, int right) {

            //比最小值小 或最大值大 直接返回-1
            if (nums[left] > target || nums[right] < target) {
                return new int[]{-1, -1};
            }
            if (right < left){
                return new int[]{-1,-1};
            }
            if (right == left){
                return nums[left] == target? new int[]{left,left}: new int[]{-1,-1};
            }
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                int lf = findTargetFlag(nums, target, left, mid )[0];
                int rf = findTargetFlag(nums, target, mid + 1, right)[1];
                if (lf == -1){
                    lf = mid;
                }
                if (rf == -1){
                    rf = mid;
                }
                return new int[]{lf, rf};
            } else if (nums[mid] < target) {
                return findTargetFlag(nums, target, mid + 1, right);
            }
            return findTargetFlag(nums, target, left, mid - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}