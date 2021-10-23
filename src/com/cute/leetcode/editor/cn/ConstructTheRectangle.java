//作为一位web开发者， 懂得怎样去规划一个页面的尺寸是很重要的。 现给定一个具体的矩形页面面积，你的任务是设计一个长度为 L 和宽度为 W 且满足以下要求的
//矩形的页面。要求： 
//
// 
//1. 你设计的矩形页面必须等于给定的目标面积。
//
//2. 宽度 W 不应大于长度 L，换言之，要求 L >= W 。
//
//3. 长度 L 和宽度 W 之间的差距应当尽可能小。
// 
//
// 你需要按顺序输出你设计的页面的长度 L 和宽度 W。 
//
// 示例： 
//
// 
//输入: 4
//输出: [2, 2]
//解释: 目标面积是 4， 所有可能的构造方案有 [1,4], [2,2], [4,1]。
//但是根据要求2，[1,4] 不符合要求; 根据要求3，[2,2] 比 [4,1] 更能符合要求. 所以输出长度 L 为 2， 宽度 W 为 2。
// 
//
// 说明: 
//
// 
// 给定的面积不大于 10,000,000 且为正整数。 
// 你设计的页面的长度和宽度必须都是正整数。 
// 
// Related Topics 数学 👍 72 👎 0

package com.cute.leetcode.editor.cn;

public class ConstructTheRectangle {
    public static void main(String[] args) {
        Solution solution = new ConstructTheRectangle().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：模拟
        //首先从中值开始遍历
        //然后不断提升右边边界
        //因为移动左边边界会使右边边界扩大，增加距离
        //不满足则移动左指针，右指针归位即可
       /* public int[] constructRectangle(int area) {
            //面积<=1的时候直接拿area返回即可
            if (area <= 1) {
                return new int[]{area, area};
            }
            //二分找到area的根号值
            //然后移动右指针即可
            int mid = (int) Math.pow((double) area, 0.5);
            int l = mid, r = mid;
            while (l > 0) {
                while (l * r <= area) {
                    if (l * r == area) return new int[]{r, l};
                    r++;
                }
                l--;
                r = l;
            }
            return null;
        }*/
        public int[] constructRectangle(int area) {
            for (int i = (int)(Math.sqrt(area)); ;i--) {
                if (area % i == 0) return new int[]{area / i, i};
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}