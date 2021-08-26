//第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。
//
// 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
//
// 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
//
//
//
// 示例 1：
//
// 输入：people = [1,2], limit = 3
//输出：1
//解释：1 艘船载 (1, 2)
//
//
// 示例 2：
//
// 输入：people = [3,2,2,1], limit = 3
//输出：3
//解释：3 艘船分别载 (1, 2), (2) 和 (3)
//
//
// 示例 3：
//
// 输入：people = [3,5,3,4], limit = 5
//输出：4
//解释：4 艘船分别载 (3), (3), (4), (5)
//
// 提示：
//
//
// 1 <= people.length <= 50000
// 1 <= people[i] <= limit <= 30000
//
// Related Topics 贪心 数组 双指针 排序 👍 125 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class BoatsToSavePeople {
    public static void main(String[] args) {
        Solution solution = new BoatsToSavePeople().new Solution();
        int[] people = new int[]{5, 1, 7, 4, 2, 4};
        int[] people1 = new int[]{3, 5, 3, 4};
        int[] people2 = new int[]{3, 2, 2, 1};
        int[] people3 = new int[]{1, 2};
        int[] people4 = new int[]{1, 5, 3, 5};
        System.out.println(solution.numRescueBoats(people, 7));
        System.out.println(solution.numRescueBoats(people1, 5));
        System.out.println(solution.numRescueBoats(people2, 3));
        System.out.println(solution.numRescueBoats(people3, 3));
        System.out.println(solution.numRescueBoats(people4, 7));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：贪心+暴力
        //排序，从最大开始扫描
        //放入船中 如果超过limit 直接res+=1
        //不超过放入第二大的（不满足则一直往下遍历到能满足小于等于limit的元素）
        //通过大根堆定义
        //然后存储一个栈或者队列存放不满足条件的元素重新放入大根堆
        //这种居然70/78就TLE了
        /*public int numRescueBoats(int[] people, int limit) {
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            for (int person : people
            ) {
                priorityQueue.add(person);
            }
            Stack<Integer> stack = new Stack<>();
            int res = 0;
            while (!priorityQueue.isEmpty()) {
                int temp = priorityQueue.poll();
                if (temp >= limit) {
                    res += 1;
                    continue;
                }
                while (!priorityQueue.isEmpty() && temp + priorityQueue.peek() > limit) {
                    int value = priorityQueue.poll();
                    stack.add(value);
                }
                if (!priorityQueue.isEmpty()) {
                    priorityQueue.poll();
                }
                res += 1;
                while (!stack.isEmpty()) {
                    priorityQueue.add(stack.pop());
                }
            }
            return res;
        }*/
        //思路二：贪心+双指针
        //其实思路差不太多，就是不用大根堆了
        //直接排序后双指针(暴力)就可以
        //这居然也TLE
        /*public int numRescueBoats(int[] people, int limit) {
            Arrays.sort(people);
            int res = 0;
            boolean[] vis = new boolean[people.length];
            for (int i = people.length - 1; i >= 0; i--) {
                if (vis[i]) {
                    continue;
                }
                vis[i] = true;
                if (people[i] >= limit) {
                    res++;
                    continue;
                }
                int des = limit - people[i];
                int j = i - 1;
                while (j >= 0) {
                    if (people[j] <= des && !vis[j]) {
                        vis[j] = true;
                        break;
                    }
                    j--;
                }
                res++;
            }
            return res;
        }*/
        //思路三：贪心+二分
        //这是强制nlgn复杂度么
        /*List<Integer> unVis = new ArrayList<>();

        public int numRescueBoats(int[] people, int limit) {
            Arrays.sort(people);
            int res = 0;
            for (int i : people
            ) {
                unVis.add(i);
            }

            while (!unVis.isEmpty()) {
                int i = unVis.size() - 1;
                if (unVis.get(i) >= limit) {
                    res++;
                    unVis.remove(i);

                    continue;
                }
                findAndRmJ(i, limit - unVis.get(i));
                unVis.remove(unVis.size() - 1);
                res++;
            }
            return res;
        }

        public void findAndRmJ(int i, int des) {

            if (i <= 0) {
                return;
            }
            int l = 0, r = i - 1;
            while (l < r) {
                int mid = (l - r + 1) / 2 + r;
                if (unVis.get(mid) <= des) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            if (unVis.get(r) <= des) unVis.remove(r);
        }*/
        //思路四：贪心的思路优化
        //我好像想复杂了，每次取最轻和最重即可，没必要二分查找满足条件的最大补充元素
        public int numRescueBoats(int[] people, int limit) {
            Arrays.sort(people);
            int l = 0, r = people.length - 1, res = 0;
            while (l < r) {
                if (people[r] >= limit || people[l] + people[r] > limit) {
                    r--;
                    res++;
                } else {
                    r--;
                    l++;
                    res++;
                }
            }
            if (l ==r){
                res++;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}