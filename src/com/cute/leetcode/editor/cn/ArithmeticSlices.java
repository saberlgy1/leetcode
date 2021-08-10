//如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
//
//
// 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
//
//
//
//
// 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
//
// 子数组 是数组中的一个连续序列。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3,4]
//输出：3
//解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
//
//
// 示例 2：
//
//
//输入：nums = [1]
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 5000
// -1000 <= nums[i] <= 1000
//
//
//
// Related Topics 数组 动态规划
// 👍 300 👎 0

package com.cute.leetcode.editor.cn;

public class ArithmeticSlices {
    public static void main(String[] args) {
        Solution solution = new ArithmeticSlices().new Solution();
        int[] nums = new int[]{1,2,3,4,5};
        System.out.println(solution.numberOfArithmeticSlices(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力搜索
        //查找到所有等差数列
        //通过一次扫描确定所有等差数里
        //然后每个等差数列包含的数量为等差数列长度(n-2)*(n-1)/2
        public int numberOfArithmeticSlices(int[] nums) {
            int idx = 0, n = nums.length, res = 0;
            while (idx < n) {
                if (idx == n - 1) {
                    break;
                }
                int val = idx;
                int dis = nums[val + 1] - nums[val];
                while (val < n - 1 && nums[val + 1] - nums[val] == dis) {
                    val++;
                }
                if (val - idx >= 2) {
                    res = res + (val - idx - 1) * (val - idx) / 2;
                }
                idx = val;

            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}