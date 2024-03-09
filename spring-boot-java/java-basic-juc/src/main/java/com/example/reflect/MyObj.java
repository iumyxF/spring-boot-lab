package com.example.reflect;

/**
 * @author fzy
 * @description:
 * @date 2024/3/9 10:13
 */
public class MyObj {

    private String value;

    public MyObj() {
    }

    public MyObj(String value) {
        this.value = value;
    }

    public void publicSay(String str) {
        System.out.println("public say : " + str + " value = " + value);
    }

    private void privateSay(String str) {
        System.out.println("private say : " + str + " value = " + value);
    }
}
