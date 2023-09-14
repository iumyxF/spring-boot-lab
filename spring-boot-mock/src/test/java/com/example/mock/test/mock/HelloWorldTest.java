package com.example.mock.test.mock;

import com.example.mock.dao.DemoDao;
import com.example.mock.service.DemoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * @author iumyxF
 * @description: mock 的简单使用
 * @date 2023/5/22 10:23
 */
class HelloWorldTest {

    @Test
    public void helloWorldTest() {
        // mock一个mockDemoDao实例
        DemoDao mockDemoDao = Mockito.mock(DemoDao.class);

        // 使用 mockito 对 getDemoStatus 方法打桩
        Mockito.when(mockDemoDao.getDemoStatus()).thenReturn(1);

        // 调用 mock 对象的 getDemoStatus 方法，结果永远是 1
        Assertions.assertEquals(1, mockDemoDao.getDemoStatus());

        // mock DemoService
        DemoService mockDemoService = new DemoService(mockDemoDao);
        Assertions.assertEquals(1, mockDemoService.getDemoStatus());
    }

}
