package com.example.practice.leetcode.nowcoder.pointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fzy
 * @description:
 * @date 6/1/2025 上午9:46
 */
public class BM94MinmumNumberOfHost {

    /*
    有 n 个活动即将举办，每个活动都有开始时间与活动的结束时间，
    第 i 个活动的开始时间是 start i ,第 i 个活动的结束时间是 end i ,
    举办某个活动就需要为该活动准备一个活动主持人。

    一位活动主持人在同一时间只能参与一个活动。
    并且活动主持人需要全程参与活动，换句话说，一个主持人参与了第 i 个活动，那么该主持人在 (starti,endi) 这个时间段不能参与其他任何活动。
    求为了成功举办这 n 个活动，最少需要多少名主持人。

    2,[[1,2],[2,3]]
    1
    只需要一个主持人就能成功举办这两个活动

    2,[[1,3],[2,4]]
    2
    需要两个主持人才能成功举办这两个活动

    动态规划?
    dp[i] ? 执行当前活动所需要的主持人数量
    递归公式 ?
    if start[1] <= end[0]  dp[i] = dp[i-1]
    else dp[i] = dp[i-1] + 1
     */
    public int minmumNumberOfHost(int n, int[][] startEnd) {
        if (n <= 1) {
            return n;
        }
        ArrayList<Integer> startList = new ArrayList<>(startEnd.length);
        ArrayList<Integer> endList = new ArrayList<>(startEnd.length);
        for (int[] ints : startEnd) {
            startList.add(ints[0]);
            endList.add(ints[1]);
        }
        startList.sort(Integer::compare);
        endList.sort(Integer::compare);

        int res = 0;
        int j = 0;
        for (int i = 0; i < startList.size(); i++) {
            if (startList.get(i) >= endList.get(j)) {
                j++;
            } else {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        BM94MinmumNumberOfHost s = new BM94MinmumNumberOfHost();
        //int[][] arr = new int[2][];
        //arr[0] = new int[]{1, 2};
        //arr[1] = new int[]{2, 3};
        //System.out.println(s.minmumNumberOfHost(2, arr));

        //int[][] arr = new int[4][];
        //arr[0] = new int[]{1, 3};
        //arr[1] = new int[]{2, 4};
        //arr[2] = new int[]{3, 5};
        //arr[3] = new int[]{8, 9};
        //System.out.println(s.minmumNumberOfHost(4, arr));

        int[][] arr = new int[10][];
        arr[0] = new int[]{2147483646, 2147483647};
        arr[1] = new int[]{-2147483648, -2147483647};
        arr[2] = new int[]{2147483646, 2147483647};
        arr[3] = new int[]{-2147483648, -2147483647};
        arr[4] = new int[]{2147483646, 2147483647};
        arr[5] = new int[]{-2147483648, -2147483647};
        arr[6] = new int[]{2147483646, 2147483647};
        arr[7] = new int[]{-2147483648, -2147483647};
        arr[8] = new int[]{2147483646, 2147483647};
        arr[9] = new int[]{-2147483648, -2147483647};
        System.out.println(s.minmumNumberOfHost(10, arr));

        //    10,[[2147483646,2147483647],[2147483646,2147483647],[2147483646,2147483647],[2147483646,2147483647],[2147483646,2147483647],[2147483646,2147483647],[2147483646,2147483647],[2147483646,2147483647],[2147483646,2147483647],[2147483646,2147483647]]
        //    34,[[547,612],[417,551],[132,132],[168,446],[95,747],[187,908],[115,712],[15,329],[612,900],[3,509],[181,200],[562,787],[136,268],[36,784],[533,573],[165,946],[343,442],[127,725],[557,991],[604,613],[633,721],[287,847],[414,480],[428,698],[437,616],[475,932],[652,886],[19,992],[132,543],[390,869],[754,903],[284,925],[511,951],[272,739]]
    }
}
