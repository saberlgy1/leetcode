//给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。 
//
// 
//
// 示例： 
//
// 输入: "sea", "eat"
//输出: 2
//解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
// 
//
// 
//
// 提示： 
//
// 
// 给定单词的长度不超过500。 
// 给定单词中的字符只含有小写字母。 
// 
// Related Topics 字符串 动态规划 👍 285 👎 0

package com.cute.leetcode.editor.cn;

public class DeleteOperationForTwoStrings {
    public static void main(String[] args) {
        Solution solution = new DeleteOperationForTwoStrings().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //序列dp
        //很容易转换成求最长公告子序列的问题
        //最后返回的就是n-max+m-max
        //n和m表示两个字符串长度
        //max表示子序列长度
        //dp[i][j]表示第一个字符串前i位和第二个字符串前j位的最长公共子序列
        //转移过程可以根据s1[i] s2[j]的判断关系来决定
        //如果s1[i] == s2[j] dp[i][j] = dp[i-1][j-1] +1
        //否则dp[i][j] = max(dp[i-1][j]+1,dp[i][j-1]+1)
        /*public int minDistance(String word1, String word2) {
            int m = word1.length(), n = word2.length();
            int[][] dp = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return m - dp[m][n] + n - dp[m][n];
        }*/
//        第二种可以选择更加直接的dp定义
//        dp[i][j]表示一个字符串前i位和第二个字符串前j位形成相同字符串需要的操作次数
//        初始化dp[i][0] = i dp[0][j] = j
//        转移方程也是分为两种情况
//        如果s1[i] == s2[j] dp[i][j] = dp[i-1][j-1]
//        否则dp[i][j] = min(dp[i-1][j]+1,dp[i][j-1]+1)+1
        public int minDistance(String word1, String word2) {
            int m = word1.length(), n = word2.length();
            int[][] dp = new int[m + 1][n + 1];
            for (int i = 0; i <= m; i++) {
                dp[i][0] = i;
            }
            for (int i = 0; i <= n; i++) {
                dp[0][i] = i;
            }
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                    }
                }
            }
            return dp[m][n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}