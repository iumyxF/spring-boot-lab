package com.example.mock.test.jupiter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static org.junit.jupiter.params.provider.EnumSource.Mode.MATCH_ALL;

/**
 * @author fzy
 * @description: 参数化测试
 * @date 2023/5/15 11:04
 */
class ParameterTest {

    /**
     * @ValueSource 是最简单的source之一。它可以让你指定一个原生类型（String，int，long或double）的数组，并且只能为每次调用提供一个参数。
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testWithValueSource(int argument) {
        assertNotNull(argument);
        assertTrue(argument < 100);
    }

    /**
     * @EnumSource 提供了一个使用Enum常量的简便方法。该注释提供了一个可选的name参数，可以指定使用哪些常量。如果省略，所有的常量将被用在下面的例子中。
     * @EnumSource 注解还提供了一个可选的mode参数，可以对将哪些常量传递给测试方法进行细化控制。例如，您可以从枚举常量池中排除名称或指定正则表达式，如下例所示。
     */
    @ParameterizedTest
    @EnumSource(TimeUnit.class)
    void testWithEnumSource(TimeUnit timeUnit) {
        assertNotNull(timeUnit);
    }

    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, names = {"DAYS", "HOURS"})
    void testWithEnumSourceInclude(TimeUnit timeUnit) {
        assertTrue(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
    }

    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, mode = EXCLUDE, names = {"DAYS", "HOURS"})
    void testWithEnumSourceExclude(TimeUnit timeUnit) {
        assertFalse(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
        assertTrue(timeUnit.name().length() > 5);
    }

    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, mode = MATCH_ALL, names = "^(M|N).+SECONDS$")
    void testWithEnumSourceRegex(TimeUnit timeUnit) {
        String name = timeUnit.name();
        assertTrue(name.startsWith("M") || name.startsWith("N"));
        assertTrue(name.endsWith("SECONDS"));
    }


    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithSimpleMethodSource(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> stringProvider() {
        return Stream.of("foo", "bar");
    }
}
