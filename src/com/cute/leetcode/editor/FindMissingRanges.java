package com.cute.leetcode.editor;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: com.cute.leetcode
 * @description: 查询缺失元素
 * @author: lgy
 * @create: 2020-06-01 21:44
 **/

public class FindMissingRanges {


    private static List<String> findMissingRanges(int[] num, int low, int high) {
        //step1 define result value
        List<String> result = new ArrayList<>();
        //step2 handle corner cases
        int length = high - low + 1;
        if (null == num || num.length == 0) {
            result.add(low + "->" + high);
        }
        //steps fill in business logic
        //first add range after lower
        //then add all ranged between low and high
        int prev = num[0];
        for (int i = 1; i < num.length; i++) {
            int cur = num[i];
            if (prev >= low && prev <= high){
                if (cur > prev + 1 ) {
                    addToList(result, prev + 1, cur - 1);
                }
            }
            prev = cur;

        }

        //final add the range before upper
        addToList(result, num[num.length - 1], high);
        return result;
    }

    private static void addToList(List<String> result, int start, int end) {
        if (start == end) {
            result.add(String.valueOf(start));
        } else if (start < end){
            result.add(start + "->" + end);
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 3, 50, 75};
        int low = 0;
        int high = 99;
        System.out.println(findMissingRanges(nums, low, high));
    }

}
