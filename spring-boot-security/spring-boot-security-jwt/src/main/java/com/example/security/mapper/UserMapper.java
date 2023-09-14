package com.example.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.security.entities.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @Date 2023/2/17 10:45
 * @author iumyxF
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
