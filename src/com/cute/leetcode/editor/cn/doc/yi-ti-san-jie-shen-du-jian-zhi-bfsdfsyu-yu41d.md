### 解题思路

这道题一看便是bfs，dfs，递归等，首先写了最容易懂的解法。
# 解法一：
不管你数据何种关系，重不重复，父节点与子节点关系等，直接把所有节点
的值拿出来，用最小堆，弹出倒数第二个便是，因为涉及重复，也许最小值
可能重复很多次，那么加一个Set集合去重。

```java
class Solution {
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    Set<Integer> set = new HashSet();
    public int findSecondMinimumValue(TreeNode root) {
        if(root == null) return -1;
        dfs(root);
        int ans = queue.poll();
        return queue.size() == 0 ? -1 : queue.poll();
    }
    public void dfs(TreeNode node){
        if(node==null){
            return ;
        }
        if(!set.contains(node.val)){
             queue.offer(node.val);
             set.add(node.val);
        }
        dfs(node.left);
        dfs(node.right);
    }
}
```

效率肯定不高，毕竟感觉就是题的条件利用不充分，转眼看了看数据范围，居然一眼
以为数据范围【1，25】，看都没看清楚，又直接改，把集合去重部分直接用位运算，
利用int的32位信息进行去重。

```java
class Solution {
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    int set = 0;
    public int findSecondMinimumValue(TreeNode root) {
        if(root == null) return -1;
        dfs(root);
        int ans = queue.poll();
        return queue.size() == 0 ? -1 : queue.poll();
    }
    public void dfs(TreeNode node){
        if(node==null){
            return ;
        }
        if((set & (1 << node.val)) == 0){
             queue.offer(node.val);
             set |= (1 << node.val);
        }
        dfs(node.left);
        dfs(node.right);
    }
}
```
结果直接100%，正当我弄下一个解法，继续修改，不对劲啊，为啥不通过了，空指针，再次看数据范围
居然，，，，，看错了，测试用例不会很小的范围吧，这个解法严格意义错的，可是100%通过。
属于官方测试用例漏洞吧，

# 解法二：广度遍历

广度优先是老模板了，首先很好理解，原题说，当前节点的值，是来自于左孩子，右孩子中的较小值，
那么这，，，，，这么说，你的根节点不就是最小值吗？左右孩子都比你大或者等，你的根节点最后
不就是整棵树最小值吗，那么我只要往下去找，找到节点值不等于根节点的数，取最小不就OK了？
  **那就直接广度优先，把不等于根节点的值取最小
即可。然后如果找不到比根节点小的，那就是-1，**

```java
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                if(cur.val != root.val){
                    ans = Math.min(ans,cur.val);
                }
                if(cur.left != null) queue.offer(cur.left);
                if(cur.right != null) queue.offer(cur.right);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
```
但是注意到一个问题，其实我们没必要每个节点都要的，如果像这样，按照当前节点来自于它的左孩子和
右孩子中较小的值，那么也就是说，往下走，要么比当前节点大，要么等。如图，我们只有往小的一边才能
更有可能找到次小值节点值为2的，其实3这边就没有用了，3往下就算还有值，都是大的，无效。

![image.png](https://pic.leetcode-cn.com/1627325821-PwXibn-image.png)

所以深度优先可以存在剪枝，故有递归写法

# 解放三：递归剪枝

解法而末尾已经说明，我们其实可以剪枝，如果相等，我们就往下继续递归，因为当前节点值是来自于左孩子或
右孩子中较小的，相等的对应的分支才更有可能找到整个树的次小元素，不相等的分支，说明比当前节点值大，而且往下
的分支都是比当前节点值大，需要抛弃，
比如上图中，我们需要往左进行递归，难道右节点的3，不要了吗？不然，因为假如左节点没找到呢，那么3就是符合答案的
所，
想象自己就是当前的节点，我有左孩子和右孩子，他们都会传给我second的值，我要如何处理，才继续往上传，
比如整个数最小值是1，，，，左孩子认为第二小的为3，右孩子认为第二小的位4，那我肯定拿题目最小的3，才是真正第二小，
所以我们需要传递min(left,right)

![image.png](https://pic.leetcode-cn.com/1627329780-wOpTdh-image.png)

如果没到，return -1,那么我们就不能返回min，不然整棵树全是-1，我们需要返回上层可以参考的值，给
上层继续决定second，return max(left,right)。

接下来就是如何拿到左右second，而且要剪枝，其实很简单，
只要节点值相等，继续往下递归去拿，如果不相等，只要拿一个节点，这个不等的节点之后一定都大，无效。
int left = root.val == root.left.val ? //判断
           findSecondMinimumValue(root.left) : //等说明可以就递归取拿
           root.left.val;//不等就不要递归了。

图中的阴影部分就是剪枝的部分，
不等的时候，比如1 ！= 3，我们只拿3，后面的不要，
相等的时候，比如1 == 1，我们继续递归

![image.png](https://pic.leetcode-cn.com/1627327432-vEWJgc-image.png)


```java
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        if(root == null || root.left == null) return -1;
        int left = root.val == root.left.val ? 
                   findSecondMinimumValue(root.left) : 
                   root.left.val;
        int right = root.val == root.right.val ? 
                   findSecondMinimumValue(root.right) : 
                   root.right.val;
        int min = Math.min(left,right);
        return  min > 0 ? min : Math.max(left,right);            
    }
}
```


如果看懂了点个赞，在此感激不尽！