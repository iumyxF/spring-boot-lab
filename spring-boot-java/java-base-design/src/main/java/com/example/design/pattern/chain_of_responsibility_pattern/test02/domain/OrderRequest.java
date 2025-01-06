package com.example.design.pattern.chain_of_responsibility_pattern.test02.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2024/10/10 13:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    /**
     * 商品列表
     */
    private List<Product> productList;

    /**
     * 用户使用的优惠券
     */
    private String couponCode;

    /**
     * 运费
     */
    private BigDecimal shippingFee;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;


    /**
     * 计算订单总金额（含运费和扣除优惠）
     *
     * @return 优惠价格
     */
    public BigDecimal calculateTotalAmount() {
        BigDecimal productTotal = productList.stream()
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // 简单模拟优惠金额
        BigDecimal discount = (couponCode != null && !couponCode.isEmpty()) ? BigDecimal.valueOf(10) : BigDecimal.ZERO;
        return productTotal.add(shippingFee).subtract(discount);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {

        /**
         * id
         */
        private String productId;

        /**
         * 名称
         */
        private String name;

        /**
         * 数量
         */
        private int quantity;

        /**
         * 单价
         */
        private BigDecimal price;
    }
}
