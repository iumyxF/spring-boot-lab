package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author iumyx
 * @description: 优惠券对象
 * @date 2024/6/6 16:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    /**
     * 主键
     */
    private Long id;

    /**
     * 金额价值 分
     */
    private Long valueOfAmount;

    /**
     * 时间价值 毫秒
     */
    private Long valueOfTime;

    /**
     * 使用门槛 单位 分
     */
    private Long thresholds;
}
