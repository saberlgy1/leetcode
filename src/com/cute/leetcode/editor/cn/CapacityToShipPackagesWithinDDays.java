//传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。 
//
// 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。 
//
// 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。 
//
// 
//
// 示例 1： 
//
// 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
//输出：15
//解释：
//船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
//第 1 天：1, 2, 3, 4, 5
//第 2 天：6, 7
//第 3 天：8
//第 4 天：9
//第 5 天：10
//
//请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (1
//0) 是不允许的。 
// 
//
// 示例 2： 
//
// 输入：weights = [3,2,2,4,1,4], D = 3
//输出：6
//解释：
//船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
//第 1 天：3, 2
//第 2 天：2, 4
//第 3 天：1, 4
// 
//
// 示例 3： 
//
// 输入：weights = [1,2,3,1,1], D = 4
//输出：3
//解释：
//第 1 天：1
//第 2 天：2
//第 3 天：3
//第 4 天：1, 1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= D <= weights.length <= 50000 
// 1 <= weights[i] <= 500 
// 
// Related Topics 数组 二分查找 
// 👍 222 👎 0

package com.cute.leetcode.editor.cn;

public class CapacityToShipPackagesWithinDDays {
    public static void main(String[] args) {
        Solution solution = new CapacityToShipPackagesWithinDDays().new Solution();
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9,10};
        System.out.println(solution.shipWithinDays(nums, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int shipWithinDays(int[] weights, int days) {
            //看题干我感觉好像还是dp解决
            //按照days划分数组找到最小划分重量和
            //我的脑子不太好使
            //这个二分角度很新颖
            //我还是高估了我的智商
            //int[] dp = new int[days + 1];
            //初始化右边界和左边界
            //max为一天运送所有货物，min为每天只运送一个货物的最小值（无法拆分货物）
            int max = 0, min = weights[0], res = 0, temp = 0;
            for (int i = 0; i < weights.length; i++) {
                max += weights[i];
                min = Math.max(min, weights[i]);
            }
            //二分查找，max 和 min的中间状态res，满足大于res的时候都小于d天就可以运输完成，小于res 就代表d天无法完成
            //判断无法完成的标准，即从前往后遍历，加上所有遍历过的值，取到res后，如果能满足条件，则返回res，如果不能，根据是否能运输完成选择左半部分或者右半部分
            while (min < max) {
                //判断是否满足
                temp = min - (min - max) / 2;
                int day = 0, sum = 0;
                for (int i = 0; i < weights.length; i++, day++) {
                    while (i < weights.length && sum + weights[i] <= temp) {
                        sum += weights[i];
                        i++;
                    }
                    i--;
                    sum = 0;
                }
                //左右判断
                if (day <= days) {
                    res = temp;
                    max = temp;
                } else  {
                    min = temp + 1;
                }

            }
            if (min == max){
                return min;
            }

            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}