//给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
//
//
//
// 示例 1：
//
//
//输入：n = 13
//输出：6
//
//
// 示例 2：
//
//
//输入：n = 0
//输出：0
//
//
//
//
// 提示：
//
//
// 0 <= n <= 2 * 109
//
// Related Topics 递归 数学 动态规划
// 👍 253 👎 0

package com.cute.leetcode.editor.cn;

public class NumberOfDigitOne {
    public static void main(String[] args) {
        Solution solution = new NumberOfDigitOne().new Solution();
        System.out.println(solution.countDigitOne(12304));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力扫描
        //拼接成字符串 然后统计
        //如果这不TLE实在是对不起hard的难度
        /*public int countDigitOne(int n) {
            int res = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                sb.append(i);
            }
            for (char c:sb.toString().toCharArray()
                 ) {
                if (c == '1'){
                    res+=1;
                }
            }
            return res;
        }*/
        //思路二：数学规律
//        对于整数12304来说
//        我们可以定义当前位上的数字位cur
//        ex：计算cur == 0的时候，也就是指针移动到10位的时候
//        可以发现包含1的数字范围可以从00010～12219以及
//        数量 cnt = 123*10
//        ex：计算cur == 1的时候，也就是指针移动到10000位的时候
//        可以发现包含1的数字范围可以从10000～12304
//        数量 cnt = 0*10000 + 2304 + 1
//        ex：计算cur > 1的时候，也就是指针移动到1、100、1000这三个位数的时候
//        1位上包含1的数字范围 00001～12301
//        100位上包含1的数字范围 00101～12199
//        1000位上包含1的数字范围 01001～11999
//        cnt1 = （1230+1）* 1
//        cnt100 = (12+1) * 100
//        cnt1000 = (1+1) * 1000
//        res = 1231 + 1230 + 1300 + 2000 +2305
        public int countDigitOne(int n) {
            int res = 0, digit = 1, high = n / 10, cur = n % 10, low = 0;
            while (high != 0 || cur != 0) {
                if (cur == 0) {
                    res += high * digit;
                } else if (cur == 1) {
                    res = res + high * digit + 1 + low;
                } else {
                    res += (high + 1) * digit;
                }
                low += cur * digit;
                digit *= 10;
                cur = high % 10;
                high /= 10;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}