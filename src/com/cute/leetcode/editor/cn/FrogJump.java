//一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。 
//
// 给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。 
//
// 开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。 
//
// 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。
// 
//
// 
//
// 示例 1： 
//
// 
//输入：stones = [0,1,3,5,6,8,12,17]
//输出：true
//解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然
//后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。 
//
// 示例 2： 
//
// 
//输入：stones = [0,1,2,3,4,8,9,11]
//输出：false
//解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。 
//
// 
//
// 提示： 
//
// 
// 2 <= stones.length <= 2000 
// 0 <= stones[i] <= 231 - 1 
// stones[0] == 0 
// 
// Related Topics 动态规划 
// 👍 188 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FrogJump {
    public static void main(String[] args) {
        Solution solution = new FrogJump().new Solution();
        int[] stones = new int[]{0, 1, 3, 5, 6, 8, 12, 17};
        System.out.println(solution.canCross(stones));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private Boolean[][] rec;
        Map<String,Boolean> cacheMap = new HashMap<>();
        public boolean canCross(int[] stones) {
            //corner case
            if (stones[1] > 1) {
                return false;
            }
            //深度优先递归遍历
            //从0开始跳，第一次跳0单位距离，保证第二次可以跳1
            int dis = 0, index = 0;
            rec = new Boolean[stones.length][stones.length];
            return backTrack(stones, dis, index);
           /* //dp思路
            boolean[][] dp = new boolean[stones.length][stones.length];
            //边界情况
            dp[0][0] = true;
            //dp方程推导
            //首先推导出，节点为i 和i-1之间的距离如果大于i则无法跳到，可以直接返回false
            for (int i = 1; i < stones.length; i++) {
                if (stones[i] - stones[i - 1] > i) {
                    return false;
                }
            }
            for (int i = 1; i < stones.length; ++i) {
                for (int j = i - 1; j >= 0; --j) {
                    int dis = stones[i] - stones[j];
                    if (dis > j + 1) {
                        break;
                    }
                    dp[i][dis] = dp[j][dis - 1] || dp[j][dis] || dp[j][dis + 1];
                    if (i == stones.length - 1 && dp[i][dis]){
                        return true;
                    }
                }
            }
            return false;*/

        }

        //map超时
        //二维数组 也tm超时没区别
        //dp吧
        //二维数组优化
        //记忆化存储
        public boolean backTrack(int[] stones, int dis, int index) {
            String key = dis+"_" + index;
            if (cacheMap.get(key)!= null){
                return cacheMap.get(key);
            }
            if (index == stones.length - 1) {
                return true;
            }
            //记录二维数组
            if (rec[index][dis] != null) {
                return rec[index][dis];
            }

            //可跳位置
            for (int i = dis - 1; i <= dis + 1; i++) {
                if (i > 0) {
                    int j = Arrays.binarySearch(stones, index + 1, stones.length, i + stones[index]);
                    if (j >= 0 && backTrack(stones, i, j)) {
                        return rec[index][i] = true;
                    }
                }
            }
            cacheMap.put(key,false);
            return false;
        }

        public Map<Integer, Integer> findDes(int[] stones, int dis, int index) {
            Map<Integer, Integer> destination = new HashMap<>();
            for (int i = dis - 1; i <= dis + 1; i++) {
                int des = Arrays.binarySearch(stones, index + 1, stones.length, stones[index] + i);
                if (des >= 0) {
                    destination.put(des, i);
                }
            }
            return destination;
        }


    /*    //二分查找java自带实现
        public int findK(int[] stones, int dis, int index) {
            if (dis < 0) {
                return -1;
            }
            int len = stones.length;
            int left = index + 1, right = stones.length - 1;
            while (left < right) {
                int mid = left + (right - left + 1) / 2;
                if (stones[mid] == stones[index] + dis) {
                    return mid;
                } else if (stones[mid] < stones[index] + dis) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            if (left == right) {
                return stones[left] == dis + stones[index] ? left : -1;
            }
            return -1;
        }*/

    }
//leetcode submit region end(Prohibit modification and deletion)

}