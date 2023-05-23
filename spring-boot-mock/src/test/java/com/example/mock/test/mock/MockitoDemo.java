package com.example.mock.test.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.*;

/**
 * @author fzy
 * @description: @mock 和 @Spy 注解
 * 如果打了桩，无论spy还是mock都会返回打桩值，如果没打桩，spy会走真实调用，mock则只会返回默认值
 * @date 2023/5/22 11:43
 */
class ExampleService {
    int add(int a, int b) {
        return a + b;
    }
}

/**
 * 没有空参构造函数的对象，需要通过new注入测试中
 */
class ExampleService2 {

    private int a;

    public ExampleService2(int a) {
        this.a = a;
    }

    int add(int b) {
        return a + b;
    }

}

public class MockitoDemo {

    /**
     * 测试 spy
     */
    @Test
    public void test_spy() {
        ExampleService spyExampleService = spy(new ExampleService());
        // 默认会走真实方法
        Assertions.assertEquals(3, spyExampleService.add(1, 2));
        // 打桩后，不会走了
        when(spyExampleService.add(1, 2)).thenReturn(10);
        Assertions.assertEquals(10, spyExampleService.add(1, 2));
        // 但是参数比匹配的调用，依然走真实方法
        Assertions.assertEquals(3, spyExampleService.add(2, 1));
    }

    /**
     * 测试 mock
     */
    @Test
    public void test_mock() {
        ExampleService mockExampleService = mock(ExampleService.class);
        // 默认返回结果是返回类型int的默认值
        Assertions.assertEquals(0, mockExampleService.add(1, 2));
    }

    // 对比测试

    @Spy
    private ExampleService spyExampleService;

    @Test
    public void test_spy_2() {
        MockitoAnnotations.openMocks(this);
        Assertions.assertEquals(3, spyExampleService.add(1, 2));
        when(spyExampleService.add(1, 2)).thenReturn(10);
        Assertions.assertEquals(10, spyExampleService.add(1, 2));
    }

    /**
     * 对于@Spy，如果发现修饰的变量是 null，会自动调用类的无参构造函数来初始化。
     * 所以下面两种写法是等价的：
     * // 写法1
     *
     * @Spy private ExampleService spyExampleService;
     * // 写法2
     * @Spy private ExampleService spyExampleService = new ExampleService();
     */


    @Spy
    private ExampleService2 spyExampleService2 = new ExampleService2(1);

    @Test
    public void test_spy_3() {
        MockitoAnnotations.openMocks(this);
        Assertions.assertEquals(3, spyExampleService2.add(2));
    }
}
