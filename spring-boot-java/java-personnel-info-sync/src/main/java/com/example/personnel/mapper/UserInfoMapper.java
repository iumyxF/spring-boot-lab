package com.example.personnel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.personnel.model.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author iumyx
 * @description 针对表【ps_user_info】的数据库操作Mapper
 * @createDate 2024-06-28 09:28:31
 * @Entity generator.domain.UserInfo
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}




