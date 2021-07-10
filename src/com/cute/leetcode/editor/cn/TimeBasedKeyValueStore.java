//创建一个基于时间的键值存储类 TimeMap，它支持下面两个操作： 
//
// 1. set(string key, string value, int timestamp) 
//
// 
// 存储键 key、值 value，以及给定的时间戳 timestamp。 
// 
//
// 2. get(string key, int timestamp) 
//
// 
// 返回先前调用 set(key, value, timestamp_prev) 所存储的值，其中 timestamp_prev <= timestamp。 
//
// 如果有多个这样的值，则返回对应最大的 timestamp_prev 的那个值。 
// 如果没有值，则返回空字符串（""）。 
// 
//
// 
//
// 示例 1： 
//
// 输入：inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["f
//oo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
//输出：[null,null,"bar","bar",null,"bar2","bar2"]
//解释：  
//TimeMap kv;   
//kv.set("foo", "bar", 1); // 存储键 "foo" 和值 "bar" 以及时间戳 timestamp = 1   
//kv.get("foo", 1);  // 输出 "bar"   
//kv.get("foo", 3); // 输出 "bar" 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即
// "bar"）   
//kv.set("foo", "bar2", 4);   
//kv.get("foo", 4); // 输出 "bar2"   
//kv.get("foo", 5); // 输出 "bar2"   
//
// 
//
// 示例 2： 
//
// 输入：inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [
//[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["lov
//e",20],["love",25]]
//输出：[null,null,null,"","high","high","low","low"]
// 
//
// 
//
// 提示： 
//
// 
// 所有的键/值字符串都是小写的。 
// 所有的键/值字符串长度都在 [1, 100] 范围内。 
// 所有 TimeMap.set 操作中的时间戳 timestamps 都是严格递增的。 
// 1 <= timestamp <= 10^7 
// TimeMap.set 和 TimeMap.get 函数在每个测试用例中将（组合）调用总计 120000 次。 
// 
// Related Topics 设计 哈希表 字符串 二分查找 
// 👍 82 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TimeBasedKeyValueStore {
    public static void main(String[] args) {
        TimeMap timeMap = new TimeBasedKeyValueStore().new TimeMap();
        timeMap.set("foo", "bar", 1);
        timeMap.get("foo", 1);
        timeMap.get("foo", 3);
        timeMap.set("foo", "bar2", 4);
        timeMap.get("foo", 4);
        timeMap.get("foo", 5);


    }


    //思路一：两个map分别存储key和value 以及value和timestamp
    //leetcode submit region begin(Prohibit modification and deletion)
    class TimeMap {


        private Map<String, Map<String, Integer>> keyMap;

        private Map<String, Integer> valueMap;


        public TimeMap(String key, String value, int timestamp) {
            this.valueMap.put(value, timestamp);
            this.keyMap.put(key, valueMap);
            System.out.println("null");
        }


        /**
         * Initialize your data structure here.
         */

        public TimeMap() {
            this.keyMap = new HashMap<>();

        }

        public void set(String key, String value, int timestamp) {
            Map<String, Integer> valueMap = this.keyMap.getOrDefault(key, new HashMap<>());
            valueMap.put(value, timestamp);
            this.keyMap.put(key, valueMap);
            System.out.println("null");
        }

        public String get(String key, int timestamp) {
            if (!this.keyMap.containsKey(key)) {
                System.out.println("null");
                return "";
            }
            String res = "";
            int max = 0;
            Map<String, Integer> valueMap = this.keyMap.get(key);
            for (Map.Entry<String, Integer> entry : valueMap.entrySet()
            ) {
                if (entry.getValue() < timestamp && entry.getValue() > max) {
                    max = entry.getValue();
                    res = entry.getKey();
                }
                if (entry.getValue() == timestamp) {
                    System.out.println(entry.getKey());
                    return entry.getKey();
                }
            }
            System.out.println("".equals(res) ? "null" : res);
            return res;
        }
    }

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
//leetcode submit region end(Prohibit modification and deletion)

}