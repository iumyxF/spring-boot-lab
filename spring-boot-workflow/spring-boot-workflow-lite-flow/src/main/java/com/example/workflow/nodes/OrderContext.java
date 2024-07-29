package com.example.workflow.nodes;

import com.example.workflow.model.Order;
import com.yomahub.liteflow.context.ContextBean;
import lombok.Data;

import java.util.Date;

/**
 * @author fzy
 * @description:
 * @date 2024/7/27 14:31
 */
@ContextBean("orderContext")
@Data
public class OrderContext {

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

    /**
     * 最后计算金额
     */
    private Long finalAmount;

    public OrderContext(Order order) {
        this.acceptTime = order.getAcceptTime();
        this.arriveTime = order.getArriveTime();
        this.startServiceTime = order.getStartServiceTime();
        this.endServiceTime = order.getEndServiceTime();
        this.distance = order.getDistance();
        this.finalAmount = 0L;
    }
}
