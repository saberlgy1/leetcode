package com.cute.leetcode.editor.huawei;

import jdk.nashorn.internal.runtime.regexp.RegExp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * @program: leetcode
 * @description: 英文输入法
 * 【英文输入法】
 * 主管期望你来实现英文输入法单词联想功能。需求如下:
 * 依据用户输入的单词前缀,从已输入的英文语句中联想出用户想输入的单词,按字典序输出联想到的单词序列,如果联想不到,请输出用户输入的单词前缀。
 * 注意:
 * 1. 英文单词联想时,区分大小写
 * 2. 缩略形式如”don't”,判定为两个单词,”don”和”t”
 * 3. 输出的单词序列,不能有重复单词,且只能是英文单词,不能有标点符号
 * 输入描述:
 *
 * 输出描述:
 * 输入为两行。
 * 首行输入一段由英文单词word和标点符号组成的语句str;
 * 接下来一行为一个英文单词前缀pre。
 * 0 < word.length() <= 20
 * 0 < str.length <= 10000
 * 0 < pre <= 20
 *
 * 输出符合要求的单词序列或单词前缀,存在多个时,单词之间以单个空格分割
 * @author: lgy
 * @create: 2020-08-14 22:35
 **/

public class EngInputType {
    public static List<String> input(String str,String  pre){
        //corner case
        if (str.length()< pre.length()){
            List<String> res = new ArrayList<>();
            res.add(pre);
            return res;
        }
        //用例有点少不太好理解题意
        //Step 1：按照当前理解可以理解为 首先按照标点spilt 字符串为数组
        //Step 2：轮训判断每个word 和pre 可以通过长度过滤一部分不合理选项
        //Step 3：数组排序
        //set存储去重
        HashSet<String> strSet = new HashSet<>();
        List<String> res = new ArrayList<>();
        String[] pattern = str.replaceAll("\"","").split("`+|\\?+|\\!+|\\,+|\\.+| +");
        for (String s:pattern
             ) {
            if (s.length() >= pre.length()){
                if (s.startsWith(pre)){
                   if (!strSet.contains(s)){
                       strSet.add(s);
                       res.add(s);
                   }
                }
            }
        }
        Collections.sort(res);
        return res;
    }

    public static void main(String[] args) {
        String  str = "I don`t yolove you, yobut \"you\" must love me!";
       List<String> res = input(str, "yo");
        for (String a:res
             ) {
            System.out.println(a);
        }
    }

}
