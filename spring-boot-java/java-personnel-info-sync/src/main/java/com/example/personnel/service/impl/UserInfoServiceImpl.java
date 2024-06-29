package com.example.personnel.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.personnel.mapper.UserInfoMapper;
import com.example.personnel.model.bean.UserInfo;
import com.example.personnel.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author iumyx
 * @description 针对表【ps_user_info】的数据库操作Service实现
 * @createDate 2024-06-28 09:28:31
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}




