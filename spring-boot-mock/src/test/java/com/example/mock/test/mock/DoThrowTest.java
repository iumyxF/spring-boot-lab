package com.example.mock.test.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doThrow;

/**
 * @author iumyxF
 * @description:
 * @date 2023/5/22 11:26
 */
@ExtendWith(MockitoExtension.class)
public class DoThrowTest {

    static class ExampleService {
        public void hello() {
            System.out.println("Hello");
        }
    }

    @Mock
    private ExampleService exampleService;

    @Test
    public void test() {
        // 这种写法可以达到效果
        doThrow(new RuntimeException("异常")).when(exampleService).hello();
        try {
            exampleService.hello();
            Assertions.fail();
        } catch (RuntimeException ex) {
            Assertions.assertEquals("异常", ex.getMessage());
        }
    }
}
