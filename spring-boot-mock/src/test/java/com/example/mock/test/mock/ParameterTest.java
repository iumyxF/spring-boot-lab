package com.example.mock.test.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * @author fzy
 * @description: 参数化测试
 * @date 2023/5/22 11:10
 */
@ExtendWith(MockitoExtension.class)
public class ParameterTest {

    @Mock
    private List<String> testList;

    @Test
    public void test01() {
        // 精确匹配 0
        when(testList.get(0)).thenReturn("a");
        Assertions.assertEquals("a", testList.get(0));

        // 精确匹配 0
        when(testList.get(0)).thenReturn("b");
        Assertions.assertEquals("b", testList.get(0));

        // 模糊匹配
        when(testList.get(anyInt())).thenReturn("c");
        Assertions.assertEquals("c", testList.get(0));
        Assertions.assertEquals("c", testList.get(1));
    }
}
