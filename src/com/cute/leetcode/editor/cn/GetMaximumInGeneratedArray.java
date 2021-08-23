//给你一个整数 n 。按下述规则生成一个长度为 n + 1 的数组 nums ：
//
//
// nums[0] = 0
// nums[1] = 1
// 当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
// 当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]
//
//
// 返回生成数组 nums 中的 最大 值。
//
//
//
// 示例 1：
//
//
//输入：n = 7
//输出：3
//解释：根据规则：
//  nums[0] = 0
//  nums[1] = 1
//  nums[(1 * 2) = 2] = nums[1] = 1
//  nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
//  nums[(2 * 2) = 4] = nums[2] = 1
//  nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
//  nums[(3 * 2) = 6] = nums[3] = 2
//  nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
//因此，nums = [0,1,1,2,1,3,2,3]，最大值 3
//
//
// 示例 2：
//
//
//输入：n = 2
//输出：1
//解释：根据规则，nums[0]、nums[1] 和 nums[2] 之中的最大值是 1
//
//
// 示例 3：
//
//
//输入：n = 3
//输出：2
//解释：根据规则，nums[0]、nums[1]、nums[2] 和 nums[3] 之中的最大值是 2
//
//
//
//
// 提示：
//
//
// 0 <= n <= 100
//
// Related Topics 数组 动态规划 模拟 👍 19 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class GetMaximumInGeneratedArray {
    public static void main(String[] args) {
        Solution solution = new GetMaximumInGeneratedArray().new Solution();
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " : " + solution.getMaximumGenerated(i));
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：打表
        //看到这个数据量大家都懂了8
        //模拟100个数而已
        //打表结果

        /**
         * 0 : 0
         * 1 : 1
         * 2 : 1
         * 3 : 2
         * 4 : 2
         * 5 : 3
         * 6 : 3
         * 7 : 3
         * 8 : 3
         * 9 : 4
         * 10 : 4
         * 11 : 5
         * 12 : 5
         * 13 : 5
         * 14 : 5
         * 15 : 5
         * 16 : 5
         * 17 : 5
         * 18 : 5
         * 19 : 7
         * 20 : 7
         * 21 : 8
         * 22 : 8
         * 23 : 8
         * 24 : 8
         * 25 : 8
         * 26 : 8
         * 27 : 8
         * 28 : 8
         * 29 : 8
         * 30 : 8
         * 31 : 8
         * 32 : 8
         * 33 : 8
         * 34 : 8
         * 35 : 9
         * 36 : 9
         * 37 : 11
         * 38 : 11
         * 39 : 11
         * 40 : 11
         * 41 : 11
         * 42 : 11
         * 43 : 13
         * 44 : 13
         * 45 : 13
         * 46 : 13
         * 47 : 13
         * 48 : 13
         * 49 : 13
         * 50 : 13
         * 51 : 13
         * 52 : 13
         * 53 : 13
         * 54 : 13
         * 55 : 13
         * 56 : 13
         * 57 : 13
         * 58 : 13
         * 59 : 13
         * 60 : 13
         * 61 : 13
         * 62 : 13
         * 63 : 13
         * 64 : 13
         * 65 : 13
         * 66 : 13
         * 67 : 13
         * 68 : 13
         * 69 : 14
         * 70 : 14
         * 71 : 14
         * 72 : 14
         * 73 : 15
         * 74 : 15
         * 75 : 18
         * 76 : 18
         * 77 : 18
         * 78 : 18
         * 79 : 18
         * 80 : 18
         * 81 : 18
         * 82 : 18
         * 83 : 19
         * 84 : 19
         * 85 : 21
         * 86 : 21
         * 87 : 21
         * 88 : 21
         * 89 : 21
         * 90 : 21
         * 91 : 21
         * 92 : 21
         * 93 : 21
         * 94 : 21
         * 95 : 21
         * 96 : 21
         * 97 : 21
         * 98 : 21
         * 99 : 21
         */
        //我就不写了
        //思路二：递归
        //暴力递归
        //定义一个map记录每个f(i)的值
        //原来是返回数组的最大值
        /*Map<Integer, Integer> map = new HashMap<>();

        public int getMaximumGenerated(int n) {
            int max = Integer.MIN_VALUE;
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            map.put(0, 0);
            map.put(1, 1);
            for (int i = 2; i <= n; i++) {
                int temp = 0;
                //奇数
                if (i % 2 == 0) {
                    temp = map.get(i / 2);
                    map.put(i, temp);
                }
                //偶数
                else {
                     temp = map.get((i - 1) / 2) + map.get((i + 1) / 2);
                    map.put(i, temp);
                }
                max = Math.max(temp, max);
            }
            return max;
        }*/
        //思路二：动态规划
        //f[i] = f[i/2] + i%2==0?0:f[(i+1)/2]
        //定义一个数组f[],最大值max
        public int getMaximumGenerated(int n) {
            if (n <= 1) {
                return n;
            }
            int[] f = new int[n + 1];
            int max = Integer.MIN_VALUE;
            f[0] = 0;
            f[1] = 1;
            for (int i = 2; i <= n; i++) {
                f[i] = f[i / 2] + (i % 2 == 0 ? 0 : f[(i + 1) / 2]);
                max = Math.max(max, f[i]);
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}