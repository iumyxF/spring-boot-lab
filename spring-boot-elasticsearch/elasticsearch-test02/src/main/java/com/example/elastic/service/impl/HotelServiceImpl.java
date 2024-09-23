package com.example.elastic.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.elastic.mapper.HotelMapper;
import com.example.elastic.model.Hotel;
import com.example.elastic.service.HotelService;
import org.springframework.stereotype.Service;

/**
 * @author iumyxF
 * @description 针对表【hotel】的数据库操作Service实现
 * @createDate 2024-09-23 15:58:29
 */
@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService {

}
