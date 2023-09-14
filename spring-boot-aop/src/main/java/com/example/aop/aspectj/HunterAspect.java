package com.example.aop.aspectj;

import com.example.aop.annotation.HunterAsyncAnno;
import com.example.aop.annotation.HunterLogAnno;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description: 使用 Aspect 实现注解
 * @Date 2023/2/13 10:27
 * @author iumyxF
 */
@Slf4j
@Component
@Aspect
public class HunterAspect {

    @Autowired
    @Qualifier("threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    /**
     * 这里定义一个PointCut，用于描述满足条件的方法
     * execution(方法类型 方法返回值 包路径.方法名称(方法参数))
     */
    @Pointcut("execution(public * com.example.aop.service.*.*(*))")
    private void normalPointCut() {
        System.out.println("pointCut");
    }

    /**
     * 将Services 文件下，且带有 HunterLogAnno 注解的类进行
     *
     * @param joinPoint
     */
    @Around("normalPointCut() && @annotation(com.example.aop.annotation.HunterLogAnno)")
    private void normalPointAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("===========================");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 执行具体的方法，在使用 Around 类的时候，需要对执行方法进行调用，否则只会执行 Around 方法 normalPointBefore 里面的逻辑
        joinPoint.proceed();
        // 注解解析
        HunterLogAnno hunterLogAnno = method.getAnnotation(HunterLogAnno.class);
        String value = hunterLogAnno.value();
        // 打印注解记录
        log.info("hunter注解的值: " + value);
        log.info("===========================");
    }

    /**
     * 异步注解：定义一个注解的切点
     *
     * @param joinPoint
     */
    @Around("normalPointCut() && @annotation(com.example.aop.annotation.HunterAsyncAnno)")
    private void asyncPointAround(ProceedingJoinPoint joinPoint) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            HunterAsyncAnno hunterAsyncAnno = method.getAnnotation(HunterAsyncAnno.class);
            threadPoolTaskExecutor.execute(() -> {
                try {
                    Thread.sleep(5000);
                    joinPoint.proceed();
                    System.out.println("结束时间: " + System.currentTimeMillis());
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
