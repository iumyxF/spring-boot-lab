package com.example.practice.leetcode.nowcoder.heapstackqueue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2024/11/26 15:27
 */
public class BM43MainStack {

    /*
    定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数，输入操作时保证pop、top和min函数操作时，栈中一定有元素。
    此栈包含的方法有：
    push(value)将value压入栈中
    pop()弹出栈顶元素
    top()获取栈顶元素
    min()获取栈中最小元素

    数据范围：操作数量满足0 ≤ n ≤ 300，输入的元素满足 |val| <= 10000
    进阶：栈的各个操作的时间复杂度是O(1)，空间复杂度是O(n )
    示例：
    输入："PSH-1","PSH2","MIN","TOP","POP","PSH1",TOP","MN"I
    输出：-1,2,1，-1
    解析：
    "PSH-1"表示将-1压入栈中，栈中元素为 -1
    "PSH2"表示将2压入栈中，栈中元素为 2,-1
    "MIN"表示获取此时栈中最小元素==> 返回-1
    "TOP"表示获取栈顶元素==>返回2
    "POP表示弹出栈顶元素，弹出2，栈中元素为-1
    "PSH1"表示将1压入栈中，栈中元素为 1,-1
    TOP表示获取栈顶元素==>返回1
    MIN表示获取此时栈中最小元素==>返回-1


    ["PSH9","PSH1","PSH6","MIN","POP","POP","MIN"]
     */

    int min = Integer.MAX_VALUE;

    List<Integer> list = new ArrayList<>();

    public void push(int node) {
        min = Math.min(node, min);
        list.add(node);
    }

    public void pop() {
        Integer pop = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        // 刷新最小值
        if (pop == min) {
            min = Integer.MAX_VALUE;
            for (Integer item : list) {
                min = Math.min(item, min);
            }
        }
    }

    public int top() {
        return list.get(list.size() - 1);
    }

    public int min() {
        return min;
    }
}
