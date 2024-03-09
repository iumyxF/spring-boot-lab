package com.example.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author fzy
 * @description:
 * @date 2024/3/9 10:13
 */
public class ReflectTest01 {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //通过反射创建类
        Class<?> aClass = Class.forName("com.example.reflect.MyObj");
        //无参构造
        MyObj instance = (MyObj) aClass.newInstance();

        //有参构造
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class);
        Object o = declaredConstructor.newInstance("wwww");


        //查询所有方法 public和private都能查询
        Method[] methods = aClass.getDeclaredMethods();
        System.out.println("该类存在以下方法");
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        //指定某个方法调用
        Method publicMethod = aClass.getDeclaredMethod("publicSay", String.class);
        //publicMethod.invoke(instance, "reflect test");

        publicMethod.invoke(o, "aaa");


        Field field = aClass.getDeclaredField("value");
        //为了对类中的参数进行修改我们取消安全检查
        field.setAccessible(true);
        field.set(instance, "abc");

        Method privateSay = aClass.getDeclaredMethod("privateSay", String.class);
        privateSay.setAccessible(true);
        //privateSay.invoke(instance, "eee");
    }
}
