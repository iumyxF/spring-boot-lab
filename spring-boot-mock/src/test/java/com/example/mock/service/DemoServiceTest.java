package com.example.mock.service;

import com.example.mock.dao.DemoDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

/**
 * @author iumyxF
 * @description:
 * @date 2023/5/22 10:22
 */
class DemoServiceTest {

    @Mock
    DemoDao demoDao;

    @InjectMocks
    DemoService demoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDemoStatus() {
        when(demoDao.getDemoStatus()).thenReturn(0);

        int result = demoService.getDemoStatus();
        Assertions.assertEquals(0, result);
    }
}
