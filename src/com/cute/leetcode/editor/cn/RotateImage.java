//给定一个 n × n 的二维矩阵表示一个图像。 
//
// 将图像顺时针旋转 90 度。 
//
// 说明： 
//
// 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。 
//
// 示例 1: 
//
// 给定 matrix = 
//[
//  [1,2,3],
//  [4,5,6],
//  [7,8,9]
//],
//
//原地旋转输入矩阵，使其变为:
//[
//  [7,4,1],
//  [8,5,2],
//  [9,6,3]
//]
// 
//
// 示例 2: 
//
// 给定 matrix =
//[
//  [ 5, 1, 9,11],
//  [ 2, 4, 8,10],
//  [13, 3, 6, 7],
//  [15,14,12,16]
//], 
//
//原地旋转输入矩阵，使其变为:
//[
//  [15,13, 2, 5],
//  [14, 3, 4, 1],
//  [12, 6, 8, 9],
//  [16, 7,10,11]
//]
// 
// Related Topics 数组 
// 👍 547 👎 0

package com.cute.leetcode.editor.cn;

public class RotateImage {
    public static void main(String[] args) {
        Solution solution = new RotateImage().new Solution();
        int[][] nums = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        solution.rotate(nums);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void rotate(int[][] matrix) {
            int n = matrix.length;
/*
            //方法1 先转置矩阵、再反转每一行
            for (int i = 0;i < n; i++){
                for (int j = i; j < n; j++){
                    swap(matrix, i ,j);
                }
            }
            //同行元素转置
            for (int i = 0 ; i < n; i++){
                for (int j = 0; j < n/2; j++){
                   int temp = matrix[i][j];
                   matrix[i][j] = matrix[i][n -1 - j];
                    matrix[i][n -1 - j] = temp;
                }
            }
*/
            //方法2 一次移每条边上的一个
            for (int i = 0; i < n / 2 + n % 2; i++) {
                for (int j = 0; j < n / 2; j++) {

                    int[] temp = new int[4];
                    int x = i, y = j;
                    for (int k = 0; k < 4; k++) {
                        temp[k] = matrix[x][y];
                        int t = x;
                        x = y;
                        y = n - 1 - t;
                    }
                    for (int k = 0; k < 4; k++) {
                        int t = x;
                        x = y;
                        y = n - 1 - t;
                        matrix[x][y] = temp[k];

                    }

                }

            }

        }


        public void swap(int[][] nums, int i, int j) {
            int temp = nums[i][j];
            nums[i][j] = nums[j][i];
            nums[j][i] = temp;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}