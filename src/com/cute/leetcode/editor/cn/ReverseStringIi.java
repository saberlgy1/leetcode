//给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
//
//
// 如果剩余字符少于 k 个，则将剩余字符全部反转。
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
//
//
//
//
// 示例 1：
//
//
//输入：s = "abcdefg", k = 2
//输出："bacdfeg"
//
//
// 示例 2：
//
//
//输入：s = "abcd", k = 2
//输出："bacd"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 104
// s 仅由小写英文组成
// 1 <= k <= 104
//
// Related Topics 双指针 字符串
// 👍 157 👎 0

package com.cute.leetcode.editor.cn;

public class ReverseStringIi {
    public static void main(String[] args) {
        Solution solution = new ReverseStringIi().new Solution();
        System.out.println(solution.reverseStr("abcdefg", 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：双指针
        //快慢指针移动
        //反转字符
        /*char[] chars;

        public String reverseStr(String s, int k) {
            int n = s.length();
            chars = s.toCharArray();
            StringBuilder res = new StringBuilder();
            if (k >= n) {
                for (int i = n - 1; i >= 0; i--) {
                    res.append(chars[i]);
                }
                return res.toString();
            }
            int l = 0;

            while (l + 2 * k < n) {
                res.append(reverse(s, l, l + 2 * k));
                l += 2 * k;
            }
            if (l + k < n) {
                for (int i = l + k - 1; i >= l; i--) {
                    res.append(chars[i]);
                }
                l = l + k;
                for (int i = l; i < n; i++) {
                    res.append(chars[i]);
                }
            } else {
                for (int i = n - 1; i >= l; i--) {
                    res.append(chars[i]);
                }
            }
            return res.toString();
        }

        public String reverse(String s, int l, int r) {
            StringBuilder sb = new StringBuilder();
            for (int i = l + (r - l) / 2 - 1; i >= l; i--) {
                sb.append(chars[i]);
            }
            for (int i = l + (r - l) / 2; i < r; i++) {
                sb.append(chars[i]);
            }
            return sb.toString();
        }*/
        //思路二：优雅的双指针
        //上述逻辑判断太多了，其实没有必要
        char[] chars;

        public String reverseStr(String s, int k) {
            int n = s.length();
            chars = s.toCharArray();
            StringBuilder res = new StringBuilder();
            int l = 0, r = l + k;
            while (r < n) {
                res.append(reverse(s, l, r));
                res.append(s, r, Math.min(r + k, n));
                l += 2 * k;
                r = l + k;
            }
            if (l < n) {
                res.append(reverse(s, l, n));
            }

            return res.toString();
        }

        public String reverse(String s, int l, int r) {
            StringBuilder sb = new StringBuilder();
            for (int i = r - 1; i >= l; i--) {
                sb.append(chars[i]);
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}