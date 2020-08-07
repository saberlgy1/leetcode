//给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。 
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 示例 1: 
//
// 输入: [1,2,3]
//输出: [1,2,4]
//解释: 输入数组表示数字 123。
// 
//
// 示例 2: 
//
// 输入: [4,3,2,1]
//输出: [4,3,2,2]
//解释: 输入数组表示数字 4321。
// 
// Related Topics 数组 
// 👍 521 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class PlusOne {
    public static void main(String[] args) {
        Solution solution = new PlusOne().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] plusOne(int[] digits) {
            int flag = 0;
            int temp = 1;
            int len = digits.length;
            for (int i = len - 1; i >= 0; i--) {
                if (digits[i] + flag + temp >= 10) {

                    digits[i] = (digits[i]+ flag + temp) % 10;
                    flag = 1;
                }else{

                    digits[i] = digits[i]+ flag + temp;
                    flag = 0;
                }
                temp = 0;
            }
            if (flag == 1){
                 int[] array = new int[len + 1];
                 array[0] = 1;
                 for (int i = 1; i < len + 1; i ++){
                     array[i] = digits[i - 1];
                 }
                 return array;
            }
            return  digits;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}