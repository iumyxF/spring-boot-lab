package com.example.openvidu.entities.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fzy
 * @description:
 * @date 2024/11/18 15:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userKey;

    private String name;
}
