package com.example.security.handler;

import com.alibaba.fastjson2.JSON;
import com.example.security.entities.LoginUser;
import com.example.security.entities.Result;
import com.example.security.service.TokenService;
import com.example.security.utils.SecurityUtils;
import com.example.security.utils.ServletUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @description: 退出登录处理
 * @Date 2023/2/22 9:54
 * @author iumyxF
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Resource
    private TokenService tokenService;

    /**
     * 退出登录 删除redis中token
     * @param request
     * @param response
     * @param authentication
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (Objects.nonNull(loginUser)) {
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
        }
        ServletUtils.renderString(response, JSON.toJSONString(Result.ok("退出成功")));
    }
}
