//几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第k小的数字吗？ 
//
// 给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。 
//
// 例 1： 
//
// 
//输入: m = 3, n = 3, k = 5
//输出: 3
//解释: 
//乘法表:
//1	2	3
//2	4	6
//3	6	9
//
//第5小的数字是 3 (1, 2, 2, 3, 3).
// 
//
// 例 2： 
//
// 
//输入: m = 2, n = 3, k = 6
//输出: 6
//解释: 
//乘法表:
//1	2	3
//2	4	6
//
//第6小的数字是 6 (1, 2, 2, 3, 4, 6).
// 
//
// 注意： 
//
// 
// m 和 n 的范围在 [1, 30000] 之间。 
// k 的范围在 [1, m * n] 之间。 
// 
// Related Topics 二分查找 👍 257 👎 0

package com.cute.leetcode.editor.cn;

public class KthSmallestNumberInMultiplicationTable {
    public static void main(String[] args) {
        Solution solution = new KthSmallestNumberInMultiplicationTable().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int m, n, k;

        public int findKthNumber(int _m, int _n, int _k) {
            n = Math.min(_m, _n);
            m = Math.max(_m, _n);
            k = _k;
            int l = 1, r = m * n;
            while (l < r){
                int mid = l + r >> 1, cnt = getCnt(mid);
                if (cnt >=k) r = mid;
                else l = mid + 1;
            }
            return r;
        }

        public int getCnt(int mid ){
            int a = 0, b = 0;
            for (int i = 1; i <= n; i++) {
                if (i * m < mid) {
                    a += m;
                } else {
                    if (mid % i == 0 && ++b >= 0) a += mid / i - 1;
                    else a += mid / i;
                }
            }
            return a + b;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}