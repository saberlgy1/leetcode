//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。 
//
// 示例: 
//
// 输入: [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 说明: 
//
// 假设你总是可以到达数组的最后一个位置。 
// Related Topics 贪心算法 数组 
// 👍 679 👎 0

package com.cute.leetcode.editor.cn;

public class JumpGameIi {
    public static void main(String[] args) {
        Solution solution = new JumpGameIi().new Solution();
        int[] nums = new int[]{2,3,1,1,4};
        //int[] nums = new int[]{0};
        System.out.println(solution.jump(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int jump(int[] nums) {
            int n = nums.length - 1;
            if (n <= 0){
                return 0;
            }
            //跳跃次数、边界、位置
            /**
             * 方法1 ,贪心法从左至右不断更新边界，
             */
//            int res = 0, end = 0, mp = 0;
//            for (int i = 0; i <= n; i++) {
//                mp = Math.max(mp,nums[i]+ i);
//                if (mp >= nums.length - 1){
//                    return  res+=1;
//                }
//                if (i == end){
//                    end = mp;
//                    res++;
//                }
//            }
//            return res;
            /**
             * 方法2, 贪心法从右至左
             */
            //从右至左
            int mp = n, res = 0;
            while (mp != 0){
                for (int i = 0; i < n; i++) {
                    if (nums[i] + i >= mp){
                        mp = i;
                        res += 1;
                        break;
                    }
                }
            }
            return res;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}