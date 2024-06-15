package com.example.test;

import com.example.model.Coupon;

import java.util.Arrays;
import java.util.List;

/**
 * @author iumyx
 * @description:
 * @date 2024/6/6 16:47
 */
public class TestServiceImpl {

    /**
     * 假设 租用场地2小时，场地费用（100元/1小时）
     */
    public static void main(String[] args) {
        // 199-30 券
        Coupon c1 = new Coupon(1L, 30 * 100L, 0L, 299 * 100L);
        //
    }

    /**
     * 初始化 dp 数组，将所有元素设置为正无穷大（表示不可达）。
     * 对于每张优惠券，遍历 dp 数组，更新 dp[i]：
     * 如果订单金额 i 大于等于该优惠券的门槛 thresholds，则更新 dp[i] 为 min(dp[i], dp[i - thresholds] + valueOfAmount)，其中 valueOfAmount 是该优惠券的抵扣金额。
     * 最终，dp[订单金额] 就是最小结算金额。
     */
    public long minSettlementAmount(List<Coupon> coupons, long orderAmount) {
        long[] dp = new long[(int) (orderAmount + 1)];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        for (Coupon coupon : coupons) {
            long thresholds = coupon.getThresholds();
            long valueOfAmount = coupon.getValueOfAmount();
            for (long i = thresholds; i <= orderAmount; i++) {
                dp[(int) i] = Math.min(dp[(int) i], dp[(int) (i - thresholds)] + valueOfAmount);
            }
        }
        return dp[(int) orderAmount];
    }
}
