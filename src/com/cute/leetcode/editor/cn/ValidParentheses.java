//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串 
// 👍 1660 👎 0

package com.cute.leetcode.editor.cn;

import com.sun.deploy.util.StringUtils;

import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new ValidParentheses().new Solution();
        solution.isValid("()");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValid(String s) {
            if ("".equals(s)) {
                return true;
            }
            Stack<Character> stack = new Stack<>();
            char[] chs = s.toCharArray();
            int length = chs.length;
            for (int i = 0; i <length; i++) {
                if ('(' == chs[i] || '[' == chs[i] || '{' == chs[i]) {
                    stack.push(chs[i]);
                }
                if (')' == chs[i] || ']' == chs[i] || '}' == chs[i]) {
                    if (stack.empty()) return false;
                    if (chs[i]==')') {
                        if ((char)stack.pop()!='(') return false;
                    }
                    if (chs[i]=='}') {
                        if ((char)stack.pop()!='{') return false;
                    }
                    if (chs[i]==']') {
                        if ((char)stack.pop()!='[') return false;
                    }
                }
            }
            return stack.isEmpty();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}