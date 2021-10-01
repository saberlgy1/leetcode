//给你 二维 平面上两个 由直线构成的 矩形，请你计算并返回两个矩形覆盖的总面积。 
//
// 每个矩形由其 左下 顶点和 右上 顶点坐标表示： 
//
// 
// 
// 第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。 
// 第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。 
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
//输出：45
// 
//
// 示例 2： 
//
// 
//输入：ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
//输出：16
// 
//
// 
//
// 提示： 
//
// 
// -10⁴ <= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 <= 10⁴ 
// 
// Related Topics 几何 数学 👍 140 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;

public class RectangleArea {
    public static void main(String[] args) {
        Solution solution = new RectangleArea().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //暴力法就可以
        //找到重叠坐标即可
        //先求两个矩形的面积
        public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
            int a1 = ax2 - ax1;
            int b1 = ay2 - ay1;
            int s1 = Math.abs(a1 * b1);
            int a2 = bx2 - bx1;
            int b2 = by2 - by1;
            int s2 = Math.abs(a2 * b2);
            //两种无交叉矩形逻辑
            if (Math.max(ax1, ax2) <= Math.min(bx1, bx2) || Math.max(ay1, ay2) <= Math.min(by1, by2)) {
                return s1 + s2;
            }
            if (Math.min(ax1, ax2) >= Math.max(bx1, bx2) || Math.min(ay1, ay2) >= Math.max(by1, by2)) {
                return s1 + s2;
            }
            //有交叉逻辑
            int[] x = new int[]{ax1, bx1, ax2, bx2};
            int[] y = new int[]{ay1, by1, ay2, by2};
            Arrays.sort(x);
            Arrays.sort(y);
            int cx = x[2] - x[1];
            int cy = y[2] - y[1];
            return s1 + s2 - Math.abs(cx * cy);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}