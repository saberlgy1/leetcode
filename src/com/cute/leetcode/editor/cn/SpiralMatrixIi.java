//给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。 
//
// 示例: 
//
// 输入: 3
//输出:
//[
// [ 1, 2, 3 ],
// [ 8, 9, 4 ],
// [ 7, 6, 5 ]
//] 
// Related Topics 数组 
// 👍 233 👎 0

package com.cute.leetcode.editor.cn;

/**
 * @author 2440917872qq.com
 */
public class SpiralMatrixIi {
    public static void main(String[] args) {
        Solution solution = new SpiralMatrixIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] generateMatrix(int n) {
            int[][] res = new int[n][n];
            int l = 0, r = n- 1, b = n - 1,t = 0;
            int temp = 1;
            int tar = n * n;
            int num = 1;
            while (num <= tar){
                for (int i = l; i <= r; i++){
                    res[t][i] = num;
                    num ++;
                }
                t++;
                for (int i = t; i <= b; i++){
                    res[i][r] = num;
                    num ++;
                }
                r--;
                for (int i = r; i >= l; i--){
                    res[b][i] = num;
                    num ++;
                }
                b--;
                for (int i = b; i >= t; i --){
                    res[i][l] = num;
                    num ++;
                }
                l++;
            }
            return res;

        }


        public void turnRight(int i, int j, int temp){

        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}