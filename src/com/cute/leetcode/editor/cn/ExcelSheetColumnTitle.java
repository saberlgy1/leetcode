//给定一个正整数，返回它在 Excel 表中相对应的列名称。
//
// 例如，
//
//     1 -> A
//    2 -> B
//    3 -> C
//    ...
//    26 -> Z
//    27 -> AA
//    28 -> AB
//    ...
//
//
// 示例 1:
//
// 输入: 1
//输出: "A"
//
//
// 示例 2:
//
// 输入: 28
//输出: "AB"
//
//
// 示例 3:
//
// 输入: 701
//输出: "ZY"
//
// Related Topics 数学 字符串
// 👍 387 👎 0

package com.cute.leetcode.editor.cn;

public class ExcelSheetColumnTitle {
    public static void main(String[] args) {
        Solution solution = new ExcelSheetColumnTitle().new Solution();
        System.out.println(solution.convertToTitle(701));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：26进制转换
        //整体题意还是比较清晰的，类似二进制的转换
        //区别在于本题没有0这个数也就是说每位最小位A
        //然后通过char值可以进行转换
        //本题特殊测试用例 52 701 1
        public String convertToTitle(int columnNumber) {
            String res = "";
            while (columnNumber != 0) {
                int temp = columnNumber % 26 == 0 ? 26 : columnNumber % 26;
                //因为不能有0 所以需要当余数为0时需保留当前位置Z，并当前运算数-26
                if (temp == 26) {
                    columnNumber -= 26;
                }
                columnNumber = columnNumber == 26 ? 1 : columnNumber / 26;
                res = Character.valueOf((char) (temp + 64)).toString() + res;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}