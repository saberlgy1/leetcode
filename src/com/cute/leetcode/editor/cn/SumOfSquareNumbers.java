//给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。 
//
// 
//
// 示例 1： 
//
// 输入：c = 5
//输出：true
//解释：1 * 1 + 2 * 2 = 5
// 
//
// 示例 2： 
//
// 输入：c = 3
//输出：false
// 
//
// 示例 3： 
//
// 输入：c = 4
//输出：true
// 
//
// 示例 4： 
//
// 输入：c = 2
//输出：true
// 
//
// 示例 5： 
//
// 输入：c = 1
//输出：true 
//
// 
//
// 提示： 
//
// 
// 0 <= c <= 231 - 1 
// 
// Related Topics 数学 
// 👍 189 👎 0

package com.cute.leetcode.editor.cn;

public class SumOfSquareNumbers {
    public static void main(String[] args) {
        Solution solution = new SumOfSquareNumbers().new Solution();
        System.out.println(solution.judgeSquareSum(5));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean judgeSquareSum(int c) {
            //a2 + b2 + 2ab = (a+b)2
            //a2 + b2 = c
            //c = (a+b)2 - 2ab
            //c + 2ab 能被开根号就代表可以
            //也就是说减去一个偶数能被开方成整数 即可
            //从最小开始遍历
            //我多少事高估了我的智商，这个数学用费马定理属实超出我理解范畴了
            //还是消停双指针遍历吧
            //开根号向下取整
            //int temp = (int) Math.pow(c, 0.5);
            //corner case 负数以及平方数
            if (c < 0) {
                return false;
            }
//            if (temp == Math.pow(c, 0.5)) {
//                return true;
//            }
            //先暴力法 (java超时)
            /*for (int i = 0; i <= temp; i++) {
                for (int j = 0; j <= temp; j++) {
                    if (i * i + j * j == c) {
                        return true;
                    }
                }
            }*/
            //优化1 直接求解j值 无需遍历
            /*int temp = (int) Math.pow(c , 0.5);
            for (int i = 0; i <= temp; i++) {
                int j = (int) Math.pow(c - i * i, 0.5);
                if (i * i + j * j == c) {
                    return true;
                }

            }*/
            //双指针
            int temp = (int) Math.pow(c , 0.5);
            int a = 0, b = temp;
            while (a <= b) {
                if (a* a + b * b == c ){
                    return true;
                }else if(a* a + b * b > c){
                    b--;
                }else{
                    a++;
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}