package com.cute.leetcode.editor.sort;

import java.util.Arrays;

/**
 * @program: leetcode
 * @description: 冒泡排序
 * @author: lgy
 * @create: 2021-06-29 20:05
 **/

public class BubbleAndInsertSort {

    /**
     * 本题不考虑cornercase
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6, 2, 4, 7, 9, 8, 16, 13, 12};
        Solution.anotherBubbleSort(nums);
        for (int i : nums
        ) {
            System.out.print(i);
        }

    }


    static class Solution {


        /**
         * 冒泡排序
         * 冒泡排序算法其实可以一直移动两个比较元素
         *
         * @param nums
         */
        public static void anotherBubbleSort(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums.length - 1; j++) {
                    //保证每次比较右边所有数字比自己大
                    if (nums[j] > nums[j + 1]) {
                        swap(nums, j, j + 1);
                    }
                }
            }
        }

        /**
         * 冒泡排序的优化
         * 上述冒泡过程其实是可以做一部分优化的
         * 也就是说如果当前元素比右边元素小我们可以停止往下遍历，因为当前元素右侧已经可以保证完全有序
         * 定义一个标志，当交换元素师元素标志位true 否则为false
         * @param nums
         */
        public static void upgradeBubbleSort(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                boolean flag =false;
                for (int j = 0; j < nums.length - i - 1; j++) {
                    //保证右边所有数字都比自己大
                    if (nums[j] > nums[j + 1]) {
                        swap(nums, j, j + 1);
                        flag = true;
                    }
                }
                if (!flag){
                    break;
                }
            }
        }

        private static void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }


    }

}
