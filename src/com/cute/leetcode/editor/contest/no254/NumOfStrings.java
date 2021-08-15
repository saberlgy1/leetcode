package com.cute.leetcode.editor.contest.no254;

/**
 * @program: leetcode
 * @description:
 * @author: lgy
 * @create: 2021-08-15 10:33
 **/

public class NumOfStrings {

    public int numOfStrings(String[] patterns, String word) {
            int res = 0;
        for (String str:patterns
             ) {
            if (word.contains(str)){
                res+=1;
            }
        }
        return res;
    }

}
