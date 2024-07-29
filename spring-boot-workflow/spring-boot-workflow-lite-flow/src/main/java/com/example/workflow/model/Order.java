package com.example.workflow.model;

import lombok.Data;

import java.util.Date;

/**
 * @author fzy
 * @description:
 * @date 2024/7/27 14:14
 */
@Data
public class Order {

    private Long id;

    /**
     * 接单时间
     */
    private Date acceptTime;

    /**
     * 到达接乘客地点的时间
     */
    private Date arriveTime;

    /**
     * 开始服务时间(接到乘客开始出发的时间)
     */
    private Date startServiceTime;

    /**
     * 结束服务时间(送顾客到目的地的时间)
     */
    private Date endServiceTime;

    /**
     * 估计行程 公里
     */
    private Long distance;
}
