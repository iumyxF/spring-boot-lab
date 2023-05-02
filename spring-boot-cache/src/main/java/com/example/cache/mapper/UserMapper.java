package com.example.cache.mapper;

import com.example.cache.model.entities.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author gonsin
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2023-05-02 10:08:11
 * @Entity generator.domain.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




