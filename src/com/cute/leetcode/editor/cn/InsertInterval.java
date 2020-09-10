//给出一个无重叠的 ，按照区间起始端点排序的区间列表。 
//
// 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。 
//
// 
//
// 示例 1： 
//
// 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
//输出：[[1,5],[6,9]]
// 
//
// 示例 2： 
//
// 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//输出：[[1,2],[3,10],[12,16]]
//解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
// 
//
// 
//
// 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。 
// Related Topics 排序 数组 
// 👍 187 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    public static void main(String[] args) {
        Solution solution = new InsertInterval().new Solution();
//        int[][] nums = new int[][]{{1,2},{3,5}, {6, 7}, {8, 10}, {12,16}};
        int[][] nums = new int[][]{{1,5}};
        int[][] res = solution.insert(nums, new int[]{0,1});
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i][0] + " ===>  " + res[i][1]);
        }
        System.out.println();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            if (intervals.length == 0) {
                return new int[][]{{newInterval[0], newInterval[1]}};
            }
            //step1
            //二分查找感觉有问题，找不到最边界元素
            //先顺序遍历
            // 查找所有区间，找到插入区间的最左边界和最右区边界
            List<int[]> res = new ArrayList<>();
            int left = intervals[0][0];
            int right = intervals[0][1];
            for (int i = 0; i < intervals.length; i++) {
                if (intervals[i][1] < newInterval[0]) {
                    left = intervals[i][0];
                    right = intervals[i][1];
                    res.add(intervals[i]);
                } else if (intervals[i][0] > newInterval[1]){
                    res.add(new int[]{newInterval[0], newInterval[1]});
                    while (i < intervals.length){
                        res.add(intervals[i]);
                        i++;
                    }
                } else if (intervals[i][0]<=newInterval[1]){
                    left = Math.min(intervals[i][0], newInterval[0]);
                    while (i < intervals.length && newInterval[1] >= intervals[i][0]){
                        right  = Math.max(intervals[i][1], newInterval[1]);
                        i++;
                    }
                    res.add(new int[]{left,right});
                    while (i < intervals.length){
                        res.add(intervals[i]);
                        i++;
                    }
                }
            }
            if (newInterval[0] > intervals[intervals.length - 1][1]){
                res.add(new int[]{newInterval[0], newInterval[1]});
            }
            return res.toArray(new int[0][]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}