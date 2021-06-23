//请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 
//9，则该函数输出 2。 
//
// 
//
// 示例 1： 
//
// 
//输入：00000000000000000000000000001011
//输出：3
//解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
// 
//
// 示例 2： 
//
// 
//输入：00000000000000000000000010000000
//输出：1
//解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
// 
//
// 示例 3： 
//
// 
//输入：11111111111111111111111111111101
//输出：31
//解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。 
//
// 
//
// 提示： 
//
// 
// 输入必须是长度为 32 的 二进制串 。 
// 
//
// 
//
// 注意：本题与主站 191 题相同：https://leetcode-cn.com/problems/number-of-1-bits/ 
// Related Topics 位运算 
// 👍 131 👎 0

package com.cute.leetcode.editor.cn;

public class ErJinZhiZhong1deGeShuLcof {
    public static void main(String[] args) {
        Solution solution = new ErJinZhiZhong1deGeShuLcof().new Solution();
        System.out.println(solution.hammingWeight(00000000000000000000000000001011));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        // you need to treat n as an unsigned value
        //思路一：位运算
        //这会TLE我属实是没想到的
        //看不出来优化的地方0 0可能需要异或？
        //看来需要优化的事2n-》n
        /*public int hammingWeight(int n) {
            int res = 0;
            while (n != 0) {
                if ((n & 1) == 1) {
                    res += 1;
                }
                n >>= 1;
            }
            return res;
        }*/
        //思路二：java Integer库调用
        /*public int hammingWeight(int n) {
           return Integer.bitCount(n);
        }*/
        //思路三：与运算
        //n &= (n-1) 会将最后位1变成0
        /*public int hammingWeight(int n) {
            int res = 0;
            while (n != 0) {
                n &= n - 1;
                res += 1;
            }
            return res;
        }*/
        //思路四：思路一的优化
        //因为有整型限制，所以可以限制循环次数32次
       /* public int hammingWeight(int n) {
            int ret = 0;
            for (int i = 0; i < 32; i++) {
                if ((n & (1 << i)) != 0) {
                    ret += 1;
                }
            }
            return ret;
        }*/
        //思路五：javaInteger类库实现
        /*public int hammingWeight(int i) {
            // HD, Figure 5-2
            i = i - ((i >>> 1) & 0x55555555);
            i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
            i = (i + (i >>> 4)) & 0x0f0f0f0f;
            i = i + (i >>> 8);
            i = i + (i >>> 16);
            return i & 0x3f;
        }*/
        //思路六：思路一的又一次优化
        public int hammingWeight(int n) {
            int res = 0;
            while (n != 0) {
                res += (n & 1);
                n >>>= 1;
            }
            return res;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}