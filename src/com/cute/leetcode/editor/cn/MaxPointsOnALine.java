//给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
//
//
//
// 示例 1：
//
//
//输入：points = [[1,1],[2,2],[3,3]]
//输出：3
//
//
// 示例 2：
//
//
//输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
//输出：4
//
//
//
//
// 提示：
//
//
// 1 <= points.length <= 300
// points[i].length == 2
// -104 <= xi, yi <= 104
// points 中的所有点 互不相同
//
// Related Topics 几何 哈希表 数学
// 👍 275 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxPointsOnALine {
    public static void main(String[] args) {
        Solution solution = new MaxPointsOnALine().new Solution();
        int[][] points = new int[][]{
                //{0, 0}, {4, 5}, {7, 8}, {8, 9}, {5, 6}, {3, 4},{1,1}
                {2,3},{3,3},{-5,3}
        };
        System.out.println(solution.maxPoints(points));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力法，确认每两个点的线路
        //找到y = kx + b 的唯一(k,b)
        //定义一个map把与当前节点为kb的节点放进去即可
        public int maxPoints(int[][] points) {
            //corner case
            if (points.length <=2){
                return points.length;
            }
            int max = 2;
            for (int i = 0; i < points.length; i++) {
                Map<String, List<Integer>> map = new HashMap<>();
                for (int j = i + 1; j < points.length; j++) {
                    //水平和垂直线（）
                    double k = (points[i][0] - points[j][0]) == 0 ? Integer.MAX_VALUE : (double)(points[i][1] - points[j][1]) / (points[i][0] - points[j][0]);
                    double b = k == Integer.MAX_VALUE ? Integer.MAX_VALUE : (points[i][1] - k * points[i][0]);

                    String key = k == (-0.0)? "0.0":k + "," + b;
                    List<Integer> list = map.getOrDefault(key, new ArrayList<>());
                    list.add(j);
                    map.put(key, list);
                }
                for (Map.Entry<String, List<Integer>> entry : map.entrySet()
                ) {
                    max = Math.max(max, entry.getValue().size() + 1);
                }
            }

            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}