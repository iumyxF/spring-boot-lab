package com.example.openvidu.service;

import com.example.openvidu.entities.po.User;

/**
 * @author fzy
 * @description:
 * @date 2024/11/18 16:10
 */
public interface UserService {

    User getByKey(String userKey);

}
