//已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。 
//
// 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums
//[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,
//2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。 
//
// 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 targ
//et ，则返回 true ，否则返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,5,6,0,0,1,2], target = 0
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,5,6,0,0,1,2], target = 3
//输出：false 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5000 
// -104 <= nums[i] <= 104 
// 题目数据保证 nums 在预先未知的某个下标上进行了旋转 
// -104 <= target <= 104 
// 
//
// 
//
// 进阶： 
//
// 
// 这是 搜索旋转排序数组 的延伸题目，本题中的 nums 可能包含重复元素。 
// 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？ 
// 
// Related Topics 数组 二分查找 
// 👍 331 👎 0

package com.cute.leetcode.editor.cn;

public class SearchInRotatedSortedArrayIi {
    public static void main(String[] args) {
        Solution solution = new SearchInRotatedSortedArrayIi().new Solution();
        //int[] nums = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1};
        //int[] nums1 = new int[]{2,5,6,0,0,1,2};
        int nums[] = new int[]{1, 3};
        //System.out.println(solution.containsT(nums,3,0,2));
        System.out.println(solution.search(nums, 1));
        //System.out.println(solution.search(nums1, 2));
        //System.out.println(solution.search(nums1, 3));
        //System.out.println(solution.search(nums1, 0));
//        System.out.println(solution.search(nums1, 5));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean search(int[] nums, int target) {

            //写了半天二分 还是O(n),因为又重复元素
/*            int m = nums.length, r = m - 1, l = 0;
            //相同元素段内旋转需要恢复二分性，也就是将末尾元素去掉

           while (l < r && nums[0] == nums[r]){
               r--;
           }
            int k = findK(nums, 0, r);
            //等于最大元素
            if (nums[k] == target) {
                return nums[k] == target;
            }
            //超出最大元素
            if (nums[k] < target) {
                return false;
            }
            if (target >= nums[0]) {
                return containsT(nums, target, 0, k);
            }

            return containsT(nums, target, k + 1, r);*/
            //直接顺序查找
            int m = nums.length;
            for (int i = 0; i < m ; i ++){
                if (nums[i] == target){
                    return true;
                }
/*                if((nums[i] < target) && (i + 1 < m) && (nums[i] > nums[i+1]) ){
                    return false;
                }*/
            }
            return false;
        }


        private int findK(int[] nums, int l, int r) {
            //旋转数组，先定位中间元素,大于等于首位元素，往右找，小于首位元素往左找
            while (l < r) {
                int mid = (r - l + 1) / 2 + l;
                if (nums[mid] >= nums[0]) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return r;
        }

        private boolean containsT(int[] nums, int target, int l, int r) {
            while (l < r) {
                int mid = (r - l + 1) / 2 + l;
                if (nums[mid] == target) {
                    return true;
                } else if (nums[mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
            if (l == r) {
                return nums[l] == target;
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}