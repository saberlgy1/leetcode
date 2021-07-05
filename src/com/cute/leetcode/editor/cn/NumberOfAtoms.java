//给定一个化学式formula（作为字符串），返回每种原子的数量。 
//
// 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。 
//
// 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
// 
//
// 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。 
//
// 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。 
//
// 给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数
//量（如果数量大于 1），以此类推。 
//
// 示例 1: 
//
// 
//输入: 
//formula = "H2O"
//输出: "H2O"
//解释: 
//原子的数量是 {'H': 2, 'O': 1}。
// 
//
// 示例 2: 
//
// 
//输入: 
//formula = "Mg(OH)2"
//输出: "H2MgO2"
//解释: 
//原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
// 
//
// 示例 3: 
//
// 
//输入: 
//formula = "K4(ON(SO3)2)2"
//输出: "K4N2O14S4"
//解释: 
//原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
// 
//
// 注意: 
//
// 
// 所有原子的第一个字母为大写，剩余字母都是小写。 
// formula的长度在[1, 1000]之间。 
// formula只包含字母、数字和圆括号，并且题目中给定的是合法的化学式。 
// 
// Related Topics 栈 哈希表 字符串 
// 👍 151 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class NumberOfAtoms {
    public static void main(String[] args) {
        Solution solution = new NumberOfAtoms().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：栈
        //这种字符串匹配类型的问题第一反应都是用栈处理
        //通过栈匹配计算原子数的计算规则
        //通过hashmap存储原子数数量
        //存入hashmap时可以通过索引记录每一次入栈的原子，根据是否需要进行乘法来进行乘法运算
        //本题咱不需要考虑边界情况

        class Node {
            String s; int v;
            Node (String _s, int _v) {
                s = _s; v = _v;
            }
        }
        public String countOfAtoms(String s) {
            int n = s.length();
            char[] cs = s.toCharArray();
            Map<String, Integer> map = new HashMap<>();
            Deque<String> d = new ArrayDeque<>();
            int idx = 0;
            for (int i = 0; i < n; ) {
                char c = cs[i];
                //如果是括号则可以直接压栈
                if (c == '(' || c == ')') {
                    d.addLast(String.valueOf(c));
                    i++;
                } else {
                    //如果是数字则要获得完整数字
                    if (Character.isDigit(c)) {
                        // 获取完整的数字，并解析出对应的数值
                        int j = i;
                        while (j < n && Character.isDigit(cs[j])) {
                            j++;
                        }
                        String numStr = s.substring(i, j);
                        i = j;
                        int cnt = Integer.parseInt(numStr);
                        // 如果栈顶元素是 )，说明当前数值可以应用给「连续一段」的原子中
                        if (!d.isEmpty() && ")".equals(d.peekLast())) {
                            List<String> tmp = new ArrayList<>();
                            d.pollLast(); // pop )
                            while (!d.isEmpty() && !"(".equals(d.peekLast())) {
                                String cur = d.pollLast();
                                map.put(cur, map.getOrDefault(cur, 1) * cnt);
                                tmp.add(cur);
                            }
                            d.pollLast(); // pop (

                            for (int k = tmp.size() - 1; k >= 0; k--) {
                                d.addLast(tmp.get(k));
                            }
                            // 如果栈顶元素不是 )，说明当前数值只能应用给栈顶的原子
                        } else {
                            String cur = d.pollLast();
                            map.put(cur, map.getOrDefault(cur, 1) * cnt);
                            d.addLast(cur);
                        }
                    } else {
                        // 获取完整的原子名
                        int j = i + 1;
                        while (j < n && Character.isLowerCase(cs[j])) j++;
                        String cur = s.substring(i, j) + "_" + String.valueOf(idx++);
                        map.put(cur, map.getOrDefault(cur, 0) + 1);
                        i = j;
                        d.addLast(cur);
                    }
                }
            }

            // 将不同编号的相同原子进行合并
            Map<String, Node> mm = new HashMap<>();
            for (String key : map.keySet()) {
                String atom = key.split("_")[0];
                int cnt = map.get(key);
                Node node = null;
                if (mm.containsKey(atom)) {
                    node = mm.get(atom);
                } else {
                    node = new Node(atom, 0);
                }
                node.v += cnt;
                mm.put(atom, node);
            }

            // 使用优先队列（堆）对 Node 进行字典序排序，并构造答案
            PriorityQueue<Node> q = new PriorityQueue<Node>((a,b)->a.s.compareTo(b.s));
            for (String atom : mm.keySet()) q.add(mm.get(atom));

            StringBuilder sb = new StringBuilder();
            while (!q.isEmpty()) {
                Node poll = q.poll();
                sb.append(poll.s);
                if (poll.v > 1) sb.append(poll.v);
            }

            return sb.toString();
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}