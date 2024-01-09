package com.example.oss.service;

import com.example.oss.config.OssConfig;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

/**
 * @author iumyxF
 * @description:
 * @date 2024/1/8 16:14
 */
public class OssServiceImplTest {
    @Mock
    OssConfig ossConfig;
    @Mock
    Logger log;
    @InjectMocks
    OssServiceImpl ossServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(ossConfig.getEndpoint()).thenReturn("xxx");
        when(ossConfig.getBucketName()).thenReturn("xxx");
        when(ossConfig.getAccessKeyId()).thenReturn("xxx");
        when(ossConfig.getAccessKeySecret()).thenReturn("xxx");
    }

    @Test
    public void testAddFile() throws Exception {
        ossServiceImpl.addFile();
    }

    @Test
    public void testDeleteFile() throws Exception {
        ossServiceImpl.deleteFile();
    }

    @Test
    public void testGetFile() throws Exception {
        ossServiceImpl.getFile();
    }

    @Test
    public void testListFile() throws Exception {
        ossServiceImpl.listFile();
    }
}