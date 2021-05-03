//给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,2,3,2]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,1,0,1,99]
//输出：99
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// -231 <= nums[i] <= 231 - 1 
// nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 
// 
//
// 
//
// 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？ 
// Related Topics 位运算 
// 👍 577 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SingleNumberIi {
    public static void main(String[] args) {
        Solution solution = new SingleNumberIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int singleNumber(int[] nums) {
            //最简单的肯定是hashmap额外空间感觉可以三指针
            //corner case
            if (nums.length < 3){
                return nums[0];
            }
            //先hashmap O(2n) O(n)
            /*Map<Integer,Integer> map = new HashMap<>();
            for (int i = 0;i < nums.length; i++){
                if (map.containsKey(nums[i])){
                    map.put(nums[i], map.get(nums[i]) + 1);
                }else{
                    map.put(nums[i], 1);
                }
            }
            for (int num: map.keySet()
                 ) {
                if (map.get(num)!= 3){
                    return num;
                }
            }
            return 0;*/
            //排序的话 O(nlgn) o(1)
            Arrays.sort(nums);
            int l = 0, r= l + 2;
            while (r < nums.length){
                if (l != r){
                    if (nums[l] != nums[r]){
                        return nums[l];
                    }else{
                        l++;
                    }
                }else{
                    r = r +3;
                    l ++;
                }
            }
            return nums[l];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}