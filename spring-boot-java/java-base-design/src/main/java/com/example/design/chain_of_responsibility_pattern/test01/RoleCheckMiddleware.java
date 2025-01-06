package com.example.design.chain_of_responsibility_pattern.test01;

/**
 * @author iumyxF
 * @description: 检查用户角色
 * @date 2023/8/3 9:42
 */
public class RoleCheckMiddleware extends BaseMiddleware {

    private static final String ADMIN_EMAIL = "admin@example.com";

    public boolean check(String email, String password) {
        System.out.println("RoleCheckMiddleware 认证...");
        if (ADMIN_EMAIL.equals(email)) {
            System.out.println("欢迎，管理员!");
            return true;
        }
        System.out.println("欢迎，普通用户!");
        return checkNext(email, password);
    }
}
