package com.example.workflow.nodes.mileage;

import com.example.workflow.nodes.OrderContext;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

/**
 * @author fzy
 * @description: 0~6 里程费
 * @date 2024/7/27 14:24
 */
@LiteflowComponent("MileageFeesMorning")
public class MileageFeesMorning extends NodeComponent {

    /**
     * 3公里内19元
     */
    private static final Long DEFAULT_AMOUNT = 19 * 100L;

    /**
     * 超出默认形成后每公里4元
     */
    private static final Long ONE_DISTANCE_AMOUNT = 4 * 100L;

    @Override
    public void process() {
        OrderContext orderContext = this.getContextBean("orderContext");
        Long res = DEFAULT_AMOUNT;
        Long distance = orderContext.getDistance();
        if (distance > 3 && distance <= 12) {
            res += (distance - 3) * ONE_DISTANCE_AMOUNT;
        } else if (distance > 12) {
            res += 9 * ONE_DISTANCE_AMOUNT;
        }
        orderContext.setFinalAmount(res);
    }
}