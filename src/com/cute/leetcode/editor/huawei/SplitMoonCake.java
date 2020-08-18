package com.cute.leetcode.editor.huawei;

/**
 * @program: leetcode
 * @description: 分月饼
 * 题目描述:
 * 中秋节,公司分月饼,m个员工,买了n个月饼,m<=n,每个员工至少分1个月饼,但可以分多个,单人分到最多月饼的个数是Max1,单人分到第二多月饼个数
 * 是Max2,Max1-Max2 <= 3,单人分到第n-1多月饼个数是Max(n-1),单人分到第n多月饼个数是Max(n),Max(n-1) – Max(n) <= 3, 问有多少种分月饼的方法?
 * 输入描述:
 *
 * 输出描述:
 *
 * 示例1:
 * 输入
 *
 * 输出
 *
 * 代码片段
 *
 * 每一行输入m n,表示m个员工,n个月饼,m<=n
 *
 * 输出有多少种月饼分法
 * @author: lgy
 * @create: 2020-08-14 23:23
 **/

public class SplitMoonCake {



    public static int spilt(int m, int n){
        //corner case
        int count = 0;
        if (n == 0){
            return 0;
        }
        if (m == n){
            return 1;
        }
        if (n- m == 1){
            count +=1;
        }
        if (n - m == 2){
            count +=2;
        }
        if (n - m ==3){
            count += 2;
        }
        //step 1 剩余 n- m 块月饼
        //step 2 递归
        count += spilt(m, n- m );

        return count;
    }

    public static void main(String[] args) {
        System.out.println(spilt(2,4));
    }

}
