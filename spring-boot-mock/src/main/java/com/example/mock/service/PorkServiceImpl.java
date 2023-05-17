package com.example.mock.service;

import com.example.mock.api.FactoryApi;
import com.example.mock.api.WareHouseApi;
import com.example.mock.dao.PorkStorageMapper;
import com.example.mock.entities.PorkInst;
import com.example.mock.entities.PorkStorage;
import com.example.mock.exception.BaseBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @Date 2023/2/11 11:05
 * @Author fzy
 */
@Slf4j
@Service
public class PorkServiceImpl implements PorkService {

    @Resource
    private PorkStorageMapper porkStorageMapper;
    @Resource
    private FactoryApi factoryApi;
    @Resource
    private WareHouseApi wareHouseApi;

    /**
     * 获取猪肉打包实例
     *
     * @param weight 重量
     * @param params 额外信息
     * @return {@link PorkInst} - 指定数量的猪肉实例
     * @throws BaseBusinessException 如果猪肉库存不足，返回异常，同时后台告知工厂
     */
    @Override
    public PorkInst getPork(Long weight, Map<String, Object> params) {
        //查询猪肉库存数量
        PorkStorage store = porkStorageMapper.queryStore();
        //够库存则返回成功，打包一斤猪肉
        if (weight > store.getCnt()) {
            //不够库存则返回猪肉在进货中失败结果，并且通知工厂进货猪肉
            factoryApi.supplyPork(weight);
            throw new BaseBusinessException("猪肉库存不足");
        }
        wareHouseApi.packagePork(weight, params);
        return new PorkInst(weight, params);
    }
}

