//给你一个字符串 time ，格式为 hh:mm（小时：分钟），其中某几位数字被隐藏（用 ? 表示）。
//
// 有效的时间为 00:00 到 23:59 之间的所有时间，包括 00:00 和 23:59 。
//
// 替换 time 中隐藏的数字，返回你可以得到的最晚有效时间。
//
//
//
// 示例 1：
//
//
//输入：time = "2?:?0"
//输出："23:50"
//解释：以数字 '2' 开头的最晚一小时是 23 ，以 '0' 结尾的最晚一分钟是 50 。
//
//
// 示例 2：
//
//
//输入：time = "0?:3?"
//输出："09:39"
//
//
// 示例 3：
//
//
//输入：time = "1?:22"
//输出："19:22"
//
//
//
//
// 提示：
//
//
// time 的格式为 hh:mm
// 题目数据保证你可以由输入的字符串生成有效的时间
//
// Related Topics 字符串
// 👍 19 👎 0

package com.cute.leetcode.editor.cn;

public class LatestTimeByReplacingHiddenDigits {
    public static void main(String[] args) {
        Solution solution = new LatestTimeByReplacingHiddenDigits().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //这道题似乎有些过于简单
        //时间复杂度其实都是O(1),区别就在于语言底层使用什么更快，从char数组-》string-》stringBuilder
        public String maximumTime(String time) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                if (time.charAt(i) == '?') {
                    if (i == 0) res.append(time.charAt(1) == '?' ? '2' : time.charAt(1) >= '4' ? '1' : '2');
                    else if (i == 1) res.append(res.charAt(0) == '2' ? '3' : '9');
                    else if (i == 3) res.append('5');
                    else if (i == 4) res.append('9');
                } else {
                    res.append(time.charAt(i));
                }
            }
            return res.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}