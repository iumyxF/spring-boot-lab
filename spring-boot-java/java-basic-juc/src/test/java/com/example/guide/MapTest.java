package com.example.guide;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author fzy
 * @description:
 * @date 2024/3/18 8:59
 */
public class MapTest {

    /**
     * 3种线程安全的set
     */
    @Test
    public void test1() {
        HashSet<String> strings = new HashSet<>();
        strings.add("aaa");
        strings.add("bbb");
        strings.add("ccc");

        Set<String> synchronizedSet = Collections.synchronizedSet(strings);
        System.out.println("synchronizedSet = " + synchronizedSet);

        CopyOnWriteArraySet<String> copyOnWriteArraySet = new CopyOnWriteArraySet<>();

        //这里需要传入没有元素的Map
        Set<String> newSetFromMap = Collections.newSetFromMap(new HashMap<>());
        newSetFromMap.add("aaa");
        newSetFromMap.add("bbb");
        newSetFromMap.add("ccc");
        System.out.println("newSetFromMap = " + newSetFromMap);
    }

    @Test
    public void test2(){
        HashMap<String, String> map = new HashMap<>();
        String s = map.get(null);
        System.out.println();
    }
}
