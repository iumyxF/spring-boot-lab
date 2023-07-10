package com.example.transaction.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.transaction.domain.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author iumyxF
 * @description 针对表【tr_order】的数据库操作Mapper
 * @createDate 2023-07-10 09:15:25
 * @Entity generator.domain.Order
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}




