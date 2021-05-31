//给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。 
//
// 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4x 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 16
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：n = 5
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：n = 1
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// -231 <= n <= 231 - 1 
// 
//
// 
//
// 进阶： 
//
// 
// 你能不使用循环或者递归来完成本题吗？ 
// 
// Related Topics 位运算 
// 👍 196 👎 0

package com.cute.leetcode.editor.cn;

public class PowerOfFour {
    public static void main(String[] args) {
        Solution solution = new PowerOfFour().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路1：循环或者递归

        /*public boolean isPowerOfFour(int n) {
            if (n <= 0) {
                return false;
            }
            if (n == 1) {
                return true;
            }
            if (n % 4 != 0) {
                return false;
            }
            return isPowerOfFour(n /= 4);

        }*/
        //思路2：非递归方式、位运算
        //二进制位1的判断
        //4的x次幂 二进制表示 1后面接入偶数个0（100 1000 1000000）
        //必须保证 n & n-1 ==0 且保证奇数位均为0
        /*public boolean isPowerOfFour(int n) {
            return n > 0 && ((n & (n-1)) == 0) && ((n & 0xaaaaaaaa) ==0);
        }*/
        //思路3：非递归方式，多项式拆解
        //4^x = (3+1)^x
        //多项式拆解后 必定会余下1
        //所以判断现实2的x次幂（n & (n-1) == 0）
        //在判断是否余1 如果余2 则是2的x次幂而不是4的x次幂
        //余数为2 则n = 4^x * 2 必定会余1 *2
        public boolean isPowerOfFour(int n) {
            return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}