package com.example.design.pattern.template_pattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author iumyxF
 * @description:
 * @date 2023/11/6 9:03
 */
public class Demo {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Network network = null;
        System.out.print("输入账户: ");
        String userName = reader.readLine();
        System.out.print("输入密码: ");
        String password = reader.readLine();

        System.out.print("输入消息内容: ");
        String message = reader.readLine();

        System.out.println("\n选择发送消息的社交平台.\n" +
                "1 - Facebook\n" +
                "2 - Twitter");
        int choice = Integer.parseInt(reader.readLine());

        if (choice == 1) {
            network = new Facebook(userName, password);
        } else if (choice == 2) {
            network = new Twitter(userName, password);
        }
        network.post(message);
    }

}
