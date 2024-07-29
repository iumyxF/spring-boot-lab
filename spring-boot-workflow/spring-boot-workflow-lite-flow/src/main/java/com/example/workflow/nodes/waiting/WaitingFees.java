package com.example.workflow.nodes.waiting;

import com.example.workflow.nodes.OrderContext;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author fzy
 * @description: 等候费
 * @date 2024/7/27 14:24
 */
@Slf4j
@LiteflowComponent("WaitingFees")
public class WaitingFees extends NodeComponent {

    /**
     * 10分钟 = 600000毫秒
     */
    private static final Long TEN_MINUTES_MILL = 600000L;

    @Override
    public void process() {
        OrderContext orderContext = this.getContextBean("orderContext");
        long arriveTime = orderContext.getArriveTime().getTime();
        long startServerTime = orderContext.getStartServiceTime().getTime();
        // 司机等待时长
        long waitTime = startServerTime - arriveTime;
        if (waitTime > TEN_MINUTES_MILL) {
            long waitAmount = 100L * (waitTime / TEN_MINUTES_MILL);
            log.info("等待费用: {}", waitAmount);
            orderContext.setFinalAmount(orderContext.getFinalAmount() + waitAmount);
        }
    }

}
