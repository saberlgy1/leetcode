//编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
//
//
//
// 示例 1：
//
// 输入："hello"
//输出："holle"
//
//
// 示例 2：
//
// 输入："leetcode"
//输出："leotcede"
//
//
//
// 提示：
//
//
// 元音字母不包含字母 "y" 。
//
// Related Topics 双指针 字符串
// 👍 182 👎 0

package com.cute.leetcode.editor.cn;

public class ReverseVowelsOfAString {
    public static void main(String[] args) {
        Solution solution = new ReverseVowelsOfAString().new Solution();
        System.out.println(solution.reverseVowels("aA"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：双指针
        //暴力法是可解 后续看看时间来不来得及补充一下
        //双指针 lr 分别只想左右第一个元音字母的索引
        //跳出条件是lr相等
        //每遍历到一个元音，交换lr
        //l右移，r左移
        public String reverseVowels(String s) {
            char[] res = s.toCharArray();
            int l = findl(res, 0), r = findr(res, s.length() - 1);
            while (l < r) {
                swap(res, l, r);
                l = findl(res, l + 1);
                r = findr(res, r - 1);
            }
            return String.valueOf(res);
        }

        public int findl(char[] chars, int l) {
            while (l < chars.length) {
                if (check(chars[l])) {
                    return l;
                }
                l++;
            }
            return l;
        }

        public int findr(char[] chars, int r) {
            while (r >= 0) {
                if (check(chars[r])) {
                    return r;
                }
                r--;
            }
            return r;
        }

        public void swap(char[] chars, int l, int r) {
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
        }

        public boolean check(char c) {
            c = Character.toLowerCase(c);
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
