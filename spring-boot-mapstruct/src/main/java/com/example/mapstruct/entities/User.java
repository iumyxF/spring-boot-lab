package com.example.mapstruct.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @Date 2023/2/10 9:52
 * @author iumyxF
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    private String userName;

    private List<Hobby> hobbies;

    private Integer age;

    private Date createTime;

}
