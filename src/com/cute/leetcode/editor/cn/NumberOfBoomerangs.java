//给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中
// i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
//
// 返回平面上所有回旋镖的数量。
//
//
// 示例 1：
//
//
//输入：points = [[0,0],[1,0],[2,0]]
//输出：2
//解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
//
//
// 示例 2：
//
//
//输入：points = [[1,1],[2,2],[3,3]]
//输出：2
//
//
// 示例 3：
//
//
//输入：points = [[1,1]]
//输出：0
//
//
//
//
// 提示：
//
//
// n == points.length
// 1 <= n <= 500
// points[i].length == 2
// -10⁴ <= xi, yi <= 10⁴
// 所有点都 互不相同
//
// Related Topics 数组 哈希表 数学 👍 195 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class NumberOfBoomerangs {
    public static void main(String[] args) {
        Solution solution = new NumberOfBoomerangs().new Solution();
        int[][] po = new int[][]{{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        System.out.println(solution.numberOfBoomerangs(po));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力法
        //记录所有到i节点的距离然后每两个节点可以组成一对儿，先后顺序不同代表不同的元素对
        //所以当有x个距离相同节点的时候，res = res + (x*(x-1))
        public int numberOfBoomerangs(int[][] points) {
            int res = 0, n = points.length;
            for (int i = 0; i < n; i++) {
                Map<Integer, Integer> map = new HashMap<>();
                for (int l = 0; l < n; l++) {
                    map.put(cal(points[i], points[l]), map.getOrDefault(cal(points[i], points[l]), 0) + 1);
                }
                for (int x : map.keySet()
                ) {
                    res = res + map.get(x) * (map.get(x) - 1);
                }
            }
            return res;

        }

        public int cal(int[] l, int[] r) {
            int x = (r[0] - l[0]) * (r[0] - l[0]);
            int y = (r[1] - l[1]) * (r[1] - l[1]);
            return x + y;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}