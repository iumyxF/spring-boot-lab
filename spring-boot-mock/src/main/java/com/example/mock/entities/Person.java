package com.example.mock.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fzy
 * @description: junit5 测试使用实体
 * @date 2023/5/15 10:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String firstName;

    private String lastName;
}
