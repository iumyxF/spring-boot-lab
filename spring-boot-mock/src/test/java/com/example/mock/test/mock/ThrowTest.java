package com.example.mock.test.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author fzy
 * @description: mock 异常
 * @date 2023/5/22 11:15
 */
public class ThrowTest {

    /**
     * 例子1： thenThrow 用来让函数调用抛出异常.
     */
    @Test
    public void throwTest1() {
        Random mockRandom = mock(Random.class);
        when(mockRandom.nextInt()).thenThrow(new RuntimeException("异常"));
        try {
            mockRandom.nextInt();
            // 上面会抛出异常，所以不会走到这里
            Assertions.fail();
        } catch (Exception ex) {
            //捕获异常，进行断言
            Assertions.assertTrue(ex instanceof RuntimeException);
            Assertions.assertEquals("异常", ex.getMessage());
        }
    }

    /**
     * thenThrow 中可以指定多个异常。在调用时异常依次出现。若调用次数超过异常的数量，再次调用时抛出最后一个异常。
     */
    @Test
    public void throwTest2() {
        Random mockRandom = mock(Random.class);
        when(mockRandom.nextInt()).thenThrow(new RuntimeException("异常1"), new RuntimeException("异常2"));
        try {
            mockRandom.nextInt();
            Assertions.fail();
        } catch (Exception ex) {
            Assertions.assertTrue(ex instanceof RuntimeException);
            Assertions.assertEquals("异常1", ex.getMessage());
        }
        try {
            mockRandom.nextInt();
            Assertions.fail();
        } catch (Exception ex) {
            Assertions.assertTrue(ex instanceof RuntimeException);
            Assertions.assertEquals("异常2", ex.getMessage());
        }
    }
}
