package com.example.mock.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @description: 猪肉实体
 * @Date 2023/2/11 10:44
 * @author iumyxF
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PorkInst {
    /**
     * 重量
     */
    private Long weight;

    /**
     * 附件参数，例如包装类型，寄送地址等信息
     */
    private Map<String, Object> paramsMap;
}
