package com.example.practice.leetcode.hot100.array;

import java.util.Arrays;

/**
 * @author fzy
 * @description:
 * @date 2025/2/7 10:57
 */
public class ProductExceptSelf {

    /*
    给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
    题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
    请 不要使用除法，且在 O(n) 时间复杂度内完成此题。

    2 <= nums.length <= 105
    -30 <= nums[i] <= 30
    输入 保证 数组 answer[i] 在  32 位 整数范围内

    输入: nums = [1,2,3,4]
    输出: [24,12,8,6]

    输入: nums = [-1,1,0,-3,3]
    输出: [0,0,9,0,0]
     */

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        // 前缀/后缀乘积
        int[] preProduct = new int[len + 1];
        preProduct[0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            preProduct[i] = preProduct[i - 1] * nums[i - 1];
        }
        int[] postProduct = new int[len + 1];
        postProduct[len] = 1;
        int index = len;
        for (int i = nums.length - 1; i >= 0; i--) {
            postProduct[index - 1] = postProduct[index] * nums[i];
            index--;
        }
        //answer[i] = preProduct[i-1] * postProduct[i]
        int[] answer = new int[len];
        for (int i = 1; i <= answer.length; i++) {
            answer[i - 1] = preProduct[i - 1] * postProduct[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        ProductExceptSelf s = new ProductExceptSelf();
        System.out.println(Arrays.toString(s.productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(s.productExceptSelf(new int[]{-1,1,0,-3,3})));
    }
}
