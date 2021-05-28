//两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。 
//
// 计算一个数组中，任意两个数之间汉明距离的总和。 
//
// 示例: 
//
// 
//输入: 4, 14, 2
//
//输出: 6
//
//解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。（这样表示是为了体现后四位之间关系）
//所以答案为：
//HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 
//2 + 2 = 6.
// 
//
// 注意: 
//
// 
// 数组中元素的范围为从 0到 10^9。 
// 数组的长度不超过 10^4。 
// 
// Related Topics 位运算 
// 👍 162 👎 0

package com.cute.leetcode.editor.cn;

public class TotalHammingDistance {
    public static void main(String[] args) {
        Solution solution = new TotalHammingDistance().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //最开始的想法是按照昨天的异或结果，然后依次计算
        //事实证明想的太简单会TLE O(n * n)
        //看到官方题解想到的思路
        //对于数组内的所有元素，只需要所有元素二进制没位上统计1和0的个数，每一位的汉明距离总和即为 1的个数 * 0的个数
        //上述含义可以有另一种解释，汉明距离只需要统计 1 0这种情况，对于11 00的情况都不需要参与考虑和计算，所以1的个数*0的个数即为每一数字的汉明距离总和
        //比如 示例中 4 14 2
        //0 1 0 0
        //1 1 1 0
        //0 0 1 0
        //总和即为 第一列 1 * 2 + 2 * 1 + 2 * 1 + 0 * 0 = 6
        //O(n*C)C为常数
        public int totalHammingDistance(int[] nums) {
            int res = 0, n = nums.length;
            //因为题目中最大数字位10^9 < 2^30
            //所以可以只需要计算30位的距离总和
            for (int i = 0; i < 30; i++) {
                int count = 0;
                for (int num : nums) {
                    /*num >>= i;
                    if (num % 2 ==0){
                        count +=1;
                    }*/
                    //这一段优化了计算次数和判断时间
                    count += (num >> i) & 1;

                }
                res = res + (n - count) * count;
            }
            return res;
        }
       /* public int totalHammingDistance(int[] nums) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    sum += sumDistance(nums[i], nums[j]);
                }
            }
            return sum;
        }

        private int sumDistance(int i, int j) {
            int sum = i ^ j;
            int res = 0;
            while (sum != 0) {
                if (sum % 2 != 0) {
                    res += 1;
                }
                sum /= 2;
            }
            return res;
        }*/

    }
//leetcode submit region end(Prohibit modification and deletion)

}