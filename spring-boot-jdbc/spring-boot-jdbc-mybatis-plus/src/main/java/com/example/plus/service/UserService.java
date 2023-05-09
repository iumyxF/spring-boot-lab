package com.example.plus.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.plus.model.entities.User;
import com.example.plus.model.vo.UserVo;

/**
 * The interface User service.
 *
 * @author gonsin
 * @description 针对表 【user】的数据库操作Service
 * @createDate 2023 -05-09 13:55:28
 */
public interface UserService extends IService<User> {

    /**
     * Select user vo by id user vo.
     *
     * @param userId the user id
     * @return the user vo
     */
    UserVo selectUserVoById(Long userId);
}
