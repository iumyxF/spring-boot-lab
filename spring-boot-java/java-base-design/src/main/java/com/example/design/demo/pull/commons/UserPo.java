package com.example.design.demo.pull.commons;

import lombok.Data;

/**
 * @author fzy
 * @description:
 * @date 6/1/2025 下午3:25
 */
@Data
public class UserPo {

    private Long id;

    private String name;

    private String departmentId;

    /**
     * 0 - 系统内部创建
     * 1 - 钉钉
     * 2 - 微信
     */
    private Integer type;

    /**
     * 关联的外部系统ID
     */
    private String outId;
}
