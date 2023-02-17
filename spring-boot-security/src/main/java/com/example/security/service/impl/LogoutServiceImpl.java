package com.example.security.service.impl;

import com.example.security.entities.LoginUser;
import com.example.security.entities.Result;
import com.example.security.entities.User;
import com.example.security.service.ILogoutService;
import com.example.security.utils.RedisCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @Date 2023/2/17 10:39
 * @Author fzy
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LogoutServiceImpl implements ILogoutService {

    private final RedisCache redisCache;

    @Override
    public Result logout() {
        /*
         * 获取SecurityContext对象中的用户id
         * 当用户发起退出登录的请求时经过JwtAuthenticationTokenFilter过滤器认证时
         * 会将authentication(UsernamePasswordAuthenticationToken)对象存入到SecurityContext中
         * 这里可以通过authentication.getPrincipal()直接获取到User对象
         */
        UsernamePasswordAuthenticationToken authentication
                = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        log.info("Principal: " + authentication.getPrincipal());
        LoginUser loginUser = new LoginUser();
        loginUser.setUser((User) authentication.getPrincipal());
        String id = loginUser.getUser().getId().toString();

        // 删除redis中的值
        redisCache.deleteObject("login" + id);
        return Result.ok("注销成功");
    }
}
