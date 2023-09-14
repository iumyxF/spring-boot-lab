package com.example.transaction.service.impl;

import com.example.transaction.domain.Source;
import com.example.transaction.domain.User;
import com.example.transaction.service.SourceService;
import com.example.transaction.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;

/**
 * @author iumyxF
 * @description:
 * @date 2023/7/10 9:10
 */
@Service
public class TestServiceImpl {

    @Resource
    private SourceService sourceService;
    @Resource
    private UserService userService;

    /**
     * 1.批量删除资源和新增资源 能否回滚
     * 2.上面基础上在加个判断，其中有一个判断调用本类方法，观察事务是否生效
     * 3.catch中能否正常执行
     */
    @Transactional(rollbackFor = Exception.class)
    public void doServiceVersion1() {
        System.out.println("doService-1 执行开始...");
        try {
            sourceService.save(new Source("删除数据"));
            sourceService.save(new Source("新增数据"));
            int i = 10 / 0;
        } catch (Exception e) {
            sourceService.insertErrorLog(new Source("异常数据"));
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        System.out.println("doService-1 执行完毕...");
    }

    @Transactional(rollbackFor = Exception.class)
    public void doServiceVersion2() {
        System.out.println("doService-2 执行开始...");
        try {
            sourceService.doSomething();
        } catch (Exception e) {
            sourceService.insertErrorLog(new Source("异常数据"));
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        System.out.println("doService=2 执行完毕...");
    }

    /**
     * 调用私有方法议然能被手动回滚
     */
    @Transactional(rollbackFor = Exception.class)
    public void doServiceVersion3() {
        System.out.println("doService-3 执行开始...");
        try {
            doPrivateMethod();
            sourceService.save(new Source("删除数据"));
            sourceService.save(new Source("新增数据"));
            int i = 10 / 0;
        } catch (Exception e) {
            sourceService.insertErrorLog(new Source("异常数据"));
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        System.out.println("doService-3 执行完毕...");
    }

    private void doPrivateMethod() {
        userService.save(new User("qwer"));
    }
}
