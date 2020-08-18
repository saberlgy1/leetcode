package com.cute.leetcode.editor.huawei;

import java.util.Scanner;

/**
 * @program: leetcode
 * @description: 依次变换转换成最小字符串
 * @author: lgy
 * @create: 2020-08-15 20:25
 **/

public class ChangeStringToMin {


    public static String changeStringToMin(String s){
        //corner case  字符串长度 等于1 的时候
        if (s.length() == 1){
            return s;
        }
        String[] os = s.split("");
        //step1 for循环比较 找到最小元素 并找到一个比他大的最小元素
        //step2 两个比较 （双指针可以考虑） 先暴力法吧(暴力法两次循环)
        //最小位从第2位开始循环
        //记录其他位置是否有跟首字母一样最小的位置
        boolean flag = false;
        int tag = 1;
        for (int i = 1; i < os.length; i++){
            if ( os[i].charAt(0)<= os[tag].charAt(0)){
                tag = i;
                flag = true;
            }
        }
        if (os[tag].charAt(0)< os[0].charAt(0)){
            return swap(os,0,tag);
        }
        for (int i = 0;i < s.length() && i < tag; i++){
            if (s.charAt(i)> s.charAt(tag) && flag){
                return swap(os,i, tag);
            }
        }
        return s;
    }
    public static String swap (String[] os, int i, int j ){
        String temp = os[i];
        os[i] = os[j];
        os[j] = temp;
        StringBuilder res = new StringBuilder();
        for (String s:os
             ) {
            res.append(s);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
       // String str = in.nextLine();
        System.out.println(changeStringToMin("abacedfgga"));
    }

}
