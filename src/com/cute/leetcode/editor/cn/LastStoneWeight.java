//有一堆石头，每块石头的重量都是正整数。 
//
// 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下： 
//
// 
// 如果 x == y，那么两块石头都会被完全粉碎； 
// 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。 
// 
//
// 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。 
//
// 
//
// 示例： 
//
// 
//输入：[2,7,4,1,8,1]
//输出：1
//解释：
//先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
//再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
//接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
//最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。 
//
// 
//
// 提示： 
//
// 
// 1 <= stones.length <= 30 
// 1 <= stones[i] <= 1000 
// 
// Related Topics 堆 贪心算法 
// 👍 164 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public static void main(String[] args) {
        Solution solution = new LastStoneWeight().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力法，每一次排一次序
        //然后从后往前减，在排序
        //时间复杂度n^2 + n * n * lgn
        //估计会TLE的一种做法
        /*public int lastStoneWeight(int[] stones) {
            int n = stones.length;
            //corner case;
            if (n ==1){
                return stones[0];
            }
            while (stones[n - 2] != 0) {
                Arrays.sort(stones);
                stones[n - 1] = stones[n - 1] - stones[n - 2];
                stones[n - 2] = 0;
                Arrays.sort(stones);
            }
            return stones[n - 1];
        }*/
        //思路二：堆
        //每一次都排序可以依赖维护一个最大堆
        //时间复杂度O(nlgn)
        public int lastStoneWeight(int[] stones) {
            //corner case

            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
                    new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return o2-o1;
                        }
                    }
            );
            for (int i: stones
            ) {
                priorityQueue.add(i);
            }
            while (priorityQueue.size() > 1){
                int large = priorityQueue.poll();
                int small = priorityQueue.poll();
                priorityQueue.add(large - small);

            }
            return priorityQueue.poll();
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}