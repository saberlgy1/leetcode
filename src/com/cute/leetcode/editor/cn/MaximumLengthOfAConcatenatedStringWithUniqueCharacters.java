//给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
//
// 请返回所有可行解 s 中最长长度。
//
//
//
// 示例 1：
//
// 输入：arr = ["un","iq","ue"]
//输出：4
//解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
//
//
// 示例 2：
//
// 输入：arr = ["cha","r","act","ers"]
//输出：6
//解释：可能的解答有 "chaers" 和 "acters"。
//
//
// 示例 3：
//
// 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
//输出：26
//
//
//
//
// 提示：
//
//
// 1 <= arr.length <= 16
// 1 <= arr[i].length <= 26
// arr[i] 中只含有小写英文字母
//
// Related Topics 位运算 回溯算法
// 👍 110 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class MaximumLengthOfAConcatenatedStringWithUniqueCharacters {
    public static void main(String[] args) {
        Solution solution = new MaximumLengthOfAConcatenatedStringWithUniqueCharacters().new Solution();
        for (int i =0;i<100;i++){
            System.out.println(solution.lowbit(i));
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        int res = 0;
        public int maxLength(List<String> arr) {
            List<Integer> masks = new ArrayList<>();
            //确立满足条件的字符串
            for (String s : arr) {
                int mask = 0;
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    if ((mask >> (c - 'a') & 1) != 0) {
                        mask = 0;
                        break;
                    }
                    mask |= (1 << (c - 'a'));
                }
                if (mask != 0) {
                    masks.add(mask);
                }
            }
            backTrack(masks,0,0);
            return res;
        }

        public void backTrack(List<Integer> masks, int index, int mask){
            //遍历完所有元素，取最大值作为返回
            if (index == masks.size()){
                res = Math.max(res,Integer.bitCount(mask));
                return;
            }
            //选当前元素
            if ((mask & masks.get(index)) == 0){
                backTrack(masks,index+1,mask|masks.get(index));
            }
            //不选当前元素
            backTrack(masks,index+1,mask);

        }
        int lowbit(int x) {
            return x & -x;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}