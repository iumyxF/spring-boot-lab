package com.example.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatis.entities.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author fzy
 * @description:
 * @date 2024/4/20 9:48
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User selectUserById(Integer id);
}
