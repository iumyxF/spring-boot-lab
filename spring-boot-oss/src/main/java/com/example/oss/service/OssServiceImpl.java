package com.example.oss.service;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.example.oss.config.OssConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;


/**
 * @author iumyxF
 * @description:
 * @date 2024/1/8 15:42
 */
@Slf4j
@Service
public class OssServiceImpl implements IOssService {

    @Resource
    private OssConfig ossConfig;

    @Override
    public void addFile() {
        DefaultCredentialProvider provider = CredentialsProviderFactory
                .newDefaultCredentialProvider(ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
        OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), provider);
        String objectName = "example/testOss.txt";

        try {
            String content = "test oss";
            ossClient.putObject(ossConfig.getBucketName(), objectName, new ByteArrayInputStream(content.getBytes()));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    @Override
    public void deleteFile() {
        DefaultCredentialProvider provider = CredentialsProviderFactory
                .newDefaultCredentialProvider(ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
        OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), provider);
        String objectName = "example/testOss.txt";
        try {
            ossClient.deleteObject(ossConfig.getBucketName(), objectName);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    @Override
    public void getFile() {

    }

    @Override
    public void listFile() {

    }
}
