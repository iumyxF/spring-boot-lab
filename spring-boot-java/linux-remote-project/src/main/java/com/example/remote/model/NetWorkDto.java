package com.example.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author iumyx
 * @description: 网卡信息
 * @date 2024/6/19 11:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetWorkDto {

    private String name;

    private String uuid;

    private String type;

    private String device;
}
