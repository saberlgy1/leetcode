package com.cute.leetcode.editor.juejin;

/**
 * @program: leetcode
 * @description: 掘金沸点：整数反转
 * @author: lgy
 * @create: 2021-07-26 14:21
 **/

public class ReOrderInteger {


        public int reverse(int x){
            int res = 0;
            while (x != 0 ){
                if (res < Integer.MIN_VALUE / 10 || res > Integer.MAX_VALUE / 10) {
                    return 0;
                }
                int temp = x % 10;
                res = res * 10 + temp;
                x = x /10;
            }
            return  res ;
        }

}
