//给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。 
//
// 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。 
//
// 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。 
//
// 文本的最后一行应为左对齐，且单词之间不插入额外的空格。 
//
// 说明: 
//
// 
// 单词是指由非空格字符组成的字符序列。 
// 每个单词的长度大于 0，小于等于 maxWidth。 
// 输入单词数组 words 至少包含一个单词。 
// 
//
// 示例: 
//
// 输入:
//words = ["This", "is", "an", "example", "of", "text", "justification."]
//maxWidth = 16
//输出:
//[
//   "This    is    an",
//   "example  of text",
//   "justification.  "
//]
// 
//
// 示例 2: 
//
// 输入:
//words = ["What","must","be","acknowledgment","shall","be"]
//maxWidth = 16
//输出:
//[
//  "What   must   be",
//  "acknowledgment  ",
//  "shall be        "
//]
//解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
//     因为最后一行应为左对齐，而不是左右两端对齐。       
//     第二行同样为左对齐，这是因为这行只包含一个单词。
// 
//
// 示例 3: 
//
// 输入:
//words = ["Science","is","what","we","understand","well","enough","to",
//"explain",
//         "to","a","computer.","Art","is","everything","else","we","do"]
//maxWidth = 20
//输出:
//[
//  "Science  is  what we",
//  "understand      well",
//  "enough to explain to",
//  "a  computer.  Art is",
//  "everything  else  we",
//  "do                  "
//]
// 
// Related Topics 字符串 模拟 👍 180 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public static void main(String[] args) {
        Solution solution = new TextJustification().new Solution();
        String[] words = new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        System.out.println(solution.fullJustify(words, 20));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        List<String> res = new ArrayList<>();
        int max;

        public List<String> fullJustify(String[] words, int maxWidth) {
            int n = words.length;
            max = maxWidth;
            if (n == 1) {
                StringBuilder sb = new StringBuilder(words[0]);
                while (sb.length()<maxWidth){
                    sb.append(" ");
                }
                res.add(sb.toString());
                return res;
            }
            int i = 0;
            while (i < n) {
                int tempL = 0, start = i;
                //贪心法确定那些单词放入一行，每个单词之间保证至少一个空格
                while (i < n && tempL + words[i].length() <= maxWidth) {
                    tempL += words[i].length();
                    if (tempL < maxWidth) {
                        tempL++;
                        i++;
                    } else {
                        i++;
                        break;
                    }
                }
                processString(words, start, i);
            }
            return res;
        }

        public void processString(String[] words, int start, int end) {
            //多个单词
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += words[i].length();
            }
            int space = max - sum;
            //最后一行单独处理
            if (end == words.length) {
                StringBuilder sb = new StringBuilder();
                for (int i = start; i < words.length; i++) {
                    sb.append(words[i]);
                    if (i != words.length - 1) {
                        sb.append(" ");
                    }
                }
                while (sb.length() < max) {
                    sb.append(" ");
                }
                res.add(sb.toString());
                return;
            }
            //只有一个单词，放在最左侧，剩余补全空格即可
            if (end - start - 1 == 0) {
                StringBuilder sb = new StringBuilder(words[start]);
                while (sb.length() < max) {
                    sb.append(" ");
                }
                res.add(sb.toString());
                return;
            }
            //空格数刚好可以平分，每个单词中间空格数相同
            if (space % (end - start - 1) == 0) {
                //求出每个单词之间的空格数
                int v = space / (end - start - 1);
                StringBuilder sp = new StringBuilder();
                for (int i = 0; i < v; i++) {
                    sp.append(" ");
                }
                StringBuilder sb = new StringBuilder();
                for (int i = start; i < end; i++) {
                    sb.append(words[i]);
                    //最后一个单词后面不补空格
                    if (i != end - 1) {
                        sb.append(sp);
                    }
                }
                res.add(sb.toString());
            }
            //单词中间空格不平分
            else {
                int v = space / (end - start - 1);
                //前两个单词中间新增的空格
                int cv = space % (end - start - 1);
                StringBuilder sp = new StringBuilder();
                for (int i = 0; i < v; i++) {
                    sp.append(" ");
                }
                StringBuilder sb = new StringBuilder();
                for (int i = start; i < end; i++) {
                    sb.append(words[i]);
                    if (i - start < cv ) {
                        sb.append(" ");
                    }
                    if (i != end - 1) {
                        sb.append(sp);
                    }
                }
                res.add(sb.toString());
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}