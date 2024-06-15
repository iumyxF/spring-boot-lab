package com.example.mybatis;

import com.example.mybatis.entities.User;
import com.example.mybatis.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author iumyx
 * @description:
 * @date 2024/4/20 10:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test() {
        User u1 = userMapper.selectUserById(1);
        System.out.println("u1 = " + u1.toString());

        User u2 = userMapper.selectUserById(1);
        System.out.println("u2 = " + u2.toString());
    }
}
