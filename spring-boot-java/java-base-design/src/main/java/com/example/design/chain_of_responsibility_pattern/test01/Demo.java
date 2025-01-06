package com.example.design.chain_of_responsibility_pattern.test01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author iumyxF
 * @description: 启动类
 * @date 2023/8/3 9:43
 */
public class Demo {
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private static Server server;

    private static void init() {
        server = new Server();
        server.register("admin@example.com", "admin");
        server.register("user@example.com", "user");

        // 所有检查都是关联的。客户可以使用相同的组件构建各种链。
        BaseMiddleware baseMiddleware = BaseMiddleware.link(
                new ThrottlingMiddleware(2),
                new UserExistsMiddleware(server),
                new RoleCheckMiddleware()
        );

        // 服务器从客户端代码中获取链。
        server.setMiddleware(baseMiddleware);
    }

    public static void main(String[] args) throws IOException {
        init();

        boolean success;
        do {
            System.out.print("Enter email: ");
            String email = READER.readLine();
            System.out.print("Input password: ");
            String password = READER.readLine();
            success = server.logIn(email, password);
        } while (!success);
    }
}
