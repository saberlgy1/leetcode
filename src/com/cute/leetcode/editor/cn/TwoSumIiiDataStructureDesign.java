//设计并实现一个 TwoSum 的类，使该类需要支持 add 和 find 的操作。 
//
// add 操作 - 对内部数据结构增加一个数。 
//find 操作 - 寻找内部数据结构中是否存在一对整数，使得两数之和与给定的数相等。 
//
// 示例 1: 
//
// add(1); add(3); add(5);
//find(4) -> true
//find(7) -> false
// 
//
// 示例 2: 
//
// add(3); add(1); add(2);
//find(3) -> true
//find(6) -> false 
// Related Topics 设计 哈希表

package com.cute.leetcode.editor.cn;

import java.util.*;

public class TwoSumIiiDataStructureDesign {
    public static void main(String[] args) {
        TwoSum solution = new TwoSumIiiDataStructureDesign().new TwoSum();
        solution.add(1);
        solution.add(3);
        solution.add(5);
        solution.find(4);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class TwoSum {

        private ArrayList<Integer> a;
        private boolean is_sorted;
        /**
         * Initialize your data structure here.
         */
        public TwoSum() {
            this.a = new ArrayList<Integer>();
            this.is_sorted = false;
        }

        /**
         * Add the number to an internal data structure..
         */
        public void add(int number) {
           this.a.add(number);
        }

        /**
         * Find if there exists any pair of numbers which sum is equal to the value.
         */
        public boolean find(int value) {
            if (!this.is_sorted) {
                Collections.sort(this.a);
            }
            int i = 0, j = this.a.size() - 1;
            while (i < j) {
                int sum = this.a.get(i) + this.a.get(j);
                if(value > sum ) i++;
                else if (value < sum) j--;
                else return true;
            }
            return false;
        }
    }

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}