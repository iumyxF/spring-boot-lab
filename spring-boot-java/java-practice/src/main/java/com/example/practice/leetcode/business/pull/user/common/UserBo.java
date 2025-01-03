package com.example.practice.leetcode.business.pull.user.common;

/**
 * @author fzy
 * @description:
 * @date 3/1/2025 下午3:44
 */
public class UserBo {

    private String id;

    private String name;

    private String deptId;

    public UserBo(String id, String name, String deptId) {
        this.id = id;
        this.name = name;
        this.deptId = deptId;
    }

    public String getId() {
        return id;
    }

    public UserBo setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserBo setName(String name) {
        this.name = name;
        return this;
    }

    public String getDeptId() {
        return deptId;
    }

    public UserBo setDeptId(String deptId) {
        this.deptId = deptId;
        return this;
    }
}
