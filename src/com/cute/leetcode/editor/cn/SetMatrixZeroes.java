//给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。 
//
// 示例 1: 
//
// 输入: 
//[
//  [1,1,1],
//  [1,0,1],
//  [1,1,1]
//]
//输出: 
//[
//  [1,0,1],
//  [0,0,0],
//  [1,0,1]
//]
// 
//
// 示例 2: 
//
// 输入: 
//[
//  [0,1,2,0],
//  [3,4,5,2],
//  [1,3,1,5]
//]
//输出: 
//[
//  [0,0,0,0],
//  [0,4,5,0],
//  [0,3,1,0]
//] 
//
// 进阶: 
//
// 
// 一个直接的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。 
// 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。 
// 你能想出一个常数空间的解决方案吗？ 
// 
// Related Topics 数组 
// 👍 317 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class SetMatrixZeroes {
    public static void main(String[] args) {
        int[][] test = new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        Solution solution = new SetMatrixZeroes().new Solution();
        solution.setZeroes(test);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void setZeroes(int[][] matrix) {
            Boolean isCol = false;
            //遍历一次得到所有为0的横纵坐标 空间复杂度O(m  +  n ) 额外空间存储的最多为所有行数、列数
            int m = matrix.length;
            int n = matrix[0].length;
            /*Set<Integer> row = new HashSet<>();
            Set<Integer> column = new HashSet<>();
            for (int i = 0; i < matrix[0].length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[j][i] == 0){
                        row.add(j);
                        column.add(i);
                    }
                }
            }
            Iterator it = row.iterator();
            Iterator its = column.iterator();
            while (it.hasNext()){
                int temp = (Integer) it.next();
                for (int i = 0 ; i < matrix[0].length; i ++){
                    matrix[temp][i] = 0;
                }

            }
            while (its.hasNext()){
                int temp = (Integer) its.next();
                for (int i = 0 ; i < matrix.length; i ++){
                    matrix[i][temp] = 0;
                }
            }*/
            //方法2： 原地置换 for循环两次加上每次都设置新的值，为了防止重复赋0 用标识位赋值，最后把所有标示位变成0 O(1) O(m*n(m + n))
            //方法3： 记录标志位，将标志位不存储而是用第一行以及第一列作为标志位，最后参照标志位对行列赋值O(m*b) O(1)
            for (int i = 0; i < m; i ++){

                if (matrix[i][0] == 0) {
                    isCol = true;
                }

                for (int j = 1; j <n; j++){
                    if (matrix[i][j] == 0){
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
            if (matrix[0][0] == 0) {
                for (int j = 0; j < n; j++) {
                    matrix[0][j] = 0;
                }
            }

            // See if the first column needs to be set to zero as well
            if (isCol) {
                for (int i = 0; i < m; i++) {
                    matrix[i][0] = 0;
                }
            }


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}