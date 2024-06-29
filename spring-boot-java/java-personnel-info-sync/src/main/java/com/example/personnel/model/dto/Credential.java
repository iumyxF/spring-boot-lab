package com.example.personnel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author iumyxF
 * @description: 凭证信息
 * @date 2024/6/28 10:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credential {

    private String accessToken;

    private Long expireTime;
}
