package com.example.elastic.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.elastic.model.Hotel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author iumyxF
* @description 针对表【hotel】的数据库操作Mapper
* @createDate 2024-09-23 15:58:29
* @Entity generator.domain.Hotel
*/
@Mapper
@Repository
public interface HotelMapper extends BaseMapper<Hotel> {

}
