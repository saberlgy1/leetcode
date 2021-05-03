//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。 
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−231, 231 − 1] ，就返回 0。 
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
// 
//
// 示例 1： 
//
// 
//输入：x = 123
//输出：321
// 
//
// 示例 2： 
//
// 
//输入：x = -123
//输出：-321
// 
//
// 示例 3： 
//
// 
//输入：x = 120
//输出：21
// 
//
// 示例 4： 
//
// 
//输入：x = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// -231 <= x <= 231 - 1 
// 
// Related Topics 数学 
// 👍 2743 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Stack;

public class ReverseInteger {
    public static void main(String[] args) {
        Solution solution = new ReverseInteger().new Solution();
        System.out.println(solution.reverse(1534236469));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int reverse(int x) {


            //转换成string 然后插入
            /*String s = String.valueOf(x);
            char[] temp = x >=0 ? s.toCharArray(): s.substring(1).toCharArray();
            for (char c : temp) {
                stack.push((int)c - 48);
            }
            int res = 0;
            while (stack.size() >0){
                if (Integer.MAX_VALUE / 10 >= res &&Integer.MAX_VALUE - res * 10 >= stack.peek()){
                    res = res * 10 + stack.pop();
                }else{
                    return 0;
                }
            }*/
            //我好想想的略微有些复杂了
            int res = 0;
            while (x != 0 ){
                if (res < Integer.MIN_VALUE / 10 || res > Integer.MAX_VALUE / 10) {
                    return 0;
                }
                int temp = x % 10;
                res = res * 10 + temp;
                x = x /10;
            }
            return  res ;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}