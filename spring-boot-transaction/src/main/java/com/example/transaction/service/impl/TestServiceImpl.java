package com.example.transaction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.transaction.domain.Source;
import com.example.transaction.domain.User;
import com.example.transaction.service.SourceService;
import com.example.transaction.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author iumyxF
 * @description: 使用CompletableFuture 应该不要分开线程去进行数据的修改操作（增删改）
 * 可以用于一查询操作中，如查询订单，使用CF分别查询订单不同的组成成分，最后主线程返回完整订单信息
 * @date 2023/7/10 9:10
 */
@Service
public class TestServiceImpl {

    @Resource
    private SourceService sourceService;
    @Resource
    private UserService userService;
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;
    @Resource
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * 1.批量删除资源和新增资源 能否回滚
     * 2.上面基础上在加个判断，其中有一个判断调用本类方法，观察事务是否生效
     * 3.catch中能否正常执行
     * <p>
     * 有异常的情况下：
     * 1.使用@Transactional(rollbackFor = Exception.class)，不会回滚新增的数据
     * 2.使用@Transactional(rollbackFor = Exception.class)
     * 而且TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
     * 新增的数据会回滚
     */
    @Transactional(rollbackFor = Exception.class)
    public void doServiceVersion0() {
        sourceService.save(new Source("删除数据"));
        sourceService.save(new Source("新增数据"));
        int i = 10 / 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public void doServiceVersion1() {
        System.out.println("doService-1 执行开始...");
        try {
            sourceService.save(new Source("删除数据"));
            sourceService.save(new Source("新增数据"));
            int i = 10 / 0;
        } catch (Exception e) {
            sourceService.insertErrorLog(new Source("异常数据"));
            //此处会把上面两条save数据回滚
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

    public void threadDoService() {
        //userService.save(new User("Test"));
        CompletableFuture<Boolean> cf1 = CompletableFuture.supplyAsync(() -> {
            ArrayList<User> users = new ArrayList<>();
            users.add(new User("AAA"));
            users.add(new User("BBB"));
            users.add(new User("CCC"));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return userService.saveBatch(users);
        }, threadPoolTaskExecutor);

        CompletableFuture<Boolean> cf2 = CompletableFuture.supplyAsync(() -> {
            ArrayList<User> users = new ArrayList<>();
            users.add(new User("DDD"));
            users.add(new User("EEE"));
            users.add(new User("FFF"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return userService.saveBatch(users);
        }, threadPoolTaskExecutor);

        CompletableFuture<Void> cf3 = CompletableFuture.allOf(cf1, cf2);
        CompletableFuture<ArrayList<User>> cf4 = cf3.thenApply(v -> {
            cf1.join();
            cf2.join();
            ArrayList<User> users = new ArrayList<>();
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("name", "BBB");
            wrapper.last("limit 1");
            User userB = userService.getOne(wrapper);

            QueryWrapper<User> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("name", "FFF");
            wrapper.last("limit 1");
            User userF = userService.getOne(wrapper);

            users.add(userB);
            users.add(userF);
            return users;
        });
        try {
            ArrayList<User> users = cf4.get();
            System.out.println(users);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 回滚CompletableFuture的事务
     * 结果：cf会回滚
     */
    public void threadDoServiceSingleRollBack(int error) {
        System.out.println("threadDoServiceSingleRollBack start...");
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
            try {
                userService.save(new User("cf1"));
                Thread.sleep(2000);
                if (error == 1) {
                    int i = 10 / 0;
                }
                dataSourceTransactionManager.commit(status);
                return "A ok";
            } catch (Exception e) {
                dataSourceTransactionManager.rollback(status);
                throw new RuntimeException(e);
            }
        }, threadPoolTaskExecutor);
        try {
            System.out.println(cf1.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("threadDoServiceSingleRollBack end...");
    }

    /**
     * 不应该将增删改操作分开线程执行，尽量避免
     * <p>
     * 需求：当其中一个线程的CRUD出现异常，其他线程也需要回滚
     * <p>
     * 1.不添加任何处理时，保存了用户B，且cf3没有执行
     * 2.添加@Transactional(rollbackFor = Exception.class) 和第一点的结果一样
     */
    //@Transactional(rollbackFor = Exception.class)
    public void threadDoServiceByException() {
        SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Connection connection = sqlSession.getConnection();

        //设置手动提交事务
        try {
            connection.setAutoCommit(false);

            CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int i = 10 / 0;
                userService.save(new User("AAA"));
                return "A ok";
            }, threadPoolTaskExecutor).whenComplete((res, error) -> {
                if (null != error) {
                    error.printStackTrace();
                    try {
                        connection.rollback();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("res = " + res);
            });

            CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
                try {
                    userService.save(new User("BBB"));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "B ok";
            }, threadPoolTaskExecutor);

            CompletableFuture<Void> cf3 = CompletableFuture.allOf(cf1, cf2);
            CompletableFuture<List<User>> cf4 = cf3.thenApply(v -> {
                cf1.join();
                cf2.join();
                QueryWrapper<User> wrapper = new QueryWrapper<>();
                wrapper.like("name", "AA");
                wrapper.like("name", "B");
                return userService.list(wrapper);
            });
            try {
                List<User> users = cf4.get();
                System.out.println(users);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            userService.save(new User("last"));

            connection.commit();
            System.out.println("所有任务执行完毕");

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
