package com.example.design.demo.pull.chain;

import com.example.design.demo.pull.commons.DepartmentBo;
import com.example.design.demo.pull.commons.SyncPersonInfoContext;
import com.example.design.demo.pull.commons.UserBo;
import com.example.design.demo.pull.handler.DingTalkInfoSynchronizer;

import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 6/1/2025 下午4:02
 */
public class ExecuteSyncUserNode extends AbstractSyncNode {

    @Override
    public void handle(SyncPersonInfoContext context) {
        System.out.println("开始同步人员信息...");
        // 根据类型获取 PersonnelInfoSynchronizer
        DingTalkInfoSynchronizer synchronizer = new DingTalkInfoSynchronizer();
        List<UserBo> userBos = synchronizer.syncUserInfo();
        if (null != userBos && !userBos.isEmpty()) {
            // userService.saveBatch() ...
        }
        System.out.println("同步部门人员完毕...");
        if (nextNode != null) {
            nextNode.handle(context);
        }
    }
}
