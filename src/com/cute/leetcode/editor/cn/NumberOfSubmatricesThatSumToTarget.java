//给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。 
//
// 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。 
//
//
// 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵
//也不同。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
//输出：4
//解释：四个只含 0 的 1x1 子矩阵。
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,-1],[-1,1]], target = 0
//输出：5
//解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
// 
//
// 示例 3： 
//
// 
//输入：matrix = [[904]], target = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= matrix.length <= 100 
// 1 <= matrix[0].length <= 100 
// -1000 <= matrix[i] <= 1000 
// -10^8 <= target <= 10^8 
// 
// Related Topics 数组 动态规划 Sliding Window 
// 👍 121 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class NumberOfSubmatricesThatSumToTarget {
    public static void main(String[] args) {
        Solution solution = new NumberOfSubmatricesThatSumToTarget().new Solution();
        int[][] matrix = new int[][]{{0,1,1,1,0,1},{0,0,0,0,0,1},{0,0,1,0,0,1},{1,1,0,1,1,0},{1,0,0,1,0,0}};
        System.out.println(solution.numSubmatrixSumTarget(matrix,0));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路 1 ：暴力法
        // 第一种暴力破解，求出每个矩阵的和，然后返回target矩阵
        //通过hashmap存储每个矩阵的值以及其数量
        //这种m * m * n * n  应该是一定会超时的（居然没超时）
        // 核心关键在于subArray函数确定连续子列和为target 是n * n 还是n
        //思路 2： 二维转一维 近似暴力法
        // 看官方题解第一种 二维转一维
        // 取上下边界第i行 以及第j行，得到 一个martix[j - i + 1][ martrix[0].length()]的子数组
        // 相关题目 leetcode 560， leetcode 304， leetcode 363，
        // 求出子数组中连续列和为target的数组矩阵，结果 + 1；
        public int numSubmatrixSumTarget(int[][] matrix, int target) {
            //corner case
            //本题限制了 matrix的极限状态 可以不考虑边界值
            int m = matrix.length, n = matrix[0].length, res = 0;
            for (int i = 0; i < m; i++) {
                //上下边界每列和的数组
                int[] sum = new int[n];
                for (int j = i; j < m; j++) {
                    for (int k = 0; k < n; k++) {
                        //每列求和
                        sum[k] += matrix[j][k];
                    }
                    //求连续子数组和是否为target
                    res += subArray(sum, target);
                }
            }
            return res;
        }

        //按照思路1就是直接暴力
        //我属实没太看懂为啥这没TLE
        /*private int subArray(int[] sum, int target){
            int res = 0;
            for (int i = 0; i < sum.length; i++){
                int temp = 0;
                for (int j = i; j < sum.length; j++){
                    temp += sum[j];
                    if (temp == target){
                        res +=1;
                    }
                }
            }
            return res;
        }*/
        //按照思路2则可以优化一下
        //hash求解类似于两数之和的优化做法，去寻找左右边界的互斥值
        //第一步 求得当前右边界和最左边界之间矩阵值的和也就是sum[0] +... + sum[i], i为遍历sum是的当前值
        //详情可见宫水三叶的题截图
        private int subArray(int[] sum, int target){
            Map<Integer, Integer> map = new HashMap<>();
            //防止 nums[0] == k的时候没有算到
            //因为先更新res，后更新map，所以不会重复计算
            map.put(0,1);
            int res = 0, n = sum.length,tempSum = 0;
            for (int num: sum
                 ) {
                tempSum += num;
                if (map.containsKey(tempSum - target)){
                    res += map.get(tempSum - target);
                }
                //互斥原则，找到排除前缀后剩余和为target的子数组
                map.put(tempSum, map.getOrDefault(tempSum,0) + 1);
            }
            return res;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}