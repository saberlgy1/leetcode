//给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。 
//
// 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,2]
//输出："210" 
//
// 示例 2： 
//
// 
//输入：nums = [3,30,34,5,9]
//输出："9534330"
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出："1"
// 
//
// 示例 4： 
//
// 
//输入：nums = [10]
//输出："10"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 109 
// 
// Related Topics 排序 
// 👍 541 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class LargestNumber {
    public static void main(String[] args) {
        Solution solution = new LargestNumber().new Solution();
        int[] nums = new int[]{3, 30};
        int[] nums1 = new int[]{432, 4324};
        int[] nums2 = new int[]{111311, 1113};
        int[] nums3 = new int[]{8308, 8308, 830};
//        int[] nums = new int[]{3,30};
        String s = "0123456789";
        //System.out.println(s.substring(0,3)+"+++"+ s.substring(4,5));
        System.out.println(solution.largestNumber(nums));
        System.out.println(solution.largestNumber(nums));
        System.out.println(solution.largestNumber(nums1));
        System.out.println(solution.largestNumber(nums2));
        System.out.println(solution.largestNumber(nums3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String largestNumber(int[] nums) {
          /*  List<Integer> list = new ArrayList<>();
            for (int i : nums
            ) {
                list.add(i);
            }
            Comparator<Integer> comparator = new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    // TODO Auto-generated method stub
                    if (o1.equals(o2)) {
                        return 0;
                    }
                    String a = o1.toString();
                    String b = o2.toString();
                    return Long.parseLong(a+b) >   Long.parseLong(b+a) ? -1:1;
                }
            };
            Collections.sort(list, comparator);
            String res = "";
            for (int i : list
            ) {
                res += i;
            }
            if (res.startsWith("0")){
                return "0";
            }
            return res;*/
            //数组lambda
            int n = nums.length;
            // 转换成包装类型，以便传入 Comparator 对象（此处为 lambda 表达式）
            Integer[] numsArr = new Integer[n];
            for (int i = 0; i < n; i++) {
                numsArr[i] = nums[i];
            }
            Arrays.sort(numsArr, (x, y) -> {
               /* if (o1.equals(o2)) {
                    return 0;
                }
                String a = o1.toString();
                String b = o2.toString();
                return Long.parseLong(a+b) >   Long.parseLong(b+a) ? -1:1;*/
                long sx = 10, sy = 10;
                while (sx <= x) {
                    sx *= 10;
                }
                while (sy <= y) {
                    sy *= 10;
                }
                return (int) (-sy * x - y + sx * y + x);

            });
            if (numsArr[0] == 0) {
                return "0";
            }
            StringBuilder ret = new StringBuilder();
            for (int num : numsArr) {
                ret.append(num);
            }
            return ret.toString();
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}