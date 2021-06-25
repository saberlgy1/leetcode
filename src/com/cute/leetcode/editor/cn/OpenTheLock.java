//你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
// 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。 
//
// 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。 
//
// 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。 
//
// 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。 
//
// 
//
// 示例 1: 
//
// 
//输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
//输出：6
//解释：
//可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
//注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
//因为当拨动到 "0102" 时这个锁就会被锁定。
// 
//
// 示例 2: 
//
// 
//输入: deadends = ["8888"], target = "0009"
//输出：1
//解释：
//把最后一位反向旋转一次即可 "0000" -> "0009"。
// 
//
// 示例 3: 
//
// 
//输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], targ
//et = "8888"
//输出：-1
//解释：
//无法旋转到目标数字且不被锁定。
// 
//
// 示例 4: 
//
// 
//输入: deadends = ["0000"], target = "8888"
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 死亡列表 deadends 的长度范围为 [1, 500]。 
// 目标数字 target 不会在 deadends 之中。 
// 每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。 
// 
// Related Topics 广度优先搜索 数组 哈希表 字符串 
// 👍 283 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class OpenTheLock {
    public static void main(String[] args) {
        Solution solution = new OpenTheLock().new Solution();
        //String[] deadends = new String[]{"5557", "5553", "5575", "5535", "5755", "5355", "7555", "3555", "6655", "6455", "4655", "4455", "5665", "5445", "5645", "5465", "5566", "5544", "5564", "5546", "6565", "4545", "6545", "4565", "5656", "5454", "5654", "5456", "6556", "4554", "4556", "6554"};
        String[] deadends = new String[]{"1102","1001","0111","0202","1000"};
        System.out.println(solution.openLock(deadends, "0122"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：
        //首先要确定边界条件
        //什么情况无法满足target
        //1、非法target（题目中已经排除）
        //2、所有有可能变成target的数都被包含在deadends中
        //3、限制数组中包含0000
        //转变为target 只有通过target +-1 +-10 +-100 +-1000来做
        //同时保留四种特殊情况 当前位上数字为0的时候，元素变换就变成了 +1/9 +10/90 +100/900 +1000/9000
        //广度优先搜索+多叉树
        //根据题目可以预见每一个树的变换规律有8种可能，也就是所有分支节点8^4，我们可以根据所有1分支节点进行分叉、减支
        //上述的2情况也就是剪枝条件
        //从0000开始枚举所有变化，然后进行广度优先遍历
        //然后记录变化到当前位置的最少次数，最后当遍历完所有情况，返回结果集中的最小次数
        //如果无法满足结果集，就返回-1
        //这个思路的判断太过复杂 可以优化成思路二的判断方案
        /*public int openLock(String[] deadends, String target) {
            //将deadends存入set
            Set<String> set = new HashSet<>();
            Map<String, Integer> map = new HashMap<>();
            if ("0000".equals(target)) {
                return 0;
            }
            for (String dead : deadends
            ) {
                if ("0000".equals(dead)) {
                    return -1;
                }
                set.add(dead);
            }
            Queue<String> queue = new LinkedList<>();
            queue.add("0000");
            map.put("0000", 0);
            while (!queue.isEmpty()) {
                String temp = queue.poll();
                if (temp.equals(target)) {
                    return map.get(temp);
                }
                for (int i = 0; i < 4; i++) {
                    String pre = temp.substring(0, i);
                    String cur = temp.substring(i + 1, 4);
                    if (temp.charAt(i) == '0') {
                        if (!set.contains(pre + "1" + cur)) {
                            queue.add(pre + "1" + cur);
                            set.add(pre + "1" + cur);
                            map.put(pre + "1" + cur, map.get(temp) + 1);
                        }
                        if (!set.contains(pre + "9" + cur)) {
                            queue.add(pre + "9" + cur);
                            set.add(pre + "9" + cur);
                            map.put(pre + "9" + cur, map.get(temp) + 1);
                        }
                    } else if (temp.charAt(i) == '9') {
                        if (!set.contains(pre + "0" + cur)) {
                            queue.add(pre + "0" + cur);
                            set.add(pre + "0" + cur);
                            map.put(pre + "0" + cur, map.get(temp) + 1);
                        }
                        if (!set.contains(pre + "8" + cur)) {
                            queue.add(pre + "8" + cur);
                            set.add(pre + "8" + cur);
                            map.put(pre + "8" + cur, map.get(temp) + 1);
                        }
                    } else {
                        String key1 = pre + (Integer.parseInt(temp.substring(i, i + 1)) + 1) + cur;
                        if (!set.contains(key1)) {
                            queue.add(key1);
                            set.add(key1);
                            map.put(key1, map.get(temp) + 1);
                        }
                        String key2 = pre + (Integer.parseInt(temp.substring(i, i + 1)) - 1) + cur;
                        if (!set.contains(key2)) {
                            queue.add(key2);
                            set.add(key2);
                            map.put(key2, map.get(temp) + 1);
                        }
                    }
                }
            }
            return -1;

        }*/
        //思路二：优化判断方案
/*        public int openLock(String[] deadends, String target) {
            //将deadends存入set
            Set<String> set = new HashSet<>();
            Map<String, Integer> map = new HashMap<>();
            for (String dead : deadends
            ) {
                if ("0000".equals(dead)) {
                    return -1;
                }
                set.add(dead);
            }
            Queue<String> queue = new LinkedList<>();
            queue.add("0000");
            map.put("0000", 0);
            int size = 1;
            while (!queue.isEmpty()) {
                String temp = queue.poll();
                if (temp.equals(target)) {
                    return map.get(temp);
                }
                char[] c = temp.toCharArray();
                for (int i = 0; i < 4; i++) {
                    char tempC = c[i];
                    int x = tempC - '0';
                    int y = x;
                    c[i] = (char) (x + 1 > 9 ? '0' : x + 1 + '0');
                    String s = String.valueOf(c, 0, 4);
                    if (!set.contains(s)) {
                        queue.offer(s);
                        set.add(s);
                        map.put(s, map.get(temp) + 1);
                    }
                    c[i] = (char) (y - 1 < 0 ? 9 + '0' : y - 1 + '0');
                    s = String.valueOf(c, 0, 4);
                    if (!set.contains(s)) {
                        queue.offer(s);
                        set.add(s);
                        map.put(s, map.get(temp) + 1);
                    }
                    if (s.equals(target)) {
                        return map.get(target);
                    }
                    c[i] = tempC;
                }
            }
            return -1;

        }*/
        /*思路三：双向BFS
        单向BFS的优化难度在于空间复杂度，随着queue的增多，会导致大量内存消耗
        双向BSF的思路其实有点类似二分查找的思路，就是由从起点的单向扫描，变成起点和终点相向而行的扫描过程
        我们首先要依据单向扫描的方案对分别对起点和终点进行扫描
        但是本题 为了保证两边扫描的次数基本相同，可以通过对queue的大小进行区分扫描
        准确的说双向扫描更像是一种离散式的扫描，他将BFS从单向，变成离散式的扫描，而不是再依从每个节点的连续顺序
        如果一侧map包含了另一侧queue当前弹出元素，则表示两边节点相遇，返回两边节点的value之和 + 1*/
        Set<String> set = new HashSet<>();
        Set<String> traced = new HashSet<>();
        public int openLock(String[] deadends, String target) {
            //corner case
            if ("0000".equals(target)){
                return 0;
            }
            Map<String, Integer> rmap = new HashMap<>(), lmap = new HashMap<>();
            Deque<String> rqueue = new ArrayDeque<>(), lqueue = new ArrayDeque<>();

            for (String s : deadends
            ) {
                if (s.equals(target) || "0000".equals(s)) {
                    return -1;
                }
                set.add(s);
            }
            rqueue.add(target);
            lqueue.add("0000");
            lmap.put("0000", 0);
            rmap.put(target, 0);
            while (!lqueue.isEmpty() && !rqueue.isEmpty()) {
                int t = -1;
                if (lqueue.size() <= rqueue.size()) {
                    t = trace(lqueue, lmap, rmap);
                } else {
                    t = trace(rqueue, rmap, lmap);
                }
                if (t != -1) {
                    return t;
                }
            }
            return -1;
        }

        public int trace(Deque<String> queue, Map<String, Integer> cur, Map<String, Integer> other) {
            String temp = queue.pollFirst();
            if (other.containsKey(temp)) {
                return other.get(temp) + cur.get(temp) + 1;
            }
            for (int i = 0; i < 4; i++) {
                char[] c = temp.toCharArray();
                char tempC = c[i];
                int x = tempC - '0';
                int y = x;
                c[i] = (char) (x + 1 > 9 ? '0' : x + 1 + '0');
                String s1 = String.valueOf(c, 0, 4);
                if (!set.contains(s1)){
                    if (other.containsKey(s1)) {
                        return other.get(s1) + cur.get(temp) + 1;
                    }else{
                        if (!traced.contains(s1)){
                            queue.addLast(s1);
                        }
                        traced.add(s1);
                        cur.put(s1, cur.get(temp) + 1);
                    }
                }
                c[i] = (char) (y - 1 < 0 ? 9 + '0' : y - 1 + '0');
                String s2 = String.valueOf(c, 0, 4);
                if (!set.contains(s2)){
                    if (other.containsKey(s2)) {
                        return other.get(s2) + cur.get(temp) + 1;
                    } else {
                        //判断当前节点是否被扫描过
                        if (!traced.contains(s2)){
                            queue.addLast(s2);
                        }
                        traced.add(s2);
                        cur.put(s2, cur.get(temp) + 1);
                    }
                }

            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}