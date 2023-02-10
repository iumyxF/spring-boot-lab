package com.example.mapstruct.converter;

import com.example.mapstruct.entities.Car;
import com.example.mapstruct.entities.User;
import com.example.mapstruct.entities.dto.UserDTO;
import com.example.mapstruct.entities.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @description: user类转换器
 * @Date 2023/2/10 10:54
 * @Author fzy
 */

/**
 * The interface User converter.
 *
 * @Mapper(componentModel = "spring") 将转换器交给Spring管理，这样编译出来的Impl会带有@Component注解，可以被其他类注入使用 <p> mapper中还有两个常用属性： 常量 constant：参数无论可不可用都用这个常量值。 默认值 defaultValue：如果参数值可用则用参数值，不可用才会使用defaultValue的值 <p> defaultExpression和expression，与上面两个类似，expression能使用Java表达式进行赋值 1、赋值ID（参数无论可不可用都用这个常量值）：expression = "java(UUID.randomUUID().toString())" 2、赋值日期（如果参数值可用则用参数值，不可用才会使用这个值）：java(LocalDateTime.now()) other： 源对象中的空字符串，转换时我想让他变成null
 * @Mapping(target = "str", expression ="java(source.str.isEmpty() ? null : source.str)")
 */
@Mapper
public interface UserConverter {

    /**
     * The constant INSTANCE.
     */
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    /**
     * User to vo user vo.
     *
     * @param user the user
     * @return the user vo
     */
    UserVo userToVo(User user);

    /**
     * User to dto user dto.
     *
     * @param user the user
     * @return the user dto
     */
    @Mapping(source = "user.userName", target = "name")
    UserDTO userToDto(User user);

    /**
     * User to dto car user dto.
     *
     * @param user the user
     * @param car  the car
     * @return the user dto
     */
    @Mapping(source = "user.userName", target = "name")
    @Mapping(source = "car.carId", target = "carId")
    UserDTO userToDtoCar(User user, Car car);

    /**
     * 可以根据DTO对象来更新Po对象
     *
     * @param userDTO the user dto
     * @param user    the user
     */
    @Mapping(source = "userDTO.name", target = "user.userName")
    void updateByDto(UserDTO userDTO, @MappingTarget User user);

    /**
     * Date日期类型转换成String类型,数字金钱格式差不多
     *
     * @param user the user
     * @return the user dto
     */
    @Mapping(source = "createTime", target = "create", dateFormat = "dd/MMM/yyyy")
    @Mapping(source = "age", target = "age", resultType = String.class)
    UserDTO toModel(User user);
}
