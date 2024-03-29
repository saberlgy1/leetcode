package com.cute.leetcode.editor.contest.no251;
/**
 * 给你一个整数 n 。如果 n 恰好有三个正除数 ，返回 true ；否则，返回 false 。
 *
 * 如果存在整数 k ，满足 n = k * m ，那么整数 m 就是 n 的一个 除数 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：false
 * 解释：2 只有两个除数：1 和 2 。
 * 示例 2：
 *
 * 输入：n = 4
 * 输出：true
 * 解释：4 有三个除数：1、2 和 4 。
 *
 *
 * 提示：
 *
 * 1 <= n <= 104
 */

/**
 * @program: leetcode
 * @description:
 *
 * @author: lgy
 * @create: 2021-08-01 12:47
 **/

public class isThree {

    class Solution {
        public boolean isThree(int n) {
            if(n <= 2){
                return false;
            }
            boolean flag =false;
            for(int i = 2; i <n ; i++){
                if(n%i==0){
                    if(flag){return false;}
                    flag = true;
                }
            }
            return flag;
        }
    }
}
