package com.example.elastic.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author fzy
 * @description:
 * @date 2024/9/21 16:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 经度
     */
    private String latitude;

    /**
     * 维度
     */
    private String longitude;
}
