//编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
//
// 注意：本题相对原题稍作修改
//
// 示例:
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//]
//
// 说明：
//
//
// 所有输入均为小写字母。
// 不考虑答案输出的顺序。
//
// Related Topics 哈希表 字符串 排序
// 👍 49 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class GroupAnagramsLcci {
    public static void main(String[] args) {
        Solution solution = new GroupAnagramsLcci().new Solution();
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(solution.groupAnagrams(strs));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
         思路一：堆排序+hash
         最开始想的暴力解法，每个字符串通过小根堆进行排序
         然后存入hash即可
         */
       /* public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            PriorityQueue<Character> priorityQueue = new PriorityQueue<>(new Comparator<Character>() {
                @Override
                public int compare(Character o1, Character o2) {
                    if (o1.equals(o2)) return 0;
                    return o1 > o2 ? 1 : -1;
                }
            });
            for (String str : strs
            ) {
                char[] chars = str.toCharArray();
                for (char c : chars
                ) {
                    priorityQueue.add(c);
                }
                String tempKey = "";
                while (!priorityQueue.isEmpty()) {
                    tempKey += priorityQueue.poll();
                }
                List<String> val = map.containsKey(tempKey)?map.get(tempKey):new ArrayList<>();
                val.add(str);
                map.put(tempKey, val);

            }
            List<List<String>> res = new ArrayList<>();
            for (String str : map.keySet()
            ) {
                res.add(map.get(str));
            }
            return res;
        }*/

        /**
         * 思路二：计数拼接字符串
         其实思路一主要的时间开销在于排序，那有没有能够不排序就确定变位字符串呢
         答案是肯定的，那就是字符串拼接
         我们通过每个字母的数量，只要字母数量相同，且字母相同，即可保证两个字符串互为变位字符串
         例如abcdef 和fedcba
         两个拼接下来都是
         a_1b_1c_1d_1e_1f_1
         */
        /*public List<List<String>> groupAnagrams(String[] strs) {
            Map<String,List<String>> map = new HashMap<>();
            for (String str:strs
                 ) {
                //26个字母（不含大写字母）
                int[] cnts = new int[26];
                for (char c:str.toCharArray()
                     ) {
                    ++cnts[c-'a'];
                }
                String tempKey = "";
                for (int cnt:cnts
                     ) {
                    tempKey+=cnt;
                }

                List<String> list = map.containsKey(tempKey)?map.get(tempKey):new ArrayList<>();
                list.add(str);
                map.put(tempKey,list);
            }
            List<List<String>> res = new ArrayList<>();
            for (String str : map.keySet()
            ) {
                res.add(map.get(str));
            }
            return res;
        }*/
        /**
          思路三：质数分解唯一性
          这个思路也是看三叶大佬才知道的
          其实思路和前两个差不多，都是通过字符串寻找到可确定的变位字符
          不过这个思路可能需要一定的数学基础，我是濒临挂科的高数还是不说了
          具体定义我证明不了，不过可以知道的事，质数乘积是有唯一性的
          每一个比1大的自然数N只能有一种方式分解成质数的乘积
          定理证明如果有感兴趣的小伙伴 可以参考这个链接https://blog.csdn.net/lijil168/article/details/68185549
         */
        int[] nums = new int[26];
        public List<List<String>> groupAnagrams(String[] ss) {
            //打表相对较小的26个质数确定质数因子和字符的关联关系
            for (int i = 2, idx = 0; idx != 26; i++) {
                boolean ok = true;
                for (int j = 2; j <= i / j; j++) {
                    if (i % j == 0) {
                        ok = false;
                        break;
                    }
                }
                if (ok) nums[idx++] = i;
            }
            List<List<String>> ans = new ArrayList<>();
            Map<Integer, List<String>> map = new HashMap<>();
            for (String s : ss) {
                int cur = 1;
                for (char c : s.toCharArray()) {
                    cur *= nums[c - 'a'];
                }
                List<String> list = map.getOrDefault(cur, new ArrayList<>());
                list.add(s);
                map.put(cur, list);
            }
            for (int key : map.keySet()) ans.add(map.get(key));
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}