//给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。 
//
// 注意: 
//
// 
// 十六进制中所有字母(a-f)都必须是小写。 
// 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
// 给定的数确保在32位有符号整数范围内。 
// 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。 
// 
//
// 示例 1： 
//
// 
//输入:
//26
//
//输出:
//"1a"
// 
//
// 示例 2： 
//
// 
//输入:
//-1
//
//输出:
//"ffffffff"
// 
// Related Topics 位运算 数学 👍 183 👎 0

package com.cute.leetcode.editor.cn;

public class ConvertANumberToHexadecimal {
    public static void main(String[] args) {
        Solution solution = new ConvertANumberToHexadecimal().new Solution();
        System.out.println(solution.toHex(-1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：进制转换-模的概念

        /*public String toHex(int num) {
            if (num == 0) {
                return "0";
            }
            long n = num;
            char[] c = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f'};
            if (num < 0) {
                n = (long) (num + Math.pow(2, 32));
            }
            StringBuilder sb = new StringBuilder();
            while (n != 0) {
                sb = sb.insert(0, c[(int)(n % 16)]);
                n /= 16;
            }
            return sb.toString();
        }*/
        //思路二：正常进制转换
        //正常十进制转十六进制就是先转成二进制，然后四个一组转换成十六进制
        //和（1111）2做与运算即可求的从后到前每四位的值
        //然后reverse返回即可
        public String toHex(int num) {
            char[] c = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f'};
            if (num == 0) {
                return "0";
            }
            StringBuilder sb = new StringBuilder();
            while (num != 0) {
                int val = num & 15;
                sb.append(c[val]);
                num >>>= 4;
            }
            return sb.reverse().toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}