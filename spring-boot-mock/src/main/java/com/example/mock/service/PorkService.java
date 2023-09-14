package com.example.mock.service;


import com.example.mock.entities.PorkInst;
import com.example.mock.exception.BaseBusinessException;

import java.util.Map;

/**
 * @description: 猪肉服务层
 * @Date 2023/2/11 10:57
 * @author iumyxF
 */
public interface PorkService {

    /**
     * 获取猪肉打包实例
     *
     * @param weight 重量
     * @param params 额外信息
     * @return {@link PorkInst} - 指定数量的猪肉实例
     * @throws BaseBusinessException 如果猪肉库存不足，返回异常，同时后台告知工厂
     */
    PorkInst getPork(Long weight, Map<String, Object> params);
}
