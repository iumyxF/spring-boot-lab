package com.example.excel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.excel.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author iumyxF
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2024-09-07 10:41:32
 * @Entity generator.domain.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}