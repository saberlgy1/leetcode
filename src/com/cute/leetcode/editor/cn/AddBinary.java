//给你两个二进制字符串，返回它们的和（用二进制表示）。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1:
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 数学 字符串 
// 👍 446 👎 0

package com.cute.leetcode.editor.cn;

public class AddBinary {
    public static void main(String[] args) {
        Solution solution = new AddBinary().new Solution();
        System.out.println(solution.addBinary("100", "110010"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String addBinary(String a, String b) {

            if (a.length() < b.length()) {
                return addBinary(b, a);
            }
            int flag = 0;
            //补齐的多余消耗 O(2 * max(m,n))
           /* StringBuilder bBuilder = new StringBuilder(b);
            if (a.length() != b .length()){
                for (int i = 0; i < a.length() - b.length(); i++) {
                    bBuilder.insert(0, "0");
                }
            }

            b = bBuilder.toString();
            StringBuilder s = new StringBuilder();
            for (int i = a.length() - 1; i >= 0; i--) {
                if ((flag + Integer.parseInt(String.valueOf(a.charAt(i))) + Integer.parseInt(String.valueOf(b.charAt(i))) >= 2)) {
                    s.insert(0, (flag + Integer.parseInt(String.valueOf(a.charAt(i))) + Integer.parseInt(String.valueOf(b.charAt(i)))) % 2);
                    flag = 1;
                } else {
                    s.insert(0, (flag + Integer.parseInt(String.valueOf(a.charAt(i))) + Integer.parseInt(String.valueOf(b.charAt(i)))));
                    flag = 0;
                }
            }
            if (flag == 1) {
                s.insert(0, "1");
            }*/
            //不用补齐
           /* int lena = a.length();
            int lenb = b.length();
            StringBuilder s = new StringBuilder();
            for (int i =1; i <= b.length(); i++) {
                if ((flag + Integer.parseInt(String.valueOf(a.charAt(lena - i))) + Integer.parseInt(String.valueOf(b.charAt(lenb - i))) >= 2)) {
                    s.insert(0, (flag + Integer.parseInt(String.valueOf(a.charAt(lena - i))) + Integer.parseInt(String.valueOf(b.charAt(lenb - i)))) % 2);
                    flag = 1;
                } else {
                    s.insert(0, (flag + Integer.parseInt(String.valueOf(a.charAt(lena - i))) + Integer.parseInt(String.valueOf(b.charAt(lenb - i)))));
                    flag = 0;
                }
            }
            for (int i = lenb+1; i <= lena; i++) {
                if ((flag + Integer.parseInt(String.valueOf(a.charAt(lena - i)))) >= 2) {
                    s.insert(0, (flag + Integer.parseInt(String.valueOf(a.charAt(lena - i)))) % 2);
                    flag = 1;
                } else {
                    s.insert(0, (flag + Integer.parseInt(String.valueOf(a.charAt(lena - i)))));
                    flag = 0;
                }
            }
            if (flag == 1) {
                s.insert(0, "1");
            }
            return s.toString();*/
           //精简版 一个carry取代了运算以及flag位
            StringBuffer ans = new StringBuffer();

            int n = Math.max(a.length(), b.length()), carry = 0;
            for (int i = 0; i < n; ++i) {
                carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
                carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
                ans.append((char) (carry % 2 + '0'));
                carry /= 2;
            }

            if (carry > 0) {
                ans.append('1');
            }
            ans.reverse();

            return ans.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}