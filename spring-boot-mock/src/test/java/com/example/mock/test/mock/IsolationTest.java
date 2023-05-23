package com.example.mock.test.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

/**
 * @author fzy
 * @description: 隔离测试
 * @date 2023/5/22 14:23
 */
@ExtendWith(MockitoExtension.class)
public class IsolationTest {
    static class ExampleService {
        public int add(int a, int b) {
            return a + b;
        }

    }

    @Mock
    private ExampleService exampleService;

    @Test
    public void test01() {
        System.out.println("---call test01---");

        System.out.println("打桩前: " + exampleService.add(1, 2));

        when(exampleService.add(1, 2)).thenReturn(100);

        System.out.println("打桩后: " + exampleService.add(1, 2));
    }

    @Test
    public void test02() {
        System.out.println("---call test02---");

        System.out.println("打桩前: " + exampleService.add(1, 2));

        when(exampleService.add(1, 2)).thenReturn(200);

        System.out.println("打桩后: " + exampleService.add(1, 2));
    }

}
