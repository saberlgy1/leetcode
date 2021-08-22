package com.cute.leetcode.editor.contest.noDouble59;

/**
 * @program: leetcode
 * @description:
 * @author: lgy
 * @create: 2021-08-21 22:36
 **/

public class no5834 {


    public static void main(String[] args) {
        Solution solution = new no5834().new Solution();
        System.out.println(solution.minTimeToType("pdy"));
    }

    class Solution {
        public int minTimeToType(String word) {
            int res = 0;
            char[] chars = word.toCharArray();
            char temp = 'a';
            for (int i = 0; i < word.length(); i++) {
                char l = chars[i];
                int dis = Math.abs((int) l - (int) temp);
                res += dis < 13 ? dis : 26 - dis;
                temp = chars[i];
            }
            return res + word.length();
        }
    }


}
