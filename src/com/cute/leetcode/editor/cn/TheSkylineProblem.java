//城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。
//
// 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：
//
//
//
// lefti 是第 i 座建筑物左边缘的 x 坐标。
// righti 是第 i 座建筑物右边缘的 x 坐标。
// heighti 是第 i 座建筑物的高度。
//
//
// 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。
//列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
//
// 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答
//案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
//
//
//
// 示例 1：
//
//
//输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
//输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
//解释：
//图 A 显示输入的所有建筑物的位置和高度，
//图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。
//
// 示例 2：
//
//
//输入：buildings = [[0,2,3],[2,5,3]]
//输出：[[0,3],[5,0]]
//
//
//
//
// 提示：
//
//
// 1 <= buildings.length <= 104
// 0 <= lefti < righti <= 231 - 1
// 1 <= heighti <= 231 - 1
// buildings 按 lefti 非递减排序
//
// Related Topics 树状数组 线段树 数组 分治 有序集合 扫描线 堆（优先队列）
// 👍 430 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class TheSkylineProblem {
    public static void main(String[] args) {
        Solution solution = new TheSkylineProblem().new Solution();
        int[][] buildings = new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        System.out.println(solution.getSkyline(buildings));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：
        //题意分析：
        //输出点坐标要求
        //下一个点与当前节点高度不同，能通过两个节点确定一条单拐点直线
        //边界情况：第一个节点为第一个点的横坐标以及其高度，最后一点的为最后一个节点的横坐标和0
        //其实分析一下发现，我们要找的其实是每个区间的最高高度，然后通过这个最高高度的变化进行列表输出
        //通过Map进行存储每个节点的最高高度吧
        //map好像没啥大用不如list
        //一个最大整数段直接内存溢出了
       /* public List<List<Integer>> getSkyline(int[][] buildings) {
            List<List<Integer>> res = new ArrayList<>();
            //初始节点
            List<Integer> xh = new ArrayList<>();
            for (int i = 0; i < buildings[0][0]; i++) {
                xh.add(0);
            }

            for (int[] nums : buildings
            ) {
                int l = nums[0], r = nums[1], h = nums[2];
                while (l < r) {
                    //TODO 这一部分可以优化
                    while (xh.size() < l) {
                        xh.add(0);
                    }
                    if (xh.size() == l) {
                        xh.add(h);
                    }
                    xh.set(l, Math.max(xh.get(l), h));
                    l++;
                }
            }
            int index = 0;
            //确立初始节点
            for (; index < xh.size(); index++) {
                if (xh.get(index) != 0) {
                    List<Integer> start = new ArrayList<>();
                    start.add(index);
                    start.add(xh.get(index));
                    res.add(start);
                    break;
                }
            }
            int temp = xh.get(index);
            while (index < xh.size() - 1) {
                if (xh.get(index) != temp) {
                    List<Integer> node = new ArrayList<>();
                    node.add(index);
                    node.add(xh.get(index));
                    res.add(node);
                    temp = xh.get(index);
                }
                index++;
            }
            List<Integer> end = new ArrayList<>();
            end.add(xh.size());
            end.add(0);
            res.add(end);
            return res;
        }*/
        //优化一：尝试用map记录区间
        //失败了 因为更新区间的时候会使map变化
        //看了三叶大佬的题解还是没太看懂预处理的思路由来
        //不过大概思路我换一种按我理解里更通俗的方式解释一下吧
        //1、首先要明白题意打印的点究竟是什么
        //  按照扫描线观测可以发现，打印的点是每两条满足条件的扫描线形成的矩形的右端点
        public List<List<Integer>> getSkyline(int[][] buildings) {
            List<List<Integer>> res = new ArrayList<>();
            //记录每个矩形的左右端点，为了方便分辨左右端点，我们可以通过对于height的正负值进行标注
            //也就是说当height为负代表左端点，height为正代表右端点
            List<int[]> ps = new ArrayList<>();
            for (int[] b : buildings) {
                int l = b[0], r = b[1], h = b[2];
                ps.add(new int[]{l, -h});
                ps.add(new int[]{r, h});
            }
            //排序的标准是为了按照横坐标进行排序，确定每个横坐标的点，然后按照
            Collections.sort(ps, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    //横坐标排序
                    if (a[0] != b[0]) {
                        return a[0] - b[0];
                    }
                    //高度排序
                    return a[1] - b[1];
                }
            });
            // 大根堆(保证)
            PriorityQueue<Integer> q = new PriorityQueue<>((a,b)->b-a);
            int prev = 0;
            q.add(prev);
            for (int[] p : ps) {
                int point = p[0], height = p[1];
                if (height < 0) {
                    // 如果是左端点，说明存在一条往右延伸的可记录的边，将高度存入优先队列
                    q.add(-height);
                } else {
                    // 如果是右端点，说明这条边结束了，将当前高度从队列中移除
                    q.remove(height);
                }

                // 取出最高高度，如果当前不与前一矩形“上边”延展而来的那些边重合，则可以被记录
                int cur = q.peek();
                if (cur != prev) {
                    List<Integer> list = new ArrayList<>();
                    list.add(point);
                    list.add(cur);
                    res.add(list);
                    prev = cur;
                }
            }
            return res;

        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}