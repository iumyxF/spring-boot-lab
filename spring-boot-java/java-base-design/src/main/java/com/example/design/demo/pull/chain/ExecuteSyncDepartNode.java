package com.example.design.demo.pull.chain;

import com.example.design.demo.pull.commons.DepartmentBo;
import com.example.design.demo.pull.commons.SyncPersonInfoContext;
import com.example.design.demo.pull.handler.DingTalkInfoSynchronizer;
import com.example.design.demo.pull.handler.PersonnelInfoSynchronizer;

import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 6/1/2025 下午3:53
 */
public class ExecuteSyncDepartNode extends AbstractSyncNode {

    @Override
    public void handle(SyncPersonInfoContext context) {
        System.out.println("开始同步部门信息...");
        // 根据类型获取 PersonnelInfoSynchronizer
        DingTalkInfoSynchronizer synchronizer = new DingTalkInfoSynchronizer();
        List<DepartmentBo> departmentBos = synchronizer.syncDepartmentInfo();
        if (null != departmentBos && !departmentBos.isEmpty()) {
            // departmentService.saveBatch() ...
        }
        System.out.println("同步部门信息完毕...");
        if (nextNode != null) {
            nextNode.handle(context);
        }
    }
}
