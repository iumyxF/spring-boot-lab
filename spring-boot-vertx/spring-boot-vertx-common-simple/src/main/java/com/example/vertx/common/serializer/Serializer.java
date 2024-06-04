package com.example.vertx.common.serializer;

import java.io.IOException;

/**
 * 序列化器
 *
 * @author iumyxF
 * @date 2024/5/13 21:13
 */
public interface Serializer {

    /**
     * 序列化
     *
     * @param object 序列化对象
     * @param <T>    泛型
     * @return 字节数组
     * @throws IOException
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * 反序列化
     *
     * @param bytes  字节数组
     * @param tClass 反序列化目标类
     * @param <T>    泛型
     * @return 泛型
     * @throws IOException
     */
    <T> T deserialize(byte[] bytes, Class<T> tClass) throws IOException;
}
