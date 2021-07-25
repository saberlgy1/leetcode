//存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。 
//
// 给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui
// 和 vi 在 nums 中相邻。 
//
// 题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i]
//, nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。 
//
// 返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。 
//
// 
//
// 示例 1： 
//
// 
//输入：adjacentPairs = [[2,1],[3,4],[3,2]]
//输出：[1,2,3,4]
//解释：数组的所有相邻元素对都在 adjacentPairs 中。
//特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。
// 
//
// 示例 2： 
//
// 
//输入：adjacentPairs = [[4,-2],[1,4],[-3,1]]
//输出：[-2,4,1,-3]
//解释：数组中可能存在负数。
//另一种解答是 [-3,1,4,-2] ，也会被视作正确答案。
// 
//
// 示例 3： 
//
// 
//输入：adjacentPairs = [[100000,-100000]]
//输出：[100000,-100000]
// 
//
// 
//
// 提示： 
//
// 
// nums.length == n 
// adjacentPairs.length == n - 1 
// adjacentPairs[i].length == 2 
// 2 <= n <= 105 
// -105 <= nums[i], ui, vi <= 105 
// 题目数据保证存在一些以 adjacentPairs 作为元素对的数组 nums 
// 
// Related Topics 数组 哈希表 
// 👍 41 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class RestoreTheArrayFromAdjacentPairs {
    public static void main(String[] args) {
        Solution solution = new RestoreTheArrayFromAdjacentPairs().new Solution();
        int[][] ads = new int[][]{{-3, -9}, {-5, 3}, {2, -9}, {6, -3}, {6, 1}, {5, 3}, {8, 5}, {-5, 1}, {7, 2}};
        System.out.println(solution.restoreArray(ads));
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力法
        //根据第一个数组去遍历所有数组，找到第二个元素，依次打印即可
        //如何确认第一个数组元素
        //我们可以发现，只有元素出现次数为1的时候才是首位元素或者末尾元素
        //因为输出对于首位末位没有顺序要求
        //我们可以根据cnt数量来找到首位元素
        //TLE（39/46）
        /*Set<Integer> isValid = new HashSet<>();

        public int[] restoreArray(int[][] adjacentPairs) {
            int[] res = new int[adjacentPairs.length + 1];
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int[] adp : adjacentPairs
            ) {
                map.put(adp[0], map.getOrDefault(adp[0], 0) + 1);
                map.put(adp[1], map.getOrDefault(adp[1], 0) + 1);
            }
            //确认头部元素
            for (int key : map.keySet()
            ) {
                if (map.get(key) == 1) {
                    res[0] = key;
                    isValid.add(key);
                    break;
                }
            }
            //递归求解即可
            backTrack(adjacentPairs, res[0], res, 1);
            return res;
        }

        public void backTrack(int[][] adps, int temp, int[] res, int index) {
            if (index == adps.length + 1) {
                return;
            }
            for (int[] adp : adps) {
                for (int i = 0; i < 2; i++) {
                    if (adp[i] == temp && !isValid.contains(adp[1 - i])) {
                        res[index] = adp[1 - i];
                        isValid.add(adp[1 - i]);
                        backTrack(adps, adp[1 - i], res, index + 1);
                    }
                }
            }
        }*/
        //思路二：优化思路一
        //无论从那一个节点开始寻找下个节点，最终都会寻找到一个末尾节点，
        //翻转过来寻找又一定能寻找到一个头节点
        //两个链表合在一起即可
        //还是TLE了（39/46）
       /* Set<Integer> headSet = new HashSet<>();

        public int[] restoreArray(int[][] adjacentPairs) {
            int[] res = new int[adjacentPairs.length + 1];
            List<Integer> goHead = new ArrayList<>();
            List<Integer> gotail = new ArrayList<>();
            goHead.add(adjacentPairs[0][1]);
            gotail.add(adjacentPairs[0][0]);
            gotail.add(adjacentPairs[0][1]);
            headSet.add(adjacentPairs[0][1]);
            backTrack(goHead, goHead.get(0), adjacentPairs);
            backTrack(gotail, gotail.get(1), adjacentPairs);
            //组合两个链表
            for (int i = 0; i < gotail.size(); i++) {
                res[i] = gotail.get(gotail.size() - i - 1);
            }
            for (int i = gotail.size(), j = 2; j < goHead.size(); i++, j++) {
                res[i] = goHead.get(j);
            }

            return res;
        }

        public void backTrack(List<Integer> list, int temp, int[][] ads) {
            for (int[] ad : ads
            ) {
                for (int i = 0; i < 2; i++) {
                    if (ad[i] == temp && !headSet.contains(ad[1 - i])) {
                        list.add(ad[1 - i]);
                        headSet.add(ad[1 - i]);
                        backTrack(list, ad[1 - i], ads);
                        return;
                    }
                }
            }
        }*/
        //思路三：其实这两种思路没有任何问题
        //更多的可能是写法和无用计算导致的
        //无用计算主要是递归遍历中的重复计算，对于已经扫描过的数组完全可以不再进行扫描
        //同时每一个元素的相邻元素可以通过
        //同时可以先扫描一次二维数组，确认每个元素的关联数组，后面直接通过get去查即可
        Set<Integer> headSet = new HashSet<>();
        Map<Integer,List<Integer>> map = new HashMap<>();
        public int[] restoreArray(int[][] adjacentPairs) {
            int[] res = new int[adjacentPairs.length + 1];
            for (int i = 0; i < adjacentPairs.length;i++) {
                List<Integer> indexa = map.getOrDefault(adjacentPairs[i][0],new ArrayList<>());
                indexa.add(i);
                map.put(adjacentPairs[i][0],indexa);
                List<Integer> indexb = map.getOrDefault(adjacentPairs[i][1],new ArrayList<>());
                indexb.add(i);
                map.put(adjacentPairs[i][1],indexb);
            }
            List<Integer> goHead = new ArrayList<>();
            List<Integer> gotail = new ArrayList<>();
            goHead.add(adjacentPairs[0][1]);
            gotail.add(adjacentPairs[0][0]);
            gotail.add(adjacentPairs[0][1]);
            backTrack(goHead, goHead.get(0), adjacentPairs);
            backTrack(gotail, gotail.get(1), adjacentPairs);
            //组合两个链表
            for (int i = 0; i < gotail.size(); i++) {
                res[i] = gotail.get(gotail.size() - i - 1);
            }
            for (int i = gotail.size(), j = 2; j < goHead.size(); i++, j++) {
                res[i] = goHead.get(j);
            }

            return res;
        }

        public void backTrack(List<Integer> list, int temp, int[][] ads) {
            for (int i = 0; i< map.get(temp).size();i++) {
                for (int j = 0; j < 2; j++) {
                    int index = map.get(temp).get(i);
                    if (ads[index][j] == temp && !headSet.contains(index)) {
                        list.add(ads[index][1 - j]);
                        headSet.add(index);
                        backTrack(list, ads[index][1 - j], ads);
                        return;
                    }
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}