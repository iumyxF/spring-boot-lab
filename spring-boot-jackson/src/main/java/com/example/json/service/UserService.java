package com.example.json.service;

import com.example.json.entities.Hobby;
import com.example.json.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @description:
 * @Date 2023/2/10 9:54
 * @Author fzy
 */
@Slf4j
public class UserService {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static ObjectMapper getInstance() {
        return OBJECT_MAPPER;
    }

    public User json2Bean(String json) {
        User user;
        try {
            user = getInstance().readValue(json, User.class);
            log.info("反序列化完毕,user = {}", user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public String bean2Json(User user) {
        String json;
        try {
            json = getInstance().writeValueAsString(user);
            log.info("序列化完毕,json = {}", json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    public static void main(String[] args) {
        User user = buildUser();
        UserService service = new UserService();
        String json = service.bean2Json(user);
        service.json2Bean(json);
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
