package com.cute.leetcode.editor.contest.no254;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: leetcode
 * @description:
 * @author: lgy
 * @create: 2021-08-15 10:36
 **/

public class RearrangeArray {

    public static void main(String[] args) {
        RearrangeArray rearrangeArray = new RearrangeArray();
        System.out.println(rearrangeArray.rearrangeArray(new int[]{1, 2, 3, 4, 5}));
    }

    public int[] rearrangeArray(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.sort(nums);
        int ptr = 0;
        for (int i = 0; i < nums.length; i += 2)
            res[i] = nums[ptr++];
        for (int i = 1; i < nums.length; i += 2)
            res[i] = nums[ptr++];
        return res;
    }


    void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
