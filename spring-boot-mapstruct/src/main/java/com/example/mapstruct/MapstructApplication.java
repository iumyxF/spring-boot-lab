package com.example.mapstruct;

import com.example.mapstruct.converter.UserConverter;
import com.example.mapstruct.entities.Car;
import com.example.mapstruct.entities.Hobby;
import com.example.mapstruct.entities.User;
import com.example.mapstruct.entities.dto.UserDTO;
import com.example.mapstruct.entities.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * @description:
 * @Date 2023/2/10 10:50
 * @Author fzy
 */

@Slf4j
@SpringBootApplication
public class MapstructApplication {

    /**
     * 更多使用情况：https://zhuanlan.zhihu.com/p/368731266?utm_id=0
     */
    public static void main(String[] args) {
        SpringApplication.run(MapstructApplication.class, args);
        User user = buildUser();
        Car car = buildCar();
        log.info("属性类型和名字都相同的简单转换");
        UserVo userVo = UserConverter.INSTANCE.userToVo(user);
        System.out.println(userVo.toString());
        log.info("属性类型相同，命名不相同使用@mapping注解");
        UserDTO userDTO = UserConverter.INSTANCE.userToDto(user);
        System.out.println(userDTO.toString());
        log.info("多个类聚合而成的实体");
        UserDTO userToDtoHobby = UserConverter.INSTANCE.userToDtoCar(user, car);
        System.out.println(userToDtoHobby.toString());
        log.info("列表类型转换");
        /*如：1、构建出hobby的Converter
         2、然后在UserConverter中@Mapper(uses = {HobbyConverter.class})
         3、映射@Mapping(source = "user.hobbies", target = "hobbiesDtoList")
        */

    }

    private static Car buildCar() {
        return new Car(888L, "Porsche");
    }

    public static User buildUser() {
        Hobby h1 = new Hobby(1001L, "唱");
        Hobby h2 = new Hobby(1002L, "跳");
        Hobby h3 = new Hobby(1003L, "rap");
        Hobby h4 = new Hobby(1004L, "篮球");
        User user = new User();
        user.setId(666L);
        user.setUserName("jack");
        user.setHobbies(Arrays.asList(h1, h2, h3, h4));
        user.setAge(18);
        return user;
    }
}
