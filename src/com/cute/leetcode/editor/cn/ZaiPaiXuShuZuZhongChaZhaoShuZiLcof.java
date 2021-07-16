//统计一个数字在排序数组中出现的次数。
//
//
//
// 示例 1:
//
// 输入: nums = [5,7,7,8,8,10], target = 8
//输出: 2
//
// 示例 2:
//
// 输入: nums = [5,7,7,8,8,10], target = 6
//输出: 0
//
//
//
// 限制：
//
// 0 <= 数组长度 <= 50000
//
//
//
// 注意：本题与主站 34 题相同（仅返回值不同）：https://leetcode-cn.com/problems/find-first-and-last-
//position-of-element-in-sorted-array/
// Related Topics 数组 二分查找
// 👍 170 👎 0

package com.cute.leetcode.editor.cn;

public class ZaiPaiXuShuZuZhongChaZhaoShuZiLcof {
    public static void main(String[] args) {
        Solution solution = new ZaiPaiXuShuZuZhongChaZhaoShuZiLcof().new Solution();
        int[] nums = new int[]{1, 2, 3, 3, 3, 3, 4, 5, 9};
        System.out.println(solution.search(nums, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力法
        //这道题的暴力法是真的太简单了
        //思路二：二分查找
        //第一个二分查找比target小且下一个元素是target的元素
        //第二个二分查找找target且下一个元素为元素终点或大于target的元素
        public int search(int[] nums, int target) {
            //corner case
            if (nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target) {
                return 0;
            }
            int n = nums.length, l = 0, r = n - 1,start = 0;
            //第一次寻找起点
            while (l < r) {
                int mid = (l - r + 1) / 2 + r;
                if (nums[mid] >= target) {
                    if (nums[mid] == target) {
                        if (mid == 0 || nums[mid - 1] < target) {
                            l = mid;
                            break;
                        }
                    }
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
            //未找到当前元素直接返回0；
            if (nums[l] != target) {
                return 0;
            }
            //第二次寻找终点
            //重新定义右端点，第二次寻值因为数组递增的原因可以直接找较大元素
            r = n - 1;
            start = l;
            while (l < r) {
                int mid = (l - r + 1) / 2 + r;
                if (nums[mid] == target) {
                    if (mid == n - 1 || nums[mid + 1] > target) {
                        r = mid;
                        break;
                    }
                    l = mid;
                }else{
                    r = mid - 1;
                }
            }
            return  r - start + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}