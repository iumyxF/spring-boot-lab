package com.example.plus.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2023/5/9 15:46
 */
@Data
public class RoleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private String roleName;

    private List<String> menuNames;
}
