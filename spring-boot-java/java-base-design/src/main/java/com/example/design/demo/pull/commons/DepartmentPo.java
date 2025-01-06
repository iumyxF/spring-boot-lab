package com.example.design.demo.pull.commons;

import lombok.Data;

/**
 * @author fzy
 * @description:
 * @date 6/1/2025 下午3:26
 */
@Data
public class DepartmentPo {

    private Long id;

    private Long parentId;

    private String name;

    private Integer type;

    private String outId;
}
