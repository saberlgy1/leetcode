package com.cute.leetcode.editor.contest.noDouble58;

/**
 * @program: leetcode
 * @description: 好的字符串
 * @author: lgy
 * @create: 2021-08-07 22:34
 * 删除字符使字符串变好 显示英文描述
 * 通过的用户数0
 * 尝试过的用户数0
 * 用户总通过次数0
 * 用户总提交次数0
 * 题目难度Easy
 * 一个字符串如果没有 三个连续 相同字符，那么它就是一个 好字符串 。
 * <p>
 * 给你一个字符串 s ，请你从 s 删除 最少 的字符，使它变成一个 好字符串 。
 * <p>
 * 请你返回删除后的字符串。题目数据保证答案总是 唯一的 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "leeetcode"
 * 输出："leetcode"
 * 解释：
 * 从第一组 'e' 里面删除一个 'e' ，得到 "leetcode" 。
 * 没有连续三个相同字符，所以返回 "leetcode" 。
 * 示例 2：
 * <p>
 * 输入：s = "aaabaaaa"
 * 输出："aabaa"
 * 解释：
 * 从第一组 'a' 里面删除一个 'a' ，得到 "aabaaaa" 。
 * 从第二组 'a' 里面删除两个 'a' ，得到 "aabaa" 。
 * 没有连续三个相同字符，所以返回 "aabaa" 。
 * 示例 3：
 * <p>
 * 输入：s = "aab"
 * 输出："aab"
 * 解释：没有连续三个相同字符，所以返回 "aab" 。
 **/

public class NiceString {

    public static void main(String[] args) {
        System.out.println(new NiceString().makeFancyString("leeetcode"));
    }

    public String makeFancyString(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int j = i + 1;
            while (j < s.length() && s.charAt(i) == s.charAt(j)) {
                j++;
            }
            if (j - i >= 2) {
                res.append(s.charAt(i));
                res.append(s.charAt(i));
                i = j - 1;
            } else {
                while (j - i > 0) {
                    res.append(s.charAt(i));
                    j--;
                }
            }
        }
        return res.toString();
    }

}
