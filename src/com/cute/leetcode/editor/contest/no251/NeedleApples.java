package com.cute.leetcode.editor.contest.no251;

/**
 * @program: leetcode
 * @description: 最小周长
 * @author: lgy
 * @create: 2021-08-01 11:43
 **/

public class NeedleApples {

    public static void main(String[] args) {
        long nums = 64L;
        System.out.println(new NeedleApples().new Solution().minimumPerimeter(nums));
    }

    class Solution {
        //思路一：二分等会儿写吧 先写暴力
        /*public long minimumPerimeter(long neededApples) {
            for (long i = 0; i < Integer.MAX_VALUE; i++) {
                long apples = 2 * i * (i + 1) * (2 * i + 1);
                if (apples >= neededApples) {
                    return i * 8L;
                }
            }
            return 0;
        }*/
        //思路二：二分
        public long minimumPerimeter(long neededApples) {
            int l = 0, r = Integer.MAX_VALUE;
            while (l < r) {
                int mid = (l + r )/2;
                long apples = 2 * mid * (mid + 1) * (2 * mid + 1);
                if (apples > neededApples) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
            return r;
        }
    }


}
