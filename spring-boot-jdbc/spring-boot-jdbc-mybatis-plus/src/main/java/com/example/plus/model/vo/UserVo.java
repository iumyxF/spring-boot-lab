package com.example.plus.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author fzy
 * @description: 用户视图对象
 * @date 2023/5/9 14:27
 */
@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String username;

    private Long roleId;

    private String roleName;

    private List<Long> menuIds;

    private List<String> menuNames;

    private List<String> menuPaths;
}
