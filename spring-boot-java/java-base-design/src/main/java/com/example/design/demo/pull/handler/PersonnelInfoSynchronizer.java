package com.example.design.demo.pull.handler;

import com.example.design.demo.pull.commons.DepartmentBo;
import com.example.design.demo.pull.commons.UserBo;

import java.util.List;

/**
 * @author fzy
 * @description: 人员信息同步器
 * @date 6/1/2025 下午3:34
 */
public interface PersonnelInfoSynchronizer {

    List<DepartmentBo> syncDepartmentInfo();

    List<UserBo> syncUserInfo();
}
