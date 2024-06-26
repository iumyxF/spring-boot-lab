package com.example.remote.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test1 {
    public static void main(String[] args) {
        try {
            // 构建命令和参数
            String command = "/home/user/code/file/test.sh";
            String[] cmdArray = {command, "my test netplan"};

            // 执行命令
            Process process = Runtime.getRuntime().exec(cmdArray);

            // 读取命令的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 等待命令执行完成
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
