//给一非空的单词列表，返回前 k 个出现次数最多的单词。 
//
// 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。 
//
// 示例 1： 
//
// 
//输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
//输出: ["i", "love"]
//解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
//    注意，按字母顺序 "i" 在 "love" 之前。
// 
//
// 
//
// 示例 2： 
//
// 
//输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k
// = 4
//输出: ["the", "is", "sunny", "day"]
//解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
//    出现次数依次为 4, 3, 2 和 1 次。
// 
//
// 
//
// 注意： 
//
// 
// 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。 
// 输入的单词均由小写字母组成。 
// 
//
// 
//
// 扩展练习： 
//
// 
// 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。 
// 
// Related Topics 堆 字典树 哈希表 
// 👍 290 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class TopKFrequentWords {
    public static void main(String[] args) {
        Solution solution = new TopKFrequentWords().new Solution();
        String[] words = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(solution.topKFrequent(words,2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> topKFrequent(String[] words, int k) {
            //暴力法
            //最简单的是全都存入 map去重 PriorityQueue排序
            Map<String, Integer> map = new HashMap<>();
            PriorityQueue<Map.Entry> q = new PriorityQueue<>(new Comparator<Map.Entry>() {
                @Override
                public int compare(Map.Entry o1, Map.Entry o2) {
                    int res = (Integer) o1.getValue() - (Integer) o2.getValue();
                    if (res != 0) {
                        return -res;
                    }
                    return o1.getKey().toString().compareTo(o2.getKey().toString());
                }


            });
            for (String s : words) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
            for (Map.Entry entry:map.entrySet()
                 ) {
                    q.add(entry);
            }
            //然后找到前k个大小的数字对应的字符串排序后输出
            List<String> res = new ArrayList<>();
            for (int i =0; i <k; i++){
                res.add(q.poll().getKey().toString());
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}