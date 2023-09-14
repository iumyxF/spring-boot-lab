package com.example.security.service;

import com.example.security.entities.Result;
import com.example.security.entities.User;

/**
 * The interface Login service.
 *
 * @description:
 * @Date 2023 /2/17 9:56
 * @author iumyxF
 */
public interface ILoginService {

    /**
     * Login result.
     *
     * @param user the user
     * @return the result
     */
    Result login(User user);
}
