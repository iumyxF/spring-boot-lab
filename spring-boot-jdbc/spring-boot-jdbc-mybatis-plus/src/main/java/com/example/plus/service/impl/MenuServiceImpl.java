package com.example.plus.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.plus.mapper.MenuMapper;
import com.example.plus.model.entities.Menu;
import com.example.plus.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * @author gonsin
 * @description 针对表【menu】的数据库操作Service实现
 * @createDate 2023-05-09 13:55:28
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}




