//森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。 
//
// 返回森林中兔子的最少数量。 
//
// 
//示例:
//输入: answers = [1, 1, 2]
//输出: 5
//解释:
//两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
//之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
//设回答了 "2" 的兔子为蓝色。
//此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
//因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。
//
//输入: answers = [10, 10, 10]
//输出: 11
//
//输入: answers = []
//输出: 0
// 
//
// 说明: 
//
// 
// answers 的长度最大为1000。 
// answers[i] 是在 [0, 999] 范围内的整数。 
// 
// Related Topics 哈希表 数学 
// 👍 80 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RabbitsInForest {
    public static void main(String[] args) {
        Solution solution = new RabbitsInForest().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numRabbits(int[] answers) {
            int  res = 0;
/*            if (m == 0) {
                return 0;
            }
            //x只兔子 说有y只跟我一样颜色，如果x<y,则可以按照一种颜色来计算，如果x>y 则需要按照多种颜色计算
            //x/(y+1) 种颜色的兔子（取整 + 1）（y + 1）;
            Map<Integer, Integer> map = new HashMap<>();
            for (int y : answers) {
                map.put(y, map.getOrDefault(y, 0) + 1);
            }

*//*            for (Integer i : map.keySet()
            ) {
                if (map.get(i) % (i + 1) != 0) {
                    res = res + (map.get(i) / (i + 1) + 1) * (i + 1);
                } else {
                    res = res + (map.get(i) / (i + 1)) * (i + 1);
                }
            }*//*
            //优化代码结构 余数运算特性
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int y = entry.getKey(), x = entry.getValue();
                res += (x + y) / (y + 1) * (y + 1);
            }*/
            //一次遍历
            int[] m = new int[1000];
            for (Integer i: answers
                 ) {
                if (m[i] > 0){
                    m[i] --;
                }else{
                    m[i] = i;
                    res += i + 1;
                }

            }

            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}