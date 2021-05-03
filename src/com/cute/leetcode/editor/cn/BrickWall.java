//你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和应该相等。 
//
// 你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你不能沿着墙的两个垂直边缘之一画线，这样显然是没
//有穿过一块砖的。 
//
// 给你一个二维数组 wall ，该数组包含这堵墙的相关信息。其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。你需要找出怎样画才能使这条线 穿过的
//砖块数量最少 ，并且返回 穿过的砖块数量 。 
//
// 
//
// 示例 1： 
//
// 
//输入：wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：wall = [[1],[1],[1]]
//输出：3
// 
// 
//
// 提示： 
//
// 
// n == wall.length 
// 1 <= n <= 104 
// 1 <= wall[i].length <= 104 
// 1 <= sum(wall[i].length) <= 2 * 104 
// 对于每一行 i ，sum(wall[i]) 应当是相同的 
// 1 <= wall[i][j] <= 231 - 1 
// 
// Related Topics 哈希表 
// 👍 211 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrickWall {
    public static void main(String[] args) {
        Solution solution = new BrickWall().new Solution();
        List<List<Integer>> wall = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(1);
        List<Integer> list1 = new ArrayList<>();
        list1.add(3);
        list1.add(1);
        list1.add(2);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(3);
        list2.add(2);
        List<Integer> list3 = new ArrayList<>();
        list3.add(2);
        list3.add(4);
        List<Integer> list4 = new ArrayList<>();
        list4.add(3);
        list4.add(1);
        list4.add(2);
        List<Integer> list5 = new ArrayList<>();
        list5.add(1);
        list5.add(3);
        list5.add(1);
        list5.add(1);
        wall.add(list);
        wall.add(list1);
        wall.add(list2);
        wall.add(list3);
        wall.add(list4);
        wall.add(list5);
        System.out.println(solution.leastBricks(wall));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int leastBricks(List<List<Integer>> wall) {
            //corner case正常应该不被考虑的
            //考虑到sum【i】 相同 所以可以直接统计行数和总长度
            if (wall == null || wall.size() == 0) {
                return 0;
            }
            int m = wall.size();
            int max = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < m; i++) {
                List<Integer> temp = wall.get(i);
                int prefix = 0;
                for (int j = 0; j < temp.size(); j++) {
                    prefix += temp.get(j);
                    map.put(prefix, map.getOrDefault(prefix, 0) + 1);
                    //抹掉最后一行
                    if (j != temp.size() - 1){
                        max = Math.max(max, map.get(prefix));
                    }
                }
            }

            //总行数减掉间隔次数返回即可
            return m - max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}