package com.cute.leetcode.editor.huawei;

import java.util.Scanner;

/**
 * @program: leetcode
 * @description: 找出包含偶数个 o 的最长子串
 * @author: lgy
 * @create: 2020-08-15 19:35
 **/

public class FindSubStringContainsO {

    public static int findSubStringLen(String s){
        //corner case
//        if (!s.contains("o")){
//            return s.length();
//        }
        //step 1 按照o 分割函数(没有考虑连续o)
        //fix 先替换o 为o+
        //fix 没有考虑环形
        //环形的话 奇数没有变化
        //偶数的话 可以直接比较长度 不用计算加减法
        //step 2 如果分出来偶数长度的数组，则证明有基数个o - 找到最长子串
        //step 3 如果分个出来基数个长度的数组，则证明有偶数个o 返回最大长度就可以，这种情况和边界情况可以合并
        // 没有考虑开头结尾都是o
        //环形
        String[] os = s.replaceAll("o","o+").split("o");
        //本身包含偶数个o
        int len = os.length;
        if (len % 2 != 0){
            return s.length();
        }
        else{
            return s.length() - 1;
        }

    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(findSubStringLen("looxdolx"));
    }

}
