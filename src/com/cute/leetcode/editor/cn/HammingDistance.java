//两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。 
//
// 给出两个整数 x 和 y，计算它们之间的汉明距离。 
//
// 注意： 
//0 ≤ x, y < 231. 
//
// 示例: 
//
// 
//输入: x = 1, y = 4
//
//输出: 2
//
//解释:
//1   (0 0 0 1)
//4   (0 1 0 0)
//       ↑   ↑
//
//上面的箭头指出了对应二进制位不同的位置。
// 
// Related Topics 位运算 
// 👍 426 👎 0

package com.cute.leetcode.editor.cn;

public class HammingDistance {
    public static void main(String[] args) {
        Solution solution = new HammingDistance().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int hammingDistance(int x, int y) {
            //二进制位不同很容易想到异或
            int sum = x ^ y, res = 0;
            //然后统计异或结果1的位数即可
            /*String s = Integer.toBinaryString(sum);
            for (Character c: s.toCharArray()
                 ) {
                if (c == '1'){
                    res +=1;
                }
            }*/
            //优化1 通过对2取余来做
            while (sum!=0){
                if (sum % 2 != 0){
                    res+=1;
                }
                sum /=2;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}