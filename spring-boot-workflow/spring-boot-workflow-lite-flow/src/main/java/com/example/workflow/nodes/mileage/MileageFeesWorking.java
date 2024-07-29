package com.example.workflow.nodes.mileage;

import com.example.workflow.nodes.OrderContext;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

/**
 * @author fzy
 * @description: 6~22 里程费
 * @date 2024/7/27 14:25
 */
@LiteflowComponent("MileageFeesWorking")
public class MileageFeesWorking extends NodeComponent {

    /**
     * 5公里内 19元
     */
    private static final Long DEFAULT_AMOUNT = 19 * 100L;

    /**
     * 超出默认形成后每公里3元
     */
    private static final Long ONE_DISTANCE_AMOUNT = 3 * 100L;

    @Override
    public void process() throws Exception {
        OrderContext orderContext = this.getContextBean("orderContext");
        Long res = DEFAULT_AMOUNT;
        Long distance = orderContext.getDistance();
        if (distance > 5 && distance <= 12) {
            res += (distance - 5) * ONE_DISTANCE_AMOUNT;
        } else if (distance > 12) {
            res += 7 * ONE_DISTANCE_AMOUNT;
        }
        orderContext.setFinalAmount(res);
    }
}