package com.example.practice.leetcode.hot100;

import com.example.practice.leetcode.test.mic.MicTestMain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fzy
 * @description:
 * @date 2025/1/16 10:57
 */
public class TwoSum {

    /*
    给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
    你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。

    2 <= nums.length <= 104
    -10^9 <= nums[i] <= 10^9
    -10^9 <= target <= 10^9

    输入：nums = [2,7,11,15], target = 9
    输出：[0,1]
    解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]
     */


    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{i, map.get(nums[i])};
            }
            map.put(target - nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        TwoSum s = new TwoSum();
        //int[] res = s.twoSum(new int[]{2, 7, 11, 15}, 9);
        //int[] res = s.twoSum(new int[]{3, 2, 4}, 6);
        int[] res = s.twoSum(new int[]{3, 3}, 6);
        System.out.println(Arrays.toString(res));
    }
}
