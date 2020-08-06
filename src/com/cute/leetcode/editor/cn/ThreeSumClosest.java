//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和
//。假定每组输入只存在唯一答案。 
//
// 
//
// 示例： 
//
// 输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 10^3 
// -10^3 <= nums[i] <= 10^3 
// -10^4 <= target <= 10^4 
// 
// Related Topics 数组 双指针 
// 👍 522 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumClosest {
    public static void main(String[] args) {
        Solution solution = new ThreeSumClosest().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //        public int threeSumClosest(int[] nums, int target) {
//
//            //O(n) 感觉是n方，但是并没有达到
//           /* int res = Integer.MAX_VALUE;
//            int len = nums.length;
//            for (int i = 0; i < len - 2; i++) {
//                int tar = 0;
//                int l = i + 1;
//                int r = len - 1;
//                while (l < r) {
//                    if (Math.abs(nums[i] + nums[l] + nums[r] - target) < Math.abs(res - target)) {
//                        res = nums[i] + nums[l] + nums[r];
//                    }
//                    l++;
//                    r--;
//                }
//            }
//            return res;*/
//            //优化1 排序 + 双联表
//        }
        public  List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> ans = new ArrayList();
            int len = nums.length;
            Arrays.sort(nums); // 排序
            for (int i = 0; i < len; i++) {
                if (nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
                if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重
                int L = i + 1;
                int R = len - 1;
                while (L < R) {
                    int sum = nums[i] + nums[L] + nums[R];
                    if (sum == 0) {
                        ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                        while (L < R && nums[L] == nums[L + 1]) L++; // 去重
                        while (L < R && nums[R] == nums[R - 1]) R--; // 去重
                        L++;
                        R--;
                    } else if (sum < 0) L++;
                    else if (sum > 0) R--;
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}