package com.example.openvidu.service.impl;

import com.example.openvidu.entities.po.User;
import com.example.openvidu.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * @author fzy
 * @description:
 * @date 2024/11/18 16:11
 */
@Service
public class UserServiceImpl implements UserService {

    HashMap<String, User> userMap = new HashMap<>(64);

    @PostConstruct
    public void mock() {
        for (int i = 1; i <= 50; i++) {
            User user = new User("key_" + i, "user_" + i);
            userMap.put(user.getUserKey(), user);
        }
    }

    @Override
    public User getByKey(String userKey) {
        return userMap.get(userKey);
    }
}
