//输入一个字符串，打印出该字符串中字符的所有排列。 
//
// 
//
// 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。 
//
// 
//
// 示例: 
//
// 输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]
// 
//
// 
//
// 限制： 
//
// 1 <= s 的长度 <= 8 
// Related Topics 回溯算法 
// 👍 297 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ZiFuChuanDePaiLieLcof {
    public static void main(String[] args) {
        Solution solution = new ZiFuChuanDePaiLieLcof().new Solution();
        for (String s : solution.permutation("abc")
        ) {
            System.out.println(s);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力法+状态数组（多叉树遍历）
        //每个字符串一个选择状态，
        //通过回溯进行递归字符串拼接
        //然后放入set中最后返回不含相同元素的String数组
        //时间复杂度O(n!)
        /*Set<String> set = new HashSet<>();

        public String[] permutation(String s) {

            for (int i = 0; i < s.length(); i++) {
                boolean[] vis = new boolean[s.length()];
                vis[i] = true;
                String temp = s.substring(i, i + 1);

                arrangeS(s, vis, temp);
            }
            return set.toArray(new String[0]);
        }


        //递归处理
        public void arrangeS(String s, boolean[] vis, String init) {
            if (init.length() == s.length()){
                set.add(init);
                return;
            }
            for (int i = 0; i < s.length(); i++) {
                String temp = init;
                if (!vis[i]) {
                    temp += s.substring(i, i + 1);
                    vis[i] = true;
                    arrangeS(s,vis,temp);
                    vis[i] = false;
                }
            }
        }*/
        //map优化：上述方法可以用map优化一下
        //因为题目中包含相同元素，所以部分分支可以不进行重复递归计算
        //和记忆化存储（斐波那契数列很像）
        Set<String> set = new HashSet<>();

        public String[] permutation(String s) {
            Map<String, Integer> map = new HashMap<>();
            for (char c : s.toCharArray()
            ) {
                map.put(String.valueOf(c), map.getOrDefault(String.valueOf(c), 0) + 1);
            }
            arrangeS(s,map,"");
            return set.toArray(new String[0]);
        }

        public void arrangeS(String s,  Map<String, Integer> map, String init) {
            if (init.length() == s.length()) {
                set.add(init);
                return;
            }
            for (String temp : map.keySet()
            ) {
                String str=init;
                if (map.get(temp) != 0) {
                    map.put(temp, map.get(temp) - 1);
                    arrangeS(s, map, str + temp);
                    map.put(temp, map.get(temp) + 1);
                }
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}