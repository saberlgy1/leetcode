//这里有 n 个航班，它们分别从 1 到 n 进行编号。
//
// 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从
//firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
//
// 请你返回一个长度为 n 的数组 answer，其中 answer[i] 是航班 i 上预订的座位总数。
//
//
//
// 示例 1：
//
//
//输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
//输出：[10,55,45,25,25]
//解释：
//航班编号        1   2   3   4   5
//预订记录 1 ：   10  10
//预订记录 2 ：       20  20
//预订记录 3 ：       25  25  25  25
//总座位数：      10  55  45  25  25
//因此，answer = [10,55,45,25,25]
//
//
// 示例 2：
//
//
//输入：bookings = [[1,2,10],[2,2,15]], n = 2
//输出：[10,25]
//解释：
//航班编号        1   2
//预订记录 1 ：   10  10
//预订记录 2 ：       15
//总座位数：      10  25
//因此，answer = [10,25]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 2 * 10⁴
// 1 <= bookings.length <= 2 * 10⁴
// bookings[i].length == 3
// 1 <= firsti <= lasti <= n
// 1 <= seatsi <= 10⁴
//
// Related Topics 数组 前缀和 👍 179 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;

public class CorporateFlightBookings {
    public static void main(String[] args) {
        Solution solution = new CorporateFlightBookings().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力法
        //目前好像是不会TLE
        /*public int[] corpFlightBookings(int[][] bookings, int n) {
            int[] res = new int[n];
            for (int[] booking : bookings
            ) {
                int l = booking[0] - 1, r = booking[1] - 1;
                while (l <= r) {
                    res[l] += booking[2];
                    l++;
                }
            }
            return res;
        }*/
        //思路二：差分数组+前缀和
        //这道题当然不能用这么简单的暴力做法来做了
        //差分数组
//        定义一个数组d[] d[i] = a[i]-a[i-1];
//        初始化d[i] = 0
//        每加上一个区间三元组[l,r,cnt] l->r 有cnt个订单
//        d[l] += cnt,d[r+1]-=cnt;
//        这样就可以通过d反推最后的res数组
        public int[] corpFlightBookings(int[][] bookings, int n) {
            int[] d = new int[n + 1];
            for (int[] booking : bookings) {
                int l = booking[0] - 1, r = booking[1] - 1, cnt = booking[2];
                d[l] += cnt;
                d[r + 1] -= cnt;
            }
            for (int i = 1; i < n; i++) {
                d[i] +=d[i-1];
            }
            return Arrays.copyOfRange(d,0,d.length-1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}