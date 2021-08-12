//给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
//
// 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
//
//
//
// 示例 1：
//
//
//输入：s = "bbbab"
//输出：4
//解释：一个可能的最长回文子序列为 "bbbb" 。
//
//
// 示例 2：
//
//
//输入：s = "cbbd"
//输出：2
//解释：一个可能的最长回文子序列为 "bb" 。
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 仅由小写英文字母组成
//
// Related Topics 字符串 动态规划
// 👍 508 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Collections;

public class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubsequence().new Solution();
        String s = "aa";
        System.out.println(solution.longestPalindromeSubseq(s));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：动态规划
        /*递归dp方程
        dp方程的分析思路：
        我们从单边考虑的话需要定义一个dp[i] 来表示以i结尾的最长子序列
        这个时候我们会发现我们需要同时记录当前子序列的起始坐标
        然后才能继续往下移动dp方程
        因此我们可以定义一个二维方程
        定义dp[i][j] 表示i->j区间内的最长子序列长度
        这样既保证了记录起始索引，也记录了结尾索引
        当s[i] == s[j] dp[i][j] = dp[i+1][j-1] + 2;
        当s[i] != s[j] dp[i][j] = max(dp[i+1][j],dp[i][j-1])
        同时我们需要考虑循环顺序，为了保证不漏算，需要从右侧开始进行计算*/
        public int longestPalindromeSubseq(String s) {
            int n = s.length();
            int[][] dp = new int[n][n];
            //初始化至少为1
            for (int i = 0; i < n; i++) {
                dp[i][i] = 1;
            }
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) {
                    if (s.substring(i, i + 1).equals(s.substring(j, j + 1))) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                    }
                }
            }
            return dp[0][n - 1];
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}