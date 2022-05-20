//字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。 
//
// 
//
// 示例 1: 
//
// 输入: 
//first = "pale"
//second = "ple"
//输出: True 
//
// 
//
// 示例 2: 
//
// 输入: 
//first = "pales"
//second = "pal"
//输出: False
// 
// Related Topics 双指针 字符串 👍 180 👎 0

package com.cute.leetcode.editor.cn;

public class OneAwayLcci {
    public static void main(String[] args) {
        Solution solution = new OneAwayLcci().new Solution();
        String a = "horse", b = "hrse";
        System.out.println(solution.oneEditAway(a, b));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean oneEditAway(String first, String second) {
            //corner case
            if (Math.abs(first.length() - second.length()) >= 2) {
                return false;
            }
            //找到划分key即可
            //first为较长key
            if (first.length() != second.length()) {
                if (first.length() < second.length()) {
                    String temp = first;
                    first = second;
                    second = temp;
                }
                for (int i = 0; i < first.length(); i++) {
                    if (second.equals(first.substring(0, i) + first.substring(i + 1, first.length()))) return true;
                }
                return false;
            }

            for (int i = 0; i < first.length(); i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    first = first.substring(0, i) + second.charAt(i) + first.substring(i + 1, first.length());
                    return first.equals(second);
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}