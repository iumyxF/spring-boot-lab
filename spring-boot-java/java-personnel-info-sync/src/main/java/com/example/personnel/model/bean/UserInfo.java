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
 * @description: 用户信息
 * @date 2024/6/28 9:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ps_user_info")
public class UserInfo implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long userId;

    private String name;

    private String qyWechatKey;

    private String phone;

    /**
     * {@link SourceEnum}
     */
    private Integer userSource;
}