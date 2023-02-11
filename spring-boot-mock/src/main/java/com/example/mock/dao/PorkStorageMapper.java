package com.example.mock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mock.entities.PorkStorage;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 猪肉库存DAO
 * @Date 2023/2/11 10:50
 * @Author fzy
 */
@Mapper
public interface PorkStorageMapper extends BaseMapper<PorkStorage> {

    /**
     * 查询猪肉库存
     * @return
     */
    PorkStorage queryStore();
}
