//一条包含字母 A-Z 的消息通过以下的方式进行了编码： 
//
// 
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 要 解码 一条已编码的消息，所有的数字都必须分组，然后按原来的编码方案反向映射回字母（可能存在多种方式）。例如，"11106" 可以映射为： 
//
// 
// "AAJF" 对应分组 (1 1 10 6) 
// "KJF" 对应分组 (11 10 6) 
// 
//
// 注意，像 (1 11 06) 这样的分组是无效的，因为 "06" 不可以映射为 'F' ，因为 "6" 与 "06" 不同。 
//
// 除了 上面描述的数字字母映射方案，编码消息中可能包含 '*' 字符，可以表示从 '1' 到 '9' 的任一数字（不包括 '0'）。例如，编码字符串 "1*
//" 可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条消息。对 "1*" 进行解码，相当于解码
//该字符串可以表示的任何编码消息。 
//
// 给你一个字符串 s ，由数字和 '*' 字符组成，返回 解码 该字符串的方法 数目 。 
//
// 由于答案数目可能非常大，返回对 10⁹ + 7 取余 的结果。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "*"
//输出：9
//解释：这一条编码消息可以表示 "1"、"2"、"3"、"4"、"5"、"6"、"7"、"8" 或 "9" 中的任意一条。
//可以分别解码成字符串 "A"、"B"、"C"、"D"、"E"、"F"、"G"、"H" 和 "I" 。
//因此，"*" 总共有 9 种解码方法。
// 
//
// 示例 2： 
//
// 
//输入：s = "1*"
//输出：18
//解释：这一条编码消息可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条。
//每种消息都可以由 2 种方法解码（例如，"11" 可以解码成 "AA" 或 "K"）。
//因此，"1*" 共有 9 * 2 = 18 种解码方法。
// 
//
// 示例 3： 
//
// 
//输入：s = "2*"
//输出：15
//解释：这一条编码消息可以表示 "21"、"22"、"23"、"24"、"25"、"26"、"27"、"28" 或 "29" 中的任意一条。
//"21"、"22"、"23"、"24"、"25" 和 "26" 由 2 种解码方法，但 "27"、"28" 和 "29" 仅有 1 种解码方法。
//因此，"2*" 共有 (6 * 2) + (3 * 1) = 12 + 3 = 15 种解码方法。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁵ 
// s[i] 是 0 - 9 中的一位数字或字符 '*' 
// 
// Related Topics 字符串 动态规划 👍 147 👎 0

package com.cute.leetcode.editor.cn;

public class DecodeWaysIi {
    public static void main(String[] args) {
        Solution solution = new DecodeWaysIi().new Solution();
        System.out.println(solution.numDecodings("1*72*"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：动态规划
        //定义dp[i]表示到当前字符串的方案数量
        //分为几种情况
        //初始化dp[0]=s[0]=='*'?9:1;
        //s[i] = * 的时候需要根据是否需要s[i-1]来判断情况
        //判断有s[i-1]
        //s[i-1]=1 的时候有9种 dp[i-2]*9
        //s[i-1]=2 的时候有6种 dp[i-2]*6
        //s[i-1]=* 的时候有15种(11-19,21-26)
        //无s[i-1]（也就是对应s[i-1]无法组合的情况）
        //dp[i-1]*9
        //当s[i]=数字的时候 需要判断
        //s[i]=0 dp[i] = dp[i-2]
        //s[i]!=0
        //s[i-1]=0 dp[i]=dp[i-1]
        //s[i-1]=1 dp[i]=dp[i-2]+dp[i-1]
        //s[i-1]=2
        // s[i]<6 dp[i] = dp[i-1]+dp[i-2]
        //其余情况dp[i] = dp[i-1]
        /*public int numDecodings(String s) {
            if (s.startsWith("0") || s.contains("00")) {
                return 0;
            }
            int MOD = (int) 1e9 + 7;
            long[] dp = new long[s.length()];
            dp[0] = s.charAt(0) == '*' ? 9 : 1;
            for (int i = 1; i < s.length(); i++) {
                char cur = s.charAt(i), pre = s.charAt(i - 1);
                if (cur == '*') {
                    if (pre == '*') {
                        if (i >= 2) {
                            dp[i] = dp[i - 2] * 15;
                        } else {
                            dp[i] = 15;
                        }
                    }
                    if (pre == '1') {
                        if (i >= 2) {
                            dp[i] = dp[i - 2] * 9;
                        } else {
                            dp[i] = 9;
                        }
                    }
                    if (pre == '2') {
                        if (i >= 2) {
                            dp[i] = dp[i - 2] * 6;
                        } else {
                            dp[i] = 6;
                        }
                    }
                    dp[i] += dp[i - 1] * 9;
                } else if (cur == '0') {
                    if (pre == '*') {
                        if (i >= 2) {
                            dp[i] = dp[i - 2] * 2;
                        } else {
                            dp[i] = 2;
                        }
                    } else if (pre <= '2') {
                        if (i >= 2) {
                            dp[i] = dp[i - 2];
                        } else {
                            dp[i] = 1;
                        }
                    } else {
                        return 0;
                    }
                } else {
                    if (pre == '0') {
                        dp[i] = dp[i - 1];
                    } else if (pre == '1') {
                        if (i >= 2) {
                            dp[i] = dp[i - 1] + dp[i - 2];
                        } else {
                            dp[i] = dp[i - 1] + 1;
                        }
                    } else if (pre == '2') {
                        if (cur <= '6') {
                            if (i >= 2) {
                                dp[i] = dp[i - 1] + dp[i - 2];
                            } else {
                                dp[i] = dp[i - 1] + 1;
                            }
                        } else {
                            dp[i] = dp[i - 1];
                        }
                    } else if (pre == '*') {
                        if (cur <= '6') {
                            if (i >= 2) {
                                dp[i] = dp[i - 1] + dp[i - 2] * 2;
                            } else {
                                dp[i] = dp[i - 1] + 2;
                            }
                        } else {
                            if (i >= 2) {
                                dp[i] = dp[i - 1] + dp[i - 2];
                            } else {
                                dp[i] = dp[i - 1] + 1;
                            }
                        }
                    } else {
                        dp[i] = dp[i - 1];
                    }
                }
                dp[i]%=MOD;
            }
            return (int) dp[s.length() - 1];
        }*/
        //思路二：dp+状态压缩
        //可以按照滚动数组的方式对状态进行压缩
        //只与前两个值有关
        int mod = (int)1e9+7;
        public int numDecodings(String s) {
            int n = s.length() + 1;
            long[] f = new long[3];
            f[0] = 1;
            f[1] = s.charAt(0) == '*' ? 9 : (s.charAt(0) != '0' ? 1 : 0);
            for (int i = 2; i < n; i++) {
                char c = s.charAt(i - 1), prev = s.charAt(i - 2);
                int p1 = (i - 1) % 3, p2 = (i - 2) % 3;
                long cnt = 0;
                if (c == '*') {
                    // cs[i] 单独作为一个 item
                    cnt += f[p1] * 9;
                    // cs[i] 与前一个字符共同作为一个 item
                    if (prev == '*') {
                        cnt += f[p2] * 15;
                    } else {
                        int u = (int)(prev - '0');
                        if (u == 1) cnt += f[p2] * 9;
                        else if (u == 2) cnt += f[p2] * 6;
                    }
                } else {
                    int t = (int)(c - '0');
                    if (prev == '*') {
                        if (t == 0) {
                            cnt += f[p2]* 2;
                        } else {
                            // cs[i] 单独作为一个 item
                            cnt += f[p1];
                            // cs[i] 与前一个字符共同作为一个 item
                            if (t <= 6) cnt += f[p2] * 2;
                            else cnt += f[p2];
                        }
                    } else {
                        int u = (int)(prev - '0');
                        if (t == 0) {
                            if (u == 1 || u == 2) cnt += f[p2];
                        } else {
                            // cs[i] 单独作为一个 item
                            cnt += f[p1];
                            // cs[i] 与前一个字符共同作为一个 item
                            if (u == 1) cnt += f[p2];
                            else if (u == 2 && t <= 6) cnt += f[p2];
                        }
                    }
                }
                f[i % 3] = cnt % mod;
            }
            return (int)(f[(n - 1) % 3]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}