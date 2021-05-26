//给出一个字符串 s（仅含有小写英文字母和括号）。 
//
// 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。 
//
// 注意，您的结果中 不应 包含任何括号。 
//
// 
//
// 示例 1： 
//
// 输入：s = "(abcd)"
//输出："dcba"
// 
//
// 示例 2： 
//
// 输入：s = "(u(love)i)"
//输出："iloveu"
// 
//
// 示例 3： 
//
// 输入：s = "(ed(et(oc))el)"
//输出："leetcode"
// 
//
// 示例 4： 
//
// 输入：s = "a(bcdefghijkl(mno)p)q"
//输出："apmnolkjihgfedcbq"
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 2000 
// s 中只有小写英文字母和括号 
// 我们确保所有括号都是成对出现的 
// 
// Related Topics 栈 
// 👍 94 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class ReverseSubstringsBetweenEachPairOfParentheses {
    public static void main(String[] args) {
        Solution solution = new ReverseSubstringsBetweenEachPairOfParentheses().new Solution();
        System.out.println(solution.reverseParentheses("(u(love)i)"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseParentheses(String s) {
            //栈先进后出，然后括号匹配就依次出栈，然后再出栈全部完成后入栈，最后全部出栈即可
            /*String res = "";
            char[] schar = s.toCharArray();
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++){
                if (schar[i] == ')'){
                    String temp = "";
                    while (stack.peek() != '('){
                        temp += stack.peek();
                        stack.pop();
                    }
                    //排除左括号
                    stack.pop();
                    for (Character c:temp.toCharArray()
                         ) {
                        stack.push(c);
                    }
                }else{
                    stack.push(schar[i]);
                }
            }
            while (!stack.isEmpty()){
                res=stack.peek()+res;
                stack.pop();
            }
            return res;*/

            //优化方法和我想的一样，第一次判断括号位置和对应括号，然后依次反转即可
            //双端队列
            //记录一个数组判断对应括号
            String res = "";
            int[] pair = new int[s.length()];
            Deque<Integer> stack = new LinkedList<Integer>();
            for (int i = 0; i < pair.length; i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else if (s.charAt(i) == ')') {
                    int j = stack.pop();
                    pair[i] = j;
                    pair[j] = i;
                }
            }
            StringBuffer sb = new StringBuffer();
            int index = 0, step = 1;
            while (index < s.length()) {
                if (s.charAt(index) == '(' || s.charAt(index) == ')') {
                    index = pair[index];
                    step = -step;
                } else {
                    sb.append(s.charAt(index));
                }
                index += step;
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}