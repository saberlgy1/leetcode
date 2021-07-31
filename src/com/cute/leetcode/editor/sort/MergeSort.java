package com.cute.leetcode.editor.sort;

/**
 * @program: leetcode
 * @description:归并排序
 * @author: lgy
 * @create: 2021-07-30 14:52
 **/

public class MergeSort {


    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6, 2, 4, 7, 9, 8, 16, 13, 12};
        new Solution().mergeSort(nums);
        for (int i : nums
        ) {
            System.out.print(i);
        }
    }

    static class Solution {


        public void mergeSort(int[] nums) {
            merge(nums, 0, nums.length - 1);
        }

        public void merge(int[] nums, int l, int r) {
            if (l >= r) {
                return;
            }
            int mid = (l + r) / 2;
            merge(nums, l, mid);
            merge(nums, mid + 1, r);
            update(nums, l, mid, r);
        }

        private void update(int[] nums, int l, int mid, int r) {
            int start = l, end = mid + 1, k = 0;
            int[] temp = new int[r - l + 1];
            while (start <= mid && end <= r) {
                if (nums[start] < nums[end]) {
                    temp[k++] = nums[start++];
                } else {
                    temp[k++] = nums[end++];
                }
            }

            while (start <= mid) {
                temp[k++] = nums[start++];
            }

            while (end <= r) {
                temp[k++] = nums[end++];
            }

            for (int i = 0; i < k; i++) {
                nums[l + i] = temp[i];
            }
        }


    }


}
