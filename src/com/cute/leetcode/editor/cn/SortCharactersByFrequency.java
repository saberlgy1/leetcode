//给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
//
// 示例 1:
//
//
//输入:
//"tree"
//
//输出:
//"eert"
//
//解释:
//'e'出现两次，'r'和't'都只出现一次。
//因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
//
//
// 示例 2:
//
//
//输入:
//"cccaaa"
//
//输出:
//"cccaaa"
//
//解释:
//'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
//注意"cacaca"是不正确的，因为相同的字母必须放在一起。
//
//
// 示例 3:
//
//
//输入:
//"Aabb"
//
//输出:
//"bbAa"
//
//解释:
//此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
//注意'A'和'a'被认为是两种不同的字符。
//
// Related Topics 哈希表 字符串 桶排序 计数 排序 堆（优先队列）
// 👍 283 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;
import java.util.stream.Collectors;

public class SortCharactersByFrequency {
    public static void main(String[] args) {
        Solution solution = new SortCharactersByFrequency().new Solution();
        System.out.println(solution.frequencySort("Aabb"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：通过map存储字符和字符出现数量
        //可以优化为对象，然后根据对象重写comparator函数
        /*public String frequencySort(String s) {
            Map<Character, Integer> map = new TreeMap<>();
            char[] chars = s.toCharArray();
            for (Character c : chars
            ) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            String res = "";
            List<Map.Entry<Character,Integer>> list = new ArrayList<>(map.entrySet());
            list.sort(new Comparator<Map.Entry<Character, Integer>>() {
                @Override
                public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                    return o2.getValue()- o1.getValue();
                }
            });
            Map<Character,Integer> map2 = new LinkedHashMap<>();
            for(Map.Entry<Character,Integer> entry: list){
                map2.put(entry.getKey(), entry.getValue());
            }
            for (Character c : map2.keySet()
            ) {
                for (int i = 0; i < map.get(c);i++){
                    res += c;
                }
            }
            return res;
        }*/
        //思路二：模拟数组
        //所有字母转为ASCII码共128位，所以预先设定一个128大小的数组int[128][2]
        //然后按照index放入对应数组位，nums[i][1]表示出现数量
        public String frequencySort(String s) {
            int[][] nums = new int[128][2];
            for (int i = 0; i < 128; i++) {
                nums[i][0] = i;
            }
            for (Character c : s.toCharArray()) {
                nums[c][1]++;
            }
            Arrays.sort(nums, (a, b) -> {
                if (a[1] != b[1]) {
                    return b[1] - a[1];
                }
                return a[0] - b[0];
            });
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i][1] != 0) {
                    char c = (char) (nums[i][0]);
                    int cnt = nums[i][1];
                    while (cnt > 0) {
                        sb.append(c);
                        cnt--;
                    }
                }
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}