/*
//给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aacecaaa"
//输出："aaacecaaa"
// 
//
// 示例 2： 
//
// 
//输入：s = "abcd"
//输出："dcbabcd"
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 104 
// s 仅由小写英文字母组成 
// 
// Related Topics 字符串 字符串匹配 哈希函数 滚动哈希 
// 👍 358 👎 0

package com.cute.leetcode.editor.cn;

public class ShortestPalindrome {
    public static void main(String[] args) {
        Solution solution = new ShortestPalindrome().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：hash
        //首先只能在前面添加字符串，也就是说我们需要使前缀的满足最大的回文字符串
        public String shortestPalindrome(String s) {
            //遍历找到最大的
            int len = 1;
            for (int i = 1; i < s.length(); i++) {
                String temp = s.substring(0,i+1);
                if (temp.equals())
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}*/
