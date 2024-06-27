package com.example.files.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author iumyx
 * @description:
 * @date 2024/6/27 14:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadResponse {

    private Integer code;

    private String msg;
}
