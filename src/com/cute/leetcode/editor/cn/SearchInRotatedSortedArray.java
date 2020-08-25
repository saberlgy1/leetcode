//假设按照升序排序的数组在预先未知的某个点上进行了旋转。 
//
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。 
//
// 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。 
//
// 你可以假设数组中不存在重复的元素。 
//
// 你的算法时间复杂度必须是 O(log n) 级别。 
//
// 示例 1: 
//
// 输入: nums = [4,5,6,7,0,1,2], target = 0
//输出: 4
// 
//
// 示例 2: 
//
// 输入: nums = [4,5,6,7,0,1,2], target = 3
//输出: -1 
// Related Topics 数组 二分查找 
// 👍 890 👎 0
package com.cute.leetcode.editor.cn;

public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        Solution solution = new SearchInRotatedSortedArray().new Solution();
        int[] nums = new int[]{4,5,6,7,0,1,2};
        System.out.println(solution.search(nums, 6));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int search(int[] nums, int target) {
            int len = nums.length;
            //corner case
            if (len == 0) {
                return -1;
            }
            if (len == 1){
                return nums[0] == target? 0:-1;
            }
            int flag = findFlag(nums);
            if (target > nums[flag]){
                return  - 1;
            }
            else if (target < nums[0]  ){
                return binarySearCh(nums, flag + 1, len - 1,target);
            }
            return binarySearCh(nums, 0, flag, target);
        }

        //二分查找
        public  int binarySearCh(int[] nums, int left, int right, int target){

            int mid = (left + right) /2;

            while (left < right){
                mid = (left + right) /2;
                if (nums[mid] < target){
                    left = mid + 1;
                }else if (nums[mid] > target){
                    right = mid - 1;
                }else {
                    return mid;
                }
            }
            if (nums[right] == target){
                return left;
            }
            return -1;

        }


        //找到旋转标识
        public  int findFlag(int[] nums){

            int len = nums.length;
            //step 1
            //二分查找查找到对应旋转节点
            //旋转节点的下一个节点一定比旋转后的第一个节点小
            //二分查找
            //先找到第一个比自己小的，然后判断左边元素是不是还比自己小，如果还比自己小 就截取左边区间，如果不比自己小则可以确定左边节点为旋转节点
            int left = 0;
            int right = len - 1;
            while (left <= right) {
                int mid = (left + right + 1) / 2;
                if (mid == 0){
                    return 0;
                }
                if (nums[mid] < nums[0]) {
                    //如果中间元素比第一个元素小，则正面在两者之间发生旋转
                    //如果中间元素的前一个元素是第一个元素，则证明只有第一个元素发生了旋转
                    if (mid - 1 == 0){
                        return 0;
                    }
                    if (nums[mid - 1] <= nums[mid]) {
                        right = mid - 1;
                    }else{
                        return mid - 1;
                    }
                } else{
                    left = mid + 1;
                }
            }
            return len - 1;
        }


    }

//leetcode submit region end(Prohibit modification and deletion)
}