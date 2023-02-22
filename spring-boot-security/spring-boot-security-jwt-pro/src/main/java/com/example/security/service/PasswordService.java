package com.example.security.service;

import com.example.security.context.AuthenticationContextHolder;
import com.example.security.entities.User;
import com.example.security.utils.RedisCache;
import com.example.security.utils.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @description: 密码校验
 * @Date 2023/2/22 10:23
 * @Author fzy
 */
@Service
public class PasswordService {

    @Resource
    RedisCache redisCache;
    /**
     * 密码最大错误次数
     */
    private final int maxRetryCount = 5;

    /**
     * 密码锁定时间（默认10分钟）
     */
    private final int lockTime = 10;


    public void validate(User user) {
        //获取当前登录用户的账号密码
        Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
        String username = usernamePasswordAuthenticationToken.getName();
        String password = usernamePasswordAuthenticationToken.getCredentials().toString();
        //获取输入错误密码次数
        Integer retryCount = redisCache.getCacheObject(getCacheKey(username));

        if (retryCount == null) {
            retryCount = 0;
        }

        if (retryCount >= Integer.valueOf(maxRetryCount)) {
            //已经超过最大输错密码次数
            throw new RuntimeException("账号已连续输入错误5次，冻结10分钟");
        }

        if (!matches(user, password)) {
            retryCount = retryCount + 1;
            redisCache.setCacheObject(getCacheKey(username), retryCount, lockTime, TimeUnit.MINUTES);
            throw new RuntimeException("密码错误，错误次数+1");
        } else {
            clearLoginRecordCache(username);
        }
    }

    /**
     * 登录成功后清除用户的登录密码错误记录
     */
    public void clearLoginRecordCache(String loginName) {
        if (redisCache.hasKey(getCacheKey(loginName))) {
            redisCache.deleteObject(getCacheKey(loginName));
        }
    }

    /**
     * 登录账户密码错误次数缓存键名
     *
     * @param username 用户名
     */

    private String getCacheKey(String username) {
        return "pwd_err_cnt:" + username;
    }

    /**
     * 使用security验证密码是否正确
     */
    public boolean matches(User user, String rawPassword) {
        return SecurityUtils.matchesPassword(rawPassword, user.getPassword());
    }

}
