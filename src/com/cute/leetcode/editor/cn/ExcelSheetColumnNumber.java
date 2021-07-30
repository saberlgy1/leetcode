//给定一个Excel表格中的列名称，返回其相应的列序号。 
//
// 例如， 
//
//     A -> 1
//    B -> 2
//    C -> 3
//    ...
//    Z -> 26
//    AA -> 27
//    AB -> 28 
//    ...
// 
//
// 示例 1: 
//
// 输入: "A"
//输出: 1
// 
//
// 示例 2: 
//
// 输入: "AB"
//输出: 28
// 
//
// 示例 3: 
//
// 输入: "ZY"
//输出: 701 
//
// 致谢： 
//特别感谢 @ts 添加此问题并创建所有测试用例。 
// Related Topics 数学 字符串 
// 👍 254 👎 0

package com.cute.leetcode.editor.cn;

public class ExcelSheetColumnNumber {
    public static void main(String[] args) {
        Solution solution = new ExcelSheetColumnNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一 暴力
        //这道题感觉遇到过，不过已经忘记了是哪天了
        //有点类似26进制
        //原来是168反过来
        public int titleToNumber(String columnTitle) {
            char[] chars = columnTitle.toCharArray();
            int res = 0;
            for (char c : chars
            ) {
                int val = c - 'A' + 1;
                res = res * 26 + val;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}