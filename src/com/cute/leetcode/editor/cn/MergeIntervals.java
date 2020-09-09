//给出一个区间的集合，请合并所有重叠的区间。 
//
// 
//
// 示例 1: 
//
// 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2: 
//
// 输入: intervals = [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。 
//
// 
//
// 提示： 
//
// 
// intervals[i][0] <= intervals[i][1] 
// 
// Related Topics 排序 数组 
// 👍 595 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MergeIntervals {
    public static void main(String[] args) {
        Solution solution = new MergeIntervals().new Solution();
        int[][] nums = new int[][]{{2, 3},{2,2},{3,3},{1,3},{5,7},{2,2},{4,6}};
        int[][] res = solution.merge(nums);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i][0] + " ===>  " + res[i][1]);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
            //先按照数组首位元素排序，确认左边界，然后依次比较右边界
            List<int[]> res = new ArrayList<>();
            int len = intervals.length;
            if (intervals.length == 0 || intervals == null) {
                return res.toArray(new int[0][]);
            }
            int i = 0;
            while (i < len){
                int left = intervals[i][0];
                int right = intervals[i][1];
                while (i < intervals.length - 1 && right >= intervals[i + 1][0]){

                    right = Math.max(intervals[i+ 1][1],right);
                    i++;
                }
                res.add(new int[]{left, right});
                // 接着判断下一个区间
                i++;
            }
            return res.toArray(new int[0][]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}