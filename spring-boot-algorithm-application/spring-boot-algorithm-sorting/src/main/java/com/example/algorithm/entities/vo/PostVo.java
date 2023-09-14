package com.example.algorithm.entities.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author iumyxF
 * @description:
 * @date 2023/4/28 16:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 点赞数
     */
    private Long likes;

    /**
     * 评论数
     */
    private Long comments;

    /**
     * 发布时间
     */
    private Date createTime;

    /**
     * 数据权重
     */
    private BigDecimal dataWeights;
}
