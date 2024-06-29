package com.example.personnel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author iumyxF
 * @description:
 * @date 2024/6/28 10:51
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QyWechatExternalConfig implements ExternalConfig {

    private String corpId;

    private String corpSecret;
}