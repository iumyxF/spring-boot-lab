package com.example.workflow.nodes.mileage;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.example.workflow.nodes.OrderContext;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeSwitchComponent;

import java.util.Date;

/**
 * @author fzy
 * @description: 里程费选择器
 * @date 2024/7/27 14:48
 */
@LiteflowComponent("MileageFeesSelectorNode")
public class MileageFeesSelectorNode extends NodeSwitchComponent  {

    @Override
    public String processSwitch() {
        OrderContext orderContext = this.getContextBean("orderContext");
        // 根据时间段选择里程费计费标准
        DateTime acceptTime = new DateTime(orderContext.getAcceptTime());
        DateTime zeroTime = DateUtil.beginOfDay(acceptTime);
        DateTime sixTime = DateUtil.offsetHour(zeroTime, 6);
        DateTime twentyFourTime = DateUtil.endOfDay(acceptTime);
        if (acceptTime.isAfter(zeroTime) && acceptTime.isBefore(sixTime)) {
            return "MileageFeesMorning";
        } else if (acceptTime.isAfter(sixTime) && acceptTime.isBefore(twentyFourTime)) {
            return "MileageFeesWorking";
        } else {
            System.out.println("不合法的时间");
        }
        return null;
    }

    public static void main(String[] args) {
        Date acceptTime = new Date();
        System.out.println(acceptTime);
        DateTime beginOfDay = DateUtil.beginOfDay(acceptTime);
        DateTime six = DateUtil.offsetHour(beginOfDay, 6);
        DateTime endOfDay = DateUtil.endOfDay(acceptTime);
        System.out.println(beginOfDay);
        System.out.println(six);
        System.out.println(endOfDay);
    }
}
