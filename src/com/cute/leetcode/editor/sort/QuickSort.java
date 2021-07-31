package com.cute.leetcode.editor.sort;

/**
 * @program: leetcode
 * @description: 快速排序
 * @author: lgy
 * @create: 2021-07-30 16:32
 **/

public class QuickSort {


    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6, 2, 4, 7, 9, 8, 16, 13, 12};
        new Solution().quickSort(nums);
        for (int i : nums
        ) {
            System.out.print(i);
        }
    }

    static class Solution {

        public void quickSort(int[] nums) {
            sort(nums, 0, nums.length - 1);
        }

        public void sort(int[] nums, int l, int r) {
            if (l >= r) {
                return;
            }
            int pivot = partition(nums, l, r);
            sort(nums, l, pivot);
            sort(nums, pivot + 1, r);
        }

        private int partition(int[] nums, int l, int r){
            return 0;
        }

    }


}
