package com.cute.leetcode.editor.cn;

import java.util.Arrays;
//
// 如不存在这样的两个元素，请返回 -1。 
//
// 
//
// 示例 1： 
//
// 输入：A = [34,23,1,24,75,33,54,8], K = 60
//输出：58
//解释：
//34 和 24 相加得到 58，58 小于 60，满足题意。
// 
//
// 示例 2： 
//
// 输入：A = [10,20,30], K = 15
//输出：-1
//解释：
//我们无法找到和小于 15 的两个元素。 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 100 
// 1 <= A[i] <= 1000 
// 1 <= K <= 2000 
// 
// Related Topics 数组


public class TwoSumLessThanK {
    public static void main(String[] args) {
        Solution solution = new TwoSumLessThanK().new Solution();
        int[] n = new int[]{
                254, 914, 110, 900, 147, 441, 209, 122, 571, 942, 136, 350, 160, 127, 178, 839, 201, 386, 462, 45, 735, 467, 153, 415, 875, 282, 204, 534, 639, 994, 284, 320, 865, 468, 1, 838, 275, 370, 295, 574, 309, 268, 415, 385, 786, 62, 359, 78, 854, 944
        };

        System.out.println(solution.twoSumLessThanK(n, 200));
    }

    //com.cute.leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int twoSumLessThanK(int[] A, int K) {
            int result = -1;

            if (A.length < 2) {
                result = -1;
            }

            Arrays.sort(A);
            int l = 0, r = A.length - 1;
            while (l < r) {
                if (A[l] + A[r] < K) {
                    result = Math.max(result, A[l] + A[r]);
                    l++;
                } else {
                    --r;
                }
            }


            return result;
        }
    }
//com.cute.leetcode submit region end(Prohibit modification and deletion)

}