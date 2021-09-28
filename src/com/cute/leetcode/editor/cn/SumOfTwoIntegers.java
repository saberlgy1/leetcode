//给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。
//
//
//
// 示例 1：
//
//
//输入：a = 1, b = 2
//输出：3
//
//
// 示例 2：
//
//
//输入：a = 2, b = 3
//输出：5
//
//
//
//
// 提示：
//
//
// -1000 <= a, b <= 1000
//
// Related Topics 位运算 数学 👍 521 👎 0

package com.cute.leetcode.editor.cn;

public class SumOfTwoIntegers {
    public static void main(String[] args) {
        Solution solution = new SumOfTwoIntegers().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：位运算
        //记录两个当前位的和以及进位关系
        //通过分类讨论
        /*public int getSum(int a, int b) {
            int res = 0;
            for (int i = 0, t = 0; i < 32; i++) {
                int num1 = (a >> i) & 1, num2 = (b >> i) & 1;
                if (num1 == 1 && num2 == 1) {
                    res |= (t << i);
                    t = 1;
                } else if (num1 == 1 || num2 == 1) {
                    res |= ((1 ^ t) << i);
                }else{
                    res |= (t << i);
                    t = 0;
                }
            }
            return res;
        }*/
        //思路二：位运算
        public int getSum(int a, int b) {
            return b == 0 ? a : getSum(a ^ b, (a & b) << 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}