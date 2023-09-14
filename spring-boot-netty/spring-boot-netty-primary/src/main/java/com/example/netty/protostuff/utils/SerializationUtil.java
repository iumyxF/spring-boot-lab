package com.example.netty.protostuff.utils;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 序列化工具
 * @Date 2023/3/10 11:05
 * @author iumyxF
 */
public class SerializationUtil {

    private static final Map<Class<?>, Schema<?>> CACHED_SCHEMA = new ConcurrentHashMap<>();

    private static final Objenesis OBJENESIS = new ObjenesisStd();

    private SerializationUtil() {

    }

    /**
     * 序列化(对象 -> 字节数组)
     *
     * @param obj 对象
     * @return 字节数组
     */
    public static <T> byte[] serialize(T obj) {
        Class<T> cls = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(cls);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化(字节数组 -> 对象)
     *
     * @param data 二进制数组
     * @param cls  类对象
     * @param <T>  类对象
     */
    public static <T> T deserialize(byte[] data, Class<T> cls) {
        try {
            T message = OBJENESIS.newInstance(cls);
            Schema<T> schema = getSchema(cls);
            ProtostuffIOUtil.mergeFrom(data, message, schema);
            return message;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private static <T> Schema<T> getSchema(Class<T> cls) {
        Schema<T> schema = (Schema<T>) CACHED_SCHEMA.get(cls);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(cls);
            CACHED_SCHEMA.put(cls, schema);
        }
        return schema;
    }

}
