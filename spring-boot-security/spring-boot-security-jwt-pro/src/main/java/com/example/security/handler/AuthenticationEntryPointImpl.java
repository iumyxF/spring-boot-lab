package com.example.security.handler;

import com.example.security.entities.Result;
import com.example.security.utils.ServletUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @description: 认证失败处理，返回未授权
 * @Date 2023/2/22 9:48
 * @Author fzy
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        int code = HttpStatus.UNAUTHORIZED.value();
        String msg = "请求访问：{}，认证失败，无法访问系统资源:" + request.getRequestURI();
        ServletUtils.renderString(response, objectMapper.writeValueAsString(Result.fail(code, msg)));
    }
}
