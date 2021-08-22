package com.cute.leetcode.editor.contest.noDouble59;

/**
 * @program: leetcode
 * @description:
 * @author: lgy
 * @create: 2021-08-21 23:00
 **/

public class no5835 {

    public static void main(String[] args) {

        int[][] matrix = new int[][]{{-1,-1,-1},{-1,-1,-1},{0,0,0}};
        System.out.println(maxMatrixSum(matrix));
    }


    public static long maxMatrixSum(int[][] matrix) {
        //分两种情况
        //第一种 奇数个负数找到最小的负数
        //第二种偶数个负数全为正数
        boolean flag = true;
        long sum = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                sum += Math.abs(matrix[i][j]);
                min = Math.min(Math.abs(matrix[i][j]), min);
                if (matrix[i][j] <= 0) {
                    flag = !flag;
                }
            }
        }
        return flag ? sum : sum - min;
    }

}
