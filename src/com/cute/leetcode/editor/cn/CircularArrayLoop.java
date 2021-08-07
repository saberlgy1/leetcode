//存在一个不含 0 的 环形 数组 nums ，每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数： 
//
// 
// 如果 nums[i] 是正数，向前 移动 nums[i] 步 
// 如果 nums[i] 是负数，向后 移动 nums[i] 步 
// 
//
// 因为数组是 环形 的，所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。 
//
// 数组中的 循环 由长度为 k 的下标序列 seq ： 
//
// 
// 遵循上述移动规则将导致重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ... 
// 所有 nums[seq[j]] 应当不是 全正 就是 全负 
// k > 1 
// 
//
// 如果 nums 中存在循环，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,-1,1,2,2]
//输出：true
//解释：存在循环，按下标 0 -> 2 -> 3 -> 0 。循环长度为 3 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [-1,2]
//输出：false
//解释：按下标 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。
// 
//
// 示例 3: 
//
// 
//输入：nums = [-2,1,-1,-2,-2]
//输出：false
//解释：按下标 1 -> 2 -> 1 -> ... 的运动无法构成循环，因为 nums[1] 是正数，而 nums[2] 是负数。
//所有 nums[seq[j]] 应当不是全正就是全负。 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5000 
// -1000 <= nums[i] <= 1000 
// nums[i] != 0 
// 
//
// 
//
// 进阶：你能设计一个时间复杂度为 O(n) 且额外空间复杂度为 O(1) 的算法吗？ 
// Related Topics 数组 哈希表 双指针 
// 👍 106 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class CircularArrayLoop {
    public static void main(String[] args) {
        Solution solution = new CircularArrayLoop().new Solution();
        //int[] nums = new int[]{2, -1, 1, 2, 2};
        //int[] nums = new int[]{-1, 2};
        //int[] nums = new int[]{-1, -2, -3, -4, -5};
        //int[] nums = new int[]{1, -2};
        //int[] nums = new int[]{-8, -1, 1, 7, 2};
        //int[] nums = new int[]{-2,1,-1,-2,-2};
        int[] nums = new int[]{3, 1, 2};
        System.out.println(solution.circularArrayLoop(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：hash+类深度优先遍历
        //从当前节点遍历遍历到重复起始索引位置节点元素就代表有环
        //通过set存储遍历过的节点，set大小不会超过数组的大小
        //一旦重复且不满足题目要求则可以直接返回false
        //同时判断环内元素是否为全正/全负
        /*public boolean circularArrayLoop(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (check(nums, i)) {
                    return true;
                }
            }
            return false;
        }

        public boolean check(int[] nums, int i) {
            int n = nums.length;
            //当前起点可以直接到终点则返回false即可
            if (nums[i] % n == 0) {
                return false;
            }
            int cur = nums[i] > 0 ? 1 : -1;
            Set<Integer> set = new HashSet<>();
            set.add(i);
            while (set.size() <= n) {
                //异常情况
                if (nums[i] % n == 0 || cur * nums[i] < 0) {
                    return false;
                }
                if (i + nums[i] < 0) {
                    i = Math.abs((n + i + nums[i]) % n);
                } else if (i + nums[i] >= n) {
                    i = (i + nums[i] - n) % n;
                } else {
                    i = i + nums[i];
                }
                if (set.contains(i)) {
                    return true;
                }
                set.add(i);
            }
            return false;
        }*/
        //思路二：思路一的优化
        //坐标移动的下一个索引可以进行判断优化
        //Math.abs((n + i + nums[i]) % n)
        /*public boolean circularArrayLoop(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (check(nums, i)) {
                    return true;
                }
            }
            return false;
        }

        public boolean check(int[] nums, int i) {
            int n = nums.length;
            //当前起点可以直接到终点则返回false即可
            if (nums[i] % n == 0) {
                return false;
            }
            int cur = nums[i] > 0 ? 1 : -1;
            Set<Integer> set = new HashSet<>();
            set.add(i);
            while (set.size() <= n) {
                //异常情况
                if (nums[i] % n == 0 || cur * nums[i] < 0) {
                    return false;
                }
                i = Math.abs((n + i + nums[i]) % n);
                if (set.contains(i)) {
                    return true;
                }
                set.add(i);
            }
            return false;
        }*/
        //思路三：思路一的进一步优化
        //完全可以不用set来进行判断
        // 因为环状数组最后都会经历一个点，我们循环遍历了每个节点作为起始节点的状态
        //所以在判断的时候只需要判断能不能回到重复节点的状态即可
        //大小也可以用cnt数量指针来判断，set也没什么意义
        /*public boolean circularArrayLoop(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (check(nums, i)) {
                    return true;
                }
            }
            return false;
        }

        public boolean check(int[] nums, int i) {
            int n = nums.length;
            int cur =i, k = 1;
            while (k <= n) {
                //异常情况：单点循环｜｜非单向移动
                if (nums[i] % n == 0 || nums[cur] * nums[i] < 0) {
                    return false;
                }
                i = Math.abs((n + i + nums[i]) % n);
                if (i == cur ){
                    return true;
                }
                k++;
            }
            return false;
        }*/
        //思路四：快慢指针
        //起始判断一维数组有没有换可以很容易的想到双向链表的寻找环的方法
        //快慢指针
        //证明原理大概可以理解为 若有环，快慢指针一定会相遇，且相遇节点为环的起始节点
        //证明过程稍后补充
        //同时还需要判断是否
       /* int slow = 0, fast = 0, n = 0;
        int[] _nums;

        public boolean circularArrayLoop(int[] nums) {
            _nums = nums;
            n = nums.length;
            //可能不只有一个环
            for (int i = 0; i < nums.length; i++) {
                slow = i;
                fast = i;
                move();
                while (slow != fast) {
                    move();
                }
                //当前点成环
                if (nums[slow] % n == 0) {
                    continue;
                }
                //非单向
                int temp = slow;
                move();
                while (temp != slow) {
                    if (nums[temp] * nums[slow] < 0) {
                        break;
                    }
                    move();
                }
                if (temp == slow){
                    return true;
                }
            }
            return false;
        }

        public void move() {
            slow = Math.abs((n + slow + _nums[slow]) % n);
            fast = Math.abs((n + fast + _nums[fast]) % n);
            fast = Math.abs((n + fast + _nums[fast]) % n);
        }*/
        //思路五：思路四的优化
        //快慢指针针对特殊自环情况可以直接赋予0值，就不再往下遍历
        //此时遍历所有节点形成的快慢指针可以保证是O(n)时间复杂度
     /*    int n = 0;
        int[] _nums;

       public boolean circularArrayLoop(int[] nums) {
            _nums = nums;
            n = nums.length;
            //可能不只有一个环
            for (int i = 0; i < nums.length; i++) {
                //已经扫描过的点可以不重复遍历
                if (nums[i] == 0) {
                    continue;
                }
                int slow = i, fast = move(i);
                //快慢指针移动方向均为同向
                while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[move(fast)] > 0) {
                    if (slow == fast) {
                        //非自己成环
                        if (slow != move(slow)) {
                            return true;
                        }
                        //自己成环
                        else {
                            break;
                        }
                    }
                    slow = move(slow);
                    fast = move(move(fast));
                }
                //单向扫描过成不了环的可以直接赋值0 无需重复扫描
                int temp = i;
                while (nums[temp] * nums[move(temp)] > 0) {
                    int tmp = temp;
                    temp = move(temp);
                    nums[tmp] = 0;
                }
            }
            return false;
        }

        public int move(int i) {
            return Math.abs((n + (i + _nums[i]) % n) % n);
            //return ((i + _nums[i]) % n + n) % n;
        }*/

        //思路六：寻图
        //定义一个足够大的数OFFSET作为偏移量
        //每遍历到一次就+1操作
        //如果扫描到非自环则返回true
        int OFFSET = 100010;
        public boolean circularArrayLoop(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                //已经遍历过无法成环的节点，可以不重复遍历
                if (nums[i] >= OFFSET) continue;
                //分别代表着当前节点，标记节点的值，上一个节点移动的长度
                int cur = i, tag = OFFSET + i, last = -1;
                //代表着节点移动的方向
                int flag = nums[cur] ;
                while (true) {
                    //下一个节点
                    int next = ((cur + nums[cur]) % n + n ) % n;
                    //移动的长度
                    last = nums[cur];
                    //当前节点已经被遍历过
                    nums[cur] = tag;
                    //节点移动到下一位
                    cur = next;
                    //重复一个节点--自环跳出循环
                    if (cur == i) break;
                    //已经遍历过
                    if (nums[cur] >= OFFSET) break;
                    //判断是否单向
                    if (flag * nums[cur] < 0) break;
                }
                //只遍历到一次，即满足环状的
                if (last % n != 0 && nums[cur] == tag) return true;
            }
            return false;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}