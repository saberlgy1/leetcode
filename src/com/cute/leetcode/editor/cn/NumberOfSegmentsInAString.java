//统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。 
//
// 请注意，你可以假定字符串里不包括任何不可打印的字符。 
//
// 示例: 
//
// 输入: "Hello, my name is John"
//输出: 5
//解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
// 
// Related Topics 字符串 👍 132 👎 0

package com.cute.leetcode.editor.cn;

public class NumberOfSegmentsInAString {
    public static void main(String[] args) {
        Solution solution = new NumberOfSegmentsInAString().new Solution();
        String s = "    foo    bar";
        System.out.println(solution.countSegments(s));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：原生函数
        //通过至少一个空格分割字符串
        //每个字符串不等于空res++
        //返回res即可
        /*public int countSegments(String s) {
            String[] arr = s.split(" +");
            int res = 0;
            for (String str:arr
                 ) {
                if (!"".equals(str)){
                    res++;
                }
            }
            return res;
        }*/
        //思路二：双指针
        public int countSegments(String s) {
            StringBuilder sb = new StringBuilder(s);
            sb.append(" ");
            int l = 0, res = 0;
            while (l < sb.length()) {
                while (l < sb.length() && sb.charAt(l) == ' ') {
                    l++;
                }
                int r = l + 1;
                while (r < sb.length()) {
                    if (sb.charAt(r) == ' ') {
                        l = r;
                        res++;
                        break;
                    }
                    r++;
                }

            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}