package com.example.practice.leetcode.business.pull.user;


import com.example.practice.leetcode.business.pull.user.common.DeptBo;
import com.example.practice.leetcode.business.pull.user.common.UserBo;

import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 3/1/2025 下午3:53
 */
public interface ThirdPartyService {

    List<UserBo> getUsers();

    List<UserBo> getUsersByDeptId(String deptId);

    List<DeptBo> getDepartments();
}
