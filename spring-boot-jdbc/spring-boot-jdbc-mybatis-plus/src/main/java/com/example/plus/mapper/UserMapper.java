package com.example.plus.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.plus.model.entities.User;
import com.example.plus.model.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * The interface User mapper.
 *
 * @author iumyx
 * @description 针对表 【user】的数据库操作Mapper
 * @createDate 2023 -05-09 13:55:28
 * @Entity generator.domain.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    /**
     * Select user vo by id user vo.
     *
     * @param userId the user id
     * @return the user vo
     */
    UserVo selectUserVoById(@Param("userId") Long userId);

}




