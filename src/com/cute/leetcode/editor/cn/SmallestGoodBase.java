//对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，则称 k（k>=2）是 n 的一个好进制。
//
// 以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。
//
//
//
// 示例 1：
//
//
//输入："13"
//输出："3"
//解释：13 的 3 进制是 111。
//
//
// 示例 2：
//
//
//输入："4681"
//输出："8"
//解释：4681 的 8 进制是 11111。
//
//
// 示例 3：
//
//
//输入："1000000000000000000"
//输出："999999999999999999"
//解释：1000000000000000000 的 999999999999999999 进制是 11。
//
//
//
//
// 提示：
//
//
// n的取值范围是 [3, 10^18]。
// 输入总是有效且没有前导 0。
//
//
//
// Related Topics 数学 二分查找
// 👍 65 👎 0

package com.cute.leetcode.editor.cn;

public class SmallestGoodBase {
    public static void main(String[] args) {
        Solution solution = new SmallestGoodBase().new Solution();
        System.out.println(solution.smallestGoodBase("1000000000000000000"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*思路一：等比数列求和
        $$S_n=\dfrac{a_1-a_nq}{1-q}$$
        s = (a1-an*q)/(1-q)
        a1 = 1 an = q^(n-1)
        s = (1-q^n)/(1-q)
        q是公比 n是s转换成q进制后1的个数 n越大q越小
        n最大不超过(s-1)因为s一定可以转换成s-1进制的好进制格式即（11）
        因为求的是最小好进制，所以可以从最小公比开始遍历遍历范围[2,s-1]
        i标识为公比 从i= 2开始
        推出tempT = s*(i-1)+1
        判断tempT的最大
        这种方法会TLE*/
        /*public String smallestGoodBase(String s) {
            long target = Long.parseLong(s);
            for (long i = 2; i < target; i++) {
                long tempT = target * (i - 1) + 1;
                //排除不满足的i情况
                if (tempT % i != 0) {
                    continue;
                }
                //判断是否能够满足tempT是i的n次幂
                while (tempT % i == 0 && tempT != i) {
                    tempT /= i;
                }
                if (tempT == i) {
                    return String.valueOf(i);
                }
            }
            return String.valueOf(target - 1);
        }*/
        //思路二：范围区间判断
        //思路一已经确认了此题一定有解s-1
        //tempT = i^n < s*i
        //s 最大 10^8 i 最小 2
        //n<log_i^s + 1<60
        //再根据二项式定理（官方题解可以推出i = $$ \sqrt[m]{n}$$的下线整数）
        //根据n从最大值开始枚举满足条件的i的数，第一个遇到的即为k的最小值（n标识i进制位数）
        //时间复杂度(O(logn*logn))
        public String smallestGoodBase(String s) {
            long nVal = Long.parseLong(s);
            int mMax = (int) Math.floor(Math.log(nVal) / Math.log(2));
            for (int n = mMax; n > 1; n--) {
                int i = (int) Math.pow(nVal, 1.0 / n);
                long mul = 1, sum = 1;
                for (int t = 0; t < n; t++) {
                    mul *= i;
                    sum += mul;
                }
                if (sum == nVal) {
                    return Integer.toString(i);
                }
            }
            return Long.toString(nVal - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}