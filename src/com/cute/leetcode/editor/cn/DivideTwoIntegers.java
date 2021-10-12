//给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。 
//
// 返回被除数 dividend 除以除数 divisor 得到的商。 
//
// 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2 
//
// 
//
// 示例 1: 
//
// 输入: dividend = 10, divisor = 3
//输出: 3
//解释: 10/3 = truncate(3.33333..) = truncate(3) = 3 
//
// 示例 2: 
//
// 输入: dividend = 7, divisor = -3
//输出: -2
//解释: 7/-3 = truncate(-2.33333..) = -2 
//
// 
//
// 提示： 
//
// 
// 被除数和除数均为 32 位有符号整数。 
// 除数不为 0。 
// 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2³¹, 231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。 
// 
// Related Topics 位运算 数学 👍 677 👎 0

package com.cute.leetcode.editor.cn;

public class DivideTwoIntegers {
    public static void main(String[] args) {
        Solution solution = new DivideTwoIntegers().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：减法模拟除法
        //想简单了，因为不能存储32位整数，所以无法使用long型操作
        //TLE
        /*public int divide(int dividend, int divisor) {
            long res = 0;
            boolean flag = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
            long _dividend = Math.abs((long)dividend);
            long _divisor = Math.abs((long)divisor);
            while (_dividend >= _divisor) {
                _dividend -= _divisor;
                res++;
            }
             res = flag ? -res : res;
            return res >= Integer.MAX_VALUE?Integer.MAX_VALUE:(int)res;
        }*/
        //思路二：加法模拟乘法+二分查找
        //因为存在越界情况，同时不能存储long型字符
        //所以需要对于边界直接判断结果
        //
        public int divide(int dividend, int divisor) {
            int res = 0;
            if (dividend == 0) {
                return 0;
            }

            boolean flag = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }
            if (divisor == Integer.MIN_VALUE) {
                return dividend == Integer.MIN_VALUE ? 1 : 0;
            }
            long _dividend = Math.abs((long) dividend);
            long _divisor = Math.abs((long) divisor);
            long l = 0, r = _dividend;
            while (l < r) {
                long mid = l + r + 1 >> 1;
                if (mul(mid, _divisor) <= _dividend) {
                    l = mid;
                }
                else {
                    r = mid - 1;
                }
            }
            r = flag ? -r : r;
            if (r > Integer.MAX_VALUE || r < -Integer.MAX_VALUE - 1) return Integer.MAX_VALUE;
            return (int)r;

        }

        long mul(long a, long k) {
            long ans = 0;
            while (k > 0) {
                if ((k & 1) == 1) ans += a;
                k >>= 1;
                a += a;
            }
            return ans;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}