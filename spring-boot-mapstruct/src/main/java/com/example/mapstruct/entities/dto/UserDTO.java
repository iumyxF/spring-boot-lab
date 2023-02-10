package com.example.mapstruct.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @description:
 * @Date 2023/2/10 11:19
 * @Author fzy
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String name;

    private Long carId;

    private String create;

    private String age;
}
