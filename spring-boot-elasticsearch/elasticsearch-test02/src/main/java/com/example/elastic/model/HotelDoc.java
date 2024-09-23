package com.example.elastic.model;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * @author fzy
 * @description: ES 文档对象
 * @date 2024/9/23 16:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDoc {

    private Long id;

    private String name;

    private String address;

    private Integer price;

    private Integer score;

    private String brand;

    private String city;

    private String starName;

    private String business;

    private String location;

    private String pic;

    public HotelDoc(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.address = hotel.getAddress();
        this.price = hotel.getPrice();
        this.score = hotel.getScore();
        this.brand = hotel.getBrand();
        this.city = hotel.getCity();
        this.starName = hotel.getStarName();
        this.business = hotel.getBusiness();
        if (StrUtil.isNotEmpty(hotel.getLatitude()) && StrUtil.isNotEmpty(hotel.getLongitude())) {
            this.location = hotel.getLatitude() + "," + hotel.getLongitude();
        } else {
            this.location = StrUtil.EMPTY;
        }
        this.pic = hotel.getPic();
    }
}
