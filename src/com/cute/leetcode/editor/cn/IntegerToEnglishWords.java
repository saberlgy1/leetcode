//将非负整数 num 转换为其对应的英文表示。 
//
// 
//
// 示例 1： 
//
// 
//输入：num = 123
//输出："One Hundred Twenty Three"
// 
//
// 示例 2： 
//
// 
//输入：num = 12345
//输出："Twelve Thousand Three Hundred Forty Five"
// 
//
// 示例 3： 
//
// 
//输入：num = 1234567
//输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
// 
//
// 示例 4： 
//
// 
//输入：num = 1234567891
//输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven 
//Thousand Eight Hundred Ninety One"
// 
//
// 
//
// 提示： 
//
// 
// 0 <= num <= 2³¹ - 1 
// 
// Related Topics 递归 数学 字符串 👍 198 👎 0

package com.cute.leetcode.editor.cn;
public class IntegerToEnglishWords {
    public static void main(String[] args) {
        Solution solution = new IntegerToEnglishWords().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        static String[] num2str_small = {
                "Zero",
                "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
        };
        static String[] num2str_medium = {
                "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
        };
        static String[] num2str_large = {
                "Billion", "Million", "Thousand", "",
        };
        String num2Str(int x) {
            String ans = "";
            if (x >= 100) {
                ans += num2str_small[x / 100] + " Hundred ";
                x %= 100;
            }
            if (x >= 20) {
                ans += num2str_medium[x / 10] + " ";
                x %= 10;
            }
            if (x != 0) ans += num2str_small[x] + " ";
            return ans;
        }
        public String numberToWords(int num) {
            if (num == 0) return num2str_small[0];
            StringBuilder sb = new StringBuilder();
            for (int i = (int)1e9, j = 0; i >= 1; i /= 1000, j++) {
                if (num < i) continue;
                sb.append(num2Str(num / i) + num2str_large[j] + " ");
                num %= i;
            }
            while (sb.charAt(sb.length() - 1) == ' ') sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}