package com.example.plus.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.plus.mapper.RoleMapper;
import com.example.plus.model.entities.Role;
import com.example.plus.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author gonsin
 * @description 针对表【role】的数据库操作Service实现
 * @createDate 2023-05-09 13:55:28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}




