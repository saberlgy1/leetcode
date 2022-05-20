//给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。 
//
// 区间 i 的 右侧区间 可以记作区间 j ，并满足 startj >= endi ，且 startj 最小化 。 
//
// 返回一个由每个区间 i 的 右侧区间 的最小起始位置组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。 
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,2]]
//输出：[-1]
//解释：集合中只有一个区间，所以输出-1。
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[3,4],[2,3],[1,2]]
//输出：[-1,0,1]
//解释：对于 [3,4] ，没有满足条件的“右侧”区间。
//对于 [2,3] ，区间[3,4]具有最小的“右”起点;
//对于 [1,2] ，区间[2,3]具有最小的“右”起点。
// 
//
// 示例 3： 
//
// 
//输入：intervals = [[1,4],[2,3],[3,4]]
//输出：[-1,2,-1]
//解释：对于区间 [1,4] 和 [3,4] ，没有满足条件的“右侧”区间。
//对于 [2,3] ，区间 [3,4] 有最小的“右”起点。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 2 * 10⁴ 
// intervals[i].length == 2 
// -10⁶ <= starti <= endi <= 10⁶ 
// 每个间隔的起点都 不相同 
// 
// Related Topics 数组 二分查找 排序 👍 158 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FindRightInterval {
    //时间复杂度O(nlgn)
    //空间复杂度O(n)
    public static void main(String[] args) {
        Solution solution = new FindRightInterval().new Solution();
        //int[][] intervals = new int[][]{ {1, 4}, {2, 3},{3, 4}};
        int[][] intervals = new int[][]{{1, 2}, {2, 3}, {0, 1}, {3, 4}};
        //int[][] intervals = new int[][]{{1, 1}, {3, 4}};
        for (int i : solution.findRightInterval(intervals)
        ) {
            System.out.println(i);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] findRightInterval(int[][] intervals) {
            Map<int[], Integer> map = new HashMap<>();

            int n = intervals.length;
            for (int i = 0; i < n; i++) {
                map.put(intervals[i], i);
            }
            int[] res = new int[n];
            // 根据start 排序
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
                }
            });
            //二分查找数量
            for (int i = 0; i < n; i++) {
                int temp = binarySearch(intervals, i, n - 1);
                //如果开始元素==结束元素那么代表自身则为自己的右区间，又因为每个子数组开始元素都不相同，所以不需担心前导节点为自己的右侧区见
                if (intervals[i][0] == intervals[i][1]) {
                    res[map.get(intervals[i])] = map.get(intervals[i]);
                } else {
                    res[map.get(intervals[i])] = temp == -1 ? -1 : map.get(intervals[temp]);
                }
            }
            return res;
        }


        public int binarySearch(int[][] intervals, int l, int r) {
            int n = r;
            int end = intervals[l][1];
            l += 1;

            while (l < r) {
                int mid = l + r >> 1;
                if (intervals[mid][0] < end) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return intervals[r][0] >= end ? r : -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}