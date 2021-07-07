//大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。 
//
// 你可以搭配 任意 两道餐品做一顿大餐。 
//
// 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大
//餐 的数量。结果需要对 109 + 7 取余。 
//
// 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。 
//
// 
//
// 示例 1： 
//
// 
//输入：deliciousness = [1,3,5,7,9]
//输出：4
//解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
//它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
// 
//
// 示例 2： 
//
// 
//输入：deliciousness = [1,1,1,3,3,3,7]
//输出：15
//解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。 
//
// 
//
// 提示： 
//
// 
// 1 <= deliciousness.length <= 105 
// 0 <= deliciousness[i] <= 220 
// 
// Related Topics 数组 哈希表 
// 👍 73 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountGoodMeals {
    public static void main(String[] args) {
        //int[] dels = new int[]{2160, 1936, 3, 29, 27, 5, 2503, 1593, 2, 0, 16, 0, 3860, 28908, 6, 2, 15, 49, 6246, 1946, 23, 105, 7996, 196, 0, 2, 55, 457, 5, 3, 924, 7268, 16, 48, 4, 0, 12, 116, 2628, 1468};
        //int[] dels = new int[]{1, 1, 1, 3, 3, 3, 7};
        //int[] dels = new int[]{149, 107, 1, 63, 0, 1, 6867, 1325, 5611, 2581, 39, 89, 46, 18, 12, 20, 22, 234};
        int[] dels = new int[]{64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64};
        Solution solution = new CountGoodMeals().new Solution();
        System.out.println(solution.countPairs(dels));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力法
        //暂时还不确定暴力法会不会TLE
        //思路就是很简单的穷举
        //然后可以先对数组进行排序，确定最大的美味值，然后确认2的最大次幂
        //即 $$del[del.length - 2] + del[del.length - 1] <= 2^{n}$$
        //果然TLE了，而且因为没有算到最终case 所以导致了没有用上余数，也就是说没考虑答案越界
        /*public int countPairs(int[] del) {
            //corner case
            if (del.length < 2) {
                return 0;
            }
            Arrays.sort(del);
            int n = del.length;
            int max = del[n - 2] + del[n - 1];
            int index = 0, temp = 1;
            while (temp < max) {
                temp = 2 << index;
                index++;
            }
            int res = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int sum = del[i] + del[j];
                    for (int cnt = 0; cnt <= index ; cnt++) {
                        int val = (1 << cnt);
                        if (sum > val) {
                            continue;
                        }else if (sum == val){
                            res+=1;
                        }else{
                            break;
                        }
                    }
                }
            }
            return res;
        }*/
        //优化一：思路一的优化
        //其实使用思路一的原因是我没有找到合适的如何判断是2的整数次幂的方案
        //后来想到了之前做过的2的幂的题，可以做异或处理
        //还是TLE了
        //优化二：发现这种方法不需要排序什么的有点浪费了
        //还是TLE

        /*public int countPairs(int[] del) {
            //corner case
            if (del.length < 2) {
                return 0;
            }
            int n = del.length;
            *//*Arrays.sort(del);
            int n = del.length;
            int max = del[n - 2] + del[n - 1];
            int index = 0, temp = 1;
            while (temp < max) {
                temp = 2 << index;
                index++;
            }*//*
            int res = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int sum = del[i] + del[j];
                    if (sum >0 && (sum & (sum - 1)) == 0) {
                        res += 1;
                    }
                }
            }
            return res;
        }
        */
        //优化三：hash
        //看来是题目本身就不想用n^2复杂度结局
        //那只能试试hash了
        //map存储每个元素出现的次数，然后类似两数之和进行枚举运算
        //对于元素del[i] 若存在x 使t-x = del[i] 则 有cnt[x] *(cnt[x] -1)的情况满足条件
        //这种情况回怼x t进行两次重复运算 所以求解应该除2
        //同时最大边界为2
        public int countPairs(int[] del) {
            Map<Integer, Integer> cnt = new HashMap<>();
            int max = 1 << 22, mod = (int) 1e9 + 7;
            long res = 0;
            for (int num : del
            ) {
                cnt.put(num, cnt.getOrDefault(num, 0) + 1);
            }
            for (int x : cnt.keySet()) {
                for (int i = 1; i < max; i <<= 1) {
                    int t = i - x;
                    if (cnt.containsKey(t)) {
                        //两元素相等
                        if (t == x) {
                            res += (long) (cnt.get(x) - 1) * cnt.get(x);
                        }
                        //两元素不相等
                        else {
                            res += (long) cnt.get(x) * cnt.get(t);
                        }
                    }
                }
            }
            res >>=1;
            //类型转换
            return (int) (res % mod);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}