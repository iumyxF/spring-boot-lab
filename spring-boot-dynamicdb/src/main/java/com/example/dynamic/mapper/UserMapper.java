package com.example.dynamic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dynamic.entities.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: dao
 * @Date 2023/2/14 16:14
 * @Author fzy
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
