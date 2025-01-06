package com.example.design.template_pattern;

/**
 * @author iumyxF
 * @description:
 * @date 2023/11/6 9:02
 */
public abstract class Network {
    String userName;
    String password;

    Network() {
    }

    /**
     * 发送消息
     */
    public boolean post(String message) {
        if (logIn(this.userName, this.password)) {
            boolean result = sendData(message.getBytes());
            logOut();
            return result;
        }
        return false;
    }

    public void printLogger() {
        System.out.println("Network log ...");
    }

    abstract boolean logIn(String userName, String password);

    abstract boolean sendData(byte[] data);

    abstract void logOut();
}
