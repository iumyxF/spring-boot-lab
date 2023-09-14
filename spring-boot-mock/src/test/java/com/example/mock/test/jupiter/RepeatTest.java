package com.example.mock.test.jupiter;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author iumyxF
 * @description: 重复测试
 * @date 2023/5/15 10:42
 */
@DisplayName("RepeatTest")
public class RepeatTest {
    @BeforeEach
    void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        int currentRepetition = repetitionInfo.getCurrentRepetition();
        int totalRepetitions = repetitionInfo.getTotalRepetitions();
        String methodName = testInfo.getTestMethod().get().getName();
        System.out.printf("即将执行重复: [重复次数:%d / %d , 方法名%s]%n", currentRepetition, totalRepetitions, methodName);
    }

    @RepeatedTest(3)
    void repeatedTest() {
        // something...
    }

    @RepeatedTest(2)
    void repeatedTestWithRepetitionInfo(RepetitionInfo repetitionInfo) {
        assertEquals(2, repetitionInfo.getTotalRepetitions());
    }

    /**
     * @RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
     * value=1 重复1次
     * name = "{displayName} {currentRepetition}/{totalRepetitions}"
     * {displayName} = Repeat!
     * {currentRepetition}/{totalRepetitions} 当前重复次数/总重复次数
     */
    @RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("Repeat!")
    void customDisplayName(TestInfo testInfo) {
        assertEquals(testInfo.getDisplayName(), "Repeat! 1/1");
    }

    /**
     * RepeatedTest.LONG_DISPLAY_NAME，一个风格的名字类型
     */
    @RepeatedTest(value = 1, name = RepeatedTest.LONG_DISPLAY_NAME)
    @DisplayName("Details...")
    void customDisplayNameWithLongPattern(TestInfo testInfo) {
        assertEquals(testInfo.getDisplayName(), "Details... :: repetition 1 of 1");
    }

    @RepeatedTest(value = 1, name = "Wiederholung {currentRepetition} von {totalRepetitions}")
    void repeatedTestInGerman(TestInfo testInfo) {
        // ...
        assertEquals(testInfo.getDisplayName(), "Wiederholung 1 von 1");
    }
}
