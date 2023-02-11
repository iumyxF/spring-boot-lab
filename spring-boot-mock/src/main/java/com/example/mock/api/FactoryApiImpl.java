package com.example.mock.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @Date 2023/2/11 11:04
 * @Author fzy
 */
@Slf4j
@Service
public class FactoryApiImpl implements FactoryApi {

    /**
     * 调用第三方接口，供应猪肉
     *
     * @param weight 重量
     */
    @Override
    public void supplyPork(Long weight) {
        log.info("打电话给真正的工厂供应猪肉，重量: {}", weight);
    }
}
