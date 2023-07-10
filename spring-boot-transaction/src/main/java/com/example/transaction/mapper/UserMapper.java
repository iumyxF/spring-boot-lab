package com.example.transaction.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.transaction.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author iumyxF
 * @description 针对表【tr_user】的数据库操作Mapper
 * @createDate 2023-07-10 09:15:25
 * @Entity generator.domain.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




