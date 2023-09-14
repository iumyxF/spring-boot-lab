package com.example.mock.test.jupiter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author iumyxF
 * @description:
 * @date 2023/5/15 9:36
 */
public class HelloWorldTest {

    /**
     * @Test 注解在方法上标记方法为测试方法，以便构建工具和 IDE 能够识别并执行它们。
     * JUnit 5不再需要手动将测试类与测试方法为public，包可见的访问级别就足够了。
     */
    @Test
    void firstTest() {
        assertEquals(2, 1 + 1);
    }

}
