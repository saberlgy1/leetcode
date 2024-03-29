//给你一个字符数组 chars ，请使用下述算法压缩：
//
// 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
//
//
// 如果这一组长度为 1 ，则将字符追加到 s 中。
// 否则，需要向 s 追加字符，后跟这一组的长度。
//
//
// 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会
//被拆分为多个字符。
//
// 请在 修改完输入数组后 ，返回该数组的新长度。
//
// 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
//
//
//
// 示例 1：
//
//
//输入：chars = ["a","a","b","b","c","c","c"]
//输出：返回 6 ，输入数组的前 6 个字符应该是：["a","2","b","2","c","3"]
//解释：
//"aa" 被 "a2" 替代。"bb" 被 "b2" 替代。"ccc" 被 "c3" 替代。
//
//
// 示例 2：
//
//
//输入：chars = ["a"]
//输出：返回 1 ，输入数组的前 1 个字符应该是：["a"]
//解释：
//没有任何字符串被替代。
//
//
// 示例 3：
//
//
//输入：chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
//输出：返回 4 ，输入数组的前 4 个字符应该是：["a","b","1","2"]。
//解释：
//由于字符 "a" 不重复，所以不会被压缩。"bbbbbbbbbbbb" 被 “b12” 替代。
//注意每个数字在数组中都有它自己的位置。
//
//
//
//
// 提示：
//
//
// 1 <= chars.length <= 2000
// chars[i] 可以是小写英文字母、大写英文字母、数字或符号
//
// Related Topics 双指针 字符串 👍 217 👎 0

package com.cute.leetcode.editor.cn;

public class StringCompression {
    public static void main(String[] args) {
        Solution solution = new StringCompression().new Solution();
        char[] chars = "abbbbbbbbbbbb".toCharArray();
        System.out.println(solution.compress(chars));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：双指针
        //忘记了修改原数组
        //这种方法空间复杂度是O(n)
        /*public int compress(char[] chars) {
            int n = chars.length;
            //corner case
            if (n <= 1) {
                return n;
            }
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < n) {
                char temp = chars[i];
                int j = i + 1, cnt = 1;
                while (j < n && chars[j] == temp) {
                    j++;
                    cnt++;
                }
                i = j;
                sb.append(temp);
                if (cnt > 1) {
                    sb.append(cnt);
                }
            }
            String res = sb.toString();
            char[] temp = res.toCharArray();
            for (int j = 0; j < temp.length; j++) {
                chars[j] = temp[j];
            }
            return res.length();
        }*/
        //思路二：双指针原地置换
        public int compress(char[] chars) {
            int n = chars.length;
            //corner case
            if (n <= 1) {
                return n;
            }
            int idx = 0, i = 0;
            while (i < n) {
                char temp = chars[i];
                chars[idx] = temp;
                idx++;
                int tempIdx = idx;
                int j = i + 1, cnt = 1;
                while (j < n && chars[j] == temp) {
                    j++;
                    cnt++;
                }
                if (cnt > 1) {
                    while (cnt != 0) {
                        chars[idx++] = (char) ((cnt % 10) + '0');
                        cnt /= 10;
                    }
                    reverse(chars, tempIdx, idx - 1);
                }
                i = j;
            }
            return idx;
        }

        void reverse(char[] cs, int start, int end) {
            while (start < end) {
                char t = cs[start];
                cs[start] = cs[end];
                cs[end] = t;
                start++;
                end--;
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}