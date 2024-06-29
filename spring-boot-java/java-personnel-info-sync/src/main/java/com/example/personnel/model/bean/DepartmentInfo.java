package com.example.personnel.model.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.personnel.model.enums.SourceEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author iumyxF
 * @description: 部门信息
 * @date 2024/6/28 9:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ps_department_info")
public class DepartmentInfo implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long departmentId;

    private String name;

    private Long parentId;

    /**
     * {@link SourceEnum}
     */
    private Integer departmentSource;
}
