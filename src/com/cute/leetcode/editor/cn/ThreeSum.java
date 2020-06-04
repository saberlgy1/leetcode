package com.cute.leetcode.editor.cn;
import java.util.*;

//的三元组。
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例： 
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics 数组 双指针


public class ThreeSum {
    public static void main(String[] args) {
        Solution solution = new ThreeSum().new Solution();
        int[] a = new int[]{
                -1,0,1,2,-1,-4
        };
        System.out.println(solution.threeSum(a));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<> ();
            if (nums.length == 0){
                return result;
            }
            Arrays.sort(nums);
            if ( nums[0] > 0 || nums.length < 3) {
                return result;
            }

            for (int i = 0; i < nums.length-1 ; i++) {
                if(i > 0 && nums[i] == nums[i-1]) continue; // 去重
                int l = i + 1, r = nums.length - 1;
                while (l < r) {
                    if ((nums[l] + nums[i] + nums[r]) > 0 ) {
                        r--;
                    } else if ((nums[l] + nums[i] + nums[r]) < 0) {
                        l++;
                    } else {
                        result.add(Arrays.asList(nums[i],nums[l],nums[r]));
                        while (l < r && nums[l] == nums [l + 1]) l++;
                        while (l < r && nums[r] == nums [r - 1]) r--;
                        r--;
                        l++;
                    }
                }
                if (nums[i] == nums [i+1]){
                    i++;
                }

            }
            return result;

        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}