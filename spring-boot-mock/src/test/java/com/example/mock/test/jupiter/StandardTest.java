package com.example.mock.test.jupiter;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * @author iumyxF
 * @description: 生命周期测试用例
 * @date 2023/5/15 9:55
 */
public class StandardTest {

    @BeforeAll
    static void initAll() {
        System.out.println("BeforeAll");
    }

    @BeforeEach
    void init() {
        System.out.println("BeforeEach");
    }

    @Test
    void succeedingTest() {
        System.out.println("succeedingTest");
    }

    @Test
    void failingTest() {
        System.out.println("failingTest");
        fail("a failing test");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
        System.out.println("skip test");
    }

    @Test
    void abortedTest() {
        System.out.println("abortedTest");
        assumeTrue("abc".contains("Z"));
        fail("test should have been aborted");
    }


    @AfterEach
    void tearDown() {
        System.out.println("AfterEach");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("AfterEach");
    }

}
