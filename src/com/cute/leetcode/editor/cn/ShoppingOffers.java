//在 LeetCode 商店中， 有 n 件在售的物品。每件物品都有对应的价格。然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。 
//
// 给你一个整数数组 price 表示物品价格，其中 price[i] 是第 i 件物品的价格。另有一个整数数组 needs 表示购物清单，其中 needs[
//i] 是需要购买第 i 件物品的数量。 
//
// 还有一个数组 special 表示大礼包，special[i] 的长度为 n + 1 ，其中 special[i][j] 表示第 i 个大礼包中内含第 
//j 件物品的数量，且 special[i][n] （也就是数组中的最后一个整数）为第 i 个大礼包的价格。 
//
// 返回 确切 满足购物清单所需花费的最低价格，你可以充分利用大礼包的优惠活动。你不能购买超出购物清单指定数量的物品，即使那样会降低整体价格。任意大礼包可无限
//次购买。 
//
// 
//
// 示例 1： 
//
// 
//输入：price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
//输出：14
//解释：有 A 和 B 两种物品，价格分别为 ¥2 和 ¥5 。 
//大礼包 1 ，你可以以 ¥5 的价格购买 3A 和 0B 。 
//大礼包 2 ，你可以以 ¥10 的价格购买 1A 和 2B 。 
//需要购买 3 个 A 和 2 个 B ， 所以付 ¥10 购买 1A 和 2B（大礼包 2），以及 ¥4 购买 2A 。 
//
// 示例 2： 
//
// 
//输入：price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1]
//输出：11
//解释：A ，B ，C 的价格分别为 ¥2 ，¥3 ，¥4 。
//可以用 ¥4 购买 1A 和 1B ，也可以用 ¥9 购买 2A ，2B 和 1C 。 
//需要买 1A ，2B 和 1C ，所以付 ¥4 买 1A 和 1B（大礼包 1），以及 ¥3 购买 1B ， ¥4 购买 1C 。 
//不可以购买超出待购清单的物品，尽管购买大礼包 2 更加便宜。 
//
// 
//
// 提示： 
//
// 
// n == price.length 
// n == needs.length 
// 1 <= n <= 6 
// 0 <= price[i] <= 10 
// 0 <= needs[i] <= 10 
// 1 <= special.length <= 100 
// special[i].length == n + 1 
// 0 <= special[i][j] <= 50 
// 
// Related Topics 位运算 记忆化搜索 数组 动态规划 回溯 状态压缩 👍 293 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingOffers {
    public static void main(String[] args) {
        Solution solution = new ShoppingOffers().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int ans = 0x3f3f3f3f;
        List<Integer> price, needs;
        List<List<Integer>> special;
        Map<Integer, Integer> map = new HashMap<>();
        int n, m;

        public int shoppingOffers(List<Integer> _price, List<List<Integer>> _special, List<Integer> _needs) {
            price = _price;
            special = _special;
            needs = _needs;
            n = price.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) temp.add(0);
            for (int i = 0; i < n; i++) {
                List<Integer> clone = new ArrayList<>(temp);
                clone.set(i, 1);
                clone.add(price.get(i));
                special.add(clone);
            }
            m = special.size();
            for (int i = 0; i < m; i++) {
                List<Integer> x = special.get(i);
                int min = -1;
                for (int j = 0; j < n; j++) {
                    int a = x.get(j), b = needs.get(j);
                    if (a == 0 || b == 0) continue;
                    if (min == -1) min = b / a;
                    else min = Math.min(min, b / a);
                }
                map.put(i, Math.max(min, 0));
            }
            dfs(0, needs, 0);
            return ans;
        }

        void dfs(int u, List<Integer> list, int cur) {
            if (cur >= ans) return;
            if (u == m) {
                for (int i = 0; i < n; i++) {
                    if (list.get(i) != 0) return;
                }
                ans = Math.min(ans, cur);
                return;
            }
            List<Integer> x = special.get(u);
            out:
            for (int k = 0; k <= map.get(u); k++) {
                List<Integer> clist = new ArrayList<>(list);
                for (int i = 0; i < n; i++) {
                    int a = x.get(i), b = clist.get(i);
                    if (a * k > b) break out;
                    clist.set(i, b - a * k);
                }
                dfs(u + 1, clist, cur + k * x.get(n));
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}