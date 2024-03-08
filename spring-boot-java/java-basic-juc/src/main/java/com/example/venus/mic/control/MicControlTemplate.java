package com.example.venus.mic.control;

/**
 * @author fzy
 * @description:
 * @date 2024/3/8 16:26
 */
public abstract class MicControlTemplate {

    public void open(){
        //检查话筒是否在线？是否存在数据库？是否已注册等...
        speak();
        //检查话筒是否以打开？
    }

    protected abstract void speak();
}
