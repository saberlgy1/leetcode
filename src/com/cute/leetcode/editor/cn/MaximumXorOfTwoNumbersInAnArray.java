//给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。 
//
// 进阶：你可以在 O(n) 的时间解决这个问题吗？ 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：nums = [3,10,5,25,2,8]
//输出：28
//解释：最大运算结果是 5 XOR 25 = 28. 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：nums = [2,4]
//输出：6
// 
//
// 示例 4： 
//
// 
//输入：nums = [8,10,2]
//输出：10
// 
//
// 示例 5： 
//
// 
//输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
//输出：127
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 104 
// 0 <= nums[i] <= 231 - 1 
// 
// 
// 
// Related Topics 位运算 字典树 
// 👍 291 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

public class MaximumXorOfTwoNumbersInAnArray {
    public static void main(String[] args) {
        Solution solution = new MaximumXorOfTwoNumbersInAnArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMaximumXOR(int[] nums) {
            /*//第一种方法有点意思，没太理解，但是可以试试能不能写出来
            //由题意得知最大值为2*31 ，所以可以定义最大长度31位 定义一个从0 -30
            //位运算进行右移即可得前k位元素 O(nlogC) c为元素最大值
            int MAX = 30, x = 0;
            for (int k = MAX; k >= 0; k--) {
                Set<Integer> set = new HashSet<>();
                for (int num : nums) {
                    set.add(num >> k);
                }
                int next = x * 2 +1;
                //位运算特性 a = b ^ c => b = a ^ c
                boolean found = false;
                // 枚举 i
                for (int num : nums) {
                    if (set.contains(next ^ (num >> k))) {
                        found = true;
                        break;
                    }
                }
                if (found){
                    x = next;
                }else{
                    x = next - 1;
                }
            }
            return x;
*/
            //不想写了 晚点再写
            return 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}