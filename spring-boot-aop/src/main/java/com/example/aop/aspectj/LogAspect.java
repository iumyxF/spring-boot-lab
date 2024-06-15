package com.example.aop.aspectj;

import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.example.aop.annotation.Log;
import com.example.aop.entities.OperationLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author iumyxF
 * @description: 操作日志记录处理
 * @Date 2023/2/13 9:55
 */
@Aspect
@Slf4j
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 定义spel表达式解析器
     */
    private final ExpressionParser parser = new SpelExpressionParser();
    /**
     * 定义spel解析模版
     */
    private final ParserContext parserContext = new TemplateParserContext();
    /**
     * 定义spel上下文对象进行解析
     */
    private final EvaluationContext context = new StandardEvaluationContext();
    /**
     * 方法参数解析器
     */
    private final ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();

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
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult) throws Exception {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    private void handleLog(JoinPoint joinPoint, Log controllerLog, final Exception e, Object jsonResult) throws Exception {
        OperationLog opLog = new OperationLog();
        //// 请求的地址
        //String ip = ServletUtils.getClientIp();
        //// 设置方法名称
        //String className = joinPoint.getTarget().getClass().getName();
        //String methodName = joinPoint.getSignature().getName();
        ////类名.方法名
        //opLog.setMethod(className + "." + methodName + "()");
        //opLog.setOpIp(ip);
        //opLog.setRequestMethod(ServletUtils.getRequest().getMethod());
        ////获取注解上的属性
        //log.info("title = " + controllerLog.title());
        //
        //MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //Method method = signature.getMethod();
        //
        //Map<String, Object> map = getFieldsName(joinPoint);
        //
        //// 获取参数类型
        //Object[] args = joinPoint.getArgs();
        //HashMap<Class<?>, Object> map1 = new HashMap<>();
        //for (Object arg : args) {
        //    map1.put(arg.getClass(), arg);
        //}
        //
        //map1.forEach((k, v) -> {
        //    Object cast = k.cast(v);
        //    System.out.println(cast.toString());
        //});
        //
        //System.out.println("map1 = " + map1);

        // 测试get 普通参数EL
        System.out.println("start test EL ----");
        //获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入方法的对象
        Method method = signature.getMethod();
        //获取方法上的Aop注解
        Log annotation = method.getAnnotation(Log.class);
        //获取注解上的值如 : @MyAnnotation(key = "'param id is ' + #id")
        String keyEl = annotation.content();
        //获取参数值
        Object[] args = joinPoint.getArgs();
        //获取运行时参数的名称
        String[] parameterNames = pnd.getParameterNames(method);
        for (int i = 0; i < parameterNames.length; i++) {
            String parameterName = parameterNames[i];
            Object arg = args[i];
            context.setVariable(parameterName, arg);
        }
        //将注解的值中的El表达式部分进行替换
        //获取表达式
        Expression expression = parser.parseExpression(keyEl, parserContext);
        String result = String.valueOf(expression.getValue(context));
        System.out.println(result);

        System.out.println("end test EL ----");

        // 获取注解上的参数
        //通过监听模式异步处理日志信息
        SpringUtil.getApplicationContext().publishEvent(opLog);
    }

    /**
     * 获取参数列表
     */
    private Map<String, Object> getFieldsName(JoinPoint joinPoint) {
        // 参数值
        Object[] args = joinPoint.getArgs();
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 获取参数名
        String[] parameterNames = pnd.getParameterNames(method);
        if (null == parameterNames || parameterNames.length <= 0) {
            return MapUtil.empty();
        }
        Map<String, Object> paramMap = new HashMap<>(parameterNames.length);
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        return paramMap;
    }
}
