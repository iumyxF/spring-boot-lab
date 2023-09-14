package com.example.mock.test.mock;

import com.example.mock.service.DemoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

/**
 * @author iumyxF
 * @description: PowerMock 实现静态方法的测试
 * @date 2023/5/22 14:27
 */
@ExtendWith(MockitoExtension.class)
public class StaticMockTest {

    /**
     * 直接调用静态方法会报错
     */
    @Test
    public void test() {
        //when(DemoService.add(1, 2)).thenReturn(100);
    }

    /**
     * 通过mockStatic 模拟静态方法
     */
    @Test
    public void test2() {
        mockStatic(DemoService.class);

        when(DemoService.add(1, 2)).thenReturn(100);
        Assertions.assertEquals(100, DemoService.add(1, 2));

        Assertions.assertEquals(0, DemoService.add(2, 2));
    }

    @Mock
    private Random random;

    @Test
    public void test3() {
        when(random.nextInt()).thenReturn(1);
        Assertions.assertEquals(1, random.nextInt());
    }

}
