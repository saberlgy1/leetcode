//给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。 
//
// 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。 
//
// 给定一个数对集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。 
//
// 
//
// 示例： 
//
// 
//输入：[[1,2], [2,3], [3,4]]
//输出：2
//解释：最长的数对链是 [1,2] -> [3,4]
// 
//
// 
//
// 提示： 
//
// 
// 给出数对的个数在 [1, 1000] 范围内。 
// 
// Related Topics 贪心 数组 动态规划 排序 
// 👍 170 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MaximumLengthOfPairChain {
    public static void main(String[] args) {
        Solution solution = new MaximumLengthOfPairChain().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：动态规划
        //dp[j] 表示遍历到第i个数对是能组成的最长数对链长度
        //dp[j] = pairs[i][1] < pairs[j][0]?dp[i]+1:dp[j]
        //dp[j]初始为1
       /* public int findLongestChain(int[][] pairs) {
            Arrays.sort(pairs, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] < o2[0]) {
                        return -1;
                    }
                    return o1[0] == o2[0] ? 0 : 1;
                }
            });
            int[] dp = new int[pairs.length];
            //默认至少有一个
            Arrays.fill(dp, 1);
            int max = 1;
            for (int j = 1; j < pairs.length; j++) {
                for (int i = 0; i < j; i++) {
                    if (pairs[i][1] < pairs[j][0]) {
                        dp[j] = Math.max(dp[j], dp[i] + 1);
                        max = Math.max(dp[j],max);
                    }
                }
            }
            return max;
        }*/
        //思路二：贪心法
        //按照数组末尾元素排序，依次添加满足条件的最小的数即可
        public int findLongestChain(int[][] pairs) {
            Arrays.sort(pairs,(a,b)->a[1]-b[1]);
            int cur = Integer.MIN_VALUE, ans = 0;
            for (int[] pair: pairs) {
                if (cur < pair[0]) {
                    cur = pair[1];
                    ans++;
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}