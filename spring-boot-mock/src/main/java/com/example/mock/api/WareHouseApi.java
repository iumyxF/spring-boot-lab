package com.example.mock.api;

import com.example.mock.entities.PorkInst;

import java.util.Map;

/**
 * @description:
 * @Date 2023/2/11 11:04
 * @Author fzy
 */
public interface WareHouseApi {

    /**
     * 仓库打包猪肉
     *
     * @param weight 重量
     * @param params 其他参数
     * @return 猪肉实体
     */
    PorkInst packagePork(Long weight, Map<String, Object> params);

}
