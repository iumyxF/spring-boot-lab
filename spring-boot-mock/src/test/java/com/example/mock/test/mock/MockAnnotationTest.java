package com.example.mock.test.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import static org.mockito.Mockito.when;

/**
 * @author fzy
 * @description: @Mock注解的使用
 * 使用该注解时，要使用MockitoAnnotations.initMocks 方法，让注解生效, 比如放在@Before方法中初始化。
 * 比较优雅优雅的写法是用MockitoJUnitRunner，它可以自动执行MockitoAnnotations.initMocks 方法。
 * @date 2023/5/22 10:46
 */
//@ExtendWith(MockitoExtension.class)
class MockAnnotationTest {

    @Mock
    private Random random;

    /**
     * ExtendWith(MockitoExtension.class) 的代替写法
     * <p>
     * 注意这里不能使用BeforeAll因为BeforeAll用于修饰静态方法的，而上面的mock的Random对象不是静态的，会有报错
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test() {
        when(random.nextInt()).thenReturn(100);
        Assertions.assertEquals(100, random.nextInt());
    }

}
