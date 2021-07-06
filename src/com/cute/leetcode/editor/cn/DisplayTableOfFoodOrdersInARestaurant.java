//给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerNamei,tableNumberi,foodIt
//emi] ，其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。
//
// 请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。接下来每一行中
//的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。
//
// 注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。
//
//
//
// 示例 1：
//
// 输入：orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David",
//"3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","
//Ceviche"]]
//输出：[["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1
//","0"],["5","0","1","0","1"],["10","1","0","0","0"]]
//解释：
//点菜展示表如下所示：
//Table,Beef Burrito,Ceviche,Fried Chicken,Water
//3    ,0           ,2      ,1            ,0
//5    ,0           ,1      ,0            ,1
//10   ,1           ,0      ,0            ,0
//对于餐桌 3：David 点了 "Ceviche" 和 "Fried Chicken"，而 Rous 点了 "Ceviche"
//而餐桌 5：Carla 点了 "Water" 和 "Ceviche"
//餐桌 10：Corina 点了 "Beef Burrito"
//
//
// 示例 2：
//
// 输入：orders = [["James","12","Fried Chicken"],["Ratesh","12","Fried Chicken"],[
//"Amadeus","12","Fried Chicken"],["Adam","1","Canadian Waffles"],["Brianna","1","
//Canadian Waffles"]]
//输出：[["Table","Canadian Waffles","Fried Chicken"],["1","2","0"],["12","0","3"]]
//
//解释：
//对于餐桌 1：Adam 和 Brianna 都点了 "Canadian Waffles"
//而餐桌 12：James, Ratesh 和 Amadeus 都点了 "Fried Chicken"
//
//
// 示例 3：
//
// 输入：orders = [["Laura","2","Bean Burrito"],["Jhon","2","Beef Burrito"],["Melis
//sa","2","Soda"]]
//输出：[["Table","Bean Burrito","Beef Burrito","Soda"],["2","1","1","1"]]
//
//
//
//
// 提示：
//
//
// 1 <= orders.length <= 5 * 10^4
// orders[i].length == 3
// 1 <= customerNamei.length, foodItemi.length <= 20
// customerNamei 和 foodItemi 由大小写英文字母及空格字符 ' ' 组成。
// tableNumberi 是 1 到 500 范围内的整数。
//
// Related Topics 数组 哈希表 字符串 有序集合 排序
// 👍 37 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class DisplayTableOfFoodOrdersInARestaurant {
    public static void main(String[] args) {
        Solution solution = new DisplayTableOfFoodOrdersInARestaurant().new Solution();
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        List<String> l3 = new ArrayList<>();
        List<String> l4 = new ArrayList<>();
        List<String> l5 = new ArrayList<>();
        List<String> l6 = new ArrayList<>();
        List<List<String>> orders = new ArrayList<>();
        l1.add("David");
        l1.add("3");
        l1.add("Ceviche");
        l2.add("Corina");
        l2.add("10");
        l2.add("David");
        l3.add("Beef Burrito");
        l3.add("3");
        l3.add("Fried Chicken");
        l4.add("Carla");
        l4.add("5");
        l4.add("Water");
        l5.add("Carla");
        l5.add("5");
        l5.add("Ceviche");
        l6.add("Rous");
        l6.add("3");
        l6.add("Ceviche");
        orders.add(l1);
        orders.add(l2);
        orders.add(l3);
        orders.add(l4);
        orders.add(l5);
        orders.add(l6);
        System.out.println(solution.displayTable(orders));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：穷举
        //感觉整体不难，类似设计数据结构
        public List<List<String>> displayTable(List<List<String>> orders) {
            //set去重标题栏、桌号栏
            Set<String> tempMenu = new HashSet<>();
            Set<String> tempTable = new HashSet<>();
            List<List<String>> res =  new ArrayList<>();
            for (List<String> list: orders
                 ) {
                tempMenu.add(list.get(2));
                tempTable.add(list.get(1));
            }
            List<String> tempTitle = new ArrayList<>();
            List<String> tempIndex = new ArrayList<>();
            tempTitle.addAll(tempMenu);
            tempIndex.addAll(tempTable);
            //排序
            Collections.sort(tempTitle);
            Collections.sort(tempIndex, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return Integer.parseInt(o1) - Integer.parseInt(o2);
                }
            });
            List<String> title = new ArrayList<>();
            title.add("Table");
            title.addAll(tempTitle);
            //记录标题索引
            Map<String,Integer> menuMap = new HashMap<>();
            for (int i = 0; i < title.size();i++){
                menuMap.put(title.get(i),i);
            }
            res.add(title);
            //列桌号
            Map<String,Integer> tableMap = new HashMap<>();
            int i = 1;
            for ( String index: tempIndex
            ) {
                List<String> table = new ArrayList<>();
                table.add(index);
                tableMap.put(index,i);
                i++;
                //初始化
                tempMenu.forEach(str ->{
                    table.add("0");
                });
                res.add(table);
            }
            //列菜名
            for (List<String> list: orders){
                int tableIndex = tableMap.get(list.get(1));
                int menuName = menuMap.get(list.get(2));
                List<String> cnt = res.get(tableIndex);
                cnt.set(menuName,String.valueOf(Integer.parseInt(cnt.get(menuName)) + 1));
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}