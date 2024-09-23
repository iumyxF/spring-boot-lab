package com.example.elastic.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fzy
 * @description: 酒店对象
 * @date 2024/9/23 10:59
 */
@Data
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 酒店ID
     * id的类型比较特殊，不是long，而是keyword，而且id后期肯定需要涉及到我们的增删改查，所以需要参与搜索
     */

    private Long id;

    /**
     * 酒店名称
     * 要分词要搜索，text
     */
    private String name;

    /**
     * 酒店地址
     * 不要分词要搜索 keyword
     */
    private String address;

    /**
     * 酒店价格
     * integer，需要参与搜索（做范围排序）
     */
    private Integer price;

    /**
     * integer，需要参与搜索（做范围排序）
     */
    private Integer score;

    /**
     * 酒店品牌
     * 不需要分词需要搜索 keyword
     */
    private String brand;

    /**
     * 酒店所在城市
     * 不需要分词要搜索 keyword
     */
    private String city;

    /**
     * 酒店星级，1星-5星，1钻-5钻
     * 不要分词要搜索 keyword
     */
    private String startName;

    /**
     * 酒店商圈
     * 不要分词要搜索 keyword
     */
    private String business;

    /**
     * 酒店维度
     * geo_point
     */
    private String latitude;

    /**
     * 酒店精度
     * geo_point
     */
    private String longitude;

    /**
     * 酒店图片
     * 不分词不搜索 keyword 且 index = false
     */
    private String pic;
}
