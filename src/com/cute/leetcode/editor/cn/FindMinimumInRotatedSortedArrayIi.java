//已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变
//化后可能得到：
// 
// 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4] 
// 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7] 
// 
//
// 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], 
//..., a[n-2]] 。 
//
// 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,5]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,2,0,1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 5000 
// -5000 <= nums[i] <= 5000 
// nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转 
// 
//
// 
//
// 进阶： 
//
// 
// 这道题是 寻找旋转排序数组中的最小值 的延伸题目。 
// 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？ 
// 
// Related Topics 数组 二分查找 
// 👍 307 👎 0

package com.cute.leetcode.editor.cn;

public class FindMinimumInRotatedSortedArrayIi {
    public static void main(String[] args) {
        Solution solution = new FindMinimumInRotatedSortedArrayIi().new Solution();
        int[] nums = new int[]{5,1,2,3,3,3,3,3,3,3,3,3,3,3,3,4};
        solution.findMin(nums);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMin(int[] nums) {
            int n = nums.length - 1, k = 0;
            if (n == 0) {
                return nums[0];
            }
            while (n >= 0) {
                if (nums[n] == nums[0]) {
                    n--;
                }else{
                    break;
                }
            }
            if ( n == -1 || nums[0] < nums [n]){
                return nums[0];
            }
            return nums[findK(nums, n)];
        }

        private int findK(int[] nums, int n) {
            if (n == 0) {
                return nums[0];
            }
            int l = 0, r = n;
            while (l < r) {
                int mid = (r - l + 1) / 2 + l;
                if (nums[mid] >= nums[0] ) {
                    if (mid + 1 < r && nums[mid] > nums[mid + 1]){
                        return mid + 1;
                    }else{
                        l = mid ;
                    }
                }else {
                    r = mid - 1;
                }
            }
            if (l == r){
                return l + 1;
            }
            return 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}