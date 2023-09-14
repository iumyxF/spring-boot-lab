package com.example.mock.test.jupiter;

import com.example.mock.entities.Person;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author iumyxF
 * @description: 断言测试
 * @date 2023/5/15 10:04
 */
public class AssertionsTest {

    Person person = new Person("John", "Doe");

    /**
     * assertEquals 断言预期值和实际值相等
     * assertTrue  断言条件为真,message参数是在条件不为True时才触发
     */
    @Test
    void standardAssertions() {
        assertEquals(2, 2);
        assertEquals(4, 4, "The optional assertion message is now the last parameter.");
        assertTrue(2 == 2, () -> "Assertion messages can be lazily evaluated -- "
                + "to avoid constructing complex messages unnecessarily.");
    }

    /**
     * 分组断言,执行其中包含的所有断言
     */
    @Test
    void groupedAssertions() {
        assertAll("person",
                () -> assertEquals("John", person.getFirstName()),
                () -> assertEquals("Doe", person.getLastName())
        );
    }

    /**
     * 第一个参数值 heading 代表我们自定义的错误信息
     * 第二个参数值 executables：Executable 是个函数式接口，可以使用 lamda 表达式编写要测试的代码
     */
    @Test
    void dependentAssertions() {
        // 在代码块中，如果断言失败，则将跳过同一块中的后续代码。
        assertAll("properties",
                () -> {
                    String firstName = person.getFirstName();
                    assertNotNull(firstName);
                    //assertNull(firstName);

                    //上一个断言有效，才会执行下面的断言
                    assertAll("first name",
                            () -> assertTrue(firstName.startsWith("J")),
                            () -> assertTrue(firstName.endsWith("n"))
                    );
                },
                () -> {
                    String lastName = person.getLastName();
                    assertNotNull(lastName);

                    assertAll("last name",
                            () -> assertTrue(lastName.startsWith("D")),
                            () -> assertTrue(lastName.endsWith("e"))
                    );
                }
        );
    }

    /**
     * 断言异常
     */
    @Test
    void exceptionTesting() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        assertEquals("a message", exception.getMessage());
    }

    /**
     * 超时断言
     */
    @Test
    void timeoutNotExceeded() {
        assertTimeout(ofMinutes(2), () -> {
            //执行耗时不超过2分钟的任务
        });
    }

    @Test
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds, and returns the supplied object.
        String actualResult = assertTimeout(ofMinutes(2), () -> {
            return "a result";
        });
        assertEquals("a result", actualResult);
    }

    @Test
    void timeoutNotExceededWithMethod() {
        // The following assertion invokes a method reference and returns an object.
        String actualGreeting = assertTimeout(ofMinutes(2), AssertionsTest::greeting);
        assertEquals("hello world!", actualGreeting);
    }

    private static String greeting() {
        return "hello world!";
    }

    /**
     * 断言时间限制，第一个参数是最大时间，第二个参数是执行函数
     */
    @Test
    void timeoutExceeded() {
        // The following assertion fails with an error message similar to:
        // execution exceeded timeout of 10 ms by 91 ms
        assertTimeout(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(1);
        });
    }

    /**
     * 多线程版本assertTimeout
     */
    @Test
    void timeoutExceededWithPreemptiveTermination() {
        // The following assertion fails with an error message similar to:
        // execution timed out after 10 ms
        assertTimeoutPreemptively(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(2);
        });
    }


}
