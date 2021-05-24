//有台奇怪的打印机有以下两个特殊要求： 
//
// 
// 打印机每次只能打印由 同一个字符 组成的序列。 
// 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。 
// 
//
// 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。 
// 
//
// 示例 1： 
//
// 
//输入：s = "aaabbb"
//输出：2
//解释：首先打印 "aaa" 然后打印 "bbb"。
// 
//
// 示例 2： 
//
// 
//输入：s = "aba"
//输出：2
//解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 由小写英文字母组成 
// 
// Related Topics 深度优先搜索 动态规划 
// 👍 122 👎 0

package com.cute.leetcode.editor.cn;

public class StrangePrinter {
    public static void main(String[] args) {
        Solution solution = new StrangePrinter().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int strangePrinter(String s) {
            //感觉像是寻找最长连续子串
            //想的并没有意义
            //dp倒是能猜出来但是dp方程想不到
            //如果s[i] == s[j] f[i][j] = f[i][j-1]
            //如果s[i] != s[j] f[i][j] = Math.min(f[i][k]) + f[k+1][j];
            //Math.min的意义是 k从i到j-1 的最小值
            char[] chars = s.toCharArray();
            int[][] dp = new int[chars.length][chars.length];
            for (int i = chars.length - 1; i >= 0; i--) {
                dp[i][i] = 1;
                for (int j = i + 1; j < chars.length; j++) {
                    if (chars[i] == chars[j]) {
                        dp[i][j] = dp[i][j - 1];
                    } else {
                        int minn = Integer.MAX_VALUE;
                        for (int k = i; k < j; k++) {
                            minn = Math.min(minn, dp[i][k] + dp[k + 1][j]);
                        }
                        dp[i][j] = minn;
                    }
                }
            }
            return dp[0][chars.length - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}