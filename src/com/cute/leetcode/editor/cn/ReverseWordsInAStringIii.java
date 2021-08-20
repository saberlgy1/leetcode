//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。 
//
// 
//
// 示例： 
//
// 输入："Let's take LeetCode contest"
//输出："s'teL ekat edoCteeL tsetnoc"
// 
//
// 
//
// 提示： 
//
// 
// 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。 
// 
// Related Topics 双指针 字符串 👍 315 👎 0

package com.cute.leetcode.editor.cn;

public class ReverseWordsInAStringIii {
    public static void main(String[] args) {
        Solution solution = new ReverseWordsInAStringIii().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseWords(String s) {
            StringBuilder sb = new StringBuilder();
            String[] strs = s.split(" ");
            for (int i = 0; i < strs.length; i++) {
                strs[i] = reverse(strs[i]);
            }
            for (String str : strs) {
                sb.append(str);
                sb.append(" ");
            }
            return sb.toString().substring(0, sb.length() - 1);
        }
        public String reverse(String s){
            char[] chars = s.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = chars.length-1;i>=0;i--){
                sb.append(chars[i]);
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}