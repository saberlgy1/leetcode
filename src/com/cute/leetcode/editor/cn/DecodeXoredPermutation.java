//给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。 
//
// 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说
//，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。 
//
// 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。 
//
// 
//
// 示例 1： 
//
// 输入：encoded = [3,1]
//输出：[1,2,3]
//解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
// 
//
// 示例 2： 
//
// 输入：encoded = [6,5,4,6]
//输出：[2,4,1,5,3]
// 
//
// 
//
// 提示： 
//
// 
// 3 <= n < 105 
// n 是奇数。 
// encoded.length == n - 1 
// 
// Related Topics 位运算 
// 👍 52 👎 0

package com.cute.leetcode.editor.cn;

public class DecodeXoredPermutation {
    public static void main(String[] args) {
        Solution solution = new DecodeXoredPermutation().new Solution();
        for (int i : solution.decode(new int[]{6,5,4,6})
             ) {
            System.out.println(i);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] decode(int[] encoded) {
            //定义最后长度
            int len = encoded.length;
            int[] res = new int[len + 1];
            //因为是前n个数的排列，所以一定会有 n 也就是len +1;
            //异或encoded的奇数位元素 = enc[1] ^ enc[3] … ^ enc[len - 1] = res[1]^res[2]^res[3]……res[len-1]^res[len]
            //异或满足交换律，所以异或所有res元素 = tatal = res[0]^res[1]^……res[len]
            int total = 1,init = 0, odd = encoded[1];
            for (int i = 2 ; i <= len + 1; i++){
                total = total ^ i;
            }
            for (int i = 3; i < len; i = i + 2){
                odd = odd ^ encoded[i];
            }
            init = total ^ odd;
            res[0] = init;
            for (int i = 1 ; i < len  +1; i++){
                res[i] = encoded[i - 1] ^ res[i -1];
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}