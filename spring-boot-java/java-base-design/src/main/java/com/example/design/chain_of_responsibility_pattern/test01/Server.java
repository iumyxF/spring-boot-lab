package com.example.design.chain_of_responsibility_pattern.test01;

import java.util.HashMap;
import java.util.Map;

/**
 * @author iumyxF
 * @description: 授权目标
 * @date 2023/8/3 9:43
 */
public class Server {

    private final Map<String, String> users = new HashMap<>();

    private BaseMiddleware baseMiddleware;

    /**
     * 客户端将对象链传递给服务器。
     * 这提高了灵活性，也使服务器类的测试变得更容易。
     */
    public void setMiddleware(BaseMiddleware baseMiddleware) {
        this.baseMiddleware = baseMiddleware;
    }

    /**
     * 服务器从客户端获取电子邮件和密码，并向链发送授权请求。
     */
    public boolean logIn(String email, String password) {
        if (baseMiddleware.check(email, password)) {
            System.out.println("认证成功!");

            // 在这里为授权用户做些有用的事情。

            return true;
        }
        return false;
    }

    public void register(String email, String password) {
        users.put(email, password);
    }

    public boolean hasEmail(String email) {
        return users.containsKey(email);
    }

    public boolean isValidPassword(String email, String password) {
        return users.get(email).equals(password);
    }
}
