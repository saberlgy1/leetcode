//给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含 连续的1 的个数。 
//
// 示例 1: 
//
// 输入: 5
//输出: 5
//解释: 
//下面是带有相应二进制表示的非负整数<= 5：
//0 : 0
//1 : 1
//2 : 10
//3 : 11
//4 : 100
//5 : 101
//其中，只有整数3违反规则（有两个连续的1），其他5个满足规则。 
//
// 说明: 1 <= n <= 10⁹ 
// Related Topics 动态规划 👍 153 👎 0

package com.cute.leetcode.editor.cn;

public class NonNegativeIntegersWithoutConsecutiveOnes {
    public static void main(String[] args) {
        Solution solution = new NonNegativeIntegersWithoutConsecutiveOnes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：数位dp
//        本题是一道非常典型的数位dp
//        数位dp的题目大概会有如下更改（a,b）区间内某个方案的数量
//        本题是从0开始，不过计算子区间也是完全可以做的
//        定义dp[i] 表示第i位的满足条件的方案数量
//        分情况考虑
        //dp[0]=dp[1] = 1;
        //dp方程：
        //dp[i] = dp[i-1] + dp[i-2]
        //然后观察每位上为1的数量计算满足条件的方案数量
        public int findIntegers(int n) {
            int[] dp = new int[31];
            dp[0] = dp[1] = 1;
            for (int i = 2; i < dp.length; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            int pre = 0, res = 0;
            for (int i = 29; i >= 0; i--) {
                int val = 1 << i;
                if ((val & n) != 0) {
                    n -= val;
                    res += dp[i + 1];
                    if (pre == 1) {
                        break;
                    }
                    pre = 1;
                } else {
                    pre = 0;
                }
                if (i == 0) {
                    res += 1;
                }
            }
            return res;
        }
        /*int getL(int n) {
            for (int i = 31; i >= 0; i--) {
                if ((n >> i & 1) == 1) {
                    return i;
                }
            }
            return 0;
        }

        public int findIntegers(int n) {
            int[][] dp = new int[40][2];
            dp[1][0] = 1;
            dp[1][1] = 1;
            for (int i = 1; i < n - 1; i++) {
                dp[i + 1][0] = dp[i][1];
                dp[i + 1][1] = dp[i][1] + dp[i][0];
            }
            int len = getL(n);
            int res = 0, prev = 0;
            for (int i = len; i >= 0; i--) {
                int cur = (n >> i) & 1;
                if (cur == 1){
                    res +=dp[i+1][0];
                }
                if (prev == 1 && cur == 1){
                    break;
                }
                prev = cur;
                if (i == 0){
                    res++;
                }
            }
            return res;


        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}