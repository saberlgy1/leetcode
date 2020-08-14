//实现 int sqrt(int x) 函数。 
//
// 计算并返回 x 的平方根，其中 x 是非负整数。 
//
// 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。 
//
// 示例 1: 
//
// 输入: 4
//输出: 2
// 
//
// 示例 2: 
//
// 输入: 8
//输出: 2
//说明: 8 的平方根是 2.82842..., 
//     由于返回类型是整数，小数部分将被舍去。
// 
// Related Topics 数学 二分查找 
// 👍 466 👎 0

package com.cute.leetcode.editor.cn;

public class Sqrtx {
    public static void main(String[] args) {
        Solution solution = new Sqrtx().new Solution();
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Math.sqrt(2147473647));
        solution.mySqrt(1);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int mySqrt(int x) {
            int right = (int)Math.sqrt(Integer.MAX_VALUE);
            int left = 0;
            int flag = 0;
            while (left<= right){
                flag =left + (right - left)/2;
                if (flag * flag < x){
                    left = flag + 1;
                }else if (flag * flag > x){
                    right  = flag - 1;
                }else {
                    return flag;
                }


            }
            return flag* flag > x? flag - 1: flag;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}