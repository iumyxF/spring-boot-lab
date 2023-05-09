package com.example.plus.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.plus.model.entities.Role;
import com.example.plus.model.vo.RoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * The interface Role mapper.
 *
 * @author gonsin
 * @description 针对表 【role】的数据库操作Mapper
 * @createDate 2023 -05-09 13:55:28
 * @Entity generator.domain.Role
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {


    /**
     * Select role vo by id role vo.
     *
     * @param roleId the role id
     * @return the role vo
     */
    RoleVo selectRoleVoById(@Param("roleId") Long roleId);
}




