package com.example.security.service.impl;

import com.example.security.entities.LoginUser;
import com.example.security.entities.Result;
import com.example.security.entities.User;
import com.example.security.service.ILoginService;
import com.example.security.utils.JwtUtils;
import com.example.security.utils.RedisCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @description:
 * @Date 2023/2/17 10:02
 * @Author fzy
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements ILoginService {

    private final AuthenticationManager authenticationManager;

    private final RedisCache redisCache;

    @Override
    public Result login(User user) {

        //先获取前端传入的账号密码Authentication对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        //AuthenticationManager authentication进行用户认证
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        log.info("authentication: " + authentication);

        //如果没有认证成功, 给出相应的提示
        if (Objects.isNull(authentication)) {
            return Result.fail("登陆失败");
        }

        //认证成功生成jwt, jwt存入Result中, 返回
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        log.info("看一下在LoginService中的loginUser: " + loginUser);

        String id = loginUser.getUser().getId().toString();
        Map<String, Object> claims = new HashMap<>(1);
        claims.put("id", id);
        String jwt = JwtUtils.createJwt(claims, 10000000L);
        //把完整的用户信息存入redis userid作为key

        User userInfo = loginUser.getUser();
        log.info("token: " + jwt);

        redisCache.setCacheObject("login:" + loginUser.getUser().getId(), userInfo);

        return Result.ok("登陆成功", jwt);
    }
}
