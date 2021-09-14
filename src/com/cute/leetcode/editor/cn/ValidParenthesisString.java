//给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则： 
//
// 
// 任何左括号 ( 必须有相应的右括号 )。 
// 任何右括号 ) 必须有相应的左括号 ( 。 
// 左括号 ( 必须在对应的右括号之前 )。 
// * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。 
// 一个空字符串也被视为有效字符串。 
// 
//
// 示例 1: 
//
// 
//输入: "()"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "(*)"
//输出: True
// 
//
// 示例 3: 
//
// 
//输入: "(*))"
//输出: True
// 
//
// 注意: 
//
// 
// 字符串大小将在 [1，100] 范围内。 
// 
// Related Topics 栈 贪心 字符串 动态规划 👍 301 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Stack;

public class ValidParenthesisString {
    public static void main(String[] args) {
        Solution solution = new ValidParenthesisString().new Solution();
        System.out.println(solution.checkValidString("((((()(()()()*()(((((*)()*(**(())))))(())()())(((())())())))))))(((((())*)))()))(()((*()*(*)))(*)()"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //栈模拟解决即可
        //当不考虑*号的时候很好理解
        //考虑* 需要判断左括号下标和*号下标
        /*public boolean checkValidString(String s) {
            Stack<Integer> left = new Stack<>();
            Stack<Integer> star = new Stack<>();
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '('){
                    left.push(i);
                }else if (chars[i] == '*'){
                    star.push(i);
                }else{
                    if (!left.isEmpty()){
                        left.pop();
                    }else if (!star.isEmpty()){
                        star.pop();
                    }else{
                        return false;
                    }
                }
            }
            //判断左括号是否能找到所有对称括号
            while (!left.isEmpty()&& !star.isEmpty()){
                if (left.pop()> star.pop()){
                    return false;
                }
            }
            return left.isEmpty();
        }*/
        /*public boolean checkValidString(String s) {
            int n = s.length();
            boolean[][] dp = new boolean[n][n];
            //更新*号匹配
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '*') {
                    dp[i][i] = true;
                }
            }
            //更新dp[i-1][i]的匹配
            for (int i = 1; i < n; i++) {
                dp[i - 1][i] = ((s.charAt(i - 1) == '(' || s.charAt(i - 1) == '*') && (s.charAt(i) == ')' || s.charAt(i) == '*'));
            }
            //更新dp[i][j]
            for (int i = n - 3; i >= 0; i--) {
                char c1 = s.charAt(i);
                for (int j = i + 2; j < n; j++) {
                    char c2 = s.charAt(j);
                    if ((c1 == '(' || c1 == '*') && (c2 == ')' || c2 == '*')) {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    for (int k = i; k < j && !dp[i][j]; k++) {
                        dp[i][j] = dp[i][k] && dp[k + 1][j];
                    }
                }
            }
            return dp[0][n-1];
        }*/
        //思路三：dp
        //定义dp[i][j] 表示前i个字符串需要补齐j个有括号才能满足要求
        //dp[0][0] = true
        //考虑转移过程
        //当s.charAt(i) = '('
        /*public boolean checkValidString(String s) {
            int n = s.length();
            boolean[][] dp = new boolean[n+1][n+1];
            dp[0][0] = true;
            for (int i = 1; i <= n; i++) {
                int c1 = s.charAt(i-1);
                for (int j = 0; j <= i; j++) {
                    if (c1 == '(') {
                        if (j > 0) {
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    } else if (c1 == ')') {
                        if (j < i) {
                            dp[i][j] = dp[i - 1][j + 1];
                        }
                    } else {
                        dp[i][j] = dp[i - 1][j];
                        if (j > 0) {
                            dp[i][j] |= dp[i - 1][j - 1];
                        }
                        if (j < i) {
                            dp[i][j] |= dp[i - 1][j + 1];
                        }
                    }
                }
            }
            return dp[n][0];
        }*/
        /*public boolean checkValidString(String s) {
            int l = 0, r = 0;
            for (char c : s.toCharArray()
            ) {
                if (c == '(') {
                    l++;
                    r++;
                } else if (c == ')') {
                    l--;
                    r--;
                } else {
                    l--;
                    r++;
                }
                l = Math.max(0, l);
                if (l > r) return false;
            }
            return l == 0;
        }*/
        public boolean checkValidString(String s) {
            int l = 0, r = 0, n = s.length();
            for (int i = 0; i < n; i++) {
                l += s.charAt(i) == ')' ? -1 : 1;
                r += s.charAt(n - i - 1) == '(' ? -1 : 1;
                if (l < 0 || r < 0) {
                    return false;
                }
            }
            return true;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}