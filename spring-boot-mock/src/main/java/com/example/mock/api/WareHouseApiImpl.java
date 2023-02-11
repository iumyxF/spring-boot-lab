package com.example.mock.api;

import com.example.mock.entities.PorkInst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @description:
 * @Date 2023/2/11 11:05
 * @Author fzy
 */
@Service
@Slf4j
public class WareHouseApiImpl implements WareHouseApi {

    /**
     * 第三方接口：仓库打包猪肉
     *
     * @param weight 重量
     * @param params 其他参数
     * @return 猪肉实体
     */
    @Override
    public PorkInst packagePork(Long weight, Map<String, Object> params) {
        log.info("呼叫真实仓库包装，重量: {}", weight);
        return PorkInst.builder().weight(weight).paramsMap(params).build();
    }
}
