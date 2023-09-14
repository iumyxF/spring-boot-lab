package com.example.mapstruct.entities.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @description:
 * @Date 2023/2/10 10:55
 * @author iumyxF
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    private String userName;

    private Integer age;

}
