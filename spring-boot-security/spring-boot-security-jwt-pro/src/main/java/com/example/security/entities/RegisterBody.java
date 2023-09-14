package com.example.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @description: 注册用户表单对象
 * @Date 2023/2/23 14:03
 * @author iumyxF
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBody {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
