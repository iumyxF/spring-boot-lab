package com.example.practice.leetcode.business.pull.user;

import com.example.practice.leetcode.business.pull.user.common.DeptBo;
import com.example.practice.leetcode.business.pull.user.common.UserBo;

import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 3/1/2025 下午3:55
 */
public class WechatService implements ThirdPartyService{
    @Override
    public List<UserBo> getUsers() {
        return null;
    }

    @Override
    public List<UserBo> getUsersByDeptId(String deptId) {
        return null;
    }

    @Override
    public List<DeptBo> getDepartments() {
        return null;
    }
}
