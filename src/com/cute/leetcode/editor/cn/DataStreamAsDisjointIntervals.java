//给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。 
//
// 实现 SummaryRanges 类： 
//
// 
// 
// 
// SummaryRanges() 使用一个空数据流初始化对象。 
// void addNum(int val) 向数据流中加入整数 val 。 
// int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", 
//"addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
//[[], [1], [], [3], [], [7], [], [2], [], [6], []]
//输出：
//[null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]],
// null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
//
//解释：
//SummaryRanges summaryRanges = new SummaryRanges();
//summaryRanges.addNum(1);      // arr = [1]
//summaryRanges.getIntervals(); // 返回 [[1, 1]]
//summaryRanges.addNum(3);      // arr = [1, 3]
//summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
//summaryRanges.addNum(7);      // arr = [1, 3, 7]
//summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
//summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
//summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
//summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
//summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= val <= 10⁴ 
// 最多调用 addNum 和 getIntervals 方法 3 * 10⁴ 次 
// 
// 
// 
//
// 
//
// 进阶：如果存在大量合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办? 
// Related Topics 设计 二分查找 有序集合 👍 99 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataStreamAsDisjointIntervals {
    public static void main(String[] args) {
        SummaryRanges sr = new DataStreamAsDisjointIntervals().new SummaryRanges();
        sr.addNum(1);
        sr.getIntervals();
        sr.addNum(3);
        sr.getIntervals();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class SummaryRanges {
        List<int[]> list = new ArrayList<>();

        public void addNum(int val) {
            int n = list.size();
            if (n == 0) {
                list.add(new int[]{val, val});
                return;
            }
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (list.get(mid)[0] <= val) l = mid;
                else r = mid - 1;
            }
            int[] cur = list.get(r);
            if (cur[0] > val) {
                if (val + 1 == cur[0]) {
                    cur[0] = val;
                } else {
                    list.add(r, new int[]{val, val});
                }
                return;
            }
            if (cur[0] <= val && val <= cur[1]) {
                // pass
            } else if (r == n - 1) {
                if (cur[1] + 1 == val) {
                    cur[1] = val;
                } else {
                    list.add(new int[]{val, val});
                }
            } else {
                int[] next = list.get(r + 1);
                if (cur[1] + 1 == val && val == next[0] - 1) {
                    cur[1] = next[1];
                    list.remove(r + 1);
                } else if (cur[1] + 1 == val) {
                    cur[1] = val;
                } else if (next[0] - 1 == val) {
                    next[0] = val;
                } else {
                    list.add(r + 1, new int[]{val, val});
                }
            }
        }

        public int[][] getIntervals() {
            int n = list.size();
            int[][] ans = new int[n][2];
            for (int i = 0; i < n; i++) ans[i] = list.get(i);
            return ans;
        }
    }


/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
//leetcode submit region end(Prohibit modification and deletion)

}