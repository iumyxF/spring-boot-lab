package com.example.practice.leetcode.hot100.array;

import java.util.PriorityQueue;

/**
 * @author fzy
 * @description:
 * @date 2025/2/7 15:47
 */
public class FirstMissingPositive {

    /*
    给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
    请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。

    输入：nums = [1,2,0]
    输出：3
    解释：范围 [1,2] 中的数字都在数组中。

    输入：nums = [3,4,-1,1]
    输出：2
    解释：1 在数组中，但 2 没有。

    输入：nums = [7,8,9,11,12]
    输出：1
    解释：最小的正数 1 没有出现。
     */

    /**
     * ac 用小顶堆实现，不过效率慢
     */
    public int firstMissingPositive(int[] nums) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            if (num > 0) {
                queue.add(num);
            }
        }
        if (queue.isEmpty() || queue.peek() > 1) {
            return 1;
        }
        Integer first = queue.poll();
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (poll - first > 1) {
                return first + 1;
            } else {
                first = poll;
            }
        }
        return first + 1;
    }

    /**
     * 双指针交换
     * 把数组的正整数在左边排序
     */
    public int firstMissingPositive2(int[] nums) {
        // l 表示当前处理的左边界，r 表示当前处理的右边界
        int l = 0, r = nums.length;
        while (l < r) {
            // 如果当前元素已经在正确的位置上 左边界向右移动
            if (nums[l] == l + 1) {
                l++;
            } else if (nums[l] <= l || nums[l] > r || nums[l] == nums[nums[l] - 1]) {
                // 如果当前元素小于等于左边界，或者大于右边界，或者与目标位置的元素相同
                // 将当前元素交换到右边界，并缩小右边界
                swap(nums, l, --r);
            } else {
                // 将当前元素交换到正确的位置
                swap(nums, l, nums[l] - 1);
            }
        }
        return l + 1;
    }

    public void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    public static void main(String[] args) {
        FirstMissingPositive s = new FirstMissingPositive();
        System.out.println(s.firstMissingPositive2(new int[]{2, 1, 0}));
        System.out.println(s.firstMissingPositive2(new int[]{3, 4, -1, 1}));
        System.out.println(s.firstMissingPositive2(new int[]{7, 8, 9, 11, 12}));
        System.out.println(s.firstMissingPositive2(new int[]{-1, -2, -3}));
        System.out.println(s.firstMissingPositive2(new int[]{1, 2, 3, 4, 5}));
    }
}
