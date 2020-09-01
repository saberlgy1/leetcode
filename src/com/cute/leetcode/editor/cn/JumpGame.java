//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个位置。 
//
// 示例 1: 
//
// 输入: [2,3,1,1,4]
//输出: true
//解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
// 
//
// 示例 2: 
//
// 输入: [3,2,1,0,4]
//输出: false
//解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
// 
// Related Topics 贪心算法 数组 
// 👍 796 👎 0

package com.cute.leetcode.editor.cn;

public class JumpGame {
    public static void main(String[] args) {
        Solution solution = new JumpGame().new Solution();
        int[] nums = new int[]{0};
        System.out.println(solution.canJump(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canJump(int[] nums) {
            int des = nums[0];
            int n  = nums.length - 1;
            //基本逻辑思路，贪心法更新最远位置
            for (int i = 0; i < n+1 ;i ++) {
                if (i <= des){
                    des = Math.max(i + nums[i], des);
                }else{
                    return  false;
                }
            }
            return des >= n;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}