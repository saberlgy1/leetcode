//一条包含字母 A-Z 的消息通过以下映射进行了 编码 ： 
//
// 
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为： 
//
// 
// "AAJF" ，将消息分组为 (1 1 10 6) 
// "KJF" ，将消息分组为 (11 10 6) 
// 
//
// 注意，消息不能分组为 (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。 
//
// 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。 
//
// 题目数据保证答案肯定是一个 32 位 的整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "12"
//输出：2
//解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2： 
//
// 
//输入：s = "226"
//输出：3
//解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
//
// 示例 3： 
//
// 
//输入：s = "0"
//输出：0
//解释：没有字符映射到以 0 开头的数字。
//含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
//由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
// 
//
// 示例 4： 
//
// 
//输入：s = "06"
//输出：0
//解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 只包含数字，并且可能包含前导零。 
// 
// Related Topics 字符串 动态规划 
// 👍 702 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

public class DecodeWays {
    public static void main(String[] args) {
        Solution solution = new DecodeWays().new Solution();
        String s = "226";
        System.out.println(solution.numDecodings(s));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Set<String> res = new HashSet<>();

        public int numDecodings(String s) {
            //corner case
//            if ("".equals(s)){
//                return  res.size();
//            }
            //我第一个想的是暴力破解加上递归调用
            //先写一下试试看吧再想dp
            //对不起 会超时，算了 不优化了
//            backTrack(s,"");
//            return res.size();
            //还是考虑dp吧

            int index = 0;
            //去除首位为0的元素
            if (s.startsWith("0")) {
                for (char c : s.toCharArray()) {
                    if (c == '0') {
                        index++;
                    } else {
                        break;
                    }
                }
                numDecodings(s.substring(index));
            }

            // int[] dp = new int[s.length() + 1];
            //dp[0] =1;
            //char[] charArray = s.toCharArray();
            //确定动态方程
            //dp[0] = 1
            //s[i] == '0'
            //应该属于异常信息
            //s[i] == '1'
            // i + 1 < s.length()
            //   s[i+1] > '0' dp[i] = dp[i-1] + 2
            //   s[i+1] = '0' dp[i] = dp[i-1] + 1
            // i + 1 = s.length() dp[i] = dp[i - 1] +1
            //s[i] == '2'
            // i + 1 < s.length()
            //  '6'>= s[i+1] > '0' dp[i] = dp[i-1] + 2
            //   s[i+1] = '0' dp[i] = dp[i-1] + 1
            // i + 1 = s.length() dp[i] = dp[i - 1] +1
            //dp[i] = dp[i - 1] + 1
            //第一下考虑的思路有问题
            //应该是前面字符串0->i-1 的数量 与 0 -> i-2的数量求和
            //代码实现
            /*for (int i = 1; i <= s.length(); i++) {
                if (charArray[i-1] != '0') {
                    dp[i] += dp[i - 1] ;
                }
                if (i > 1 && charArray[i - 2] != '0' && ((charArray[i - 2] - '0') * 10 + (charArray[i - 1] - '0') <= 26)) {
                    dp[i] += dp[i - 2] ;
                }
            }
            return dp[s.length()];*/
            //常量代表 可以省略数组
            /*int a = 0, b = 1, c = 0;
            for (int i = 1; i <= s.length(); i++) {
                c = 0;
                if (charArray[i - 1] != '0') {
                    c += b;
                }
                if (i > 1 && charArray[i - 2] != '0' && ((charArray[i - 2] - '0') * 10 + (charArray[i - 1] - '0') <= 26)) {
                    c += a;
                }
                a = b;
                b = c;
            }

            return c;*/
            //这个思路其实没什么问题
            //就是动态方程需要换一下
            //s[i - 1] == '1'
            // s[i] > '0' dp[i] += dp[i-2]
            //   s[i+1] = '0' dp[i] = dp[i-1]
            // i + 1 = s.length() dp[i] = dp[i - 1] +1
            //s[i] == '2'
            // i + 1 < s.length()
            //  '6'>= s[i+1] > '0' dp[i] += dp[i-2]
            //   s[i+1] = '0' dp[i] = dp[i-1]
            // i + 1 = s.length() dp[i] = dp[i - 1]
            //dp[i] = dp[i - 1]
            int[] dp = new int[s.length() + 1];
            //前面加空格，避免负数
            int n = s.length();
            s = " " + s;
            char[] charArray = s.toCharArray();
            dp[0] = 1;
            for (int i = 1; i <= n; i++) {

                int a = charArray[i] - '0', b = (charArray[i - 1] - '0') * 10 + charArray[i] - '0';
                if (a >= 1 && a <= 10) {
                    dp[i] = dp[i - 1];
                }
                if (b <= 26 && b >= 10 && i>=2) {
                    dp[i] += dp[i - 2];
                }
            }
            return dp[n];
        }

        private void backTrack(String s, String temp) {
            //跳出递归
            if ("".equals(s)) {
                res.add(temp);
                return;
            }
            //0开始的元素证明此串无意义直接返会
            if (s.startsWith("0")) {
                return;
            } else if (s.startsWith("1")) {
                String temp1 = temp;
                backTrack(s.substring(1), temp += "A");
                if (s.length() >= 2) {
                    String a = String.valueOf((char) (Integer.parseInt(s.substring(0, 2)) + 64));
                    backTrack(s.substring(2), temp1 += a);
                }
            } else if (s.startsWith("2")) {
                String temp1 = temp;
                //一位数
                backTrack(s.substring(1), temp += "B");
                if (s.length() >= 2 && s.charAt(1) <= '6') {
                    String a = String.valueOf((char) (Integer.parseInt(s.substring(0, 2)) + 64));
                    backTrack(s.substring(2), temp1 += a);
                }
            } else {
                backTrack(s.substring(1), temp += String.valueOf((char) (Integer.parseInt(s.substring(0, 1)) + 64)));
            }


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}