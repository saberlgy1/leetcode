package com.cute.leetcode.editor.huawei;

import java.util.*;

/**
 * @program: leetcode
 * @description: 找出符合要求的字符串子串
 * 标题:找出符合要求的字符串子串 | 时间限制:1秒 | 内存限制:262144K | 语言限制: 不限
 * 【找出符合要求的字符串子串】
 * 给定两个字符串,从字符串2中找出字符串1中的所有字符,去重并按照ASCII值从小到大排序
 * 输入字符串1:长度不超过1024
 * 输入字符串2:长度不超过1000000
 * 字符范围满足ASCII编码要求,按照ASCII的值由小到大排序
 * 输入描述:
 *
 * 输出描述:
 *
 * 备注:
 *
 * 示例1:
 * 输入
 *
 * 输出
 *
 * 代码片段
 *
 * 他的代码:
 * 做题用时: 14 分钟 语言:Java 运行时间:219ms 占用内存:27068K 程序状态:答案正确
 * bach
 * bbaaccedfg
 *
 * abc
 *
 * 输入字符串1 为给定字符串bach,输入字符串2 bbaaccedfg
 * 从字符串2中找出字符串1的字符,去除重复的字符,并且按照ASCII值从小到大排序,得到输出的结果为abc。
 * 字符串1中的字符h在字符串2中找不到不输出。
 * @author: lgy
 * @create: 2020-08-14 22:16
 **/

public class FindSubStringAndSort {


    public static void main(String[] args) {
        System.out.println(findSubString("fach","bbaaccedfg"));
    }

    public static String findSubString(String s1,String s2) {
        //cornercase
        if ("".equals(s1)|| "".equals(s2)){
            return "";
        }

        //第一种办法
        //去重+ 排序
        //step 1去重可以用hash
        //step 2char值可以直接排序
        //step 3 排序
        HashSet<Character> set = new HashSet<>();
       for (int i = 0 ; i < s2.length(); i ++){
           set.add(s2.charAt(i));
       }
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++){
            if (set.contains(s1.charAt(i))){
                list.add(s1.charAt(i));
                //有一个之后就可以从set中拿掉
                set.remove(s1.charAt(i));
            }
        }

        //排序
        Object[] res =  list.toArray();
        Arrays.sort(res);
        StringBuilder result = new StringBuilder();
        for (Object c:res
             ) {
            result.append(c.toString());
        }
        return result.toString();


    }


}
