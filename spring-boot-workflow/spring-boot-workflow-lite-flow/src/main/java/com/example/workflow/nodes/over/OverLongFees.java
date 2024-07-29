package com.example.workflow.nodes.over;

import com.example.workflow.nodes.OrderContext;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

/**
 * @author fzy
 * @description: 长距离
 * @date 2024/7/27 14:29
 */
@LiteflowComponent("OverLongFees")
public class OverLongFees extends NodeComponent {

    private static final Long MAX_DISTANCE = 12L;

    @Override
    public void process() {
        OrderContext orderContext = this.getContextBean("orderContext");
        if (orderContext.getDistance() > MAX_DISTANCE) {
            orderContext.setFinalAmount(orderContext.getFinalAmount()
                    + 100L * (MAX_DISTANCE - orderContext.getDistance()));
        }
    }
}
