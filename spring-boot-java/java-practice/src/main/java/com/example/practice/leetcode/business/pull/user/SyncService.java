package com.example.practice.leetcode.business.pull.user;

import com.example.practice.leetcode.business.pull.user.common.DeptBo;
import com.example.practice.leetcode.business.pull.user.common.UserBo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 3/1/2025 下午3:56
 */
public class SyncService {

    /**
     * 同步的前置工作
     */
    public void syncPostProcessor() {
        // group 初始化

    }

    public void doDeptSync() {
        ThirdPartyService thirdPartyService = ThirdPartyServiceFactory.createService("dingTalk");
        List<DeptBo> departments = thirdPartyService.getDepartments();
        for (DeptBo deptBo : departments) {
            // save...
        }
    }

    public void doUserSync() {
        ThirdPartyService thirdPartyService = ThirdPartyServiceFactory.createService("dingTalk");
        // 读取数据库中 是dingTalk拉取的部门列表
        List<DeptBo> deptBos = new ArrayList<>();
        if (null == deptBos || deptBos.isEmpty()) {
            return;
        }
        for (DeptBo deptBo : deptBos) {
            // 根据部门id获取用户
            List<UserBo> userBoList = thirdPartyService.getUsersByDeptId(deptBo.getId());
            if (null == userBoList || userBoList.isEmpty()) {
                continue;
            }
            // 创建User 和 UserGroup
        }
    }
}
