package com.example.workflow.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.workflow.domain.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author iumyx
 * @description 针对表【person】的数据库操作Mapper
 * @createDate 2023-09-14 15:19:50
 * @Entity generator.domain.Person
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return Person
     */
    Person findByUsername(@Param("username") String username);
}




