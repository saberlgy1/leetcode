//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性： 
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 100 
// -104 <= matrix[i][j], target <= 104 
// 
// Related Topics 数组 二分查找 
// 👍 320 👎 0

package com.cute.leetcode.editor.cn;

public class SearchA2dMatrix {
    public static void main(String[] args) {
        Solution solution = new SearchA2dMatrix().new Solution();
        int[][] matrix = new int[][]{
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        };
        int[][] matrix1 = new int[][]{
                {1}
        };
        int[][] matrix2 = new int[][]{
                {1,3}
        };
        int[][] matrix3 = new int[][]{
                {1},{3}
        };
        System.out.println(solution.searchMatrix(matrix1, 0));
        System.out.println(solution.searchMatrix(matrix1, 1));
        System.out.println(solution.searchMatrix(matrix1, 3));
        System.out.println(solution.searchMatrix(matrix, 0));
        System.out.println(solution.searchMatrix(matrix, 3));
        System.out.println(solution.searchMatrix(matrix, 7));
        System.out.println(solution.searchMatrix(matrix2, 3));
        System.out.println(solution.searchMatrix(matrix3, 3));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
          /*  //遍历 辣鸡做法(O(m + lgn))
            //行二分则 O（lgm + lgn）
            if (matrix.length == 0) {
                return false;
            }
            int m = matrix.length, n = matrix[0].length, i = 0;
            while (target > matrix[i][n - 1]) {
                i++;
                if (i >= m ){
                    return false;
                }
            }

            int left = 0, right = n - 1;
            while (left < right) {
                if (target == matrix[i][left + (right - left) / 2]) {
                    return true;
                } else if (target < matrix[i][left + (right - left) / 2]) {
                    right = left + (right - left) / 2;
                } else if (target > matrix[i][left + (right - left) / 2]) {
                    left = left + (right - left) / 2 + 1;
                }
            }
            if (left == right){
                return matrix[i][left] == target;
            }
            return false;*/
            //每日一题重新做
            //corner case
            if (matrix.length == 0) {
                return false;
            }
            //行列二分
            int m = matrix.length, n = matrix[0].length;
            int right = n - 1, high = 0, low = m - 1;

            while (high < low){
                //防止数组越界
                int mid = high + (low - high)/2;
                if (matrix[mid][0] == target){
                    return true;
                }
                if (matrix[mid][0] < target){
                   if (matrix[mid][right] >= target){
                       //TODO 二分行
                       return searchRow(matrix, target, mid);

                   }
                   else{
                       high = mid + 1 ;
                       continue;
                   }
                }
                if (matrix[mid][0] > target){
                    low = mid ;
                }
            }
            if (high == low){
                return searchRow(matrix,target,high);
            }
            return false;
        }

        private boolean searchRow(int[][] matrix, int target,  int index) {
            //顺序遍历
//            for (int i = 0; i < matrix[0].length; i++) {
//                if (matrix[index][i] == target){
//                    return true;
//                }
//            }
//            return false;
            //优化 二分查找
            int left = 0, right = matrix[0].length - 1;
            while (left < right){
                int mid = left + (right - left)/2;
                if (matrix[index][mid] == target){
                    return true;
                }
                if (matrix[index][mid] < target){
                    left = mid  + 1;
                }else {
                    right = mid  ;
                }
            }
            return matrix[index][left] == target;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}