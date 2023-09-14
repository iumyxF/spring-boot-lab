package com.example.sign.handler;

import com.example.sign.utils.AccessUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 接口验证拦截器
 * @Date 2023/3/8 10:45
 * @author iumyxF
 */
@Slf4j
public class InterfaceSignInterceptor implements HandlerInterceptor {

    @Resource
    private AccessUtils accessUtils;

    /**
     * 加密过程：(app_key + time_stamp + app_secret) --MD5--> str1 + app_secret ---MD5---> sign
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return 验证是否成功
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return accessUtils.signatureVerification(request);
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("接口请求完毕");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
