package com.example.aop.aspectj;

import cn.hutool.extra.spring.SpringUtil;
import com.example.aop.annotation.Log;
import com.example.aop.entities.OperationLog;
import com.example.aop.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @description: 操作日志记录处理
 * @Date 2023/2/13 9:55
 * @Author fzy
 */
@Aspect
@Slf4j
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * execution格式:
     * execution（方法类型（public等，可省略） 方法的返回值类型 包路径（可省略） 方法的名称（参数） 异常类型（可省略） )
     * 方法类型包含：Public，Protected等，可省略。
     * 方法返回值类型： * 可以包含所有的返回值类型。
     * 包路径：如“com.demo…*”,表示"com.demo"包以及该包之下子包的所有类型。
     * 方法名称：如“add*”,表示所有以add开头的方法，
     * 参数：(*)表示任意一个参数，(…)表示所有参数。
     * 异常类型：如execution(* *(…) throws Exception)”匹配所有抛出Exception的方法。
     */

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult) {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    private void handleLog(JoinPoint joinPoint, Log controllerLog, final Exception e, Object jsonResult) {
        OperationLog opLog = new OperationLog();
        // 请求的地址
        String ip = ServletUtils.getClientIp();
        // 设置方法名称
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        //类名.方法名
        opLog.setMethod(className + "." + methodName + "()");
        opLog.setOpIp(ip);
        opLog.setRequestMethod(ServletUtils.getRequest().getMethod());
        //获取注解上的属性
        log.info("title = " + controllerLog.title());
        //通过监听模式异步处理日志信息
        SpringUtil.getApplicationContext().publishEvent(opLog);
    }
}
