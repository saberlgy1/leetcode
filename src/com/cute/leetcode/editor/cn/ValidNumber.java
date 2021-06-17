//有效数字（按顺序）可以分成以下几个部分： 
//
// 
// 一个 小数 或者 整数 (可选）
// 一个 'e' 或 'E' ，后面跟着一个 整数
// 
//
// 小数（按顺序）可以分成以下几个部分： 
//
// 
// （可选）一个符号字符（'+' 或 '-'） 
// 下述格式之一：
// 
// 至少一位数字，后面跟着一个点 '.' 
// 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字 
// 一个点 '.' ，后面跟着至少一位数字 
// 
// 
// 
//
// 整数（按顺序）可以分成以下几个部分： 
//
// 
// （可选）一个符号字符（'+' 或 '-'） 
// 至少一位数字 
// 
//
// 部分有效数字列举如下： 
//
// 
// ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1",
// "53.5e93", "-123.456e789"] 
// 
//
// 部分无效数字列举如下： 
//
// 
// ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"] 
// 
//
// 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "0"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "e"
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：s = "."
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = ".1"
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。 
// 
// Related Topics 数学 字符串 
// 👍 212 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Stack;

public class ValidNumber {
    public static void main(String[] args) {
        //String[] s = new String[]{"46.e3","2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789","-.1"};
        String[] s = new String[]{".e1","+E3","abc", "1a",  "e3", "99e2.5", "--6", "-+3", "95a54e53","6e+","e1",".+"};
        //String[] s = new String[]{"46.e3"};
        Solution solution = new ValidNumber().new Solution();
        for (String str : s
        ) {
            System.out.println(solution.isNumber(str));
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：多条件匹配
        //这代码写起来恶心，读起来更恶心
        //时间复杂度O(n)
        /*public boolean isNumber(String s) {
            char[] chars = s.toCharArray();
            boolean sf = false;
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                //如果不是e/E,+/-,.,数字可以直接返回false
                if (!checkAll(c)) {
                    return false;
                }
                //e开头的数字后续必须为有符号整数
                if (checkE(c)) {
                    if (i == 0 || checkS(chars[i - 1])) {
                        return false;
                    }
                    //判断是否是有符号整数
                    return checkInt(s.substring(i + 1));
                }
                //判断是否是有符号数
                if (checkS(c)) {
                    if (i == chars.length - 1) {
                        return false;
                    }
                    if (i == 0 || checkE(chars[i - 1]) || checkP(chars[i - 1])) {
                        continue;
                    } else {
                        return false;
                    }
                }
                //判断是否包含整数位
                if (checkN(c)) {
                    continue;
                }
                //判断是否小数位
                if (checkP(c)) {
                    //小数点在第一位
                    if (i == 0 || checkS(chars[i - 1])) {
                        //后续位置需要时e或者含e合法整数
                        return checkEInteger(s.substring(i + 1));
                    } else {
                        if (checkN(chars[i - 1])) {
                            return i == s.length() - 1 || checkPInteger(s.substring(i + 1));
                        }
                    }
                }
            }
            return true;
        }

        public boolean checkAll(char c) {
            return checkE(c) || checkS(c) || checkN(c) || checkP(c);
        }

        public boolean checkN(char c) {
            return c >= '0' && c <= '9';
        }

        public boolean checkE(char c) {
            return c == 'e' || c == 'E';
        }

        public boolean checkS(char c) {
            return c == '+' || c == '-';
        }

        public boolean checkP(char c) {
            return c == '.';
        }

        public boolean checkInt(String s) {
            if ("".equals(s)) {
                return false;
            }
            char[] c = s.toCharArray();
            for (int i = checkS(c[0]) ? 1 : 0; i < s.length(); i++) {
                if (!checkN(c[i])) {
                    return false;
                }
                ;
            }
            return checkN(c[s.length() - 1]);
        }

        public boolean checkPInteger(String s) {
            if ("".equals(s)) {
                return false;
            }

            char[] c = s.toCharArray();
            if (checkS(c[0])) {
                return false;
            }
            for (int i = checkS(c[0]) ? 1 : 0; i < s.length(); i++) {
                if (!checkN(c[i])) {
                    if (checkE(c[i])) {
                        return checkInt(s.substring(i + 1));
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }
        public boolean checkEInteger(String s) {
            if ("".equals(s)) {
                return false;
            }

            char[] c = s.toCharArray();
            if (checkE(c[0]) || checkS(c[0])) {
                return false;
            }
            for (int i = checkS(c[0]) ? 1 : 0; i < s.length(); i++) {
                if (!checkN(c[i])) {
                    if (checkE(c[i])) {
                        return checkInt(s.substring(i + 1));
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }*/
        //思路二：多条件匹配
        //对思路一进行一部分优化
        //首先想到了能不能通过排除法做，写着写着发现代码一样恶心
        //后来看了宫水大大的分析，划分区域是个很好的思路
        //通过对E/e进行划分，左侧必须是浮点数，右侧必须是整数
        //整体代码有很多实现思路，但是写起来太麻烦了，上面那个呕心沥血debug完已经没有心情了
        public boolean isNumber(String s) {
            int n = s.length();
            char[] cs = s.toCharArray();
            int idx = -1;
            for (int i = 0; i < n; i++) {
                if (cs[i] == 'e' || cs[i] == 'E') {
                    if (idx == -1) idx = i;
                    else return false;
                }
            }
            boolean ans = true;
            if (idx != -1) {
                ans &= check(cs, 0, idx - 1, false);
                ans &= check(cs, idx + 1, n - 1, true);
            } else {
                ans &= check(cs, 0, n - 1, false);
            }
            return ans;
        }
        boolean check(char[] cs, int start, int end, boolean mustInteger) {
            if (start > end) return false;
            if (cs[start] == '+' || cs[start] == '-') start++;
            boolean hasDot = false, hasNum = false;
            for (int i = start; i <= end; i++) {
                if (cs[i] == '.') {
                    if (mustInteger || hasDot) return false;
                    hasDot = true;
                } else if (cs[i] >= '0' && cs[i] <= '9') {
                    hasNum = true;
                } else {
                    return false;
                }
            }
            return hasNum;
        }
        //时间复杂度O(n)
        //思路三是形式语言与自动机更是本菜🐔大学唯一的挂科科目，根本不想碰
    }
//leetcode submit region end(Prohibit modification and deletion)

}