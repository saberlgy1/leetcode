package com.cute.leetcode.editor.huawei;

import java.util.Scanner;
import java.util.Stack;

/**
 * @program: leetcode
 * @description: 找到字符串中包含joy的个数，每个字母限制使用一次
 * @author: lgy
 * @create: 2020-08-15 21:10
 **/

public class FindJoy {


    public static int findJoy(String s) {
        //corner case

        int res = 0;
        if (s.length() <= 2) {
            return res;
        }
        //思路 1 感觉栈可行,将所有joy压入栈，但是跨字符组合取不出来
        //思路 2 暴力破解 找到所有j 然后依次去找o、y 找到后 把 j、o 、y变成其他无关字符，防止重复饮用（时间不够了、三层for循环）
        //step 2 可以跨字符组合
        String[] os = s.split("");
        for (int i = 0; i < s.length(); i++) {
            if ("j".equals(os[i])) {
                for (int j = i + 1; j < s.length(); j++) {
                    if ("o".equals(os[j])) {
                        for (int v = j + 1; v < s.length(); v++) {
                            if ("y".equals(os[v])) {
                                os[i] = "!";
                                os[j] = "!";
                                os[v] = "!";
                                res += 1;
                                break;

                            }
                        }
                        break;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(findJoy("adxjxjxoxyxoxxyyy"));
    }

}
