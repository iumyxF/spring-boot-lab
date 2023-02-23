package com.example.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.security.entities.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 * @description: user DAO
 * @Date 2023/2/22 10:15
 * @Author fzy
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Results(id = "userResultMap", value = {
            @Result(column = "id", property = "userId", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR)
    })

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Select(value = "select id,username,password from security_user where username = #{userName};")
    User selectUserByUserName(@Param("userName") String userName);

}
