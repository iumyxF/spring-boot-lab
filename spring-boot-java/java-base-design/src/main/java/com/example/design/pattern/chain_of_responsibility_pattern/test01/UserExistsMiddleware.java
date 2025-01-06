package com.example.design.pattern.chain_of_responsibility_pattern.test01;

/**
 * @author iumyxF
 * @description: 检查用户登录信息
 * @date 2023/8/3 9:39
 */
public class UserExistsMiddleware extends BaseMiddleware {

    private final Server server;

    public UserExistsMiddleware(Server server) {
        this.server = server;
    }

    public boolean check(String email, String password) {
        System.out.println("UserExistsMiddleware 认证...");
        if (!server.hasEmail(email)) {
            System.out.println("该邮箱未注册!");
            return false;
        }
        if (!server.isValidPassword(email, password)) {
            System.out.println("密码错误!");
            return false;
        }
        return checkNext(email, password);
    }
}
